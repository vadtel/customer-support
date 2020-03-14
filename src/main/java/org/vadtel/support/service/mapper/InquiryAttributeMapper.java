package org.vadtel.support.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vadtel.support.dto.InquiryAttribute;
import org.vadtel.support.entity.InquiryAttributeEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InquiryAttributeMapper {
    InquiryAttribute toDto(InquiryAttributeEntity inquiryAttribute);


    InquiryAttributeEntity toEntity(InquiryAttribute inquiryAttribute);

    List<InquiryAttribute> toDtos(List<InquiryAttributeEntity> inquiryAttribute);

    List<InquiryAttributeEntity> toEntities(List<InquiryAttribute> inquiryAttribute);

}
