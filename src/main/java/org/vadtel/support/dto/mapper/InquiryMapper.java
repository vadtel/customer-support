package org.vadtel.support.dto.mapper;

import org.mapstruct.Mapper;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.entity.InquiryAttributeEntity;
import org.vadtel.support.entity.InquiryEntity;

import java.util.List;
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
}
