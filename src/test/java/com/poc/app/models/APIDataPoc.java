package com.poc.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIDataPoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("api")
    private String api;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("path")
    private String path;
}
