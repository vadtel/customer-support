package org.vadtel.support.dao.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vadtel.support.config.ApplicationContextConfig;
import org.vadtel.support.dao.config.RepositoryConfiguration;
import org.vadtel.support.entity.TopicEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
@ContextConfiguration(
        classes = {ApplicationContextConfig.class},
        loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class TopicRepositoryTest {

    @Autowired
    TopicRepository topicRepository;

    @Test
    void testFindByName() {
        Optional<TopicEntity> topicEntity = topicRepository.findByName("Connection");
        assertTrue(topicEntity.isPresent());
        assertEquals(1, topicEntity.get().getId());
        assertEquals("Connection", topicEntity.get().getName());
    }

    @Test
    void testFindOneIfNullThenCreate() {
        String name = "TestProblemTopic";
        TopicEntity topicEntity = topicRepository.findByName(name)
                .orElseGet(() -> topicRepository.save(new TopicEntity(name)));
        assertEquals(name, topicEntity.getName());

        Optional<TopicEntity> byName = topicRepository.findByName(name);
        assertTrue(byName.isPresent());
        assertEquals(name, byName.get().getName());
    }

    @Test
    void testFindAllTopic() {
        List<TopicEntity> all = topicRepository.findAll();
        assertIterableEquals(
                new ArrayList<>(Arrays.asList("Connection", "Payment", "Additional services", "Internet", "SIM", "Roaming", "Other")),
                all.stream()
                        .map(TopicEntity::getName)
                        .collect((Collectors.toList())));
    }

}
