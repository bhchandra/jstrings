package com.mchandra.jstrings;

import java.util.LinkedHashMap;


public interface Json {

    /**
     * @return an instance of JObj
     */
    static JObj create() {
        return JObj.of(new LinkedHashMap<>());
    }

    /**
     * @return string representation of Json object
     */
    String string();

}

