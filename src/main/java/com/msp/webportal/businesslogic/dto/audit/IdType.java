package com.msp.webportal.businesslogic.dto.audit;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.xml.bind.annotation.XmlValue;
import java.util.EnumMap;

public enum IdType {

    /** The uin. */
    UIN("UIN"),
    /** The vid. */
    VID("VID"),
    /** The user id. */
    USER_ID("USERID");

    /**
     * Value that indicates that default id.
     */
    public static final IdType DEFAULT_ID_TYPE = IdType.UIN;

    /** The type. */
    private String type;

    /** The aliases map. */
    private static EnumMap<IdType, String> aliasesMap = new EnumMap<>(IdType.class);

    /**
     * construct enum with id-type.
     *
     * @param type id type
     */
    private IdType(String type) {
        this.type = type;
    }

    /**
     * get id-type.
     *
     * @return type
     */
    @JsonValue
    @XmlValue
    public String getType() {
        return type;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return getType();
    }




}

