package org.vadtel.support.dao.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vadtel.support.config.ApplicationContextConfig;
import org.vadtel.support.dao.config.RepositoryConfiguration;
import org.vadtel.support.entity.InquiryEntity;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
@ContextConfiguration(
        classes = {ApplicationContextConfig.class},
        loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
class InquiryRepositoryTest {
    public static final String NAME = "Holmes";
    public static final Long ID = 1L;

    @Autowired
    InquiryRepository inquiryRepository;

    @Test
    void findAllByCustomerName() {
        List<InquiryEntity> allByCustomerName = inquiryRepository.findAllByCustomerName(NAME);
        assertNotNull(allByCustomerName);
        assertTrue(() -> allByCustomerName.stream()
                .map(InquiryEntity::getCustomerName)
                .allMatch(x -> x.equals(NAME)
                )
        );
    }

    @Test
    void findByCustomerNameAndId() {
        InquiryEntity byCustomerNameAndId = inquiryRepository.findByCustomerNameAndId(NAME, ID);
        assertNotNull(byCustomerNameAndId);
        assertAll(
                () -> assertEquals(ID, byCustomerNameAndId.getId()),
                () -> assertEquals(NAME, byCustomerNameAndId.getCustomerName())
        );
    }

    @Test
    void deleteByCustomerNameAndId() {
        inquiryRepository.deleteByCustomerNameAndId(NAME, ID);
        InquiryEntity byCustomerNameAndId = inquiryRepository.findByCustomerNameAndId(NAME, ID);
        assertNull(byCustomerNameAndId);
    }
}