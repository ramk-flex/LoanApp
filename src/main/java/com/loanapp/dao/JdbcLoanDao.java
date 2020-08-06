package com.loanapp.dao;

import com.loanapp.vo.AddressVO;
import com.loanapp.vo.BaseVO;
import com.loanapp.vo.LoanVO;
import com.loanapp.vo.PropertyVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JdbcLoanDao extends JdbcBaseDao{
    private static final Logger _logger = LogManager.getLogger(JdbcLoanDao.class);
    // C-Create, R-Read, U-Update, D-Delete opreations

    //C- Create
    private static final String INSERT_QUERY = "INSERT INTO loan_app_loan(created_date_time,created_by_user_id,modified_date_time,modified_by_user_id," +
            "loan_number,mortgage_type,fha_case_number,loan_amount,loan_term,product_type) " +
            " VALUES(?,?,?,?,?,?)";

    private static final String NEW_INSERT_QUERY = "INSERT INTO loan_app_loan(loan_number,mortgage_type,fha_case_number,loan_amount,loan_term,product_type) " +
            " VALUES(?,?,?,?,?,?)";

    //R - Read
    private static final String READ_QUERY = "SELECT * FROM loan_app_loan WHERE loan_number = ?";

    //All - Records

    private static final String ALL_LOAN_QUERY = "SELECT * FROM v_loan_property_info";


    public Long save(LoanVO loanVO){
        Long loanId = null;
        Connection _conn = null;
        PreparedStatement _stmt = null;
        Statement _regularStmt = null;
        try{
            _conn = getConnection();
           _stmt = _conn.prepareStatement(NEW_INSERT_QUERY);
           // _stmt.setLong(1, loanVO.getId());
            /*_stmt.setTimestamp(1, Timestamp.from(Instant.now()));
            _stmt.setString(2, loanVO.getCreatedByUserId());
            _stmt.setTimestamp(3,Timestamp.from(Instant.now()));
            _stmt.setString(4,loanVO.getModifiedByUserId());*/
            _stmt.setString(1,loanVO.getLoanNumber());
            _stmt.setString(2,loanVO.getMortgageType());
            _stmt.setString(3, loanVO.getFhaCaseNumber());
            _stmt.setBigDecimal(4, loanVO.getLoanAmount());
            _stmt.setShort(5, loanVO.getLoanTerm());
            _stmt.setString(6,loanVO.getProductType());

            _stmt.executeUpdate();

        }catch(SQLException sqe){
            _logger.error("Error in saving loan to database", sqe);
        }finally{
            _cleanup(_conn,_stmt,null);
        }

        return loanId;
    }

    public LoanVO read(String loanNumber){
        LoanVO loanVO = null;
        Connection _conn = null;
        PreparedStatement _pstmt = null;
        ResultSet _rset = null;
        int i = 0;
        try{
            _conn = getConnection();
            _pstmt = _conn.prepareStatement(READ_QUERY);
            _pstmt.setString(1, loanNumber);
            _rset = _pstmt.executeQuery();
            if(_rset!= null && _rset.next()){
                loanVO = new LoanVO();
                loanVO.setId(_rset.getLong(++i));
                loanVO.setVersion(_rset.getLong(++i));
                loanVO.setCreatedDateTime(_rset.getTimestamp(++i).toInstant());
                loanVO.setCreatedByUserId(_rset.getString(++i));
                loanVO.setModifiedDateTime(_rset.getTimestamp(++i).toInstant());
                loanVO.setModifiedByUserId(_rset.getString(++i));
                loanVO.setLoanNumber(_rset.getString(++i));
                loanVO.setMortgageType(_rset.getString(++i));
                loanVO.setFhaCaseNumber(_rset.getString(++i));
                loanVO.setLoanAmount(_rset.getBigDecimal(++i));
                loanVO.setLoanTerm(_rset.getShort(++i));
                loanVO.setProductType(_rset.getString(++i));
            }
        }catch(SQLException sqe){
            _logger.error("Error reading loan details", sqe);
        }finally {
            _cleanup(_conn,_pstmt,_rset);
        }
        return loanVO;
    }

    public List<LoanVO> getAllLoans(){
        List<LoanVO> allLoansList = new ArrayList<>();

        Connection _conn = null;
        PreparedStatement _pstmt = null;
        ResultSet _rset = null;

        try{
            _conn = getConnection();
            _pstmt = _conn.prepareStatement(ALL_LOAN_QUERY);
            _pstmt.setFetchSize(200);
            _rset = _pstmt.executeQuery();
            while(_rset!= null && _rset.next()){
                int i = 0;
                LoanVO loanVO = new LoanVO();

                //loanVO.setId(_rset.getLong(++i));
                //loanVO.setVersion(_rset.getLong(++i));
                //loanVO.setCreatedDateTime(_rset.getTimestamp(++i).toInstant());
                //loanVO.setCreatedByUserId(_rset.getString(++i));
                //loanVO.setModifiedDateTime(_rset.getTimestamp(++i).toInstant());
                //loanVO.setModifiedByUserId(_rset.getString(++i));
                //loanVO.setMortgageType(_rset.getString(++i));
                //loanVO.setFhaCaseNumber(_rset.getString(++i));
                //loanVO.setLoanTerm(_rset.getShort(++i));
                //loanVO.setProductType(_rset.getString(++i));

                loanVO.setLoanNumber(_rset.getString(++i));
                loanVO.setLoanAmount(_rset.getBigDecimal(++i));
                PropertyVO propvo = new PropertyVO();
                propvo.setFamilyUnits(_rset.getShort(++i));

                AddressVO addressVO = new AddressVO();
                addressVO.setAddressLineOne(_rset.getString(++i));
                addressVO.setAddressLineTwo(_rset.getString(++i));
                addressVO.setCity(_rset.getString(++i));
                addressVO.setStateId(_rset.getString(++i));
                addressVO.setPostalCodeId(_rset.getString(++i));
                propvo.setPropertyAddress(addressVO);
                Set<PropertyVO> properties = new HashSet<>();
                properties.add(propvo);

                loanVO.setProperties(properties);


                allLoansList.add(loanVO);
            }
        }catch(SQLException sqe){
            _logger.error("Error reading loan details", sqe);
        }finally {
            _cleanup(_conn,_pstmt,_rset);
        }

        return allLoansList;

    }

    public static void main(String[] args) {
        JdbcLoanDao dao = new JdbcLoanDao();
        LoanVO loanVO = new LoanVO();

        //loanVO.setId(1L);
        /*loanVO.setCreatedDateTime(Instant.now());
        loanVO.setCreatedByUserId("Ram");
        loanVO.setModifiedDateTime(Instant.now());
        loanVO.setModifiedByUserId("Ram");
        loanVO.setLoanNumber("1000000099");
        loanVO.setMortgageType("CONV");
        loanVO.setFhaCaseNumber(null);
        loanVO.setLoanAmount(new BigDecimal(200000));
        short s = 180;
        loanVO.setLoanTerm(s);
        loanVO.setProductType("Fix");
        dao.save(loanVO);*/

       // LoanVO loanVO = dao.read("1000000001");

        List<LoanVO> allLoans = dao.getAllLoans();

        System.out.println(allLoans);

        /*BaseVO basevo = new BaseVO();
        basevo.setVersion(1L);
        basevo.setCreatedDateTime(Instant.now());
        basevo.setCreatedByUserId("Ram");

        basevo.setModifiedByUserId("Kumar");
        basevo.setModifiedDateTime(Instant.now());*/


    }
}
