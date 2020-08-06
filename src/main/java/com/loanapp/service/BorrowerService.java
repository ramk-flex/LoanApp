package com.loanapp.service;

import com.loanapp.dao.JdbcBorrowerDao;
import com.loanapp.vo.BorrowerVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BorrowerService {

    private final Logger _logger = LogManager.getLogger(BorrowerService.class);

    public void addBorrowers(List<BorrowerVO> borrowers, Long loanId){

        for(BorrowerVO borrowerVO : borrowers){
            JdbcBorrowerDao jdbcBorrowerDao = new JdbcBorrowerDao();
            borrowerVO.setLoanAppLoanId(loanId);
            jdbcBorrowerDao.save(borrowerVO);
        }
    }
}
