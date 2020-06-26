package com.segment.domain.model;

/**
 * A simple POJO class to track customer context
 *
 * @author Jaymin Desai
 */
public class Context {
    private final String customerId;
    private final String planId;

    public Context(String customerId, String planId) {
        this.customerId = customerId;
        this.planId = planId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPlanId() {
        return planId;
    }

    @Override
    public String toString() {
        return "Context{" + "customerId='" + customerId + '\'' + ", planId='" + planId + '\'' + '}';
    }
}
