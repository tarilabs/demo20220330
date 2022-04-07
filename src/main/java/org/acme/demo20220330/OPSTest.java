package org.acme.demo20220330;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class OPSTest {
    
    @GET
    public Number test() {
        return OPSInvoker.loanScore(200, 50000, 50000, 48, 2.8);
    }
}
