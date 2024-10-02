package com.github.phoswald.sample.sample;

import java.security.Principal;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@RequestScoped
@Path("/pages/sample")
public class SampleController {

    @Inject
    Template sample;

    @Inject
    @ConfigProperty(name = "app.sample.config")
    String sampleConfig;

    @Inject
    Principal principal;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getSamplePage() {
        return sample.data("model", new SampleViewModel(sampleConfig, principal.getName()));
    }
}
