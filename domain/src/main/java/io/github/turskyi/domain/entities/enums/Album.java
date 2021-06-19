package io.github.turskyi.domain.entities.enums;

public enum Album {
    SENSE_OF_SILENCE_LP("Відчуття.Тиші LP"),
    CRIME("Злочин"),
    ZOMBI("Зомбі"),
    ZIGMUND_AFRAID("Zigmund Afraid");

    public final String name;

    Album(String name) {
        this.name = name;
    }
}
