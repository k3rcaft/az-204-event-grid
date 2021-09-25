package com.k3rcaft.disneyapi.controllers;

import com.k3rcaft.disneyapi.services.PublisherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private final PublisherService publisherService;


    public MovieController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/push-event")
    public String pushEvent() {
        publisherService.pushEventBatch(publisherService.newMovies());
        return "pushed";
    }
}
