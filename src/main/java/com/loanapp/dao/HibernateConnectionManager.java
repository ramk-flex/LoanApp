package com.loanapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConnectionManager {

    private static HibernateConnectionManager _instance = null;
    private static Logger _logger = LogManager.getLogger(HibernateConnectionManager.class);
    private SessionFactory _sessionFactory = null;
    private Session _session = null;

    public static HibernateConnectionManager getInstance(){
        if(_instance == null){
            _instance = new HibernateConnectionManager();
        }

        return _instance;
    }

    private HibernateConnectionManager(){
        _initialize();
    }

    private void _initialize(){
        try {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            _sessionFactory = new MetadataSources(registry)
                                .buildMetadata()
                                .buildSessionFactory();
        }catch(Exception e){
            _logger.error("Error creating Hibernate Session Factory", e);
        }
    }

    public Session openSession(){
        _logger.info("Opening Hibernate Session");
        this._session = this._sessionFactory.openSession();
        return this._session;
    }

    public void closeSession(){
        _logger.info("Closing Hibernate Session");
        if(this._sessionFactory.getCurrentSession()!=null){
            this._sessionFactory.getCurrentSession().close();
        }
    }

}
