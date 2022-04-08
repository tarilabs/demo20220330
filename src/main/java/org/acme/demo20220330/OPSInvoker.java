package org.acme.demo20220330;

import java.util.Arrays;
import java.util.List;

import com.ibm.decision.ops.client.api.RunApi;
import com.ibm.decision.ops.client.model.AnyOfintegernumberstringboolean;
import com.ibm.decision.ops.client.model.Link;
import com.ibm.decision.ops.client.model.Parameter;
import com.ibm.decision.ops.client.model.Prediction;
import com.ibm.decision.ops.client.model.PredictionResponse;

import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OPSInvoker {

    private static final Logger LOG = LoggerFactory.getLogger(OPSInvoker.class);

    public static Number loanScore(Number creditScore, Number income, Number loanAmount, Number monthDuration, Number rate) {
        try {
            RunApi api = new RunApi();
            String baseUrl = ConfigProvider.getConfig().getValue("ibm.ops.url", String.class);
            LOG.debug("baseUrl {}", baseUrl);
            api.setCustomBaseUrl(baseUrl);
            Prediction prediction = new Prediction();
            Link link = new Link();
            link.setRel("endpoint");
            link.setHref("/endpoints/4");
            prediction.setTarget(Arrays.asList(link));
            prediction.setParameters(Arrays.asList(
                param("creditScore", creditScore),
                param("income", income),
                param("loanAmount", loanAmount),
                param("monthDuration", monthDuration),
                param("rate", rate)
            ));
            PredictionResponse result = api.prediction(prediction);
            @SuppressWarnings("unchecked")
            List<Number> scored = (List<Number>) result.getResult().get("scores");
            return scored.get(1);
        } catch (Throwable e) {
            LOG.warn("Problem invoking OPS via OPS Client Java SDK", e);
            return null;
        }
    }

    private static Parameter param(String name, Number value) {
        Parameter p = new Parameter();
        p.setName(name);
        p.setValue(new AnyOfintegernumberstringboolean(value.doubleValue()));
        return p;
    }
}
