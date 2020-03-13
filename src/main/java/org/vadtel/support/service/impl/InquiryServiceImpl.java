package org.vadtel.support.service.impl;

import org.springframework.stereotype.Service;
import org.vadtel.support.dao.repository.InquiryRepository;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.dto.mapper.InquiryMapper;
import org.vadtel.support.entity.InquiryEntity;
import org.vadtel.support.service.InquiryService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final InquiryMapper inquiryMapper;

    public InquiryServiceImpl(InquiryRepository inquiryRepository, InquiryMapper inquiryMapper) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryMapper = inquiryMapper;
    }

    @Override
    public List<Inquiry> getAllInquiries() {
        List<InquiryEntity> inquiryEntities = inquiryRepository.findAll();
        List<Inquiry> inquiries = inquiryMapper.toDtos(inquiryEntities);
        return inquiries;
    }

    @Override
    public List<Inquiry> getAllInquiriesByCustomerName(String customerName) {
        List<InquiryEntity> inquiryEntities = inquiryRepository.findAllByCustomerName(customerName);
        List<Inquiry> inquiries = inquiryMapper.toDtos(inquiryEntities);
        return inquiries;
    }
}
