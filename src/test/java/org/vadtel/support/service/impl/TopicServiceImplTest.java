package org.vadtel.support.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vadtel.support.config.ApplicationContextConfig;
import org.vadtel.support.dto.Topic;
import org.vadtel.support.entity.TopicEntity;
import org.vadtel.support.service.TopicService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@ContextConfiguration(
        classes = {ApplicationContextConfig.class},
        loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
class TopicServiceImplTest {

    public static final String NAME = "Holmes";

    @Autowired
    TopicService topicService;

    @Test
    void getAllTopics() {
        List<Topic> allTopics = topicService.getAllTopics();
        assertNotNull(allTopics);
        assertIterableEquals(
                new ArrayList<>(Arrays.asList("Connection", "Payment", "Additional services", "Internet", "SIM", "Roaming", "Other")),
                allTopics.stream()
                        .map(Topic::getName)
                        .collect((Collectors.toList())));
    }


    @Test
    void findByNameOrCreate() {
        TopicEntity byNameOrCreate = topicService.findByNameOrCreate(NAME);
        assertEquals(NAME, byNameOrCreate.getName());

        byNameOrCreate = topicService.findByNameOrCreate("TestProblemTopic");
        assertNotNull(byNameOrCreate);
        assertEquals("TestProblemTopic", byNameOrCreate.getName());
    }
}