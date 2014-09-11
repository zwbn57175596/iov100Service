package com.iov100.dao;

import com.iov100.common.DBPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository("topicDao")
public class TopicDao {

  private static Log log = LogFactory.getLog(TopicDao.class);

  private static DBPool dbPool = DBPool.getInstance();
  
}