package org.acme.demo20220330;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/predictions")
@RegisterRestClient
public interface OPSClient {

    @POST
    OPSResponse predictions(OPSRequest request);
}
