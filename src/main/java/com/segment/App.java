package com.segment;

import java.util.stream.IntStream;

import com.segment.domain.model.Context;
import com.segment.domain.SegmentTracker;
import com.segment.resource.Endpoints;
import com.segment.utils.PropUtils;

import static com.segment.utils.AppConstants.SEGMENT_WRITE_KEY_PROPERTY;


/**
 * Driver class
 *
 * @author Jaymin Desai
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        SegmentTracker.initialize(PropUtils.getProperty(SEGMENT_WRITE_KEY_PROPERTY));

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
