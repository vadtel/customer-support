package org.vadtel.support.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vadtel.support.dto.Inquiry;
import org.vadtel.support.service.InquiryService;

import java.util.List;

@RestController
@RequestMapping(value = "customers",
produces = MediaType.APPLICATION_JSON_VALUE,
consumes = MediaType.APPLICATION_JSON_VALUE)
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("{customerName}/inquiries")
    public ResponseEntity<List<Inquiry>> getAllInquiriesByCustomerName(@PathVariable("customerName") String customerName){
        List<Inquiry> inquiries = inquiryService.getAllInquiriesByCustomerName(customerName);
        ResponseEntity<List<Inquiry>> response = new ResponseEntity<>(inquiries, HttpStatus.OK);

        return response;
    }
}
