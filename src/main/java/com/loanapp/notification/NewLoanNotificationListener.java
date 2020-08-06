package com.loanapp.notification;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.jms.*;

//server

public class NewLoanNotificationListener implements MessageListener {

    private static final Logger _logger = LogManager.getLogger(NewLoanNotificationListener.class);
    private final String connectionURI = "tcp://localhost:61616";
    private MessageProducer replyProducer = null;
    private TextMessage responseTextMessage = null;
    private Session session = null;

    @Override
    public void onMessage(Message message) {
        try {
            responseTextMessage = this.session.createTextMessage();
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                _logger.info("Received text message == > " + textMessage.getText());
            }

            responseTextMessage.setText("Received Loan Number $$$$$");
            responseTextMessage.setJMSCorrelationID(message.getJMSCorrelationID());
            this.replyProducer.send(message.getJMSReplyTo(),responseTextMessage);
            _logger.info("Sent Response ...." );
        }catch(JMSException jmse){
            _logger.error("Error Reading New Loan Notification Listener", jmse);
        }
    }

    public void startListener(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(connectionURI);
        Connection connection = null;
        try{
            connection = activeMQConnectionFactory.createQueueConnection();
            connection.start();
            _logger.info("Started Listener.... ");
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("JMS.LOANAPP.NEWLOAN");

            this.replyProducer = session.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(this);
        }catch(JMSException jmse){
            _logger.error("Error In Starting Listener",jmse);
        }
    }

    public static void main(String[] args) {
        NewLoanNotificationListener listener = new NewLoanNotificationListener();
        listener.startListener();
    }
}
