package org.acme.demo20220330;

import java.util.Arrays;

import javax.enterprise.inject.spi.CDI;

public class OPSInvoker {

    public static Number loanScore(Number creditScore, Number income, Number loanAmount, Number monthDuration, Number rate) {
        OPSRequest req = new OPSRequest();
        Target target = new Target();
        target.setRel("endpoint");
        target.setHref("/endpoints/4");
        req.setTarget(Arrays.asList(target));
        req.setParameters(Arrays.asList(
            param("creditScore", creditScore),
            param("income", income),
            param("loanAmount", loanAmount),
            param("monthDuration", monthDuration),
            param("rate", rate)
        ));
        OPSResponse res = predict(req);
        return res.getResult().getScores().get(1);
    }

    private static Parameter param(String name, Object value) {
        Parameter p = new Parameter();
        p.setName(name);
        p.setValue(value);
        return p;
    }
    
    public static OPSResponse predict(OPSRequest payload) {
        OPSInvokerBean myInvokerBean = CDI.current().select(OPSInvokerBean.class).get();
        return myInvokerBean.predict(payload);
    }
}
