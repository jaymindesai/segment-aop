package com.segment.domain.aop;

import static org.junit.Assert.*;
import static com.segment.TestConstants.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

import com.segment.domain.model.Context;
import com.segment.domain.SegmentTracker;
import com.segment.domain.aop.TrackedEndpointAspect;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SegmentTracker.class)
public class TrackedEndpointAspectTest {

    private TrackedEndpointAspect aspect;
    private final ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);

    @BeforeClass
    public static void beforeClass() {
        PowerMockito.mockStatic(SegmentTracker.class);
    }

    @Before
    public void setUp() {
        aspect = new TrackedEndpointAspect();
        Object[] args = { new Context(ANY_CUSTOMER, ANY_PLAN), ANY_EVENT };
        when(proceedingJoinPoint.getArgs()).thenReturn(args);
    }

    @Test
    public void should_execute_around_advice_correctly() throws Throwable {
        // when
        aspect.around(proceedingJoinPoint);

        // then
        verify(proceedingJoinPoint, times(1)).proceed(); // proceed() called only once
        verify(proceedingJoinPoint, never()).proceed(null); // proceed(Object[] args) called never

        ArgumentCaptor<Context> contextCaptor = ArgumentCaptor.forClass(Context.class);
        ArgumentCaptor<String> eventCaptor = ArgumentCaptor.forClass(String.class);
        PowerMockito.verifyStatic(times(1)); // verify following behavior happened only once
        SegmentTracker.trackUsage(contextCaptor.capture(), eventCaptor.capture());

        assertEquals(contextCaptor.getValue().getCustomerId(), ANY_CUSTOMER);
        assertEquals(contextCaptor.getValue().getPlanId(), ANY_PLAN);
        assertEquals(eventCaptor.getValue(), ANY_EVENT);
    }
}