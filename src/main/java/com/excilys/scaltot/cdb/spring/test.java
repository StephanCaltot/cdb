package com.excilys.scaltot.cdb.spring;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.scaltot.cdb.utils.DatabaseManager;

/**
 * @author Caltot Stéphan
 *
 * 20 mars 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseManagerConfig.class)
public class test {

    @Autowired
    private DatabaseManager databaseManager;

    @Test
    public void beanTest() {
        assertNotNull(databaseManager);
    }
}
