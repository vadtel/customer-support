package org.vadtel.support.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vadtel.support.dao.repository.InquiryRepository;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.dto.InquiryAttribute;
import org.vadtel.support.entity.InquiryAttributeEntity;
import org.vadtel.support.entity.InquiryEntity;
import org.vadtel.support.entity.TopicEntity;
import org.vadtel.support.service.InquiryService;
import org.vadtel.support.service.TopicService;
import org.vadtel.support.service.mapper.InquiryAttributeMapper;
import org.vadtel.support.service.mapper.InquiryMapper;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final InquiryMapper inquiryMapper;
    private final InquiryAttributeMapper inquiryAttributeMapper;
    private final TopicService topicService;

    @Override
    public List<Inquiry> getAllInquiriesByCustomerName(String customerName) {
        List<InquiryEntity> inquiryEntities = inquiryRepository.findAllByCustomerName(customerName);
        List<Inquiry> inquiries = inquiryMapper.toDtos(inquiryEntities);
        return inquiries;
    }

    @Override
    public Inquiry getInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId) {
        InquiryEntity inquiryEntity = inquiryRepository.findByCustomerNameAndId(customerName, inquiryId);
        Inquiry inquiry = inquiryMapper.toDto(inquiryEntity);
        return inquiry;
    }

    @Override
    public Inquiry createInquiry(Inquiry inquiry) {
        TopicEntity topicEntity = topicService.findByNameOrCreate(inquiry.getTopicName());
        InquiryEntity inquiryEntity = inquiryMapper.toEntity(inquiry);
        inquiryEntity.setTopicEntity(topicEntity);
        inquiryEntity.getAttributeEntityList().forEach(x -> x.setInquiryEntity(inquiryEntity));

        InquiryEntity save = inquiryRepository.save(inquiryEntity);
        Inquiry saveEntity = inquiryMapper.toDto(save);

        return saveEntity;
    }

    @Override
    public void deleteInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId){
        inquiryRepository.deleteByCustomerNameAndId(customerName, inquiryId);
    }

    @Override
    public Inquiry getAndUpdateInquiryByCustomerNameAndInquiryId(Inquiry sourceInquiry,
                                                                 String customerName,
                                                                 Long inquiryId) {
        InquiryEntity targetInquiryEntity = inquiryRepository.findByCustomerNameAndId(customerName, inquiryId);


        updateInquiryEntity(sourceInquiry, targetInquiryEntity);

        targetInquiryEntity.getAttributeEntityList().add(
                new InquiryAttributeEntity("Update inquiry",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                        targetInquiryEntity));
        InquiryEntity save = inquiryRepository.save(targetInquiryEntity);

        Inquiry inquiry = inquiryMapper.toDto(save);
        return inquiry;
    }

    private void updateInquiryEntity(Inquiry source, InquiryEntity target) {
        target.setDescription(source.getDescription());
        List<InquiryAttribute> sourceAttributes = source.getAttributeEntityList();
        List<InquiryAttributeEntity> targetAttributes = target.getAttributeEntityList();

        targetAttributes.forEach(tar -> sourceAttributes
                .forEach(sor -> {
                    if (tar.getName().equals(sor.getName())) {
                        tar.setValue(sor.getValue());
                    }
                }));

        List<InquiryAttribute> attributes = sourceAttributes
                .stream()
                .filter(sour -> targetAttributes
                        .stream()
                        .noneMatch(tar -> tar.getName().equals(sour.getName())))
                .collect(Collectors.toList());


        targetAttributes.addAll(inquiryAttributeMapper.toEntities(attributes));
        targetAttributes.forEach(x->x.setInquiryEntity(target));
    }
}
