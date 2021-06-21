package com.akil.services.apigateway.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class ApiConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty
    private String port;
}
