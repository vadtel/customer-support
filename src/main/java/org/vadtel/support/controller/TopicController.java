package org.vadtel.support.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TopicController {
    //TODO add log
    //TODO add custom api exception
    //TODO add validation

    private final TopicService topicService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Topic>> getAllTopics() {
        log.debug("getAllTopics() is executed");

        List<Topic> topics = topicService.getAllTopics();
        ResponseEntity<List<Topic>> response;
        if (topics == null || topics.isEmpty()) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(topics, HttpStatus.OK);
        }

        return response;
    }


}
