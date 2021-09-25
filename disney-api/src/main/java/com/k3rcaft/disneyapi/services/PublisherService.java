package com.k3rcaft.disneyapi.services;

import com.azure.messaging.eventgrid.EventGridEvent;
import com.azure.messaging.eventgrid.EventGridPublisherClient;
import com.k3rcaft.disneyapi.models.Movie;

import java.util.List;

public interface PublisherService {
    EventGridPublisherClient<EventGridEvent> getEventGridPublisherClient();

    void pushEvent(Movie movie);

    void pushEventBatch(List<Movie> movies);

    List<Movie> newMovies();
}
