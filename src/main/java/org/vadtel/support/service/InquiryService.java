package org.vadtel.support.service;

import org.vadtel.support.dto.Inquiry;

import java.util.List;

public interface InquiryService {

    List<Inquiry> getAllInquiriesByCustomerName(String customerName);

    Inquiry getInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId);

    Inquiry createInquiry(Inquiry inquiry);


    void deleteInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId);

    Inquiry getAndUpdateInquiryByCustomerNameAndInquiryId(Inquiry sourceInquiry,
                                                          String customerName,
                                                          Long inquiryId);
}
