package com.k3rcaft.disneyapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MovieType {

    OTHER_DOCUMENTARY("Other documentary films"),
    ANIMATED("Animated films"),
    LIVE_ACTION("Live-action films"),
    TRUE_LIFE_ADVENTURES("True-Life Adventures ");

    private String type;
}
