# Segment AOP

This is a template Gradle project for annotation based AOP using vanilla AspectJ. It uses AspectJ runtime to weave the aspects in a post-compile fashion and doesn't require Spring or any other fat Java library. It uses [Segment](https://segment.com/) API to demonstrate annotation based AOP.

Segment is a platform that collects user events from mobile and web applications to analyze customer behavior. The basic idea is that we there is a new feature being introduced to the existing product and we want to the response of our existing customers. This new feature is exposed as a [REST endpoint](src/main/java/com/segment/resource/Endpoints.java#L14-L23).

To run this app end-to-end, you need to have an account on Segment Analytics. Once you have that, replace this [placeholder](src/main/java/com/segment/App.java#L16) with your Segment write key and run the gradle task as follows: 

For UNIX
```shell
./gradlew run
```

For Windows
```shell
gradlew.bat run
```
