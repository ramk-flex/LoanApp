package com.loanapp.dao;

import com.loanapp.vo.LoanVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;

public class HibernateLoanDao extends HibernateBaseDao {

    private final Logger _logger = LogManager.getLogger(HibernateBaseDao.class);
    private final String _FIND_BY_LOAN_NO = "from " + LoanVO.class.getName() + " vo WHERE vo.loanNumber = :loanNumber";

    public LoanVO save(LoanVO loanVO){
        try( Session session = openHibernateSession()){
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(loanVO);
            tx.commit();
        }catch(HibernateException he){
            _logger.error("Saving Loan Details", loanVO);
        }

        return loanVO;
    }

    public LoanVO findByLoanNumber(String loanNumber){
        LoanVO loanVO = null;
        try(Session session = openHibernateSession()) {
            Query<LoanVO> loanVOQuery = session.createQuery(_FIND_BY_LOAN_NO, LoanVO.class);
            loanVOQuery.setParameter("loanNumber", loanNumber);
            loanVO = loanVOQuery.uniqueResult();
            //loanVO.getProperties();
        }catch(HibernateException he){
            _logger.error("Error Finding By Loan Number", he);
        }

        return loanVO;
    }

    public LoanVO findById(Long id){
        LoanVO loanVO = null;
        try(Session session = openHibernateSession()) {
           loanVO = session.get(LoanVO.class,id);
           loanVO.setLoanAmount(new BigDecimal(600000));
           loanVO.getBorrowers();
           //session.save(0)
           LoanVO anotherLoanVO = session.load(LoanVO.class,id);

        }catch(HibernateException he){
            _logger.error("Error Finding By Loan Number", he);
        }
        return loanVO;
    }

}
