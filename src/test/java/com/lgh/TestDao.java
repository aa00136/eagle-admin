package com.lgh;

import com.eagle.dao.ConsumeInfoDao;
import com.eagle.dao.MessageDao;
import com.eagle.dao.SubscriberDao;
import com.eagle.dao.TopicDao;
import com.eagle.entity.po.ConsumeInfo;
import com.eagle.entity.po.Message;
import com.eagle.entity.po.Topic;
import com.huisa.common.database.BaseDao;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ligh on 2017/5/6.
 */
public class TestDao {
    private TopicDao topicDao = new TopicDao();
    private MessageDao messageDao = new MessageDao();
    private SubscriberDao subscriberDao = new SubscriberDao();
    private ConsumeInfoDao consumeInfoDao = new ConsumeInfoDao();

    @org.junit.Test
    public void test1() throws ServiceException {
        Topic topic = topicDao.getTopicByName("lgh");
        System.out.println(topic.getId());
    }

    @org.junit.Test
    public void test2() throws ServiceException {
        //System.out.println(SubscriberService.getSubscriber("lgh").getTopicName());
    }

    @org.junit.Test
    public void test3() throws ServiceException {
        String sql = "CREATE TABLE `test2` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `content` varchar(1000) DEFAULT NULL,\n" +
                "  `create_time` datetime NOT NULL,\n" +
                "  `update_time` datetime NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;\n";
        new BaseDao().update(sql, new ArrayList<Object>());
    }

    @org.junit.Test
    public void test4() throws ServiceException {
        List<Message> messageList = messageDao.listMessageById("test", 0, 3);
        System.out.println(messageList.size());
    }

    @org.junit.Test
    public void test5() throws ServiceException {
        System.out.println(subscriberDao.getByClientNameAndTopicName("lgh", "test").getId());
    }

    @org.junit.Test
    public void test6() throws ServiceException {
        subscriberDao.updateMaxSendMsgId("lgh", "test", 110);
    }

    @org.junit.Test
    public void test7() throws ServiceException {
        subscriberDao.updateMinConsumeMsgId("lgh", "test", 110);
    }

    @org.junit.Test
    public void test8() throws ServiceException {
        ConsumeInfo consumeInfo = new ConsumeInfo();
        consumeInfo.setMsgId(1);
        consumeInfo.setTopicId(2);
        consumeInfo.setSubscriberId(8);
        consumeInfo.setConsumeCount(0);
        consumeInfo.setCreateTime(new Date());

        consumeInfoDao.addConsumeInfo(consumeInfo);
    }

    @org.junit.Test
    public void test9() throws ServiceException {
        consumeInfoDao.updateConsumeInfo("test", "lgh", 1, 11);
    }

    @org.junit.Test
    public void test10() throws ServiceException {
        System.out.println(consumeInfoDao.getConsumeInfo("test", "lgh", 1).getConsumeCount());
    }

    @org.junit.Test
    public void test11() throws ServiceException {
        StringBuffer querySqlBuffer = new StringBuffer();
        List<Object> queryParams = new ArrayList<Object>();

        querySqlBuffer.append("SELECT s.*,t.`name` as topic_name " +
                "FROM subscriber AS s " +
                "LEFT JOIN topic AS t ON s.topic_id = t.id where 1=1 ");
        System.out.println(subscriberDao.queryUserByPage(new PageBean(1, 10), querySqlBuffer.toString(), queryParams).get(0).getTopicName());
    }
}
