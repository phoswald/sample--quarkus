package com.github.phoswald.sample.security;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
