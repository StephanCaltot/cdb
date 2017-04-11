package com.excilys.scaltot.cdb.persistence.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.scaltot.cdb.entities.QUser;
import com.excilys.scaltot.cdb.entities.User;
import com.excilys.scaltot.cdb.persistence.interfaces.UserDao;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

@Repository
public class UserDaoImpl implements UserDao {

    private static QUser qUser = QUser.user;

    private SessionFactory sessionFactory;

    private Supplier<HibernateQueryFactory> queryFactory =
            () -> new HibernateQueryFactory(sessionFactory.getCurrentSession());

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User findByUserName(String username) {

        List<User> users = new ArrayList<User>();

        users = queryFactory.get()
        .select(qUser)
        .from(qUser)
        .where(qUser.username.eq(username))
        .fetch();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

}
