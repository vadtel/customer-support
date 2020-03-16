package org.vadtel.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InquiryController {
    //TODO add log
    //TODO add custom api exception
    //TODO add validation

    private final InquiryService inquiryService;


    @GetMapping("customers/{customerName}/inquiries")
    public ResponseEntity<List<Inquiry>> getAllInquiriesByCustomerName(
            @PathVariable("customerName") String customerName) {

        List<Inquiry> inquiries = inquiryService.getAllInquiriesByCustomerName(customerName);

        ResponseEntity<List<Inquiry>> response;
        if (inquiries == null || inquiries.isEmpty()) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(inquiries, HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("customers/{customerName}/inquiries/{inquiryId}")
    public ResponseEntity<Inquiry> getInquiryByCustomerNameAndInquiryId(@PathVariable("customerName") String customerName,
                                                                        @PathVariable("inquiryId") Long inquiryId) {
        Inquiry inquiry = inquiryService.getInquiryByCustomerNameAndInquiryId(customerName, inquiryId);

        ResponseEntity<Inquiry> response;
        if (inquiry == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(inquiry, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/customers/{customerName}/inquiries")
    public ResponseEntity<Inquiry> createInquiry(@RequestBody Inquiry inquiry,
                                                 @PathVariable("customerName") String customerName) {
        customerName = customerName.substring(0, 1).toUpperCase() + customerName.substring(1).toLowerCase();
        inquiry.setCreateDate(new Date());
        inquiry.setCustomerName(customerName);
        Inquiry createdInquiry = inquiryService.createInquiry(inquiry);
        ResponseEntity<Inquiry> response;

        if (createdInquiry == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } else {
            response = new ResponseEntity<>(createdInquiry, HttpStatus.OK);
        }
        return response;
    }

    @PutMapping("/customers/{customerName}/inquiries/{inquiryId}")
    public ResponseEntity<Inquiry> undateInquiry(@RequestBody Inquiry inquiry,
                                                 @PathVariable("customerName") String customerName,
                                                 @PathVariable("inquiryId") Long inquiryId) {
        customerName = customerName.substring(0, 1).toUpperCase() + customerName.substring(1).toLowerCase();
        Inquiry updatedInquiry = inquiryService.getAndUpdateInquiryByCustomerNameAndInquiryId(inquiry, customerName, inquiryId);
        ;
        ResponseEntity<Inquiry> response;

        if (updatedInquiry == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } else {
            response = new ResponseEntity<>(updatedInquiry, HttpStatus.OK);
        }
        return response;
    }

    @DeleteMapping("customers/{customerName}/inquiries/{inquiryId}")
    public ResponseEntity<Void> deleteInquiryByCustomerNameAndInquiryId(@PathVariable("customerName") String customerName,
                                                                        @PathVariable("inquiryId") Long inquiryId) {
        inquiryService.deleteInquiryByCustomerNameAndInquiryId(customerName, inquiryId);

        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        return response;
    }


}
