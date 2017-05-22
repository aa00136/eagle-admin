package com.eagle.service.subscriber;

import com.eagle.dao.MessageDao;
import com.eagle.dao.SubscriberDao;
import com.eagle.dao.TopicDao;
import com.eagle.entity.po.Subscriber;
import com.eagle.entity.vo.QuerySubscriberVo;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberService {
    @Autowired
    private SubscriberDao subscriberDao;
    @Autowired
    private TopicDao topicDao;
    
    public List<Subscriber> querySubscriberListByPage(QuerySubscriberVo querySubscriberVo,
                                                      PageBean pageBean) throws ServiceException {
        StringBuffer querySqlBuffer = new StringBuffer();
        List<Object> queryParams = new ArrayList<Object>();

        querySqlBuffer.append("SELECT s.*,t.`name` as topic_name " +
                "FROM subscriber AS s " +
                "LEFT JOIN topic AS t ON s.topic_id = t.id where 1=1 ");

        List<Subscriber> subscriberList = subscriberDao.queryUserByPage(pageBean, querySqlBuffer.toString(), queryParams);
        for (Subscriber subscriber : subscriberList) {
            Integer maxMsgId = topicDao.getQueueMaxMsgId(subscriber.getTopicName());
            if (maxMsgId == null) {
                maxMsgId = 0;
            }
            subscriber.setStackingCapacity(maxMsgId - subscriber.getMinConsumeMsgId());
        }

        return subscriberList;
    }
}
