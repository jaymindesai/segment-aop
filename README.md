# Segment AOP

This is a template project to demonstrate annotation based AOP using vanilla AspectJ. It uses AspectJ runtime to weave the aspects in a post-compile fashion and doesn't require Spring or any other fat Java library.

## Basic Idea

Imagine that there is a new feature being introduced to the existing product and we want to analyze the response of our customers. The new feature, in this case is exposed as a [REST endpoint](src/main/java/com/segment/resource/Endpoints.java#L14-L23) and we want to track activity of customers calling this endpoint. We have different types of customers [Basic, Advanced, Premium] and want to know which customer segment is more responsive to this newly introduced feature. We will use [Segment](https://segment.com/) to achieve this. Segment is a platform that collects user events from mobile and web applications to analyze customer behavior.

### Application

To run this app end-to-end, you need to have an account on Segment Analytics. Once you have that, replace this [placeholder](src/main/java/com/segment/App.java#L16) with your Segment write key and run the gradle task as follows. You'll be able to see the events being triggered on the Segment dashboard. 

For UNIX
```shell
./gradlew run
```

For Windows
```shell
gradlew.bat run
```
