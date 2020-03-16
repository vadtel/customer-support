package org.vadtel.support.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vadtel.support.dao.repository.TopicRepository;
import org.vadtel.support.dto.Topic;
import org.vadtel.support.entity.TopicEntity;
import org.vadtel.support.exception.DaoException;
import org.vadtel.support.service.TopicService;
import org.vadtel.support.service.mapper.TopicMapper;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public List<Topic> getAllTopics() {
        List<TopicEntity> topicEntity = null;
        try {
            topicEntity = topicRepository.findAll();
        } catch (Exception e) {
            throw new DaoException(e);
        }
        List<Topic> topics = topicMapper.toDtos(topicEntity);

        return topics;
    }

    @Override
    public TopicEntity findByNameOrCreate(String name) {
        TopicEntity topicEntity = null;
        try {
            topicEntity = topicRepository.findByName(name)
                    .orElseGet(() -> topicRepository.save(new TopicEntity(name)));
        } catch (Exception e) {
            throw new DaoException(e);
        }

        return topicEntity;
    }
}
