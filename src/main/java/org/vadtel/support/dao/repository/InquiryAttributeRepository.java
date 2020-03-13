package org.vadtel.support.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vadtel.support.entity.InquiryAttributeEntity;

@Repository
public interface InquiryAttributeRepository extends JpaRepository<InquiryAttributeEntity, Long> {
}
