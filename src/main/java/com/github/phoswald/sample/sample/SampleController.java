package com.github.phoswald.sample.sample;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getSamplePage() {
        return sample.data("model", new SampleViewModel(sampleConfig));
    }
}
