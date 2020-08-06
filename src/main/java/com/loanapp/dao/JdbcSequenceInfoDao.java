package com.loanapp.dao;

import com.loanapp.vo.LoanVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSequenceInfoDao extends JdbcBaseDao {
    private static final Logger _logger = LogManager.getLogger(JdbcLoanDao.class);

    private final String _SELECT_QUERY = "SELECT * FROM loan_app_sequence_info "+
                                          " WHERE sequence_name = ?";

    public String getNextSequceNumber(String sequnceName){
        String sequnceNumber = null;
        Connection _conn = null;
        PreparedStatement _pstmt = null;
        ResultSet _rset = null;
        try{
            _conn = getConnection();
            _pstmt = _conn.prepareStatement(_SELECT_QUERY,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            _pstmt.setString(1, sequnceName);
            _rset = _pstmt.executeQuery();
            if(_rset!= null && _rset.next()){
                Long temp = Long.parseLong(_rset.getString("sequence_number"));
                temp = temp + 1;
                sequnceNumber = temp.toString();
                _rset.updateString("sequence_number",sequnceNumber);
                _rset.updateRow();
            }
        }catch(SQLException sqe){
            _logger.error("Error reading loan details", sqe);
        }finally {
            _cleanup(_conn,_pstmt,_rset);
        }

        return sequnceNumber;
    }
}
