package com.loanapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcConnectionManager {

    private static JdbcConnectionManager _instance = null;
    private final String db_propeties_file_name = "/db.properties";
    private static final Logger _logger = LogManager.getLogger(JdbcConnectionManager.class);
    private Properties dbConnectionProps = null;


    public static JdbcConnectionManager getInstance(){
        if(_instance == null){
            _instance = new JdbcConnectionManager();
        }

        return _instance;
    }

    private JdbcConnectionManager(){
        _initialize();
    }

    private void _initialize(){
        // initialize database connection properties
        try (InputStream is = this.getClass().getResourceAsStream(db_propeties_file_name)){
            dbConnectionProps = new Properties();
            dbConnectionProps.load(is);
            Class.forName(dbConnectionProps.getProperty("db.driver.classname"));
        }catch(ClassNotFoundException cnf){
            _logger.error("Driver class not found " , cnf);
        }catch(IOException ioe){
            _logger.error("Error loading db properties", ioe);
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(dbConnectionProps.getProperty("db.url"),
                                                 dbConnectionProps.getProperty("db.username"),
                                                 dbConnectionProps.getProperty("db.password"));
        return connection;
    }

}
