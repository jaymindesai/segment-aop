package com.segment.resource;

import com.segment.domain.aop.Tracked;
import com.segment.domain.model.Context;


/**
 * For the purpose of this POC - This class acts as a REST resource and the methods are HTTP endpoints.
 *
 * @author Jaymin Desai
 */
public class Endpoints {

    /**
     * Endpoint corresponding to newly introduced feature in Beta phase.
     * We want to track the activity on this endpoint to evaluate the new feature
     * @param context
     * @param event
     */
    @Tracked
    public static void newFeature(Context context, String event) {
        System.out.println(String.format("[TRACKED ENDPOINT] Incoming request for %s: %s", event, context));
    }

    /**
     * Endpoint corresponding to an existing feature
     * We don't care about the activity on this endpoint since it's already stable and being used by multiple customers
     * @param context
     * @param event
     */
    public static void existingFeature(Context context, String event) {
        System.out.println(String.format("[UNTRACKED ENDPOINT] Incoming request for %s: %s", event, context));
    }
}