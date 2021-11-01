package io.github.turskyi.domain.entities.enums;

@SuppressWarnings("unused")
public enum Band {
    SENSE_OF_SILENCE("відчуття.тиші"),
    ZIGMUND_AFRAID("Zigmund Afraid");

    public final String name;

    Band(String name) {
        this.name = name;
    }
}
