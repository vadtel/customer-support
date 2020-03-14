package org.vadtel.support.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.vadtel.support.dao.repository.TopicRepository;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.dto.Topic;
import org.vadtel.support.entity.InquiryAttributeEntity;
import org.vadtel.support.entity.InquiryEntity;
import org.vadtel.support.entity.TopicEntity;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InquiryMapper {


    List<Inquiry> toDtos(List<InquiryEntity> inquiryEntity);

    default Inquiry toDto(InquiryEntity inquiryEntity) {
        if (inquiryEntity == null) {
            return null;
        }
        Inquiry inquiry = new Inquiry();

        inquiry.setId(inquiryEntity.getId());
        inquiry.setDescription(inquiryEntity.getDescription());
        inquiry.setCustomerName(inquiryEntity.getCustomerName());
        inquiry.setTopicName(inquiryEntity.getTopicEntity().getName());
        inquiry.setCreateDate(inquiryEntity.getCreateDate());
        inquiry.setAttributeEntityList(
                inquiryEntity.getAttributeEntityList() == null ? null :
                        inquiryEntity.getAttributeEntityList()
                                .stream()
                                .collect(Collectors
                                        .toMap(InquiryAttributeEntity::getName, InquiryAttributeEntity::getValue))
        );

        return inquiry;
    }

    default InquiryEntity toEntity(Inquiry inquiry, TopicEntity topicEntity) {
        if (inquiry == null) {
            return null;
        }

        InquiryEntity inquiryEntity = new InquiryEntity();

        inquiryEntity.setId(inquiry.getId());
        inquiryEntity.setDescription(inquiry.getDescription());
        inquiryEntity.setCustomerName(inquiry.getCustomerName());
        inquiryEntity.setTopicEntity(topicEntity);
        inquiryEntity.setCreateDate(new Date(inquiry.getCreateDate().getTime()));

        Map<String, String> attributeEntityList = inquiry.getAttributeEntityList();
        List<InquiryAttributeEntity> inquiryAttributeEntities = new ArrayList<>();

        if (attributeEntityList != null) {
            inquiryAttributeEntities = attributeEntityList.entrySet()
                    .stream()
                    .map(attr -> new InquiryAttributeEntity(
                            attr.getKey(),
                            attr.getValue(),
                            inquiryEntity))
                    .collect(Collectors.toList());
        }

        inquiryEntity.setAttributeEntityList(inquiryAttributeEntities);

        return inquiryEntity;
    }


}
