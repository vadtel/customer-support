package org.vadtel.support.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vadtel.support.dto.Topic;
import org.vadtel.support.entity.TopicEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    Topic toDto(TopicEntity topicEntity);

    List<Topic> toDtos(List<TopicEntity> topicEntities);

    @Mapping(target = "inquiryList", ignore = true)
    TopicEntity toEntity(Topic topic);


}
