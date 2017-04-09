package com.excilys.scaltot.cdb.persistence.interfaces;

import com.excilys.scaltot.cdb.entities.User;

public interface UserDao {
    User findByUserName(String username);
}