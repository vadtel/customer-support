package org.vadtel.support.service;

import org.vadtel.support.dto.Inquiry;

import java.util.List;

public interface InquiryService {
    List<Inquiry> getAllInquiries();
    List<Inquiry> getAllInquiriesByCustomerName(String customerName);

}
