package org.acme.demo20220330;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.arc.Unremovable;

/**
 * Needed for CDI lookup programmatically from static method; alternatively, consider using JDK11 httpclient directly in the static method?
 */
@Singleton
@Unremovable
public class OPSInvokerBean {
    @Inject
    @RestClient
    OPSClient myClient;

    public OPSResponse predict(OPSRequest in) {
        return myClient.predictions(in);
    }
}
