package com.segment.domain;

import static com.segment.TestConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.segment.domain.model.Context;
import com.segment.domain.SegmentTracker;
import com.segment.analytics.Analytics;
import com.segment.analytics.messages.MessageBuilder;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ Analytics.class })
public class SegmentTrackerTest {

    private static final Analytics segmentClient = PowerMockito.mock(Analytics.class);
    private static final Analytics.Builder builder = mock(Analytics.Builder.class, RETURNS_DEEP_STUBS);

    @BeforeClass
    public static void beforeClass() {
        PowerMockito.mockStatic(Analytics.class);
        when(builder.flushQueueSize(SEGMENT_QUEUE_SIZE).build()).thenReturn(segmentClient);
        PowerMockito.when(Analytics.builder(SEGMENT_WRITE_KEY)).thenReturn(builder);
    }

    @Test
    public void should_initialize_segment_client() {
        // when
        SegmentTracker.initialize(SEGMENT_WRITE_KEY);

        // then
        ArgumentCaptor<String> writeKeyCaptor = ArgumentCaptor.forClass(String.class);
        PowerMockito.verifyStatic(times(1));
        Analytics.builder(writeKeyCaptor.capture());
        assertEquals(writeKeyCaptor.getValue(), SEGMENT_WRITE_KEY);
    }

    @Test
    public void should_enqueue_track_messages() {
        // given
        SegmentTracker.initialize(SEGMENT_WRITE_KEY);

        // when
        SegmentTracker.trackUsage(new Context(ANY_CUSTOMER, ANY_PLAN), ANY_EVENT);

        // then
        verify(segmentClient).enqueue(any(MessageBuilder.class));
    }

    @Test
    public void should_gracefully_shutdown_the_client() throws InterruptedException {
        // given
        SegmentTracker.initialize(SEGMENT_WRITE_KEY);

        // when
        SegmentTracker.shutdown();

        // then
        verify(segmentClient).flush();
        verify(segmentClient).shutdown();
    }
}