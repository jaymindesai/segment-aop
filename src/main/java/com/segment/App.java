package com.segment;

import java.util.stream.IntStream;

import com.segment.domain.model.Context;
import com.segment.domain.SegmentTracker;
import com.segment.resource.Endpoints;


/**
 * Driver class
 *
 * @author Jaymin Desai
 */
public class App {
    private static final String SEGMENT_WRITE_KEY = "<YOUR-SEGMENT-WRITE-KEY>";

    public static void main(String[] args) throws InterruptedException {
        SegmentTracker.initialize(SEGMENT_WRITE_KEY);

        // Make 5 calls per customer to different endpoints. You should only see events from
        // customer-2 and customer-3 on your segment dashboard since only the new feature endpoint
        // is being tracked (annotated with @Tracked).
        IntStream.range(0, 5).forEach(__ -> {
            Endpoints.existingFeature(new Context("customer-1", "basic"), "existing-feature");
            Endpoints.newFeature(new Context("customer-2", "basic"), "new-feature");
            Endpoints.newFeature(new Context("customer-3", "premium"), "new-feature");
        });

        SegmentTracker.shutdown();
    }
}
