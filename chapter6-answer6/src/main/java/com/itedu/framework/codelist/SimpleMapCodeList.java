package com.itedu.framework.codelist;

import java.util.Collections;
import java.util.Map;

public class SimpleMapCodeList extends AbstractCodeList {

    private Map<String, String> map;

    @Override
    public Map<String, String> asMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = Collections.unmodifiableMap(map);
    }


}
