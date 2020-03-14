package org.vadtel.support.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vadtel.support.dao.repository.TopicRepository;
import org.vadtel.support.dto.Topic;
import org.vadtel.support.entity.TopicEntity;
import org.vadtel.support.service.TopicService;
import org.vadtel.support.service.mapper.TopicMapper;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    @Override
    public List<Topic> getAllTopics() {
        List<TopicEntity> topicEntity = topicRepository.findAll();
        List<Topic> topics = topicMapper.toDtos(topicEntity);

        return topics;
    }

    @Override
    public TopicEntity findByNameOrCreate(String name) {
        TopicEntity topicEntity = topicRepository.findByName(name)
                .orElseGet(() -> topicRepository.save(new TopicEntity(name)));

        return topicEntity;
    }
}
