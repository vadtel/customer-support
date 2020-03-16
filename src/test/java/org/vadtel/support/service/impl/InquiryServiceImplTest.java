package org.vadtel.support.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vadtel.support.config.ApplicationContextConfig;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.dto.InquiryAttribute;
import org.vadtel.support.service.InquiryService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
@ContextConfiguration(
        classes = {ApplicationContextConfig.class})
@WebAppConfiguration
class InquiryServiceImplTest {

    public static final String NAME = "Dima";
    public static final Long ID = 5L;

    @Autowired
    InquiryService inquiryService;


    @Test
    void getAllInquiriesByCustomerName() {
        List<Inquiry> allInquiriesByCustomerName = inquiryService.getAllInquiriesByCustomerName(NAME);
        assertNotNull(allInquiriesByCustomerName);
        System.out.println(allInquiriesByCustomerName);
        allInquiriesByCustomerName.stream()
                .map(Inquiry::getCustomerName)
                .forEach(x -> assertEquals(NAME, x));

    }

    @Test
    void getInquiryByCustomerNameAndInquiryId() throws ParseException {

        Inquiry inquiry = new Inquiry();
        inquiry.setId(5L);
        inquiry.setTopicName("Other");
        inquiry.setDescription("How do I cancel my contract");
        inquiry.setCreateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-03-05 17:52:00"));
        inquiry.setCustomerName("Dima");
        inquiry.setAttributeEntityList(Arrays.asList(new InquiryAttribute(
                6L,
                "Contract number",
                "123/78-09B"
        )));
        Inquiry dima = inquiryService.getInquiryByCustomerNameAndInquiryId("Dima", 5L);
        assertEquals(inquiry, dima);

    }

    @Test
    void createInquiry() throws ParseException {
        Inquiry inquiry = new Inquiry();
        inquiry.setTopicName("Other");
        inquiry.setDescription("Its my test Inquiry");
        inquiry.setCreateDate(new Date());
        inquiry.setCustomerName("TestName");
        Inquiry savedInquiry = inquiryService.createInquiry(inquiry);
        Inquiry test = inquiryService.getInquiryByCustomerNameAndInquiryId("TestName", savedInquiry.getId());
        assertAll(
                () -> assertEquals("TestName", test.getCustomerName()),
                () -> assertEquals("Its my test Inquiry", test.getDescription()),
                () -> assertEquals("Other", test.getTopicName()),
                () -> assertNull(test.getAttributeEntityList())
        );
    }

    @Test
    void deleteInquiryByCustomerNameAndInquiryId() {
        inquiryService.deleteInquiryByCustomerNameAndInquiryId(NAME, ID);
        Inquiry inquiryByCustomerNameAndInquiryId = inquiryService.getInquiryByCustomerNameAndInquiryId(NAME, ID);
        assertNull(inquiryByCustomerNameAndInquiryId);
    }

    @Test
    void getAndUpdateInquiryByCustomerNameAndInquiryId() {
        Inquiry inquiry = new Inquiry();
        inquiry.setDescription("Its my test Inquiry");
        Inquiry updatedInquiry = inquiryService.getAndUpdateInquiryByCustomerNameAndInquiryId(inquiry, NAME, ID);
        assertAll(
                () -> assertEquals("Its my test Inquiry", updatedInquiry.getDescription()),
                () -> assertEquals(NAME, updatedInquiry.getCustomerName()),
                () -> assertEquals(ID, updatedInquiry.getId())
        );

        Inquiry testUpdatedInquiry = inquiryService.getInquiryByCustomerNameAndInquiryId(NAME, ID);
        assertEquals("Its my test Inquiry", testUpdatedInquiry.getDescription());

    }
}