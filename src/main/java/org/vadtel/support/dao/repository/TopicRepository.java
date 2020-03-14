package org.vadtel.support.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vadtel.support.dto.Topic;
import org.vadtel.support.entity.TopicEntity;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
    Optional<TopicEntity> findByName(String name);
}
