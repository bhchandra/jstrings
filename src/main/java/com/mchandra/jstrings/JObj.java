package com.mchandra.jstrings;


import lombok.NonNull;
import lombok.Value;

import java.util.Map;

import static java.util.stream.Collectors.joining;

@Value(staticConstructor = "of")
public class JObj implements Json {
    private final Map<String, Json> bindings;

    public JObj add(@NonNull String key, @NonNull String value) {
        return add(key, JStr.of(value));
    }

    public JObj add(@NonNull String key, boolean value) {
        return add(key, JBool.of(value));
    }

    public JObj add(@NonNull String key, @NonNull Json value) {
        bindings.put(key, value);
        return this;
    }

    @Override
    public String string() {
        return bindings.entrySet().stream()
                .map(e -> "\"" + e.getKey() + "\": " + e.getValue().string())
                .collect(joining(", ", "{", "}"));
    }
}