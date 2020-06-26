package com.segment.domain;

import java.util.Collections;

import com.segment.domain.model.Context;
import com.segment.analytics.Analytics;
import com.segment.analytics.messages.TrackMessage;


/**
 * A wrapper class for Segment Analytics client for any additional processing
 *
 * @author Jaymin Desai
 */
public class SegmentTracker {
    private static Analytics segmentClient;

    public static void initialize(String writeKey) {
        if (segmentClient == null) {
            segmentClient = Analytics.builder(writeKey).flushQueueSize(1).build();
        }
    }

    public static void trackUsage(Context context, String event) {
        segmentClient.enqueue(TrackMessage.builder(event)
                                          .userId(context.getCustomerId())
                                          .properties(Collections.singletonMap("planId", context.getPlanId())));
    }

    public static void shutdown() throws InterruptedException {
        segmentClient.flush();
        Thread.sleep(10);
        segmentClient.shutdown();
    }
}
