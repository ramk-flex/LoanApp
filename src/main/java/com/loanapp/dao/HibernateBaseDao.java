package com.loanapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class HibernateBaseDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session openHibernateSession(){
        /*HibernateConnectionManager hcm = HibernateConnectionManager.getInstance();
        return hcm.openSession();*/
        return sessionFactory.openSession();
    }
}
