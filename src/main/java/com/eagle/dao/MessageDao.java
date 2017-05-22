package com.eagle.dao;

import com.eagle.entity.po.Message;
import com.huisa.common.database.BaseDao;
import com.huisa.common.exception.ServiceException;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ligh on 2017/5/4.
 */
@Repository
public class MessageDao extends BaseDao{
    public Message getMessageByMaxMsgId(String topicName, Integer maxMsgId) throws ServiceException {
        String sql = String.format("select * from %s where id > ? limit 1", topicName);
        return get(sql, Message.class, maxMsgId);
    }

    public void addMessage(String topicName, String content) throws ServiceException {
        String sql = String.format("insert into %s (`content`, `create_time`, `update_time`) values (?, ?, ?)", topicName);
        add(sql, content, new Date(), new Date());
    }

    public List<Message> listMessageById(String topicName, Integer id, Integer limit) throws ServiceException {
        String sql = String.format("select * from %s where id > ? limit ?", topicName);
        return list(sql, Message.class, id, limit);
    }
}
