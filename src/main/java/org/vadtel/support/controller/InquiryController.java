package org.vadtel.support.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.service.InquiryService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("customers/{customerName}/inquiries")
    public ResponseEntity<List<Inquiry>> getAllInquiriesByCustomerName(
                                                                @PathVariable("customerName") String customerName) {
        List<Inquiry> inquiries = inquiryService.getAllInquiriesByCustomerName(customerName);
        ResponseEntity<List<Inquiry>> response = new ResponseEntity<>(inquiries, HttpStatus.OK);

        return response;
    }

    @GetMapping("customers/{customerName}/inquiries/{inquiryId}")
    public ResponseEntity<Inquiry> getInquiryByCustomerNameAndInquiryId(
                                                                @PathVariable("customerName") String customerName,
                                                                @PathVariable("inquiryId") Long inquiryId){
        Inquiry inquiry = inquiryService.getInquiryByCustomerNameAndInquiryId(customerName, inquiryId);
        ResponseEntity<Inquiry> response = new ResponseEntity<>(inquiry, HttpStatus.OK);

        return response;
    }

    @PostMapping("/customers/{customerName}/inquiries")
    public ResponseEntity<Inquiry> createInquiry(@RequestBody Inquiry inquiry, @PathVariable("customerName") String customerName){
        inquiry.setCreateDate(new Date());
        inquiry.setCustomerName(customerName);
        Inquiry createdInquiry = inquiryService.createInquiry(inquiry);

        ResponseEntity<Inquiry> response = new ResponseEntity<>(createdInquiry, HttpStatus.OK);
        return response;

    }
}
