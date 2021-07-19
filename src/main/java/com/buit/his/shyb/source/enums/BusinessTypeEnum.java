package com.buit.his.shyb.source.enums;

import java.util.HashMap;
import java.util.Map;

public enum BusinessTypeEnum {
    /**
     * 挂号
     */
    REGISTER("01101"),

    /**
     * 结算
     */
    SETTLEMENT("01301"),
    /**
     * 住院建档
     */
    CREATE_ARCHIVES("01102"),
    /**
     * 入院登记
     */
    REGISTER_IM("01103");

    private String value;

    BusinessTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public BusinessTypeEnum fromValue(String value){

        return ALL_ENTRIES.get(value);
    }

    private static final Map<String, BusinessTypeEnum> ALL_ENTRIES = new HashMap<>(BusinessTypeEnum.values().length);
    static {
        for(BusinessTypeEnum each : BusinessTypeEnum.values()){
            ALL_ENTRIES.put(each.value, each);
        }
    }

}
