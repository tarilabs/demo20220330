package org.acme.demo20220330;

import java.util.List;

public class OPSRequest {
    private List<Target> target;
    private List<Parameter> parameters;
    public List<Target> getTarget() {
        return target;
    }
    public void setTarget(List<Target> target) {
        this.target = target;
    }
    public List<Parameter> getParameters() {
        return parameters;
    }
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    
}
