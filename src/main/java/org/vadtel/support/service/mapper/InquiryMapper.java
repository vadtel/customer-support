package org.vadtel.support.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.entity.InquiryEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = InquiryAttributeMapper.class)
public interface InquiryMapper {

    @Mapping(source = "topicEntity.name", target = "topicName")
    Inquiry toDto(InquiryEntity inquiryEntity);

    @InheritInverseConfiguration
    InquiryEntity toEntity(Inquiry inquiry);

    List<Inquiry> toDtos(List<InquiryEntity> inquiryEntities);

    List<InquiryEntity> toEntities(List<Inquiry> inquiryEntities);


}


