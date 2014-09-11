package com.iov100.common;

import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBPool {

  private static Log log = LogFactory.getLog(DBPool.class);

  // singleton
  private static DBPool instance = new DBPool();
  private ComboPooledDataSource dataSource;
  private int MAX_CONN_NUM = 10;
  private ArrayList<Connection> connectionPool = new ArrayList<Connection>();

  // private contructor
  private DBPool () {
    try {
      Properties p = new Properties();
      p.load(this.getClass().getResourceAsStream("db"));
      dataSource = new ComboPooledDataSource();
      dataSource.setUser(p.getProperty("user"));
      dataSource.setPassword(p.getProperty("password"));
      dataSource.setJdbcUrl(p.getProperty("url"));
      dataSource.setDriverClass(p.getProperty("driver"));
      dataSource.setInitialPoolSize(Integer.parseInt(p.getProperty("initalPoolSize")));
      dataSource.setMinPoolSize(Integer.parseInt(p.getProperty("minPoolSize")));
      dataSource.setMaxPoolSize(Integer.parseInt(p.getProperty("maxPoolSize")));
      dataSource.setMaxStatements(Integer.parseInt(p.getProperty("maxStatments")));
      dataSource.setMaxIdleTime(Integer.parseInt(p.getProperty("maxIdleTime")));
    } catch (PropertyVetoException e) {
      log.error(e);
    } catch (IOException e) {
      log.error(e);
    }
  }

  // public getINstance
  public static DBPool getInstance() {
    return instance;
  }

  // getConnection
  public synchronized final Connection getConnecton() throws SQLException {
    return dataSource.getConnection();
  }

}