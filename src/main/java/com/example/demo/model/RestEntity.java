package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author haocun li
 * @date created in 2018/10/24 23:42
 * @since 1.0.0
 */

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestEntity implements Serializable {

    private static final long serialVersionUID = 3550224421750657701L;

    /**
     * [M] condition code
     */
    @JsonProperty("code")
    private Integer code;

    /**
     * [M] error message
     */
    @JsonProperty("msg")
    private String msg;

    /**
     * [C] username
     */
    @JsonProperty("username")
    private String username;

    /**
     * [C] role
     */
    @JsonProperty("role")
    private String role;

    /**
     * [C] details
     */
    @JsonProperty("details")
    private Object details;

}