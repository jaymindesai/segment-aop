package com.segment.domain.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.segment.domain.SegmentTracker;
import com.segment.domain.model.Context;


/**
 * Handler aspect for @Tracked annotation
 *
 * @author Jaymin Desai
 */
@Aspect
public class TrackedEndpointAspect {

    @Around("execution(* *(..)) && @annotation(com.segment.domain.aop.Tracked)")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        trackUsage(pjp.getArgs());
    }

    private void trackUsage(Object[] args) {
        Context context = (Context) args[0];
        String event = (String) args[1];
        SegmentTracker.trackUsage(context, event);
    }
}
