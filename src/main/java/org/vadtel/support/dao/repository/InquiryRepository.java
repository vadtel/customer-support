package org.vadtel.support.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.entity.InquiryEntity;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    List<InquiryEntity> findAllByCustomerName(String customerName);
}
