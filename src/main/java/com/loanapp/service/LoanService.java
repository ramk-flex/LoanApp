package com.loanapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanapp.component.LoanNumberGeneratorComponent;
import com.loanapp.dao.HibernateLoanDao;
import com.loanapp.notification.NewLoanNotificationService;
import com.loanapp.vo.LoanVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class LoanService {

    private final Logger _logger = LogManager.getLogger(LoanService.class);

    private HibernateLoanDao hibernateLoanDao = null;

    private NewLoanNotificationService newLoanNotificationService = null;

    public HibernateLoanDao getHibernateLoanDao() {
        return hibernateLoanDao;
    }

    public void setHibernateLoanDao(HibernateLoanDao hibernateLoanDao) {
        this.hibernateLoanDao = hibernateLoanDao;
    }

    public NewLoanNotificationService getNewLoanNotificationService() {
        return newLoanNotificationService;
    }

    public void setNewLoanNotificationService(NewLoanNotificationService newLoanNotificationService) {
        this.newLoanNotificationService = newLoanNotificationService;
    }

    public LoanVO createNewLoan(){
        _logger.info("Creating A New Loan");
        final String jsonFilePath = "/loan.json";

        LoanVO loanVO = null;
        try(InputStream is = this.getClass().getResourceAsStream(jsonFilePath)){
            ObjectMapper objectMapper = new ObjectMapper();
            loanVO = objectMapper.readValue(is,LoanVO.class);
            String loanNumber = LoanNumberGeneratorComponent.getNextLoanNumber();
            loanVO.setLoanNumber(loanNumber);

            //HibernateLoanDao hibernateLoanDao = new HibernateLoanDao();

            loanVO = hibernateLoanDao.save(loanVO);
            _logger.info("Created A New Loan ==> " + loanNumber);
            _publishNewLoanNotification(loanNumber);

            // SENDER ==> MESSAGE PROVIDER(Apache Web MQ) ==> RECEIVER

            /*JdbcLoanDao jdbcLoanDao = new JdbcLoanDao();
            jdbcLoanDao.save(loanVO);

            LoanVO savedLoanVO = jdbcLoanDao.read(loanVO.getLoanNumber());

            BorrowerService borrowerService = new BorrowerService();
            borrowerService.addBorrowers(loanVO.getBorrowers(),loanVO.getId());**/
        }catch(Exception ex){
            _logger.error("Error Creating A Loan", ex);
        }

        return loanVO;
    }

    public LoanVO createNewLoan(LoanVO loanVO){
        String loanNumber = LoanNumberGeneratorComponent.getNextLoanNumber();
        loanVO.setLoanNumber(loanNumber);
        loanVO = hibernateLoanDao.save(loanVO);
        _logger.info("Created a new loan " + loanVO.getLoanNumber());

        return loanVO;
    }

    private void _publishNewLoanNotification(String loanNumber){
        newLoanNotificationService.sendNotification(loanNumber);
    }

    /*public LoanVO findLoanByLoanNumber(String loanNumber){
        HibernateLoanDao hibernateLoanDao = new HibernateLoanDao();
        LoanVO loanVO = hibernateLoanDao.findByLoanNumber(loanNumber);
        _logger.info("Loan Details ==> " + loanVO.toString());

        return loanVO;
    }*/

    public LoanVO findLoanByLoanNumber(String loanNumber){
        //HibernateLoanDao hibernateLoanDao = new HibernateLoanDao();
        LoanVO loanVO = hibernateLoanDao.findByLoanNumber(loanNumber);
        _logger.info("Loan Details ==> " + loanVO.toString());

        return loanVO;
    }

    public LoanVO updateLoan(LoanVO loanVO){
        return hibernateLoanDao.save(loanVO);
    }

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        LoanService loanService = (LoanService) context.getBean("loanService");


        /*LoanService loanService = new LoanService();
        HibernateLoanDao hibernateLoanDao = new HibernateLoanDao();
        loanService.setHibernateLoanDao(hibernateLoanDao);*/

        loanService.createNewLoan();

        //LoanVO loanVO = loanService.findLoanByLoanNumber("1000000025");


        //int i = 0;
        //loanVO.getProperties();
        //System.out.println(loanVO);

        /*BigDecimal bd = new BigDecimal(850000);
        loanVO.setLoanAmount(bd);
        Set borrowers = loanVO.getBorrowers();
        Iterator iter = borrowers.iterator();
        BorrowerVO borrower = (BorrowerVO)iter.next();
        short s = 55;
        borrower.setYearsInSchool(new Short(s));
        //loanVO.getBorrowers().add(borrower);
        loanService.updateLoan(loanVO);
        int i = 0;*/


    }
}
