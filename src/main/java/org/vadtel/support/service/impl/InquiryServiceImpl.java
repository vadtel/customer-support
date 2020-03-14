package org.vadtel.support.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vadtel.support.dao.repository.InquiryRepository;
import org.vadtel.support.dao.repository.TopicRepository;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.dto.Topic;
import org.vadtel.support.entity.TopicEntity;
import org.vadtel.support.service.TopicService;
import org.vadtel.support.service.mapper.InquiryMapper;
import org.vadtel.support.entity.InquiryEntity;
import org.vadtel.support.service.InquiryService;


import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final InquiryMapper inquiryMapper;
    private final TopicService topicService;


    @Autowired
    public InquiryServiceImpl(InquiryRepository inquiryRepository,
                              InquiryMapper inquiryMapper,
                              TopicService topicService) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryMapper = inquiryMapper;
        this.topicService = topicService;
    }

    @Override
    public List<Inquiry> getAllInquiriesByCustomerName(String customerName) {
        List<InquiryEntity> inquiryEntities = inquiryRepository.findAllByCustomerName(customerName);
        List<Inquiry> inquiries = inquiryMapper.toDtos(inquiryEntities);
        return inquiries;
    }

    @Override
    public Inquiry getInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId){
        InquiryEntity inquiryEntity = inquiryRepository.findByCustomerNameAndId(customerName, inquiryId);
        Inquiry inquiry = inquiryMapper.toDto(inquiryEntity);
        return inquiry;
    }

    @Override
    public Inquiry createInquiry(Inquiry inquiry){
        TopicEntity topicEntity = topicService.findByNameOrCreate(inquiry.getTopicName());
        InquiryEntity inquiryEntity = inquiryMapper.toEntity(inquiry, topicEntity);
        InquiryEntity save = inquiryRepository.save(inquiryEntity);
        Inquiry saveEntity = inquiryMapper.toDto(save);

        return saveEntity;
    }
}
