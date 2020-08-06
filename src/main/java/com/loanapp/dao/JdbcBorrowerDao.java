package com.loanapp.dao;

import com.loanapp.vo.BorrowerVO;
import com.loanapp.vo.LoanVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;

public class JdbcBorrowerDao extends JdbcBaseDao {

    private static final Logger _logger = LogManager.getLogger(JdbcBorrowerDao.class);

    private static final String _INSERT_QUERY = "INSERT INTO loan_app_borrower(loan_app_loan_id,created_date_time,created_by_user_id," +
                   "modified_date_time,modified_by_user_id,borrower_number,borrower_role_id,first_name,middle_name,last_name,social_security_number," +
                    "home_phone_number, date_of_birth, years_at_school,marital_status,number_of_dependents) values " +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String _READ_BORROWER_QUERY = "SELECT * FROM loan_app_borrower where loan_app_loan_id = ? and borrower_number = ?";

    public Long save(BorrowerVO borrowerVO){
        Long borrowerId = null;
        Connection _conn = null;
        PreparedStatement _stmt = null;
        try{
            _conn = getConnection();
            _stmt = _conn.prepareStatement(_INSERT_QUERY);
            _stmt.setLong(1, borrowerVO.getLoanAppLoanId());
            _stmt.setTimestamp(2, Timestamp.from(Instant.now()));
            _stmt.setString(3, borrowerVO.getCreatedByUserId());
            _stmt.setTimestamp(4,Timestamp.from(Instant.now()));
            _stmt.setString(5,borrowerVO.getModifiedByUserId());
            _stmt.setShort(6,borrowerVO.getBorrowerNumber());
            _stmt.setString(7,borrowerVO.getBorrowerRoleId());
            _stmt.setString(8, borrowerVO.getFirstName());
            _stmt.setString(9, borrowerVO.getMiddleName());
            _stmt.setString(10, borrowerVO.getLastName());
            _stmt.setString(11,borrowerVO.getSocialSecurityNumber());
            _stmt.setString(12, borrowerVO.getHomePhoneNumber());
            _stmt.setDate(13,borrowerVO.getDateOfBirth());
            _stmt.setShort(14,borrowerVO.getYearsInSchool());
            _stmt.setString(15,borrowerVO.getMaritalStatus());
            _stmt.setShort(16,borrowerVO.getNumberOfDependents());

            _stmt.executeUpdate();

        }catch(SQLException sqe){
            _logger.error("Error in saving borrower", sqe);
        }finally{
            _cleanup(_conn,_stmt,null);
        }

        return borrowerId;
    }

    public BorrowerVO read(String loanId,int borrowerNumber){
        BorrowerVO borrowerVO = null;
        Connection _conn = null;
        PreparedStatement _pstmt = null;
        ResultSet _rset = null;
        int i = 0;
        try{
            _conn = getConnection();
            _pstmt = _conn.prepareStatement(_READ_BORROWER_QUERY);
            _pstmt.setString(1, loanId);
            _pstmt.setLong(2,borrowerNumber);
            _rset = _pstmt.executeQuery();
            if(_rset!= null && _rset.next()){
                borrowerVO = new BorrowerVO();
                borrowerVO.setBorrowerRoleId(_rset.getString("borrower_role_id"));
            }
        }catch(SQLException sqe){
            _logger.error("Error reading loan details", sqe);
        }finally {
            _cleanup(_conn,_pstmt,_rset);
        }
        return borrowerVO;
    }


}
