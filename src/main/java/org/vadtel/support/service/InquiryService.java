package org.vadtel.support.service;

import org.vadtel.support.dto.Inquiry;

import java.util.List;

public interface InquiryService {

    List<Inquiry> getAllInquiriesByCustomerName(String customerName);

    Inquiry getInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId);

    Inquiry createInquiry(Inquiry inquiry);


    void getAndUpdateInquiryByCustomerNameAndInquiryId(String customerName,
                                                          Long inquiryId,
                                                          Inquiry inquiry);
}
