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
import org.vadtel.support.exception.DaoException;
import org.vadtel.support.service.InquiryService;
import org.vadtel.support.service.TopicService;
import org.vadtel.support.service.mapper.InquiryAttributeMapper;
import org.vadtel.support.service.mapper.InquiryMapper;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        List<InquiryEntity> inquiryEntities = null;
        try {
            inquiryEntities = inquiryRepository.findAllByCustomerName(customerName);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        List<Inquiry> inquiries = inquiryMapper.toDtos(inquiryEntities);
        return inquiries;
    }

    @Override
    public Inquiry getInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId) {
        InquiryEntity inquiryEntity = null;
        try {
            inquiryEntity = inquiryRepository.findByCustomerNameAndId(customerName, inquiryId);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        Inquiry inquiry = inquiryMapper.toDto(inquiryEntity);
        return inquiry;
    }

    @Override
    public Inquiry createInquiry(Inquiry inquiry) {
        TopicEntity topicEntity = topicService.findByNameOrCreate(inquiry.getTopicName());
        InquiryEntity save = null;
        try {
            InquiryEntity inquiryEntity = inquiryMapper.toEntity(inquiry);
            inquiryEntity.setTopicEntity(topicEntity);

            Optional.ofNullable(inquiryEntity.getAttributeEntityList())
                    .ifPresent(l -> l.forEach(x -> x.setInquiryEntity(inquiryEntity)));

            save = inquiryRepository.save(inquiryEntity);
        } catch (Exception e) {
            throw new DaoException(e);
        }

        Inquiry saveEntity = inquiryMapper.toDto(save);
        return saveEntity;
    }

    @Override
    public void deleteInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId) {
        try {
            inquiryRepository.deleteByCustomerNameAndId(customerName, inquiryId);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Inquiry getAndUpdateInquiryByCustomerNameAndInquiryId(Inquiry sourceInquiry,
                                                                 String customerName,
                                                                 Long inquiryId) {
        InquiryEntity targetInquiryEntity = null;
        try {
            targetInquiryEntity = inquiryRepository.findByCustomerNameAndId(customerName, inquiryId);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        updateInquiryEntity(sourceInquiry, targetInquiryEntity);

        targetInquiryEntity.getAttributeEntityList().add(
                new InquiryAttributeEntity("Update inquiry",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                        targetInquiryEntity));
        InquiryEntity save = null;
        try {
            save = inquiryRepository.save(targetInquiryEntity);
        } catch (Exception e) {
            throw new DaoException(e);
        }

        Inquiry inquiry = inquiryMapper.toDto(save);
        return inquiry;
    }

    private void updateInquiryEntity(Inquiry source, InquiryEntity target) {
        target.setDescription(source.getDescription());
        List<InquiryAttribute> sourceAttributes = source.getAttributeEntityList();
        List<InquiryAttributeEntity> targetAttributes = target.getAttributeEntityList();
        List<InquiryAttribute> attributes = new ArrayList<>();

        if (targetAttributes != null && sourceAttributes != null) {
            targetAttributes.forEach(tar -> sourceAttributes
                    .forEach(sor -> {
                        if (tar.getName().equals(sor.getName())) {
                            tar.setValue(sor.getValue());
                        }
                    }));

            attributes = sourceAttributes
                    .stream()
                    .filter(sour -> targetAttributes
                            .stream()
                            .noneMatch(tar -> tar.getName().equals(sour.getName())))
                    .collect(Collectors.toList());
        }

        targetAttributes.addAll(inquiryAttributeMapper.toEntities(attributes));
        targetAttributes.forEach(x -> x.setInquiryEntity(target));
    }
}
