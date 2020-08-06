package com.loanapp.notification;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.jms.*;


/* https://activemq.apache.org/ */

public class NewLoanNotificationService implements MessageListener{

    private static final Logger _logger = LogManager.getLogger(NewLoanNotificationService.class);
    private final String connectionURI = "tcp://localhost:61616";
    boolean isResponseReceived = false;
    //private ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(connectionURI);
    private ConnectionFactory connectionFactory;
    private Connection connection = null;

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void sendNotification(String loanNumber){
        String message = "New Loan Number " + loanNumber + " is boarded";
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination queue  = session.createQueue("JMS.LOANAPP.NEWLOAN");

            MessageProducer messageProducer = session.createProducer(queue);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            textMessage.setIntProperty("id",1);

            Destination tempDest = session.createTemporaryQueue();

            MessageConsumer consumer = session.createConsumer(tempDest);
            consumer.setMessageListener(this);

            textMessage.setJMSReplyTo(tempDest);

            messageProducer.send(textMessage);
            _logger.info("Sent Message ....");
            _logger.info("Waiting For Response .....");
        }catch(JMSException jmse){
            _logger.error("Can not send a message " + jmse);
        }

    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                this.isResponseReceived = true;
                _logger.info("Received Reply Message to ==> " + textMessage.getText() + "");
            }
        }catch(JMSException jmse){
            _logger.error("Error in Reading Reply Message", jmse);
        }
    }

    public static void main(String [] args){
        NewLoanNotificationService nlns = new NewLoanNotificationService();
        nlns.sendNotification("100000009");
    }
}
