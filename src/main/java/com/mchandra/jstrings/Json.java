package com.mchandra.jstrings;

import lombok.NonNull;
import lombok.Value;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public interface Json {

    /**
     * @return an instance of JObj
     */
    static JObj create() {
        return new JObj(new LinkedHashMap<>());
    }

    /**
     * @return string representation of Json object
     */
    String string();

    @Value(staticConstructor = "of")
    class JList implements Json {
        private final List<Json> elems;

        public static JList of(Json... jsons) {
            return JList.of(Arrays.asList(jsons));
        }

        public static JList of(String... strs) {
            return JList.of(
                    Arrays.stream(strs).map(JStr::of).collect(toList())
            );
        }

        @Override
        public String string() {
            return elems.stream()
                    .map(Json::string)
                    .collect(joining(", ", "[", "]"));
        }
    }

    @Value(staticConstructor = "of")
    class JObj implements Json {
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

    @Value(staticConstructor = "of")
    class JNum implements Json {
        private final Double num;

        @Override
        public String string() {
            return num.toString();
        }
    }

    @Value(staticConstructor = "of")
    class JStr implements Json {
        private final String str;

        @Override
        public String string() {
            return '\"' + str + '\"';
        }
    }

    @Value(staticConstructor = "of")
    class JBool implements Json {
        private final boolean b;

        @Override
        public String string() {
            return Boolean.toString(b);
        }
    }
}

