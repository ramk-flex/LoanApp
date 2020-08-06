package com.loanapp.component;

import com.loanapp.dao.JdbcSequenceInfoDao;

public class LoanNumberGeneratorComponent {

    public static String getNextLoanNumber(){
        JdbcSequenceInfoDao jdbcSequenceInfo = new JdbcSequenceInfoDao();
        return jdbcSequenceInfo.getNextSequceNumber("LOAN_NUMBER");
    }

    public static void main(String[] args) {
        LoanNumberGeneratorComponent lngc = new LoanNumberGeneratorComponent();
        System.out.println(lngc.getNextLoanNumber());
    }
}
