package org.vadtel.support.service;

import org.vadtel.support.dto.Topic;
import org.vadtel.support.entity.TopicEntity;

import java.util.List;

public interface TopicService {
    List<Topic> getAllTopics();

    TopicEntity findByNameOrCreate(String name);
}
