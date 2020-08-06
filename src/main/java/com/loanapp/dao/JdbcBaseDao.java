package com.loanapp.dao;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class JdbcBaseDao {

    private static final Logger _logger = LogManager.getLogger(JdbcBaseDao.class);

    protected final Connection getConnection() throws SQLException{
        return JdbcConnectionManager.getInstance().getConnection();
    }

    protected final void _cleanup(Connection conn, Statement stmt, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
