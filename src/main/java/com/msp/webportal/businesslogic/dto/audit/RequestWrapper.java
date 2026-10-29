package com.msp.webportal.businesslogic.dto.audit;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * This class is used to provide response for Base response.
 *
 * @author Ajith
 */

@Data
public class RequestWrapper<T> {

    /**
     * Variable to hold id
     */
    private String id;

    /**
     * Variable to hold id
     */
    private String version;

    /**
     * Variable to hold id
     */
    private String transactionID;

    /**
     * Variable to hold id
     */
    private String requestTime;

    /**
     * List to hold errors
     */
    private List<AuthError> errors;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String responseSessionKey;

    /**
     * List to hold response
     */
    private T request;

    private Map<String, Object> metadata;

}
