package org.vadtel.support.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vadtel.support.dto.Topic;
import org.vadtel.support.service.TopicService;

import java.util.List;

@RestController
@RequestMapping(value = "topics",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @GetMapping(value = "/")
    public ResponseEntity<List<Topic>> getAllTopics(){
        List<Topic> topics = topicService.getAllTopics();
        ResponseEntity<List<Topic>> response = new ResponseEntity<>(topics, HttpStatus.OK);

        return response;
    }




}
