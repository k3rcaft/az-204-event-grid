package com.k3rcaft.disneyapi.services;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.BinaryData;
import com.azure.messaging.eventgrid.EventGridEvent;
import com.azure.messaging.eventgrid.EventGridPublisherClient;
import com.azure.messaging.eventgrid.EventGridPublisherClientBuilder;
import com.k3rcaft.disneyapi.models.Movie;
import com.k3rcaft.disneyapi.models.MovieType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Value("${EVENT_GRID_END_POINT}")
    private String endpoint;
    @Value("${EVENT_GRID_ACCESS_KEY}")
    private String accessKey;

    @Override
    public EventGridPublisherClient<EventGridEvent> getEventGridPublisherClient() {
        return new EventGridPublisherClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(accessKey))
                .buildEventGridEventPublisherClient();
    }

    @Override
    public void pushEvent(Movie movie) {
        var events = new ArrayList<EventGridEvent>();
        events.add(new EventGridEvent("New movie is published", movie.getType().toString(), BinaryData.fromObject(movie), "0.1"));
        getEventGridPublisherClient().sendEvents(events);
    }

    @Override
    public void pushEventBatch(List<Movie> movies) {
        for (var movie : movies) {
            pushEvent(movie);
        }
    }

    @Override
    public List<Movie> newMovies() {
        return Arrays.asList(
                new Movie(1, "Avenger", MovieType.LIVE_ACTION),
                new Movie(2, "The Little Mermaid", MovieType.ANIMATED),
                new Movie(3, "Frank and Ollie", MovieType.OTHER_DOCUMENTARY),
                new Movie(4, "Secrets of Life", MovieType.TRUE_LIFE_ADVENTURES));
    }
}
