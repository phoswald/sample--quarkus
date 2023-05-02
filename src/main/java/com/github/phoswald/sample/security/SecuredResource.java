package com.github.phoswald.sample.security;

import java.security.Principal;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Path("/rest/secured")
public class SecuredResource {

	@Inject
	Principal principal;

    @GET
    @Path("/me")
    @RolesAllowed("user")
    @Produces(MediaType.TEXT_PLAIN)
    public String me() {
        return (principal == null ? "<unknown" : principal.getName()) + "\n";
    }
}
