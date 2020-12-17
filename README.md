# Segment AOP

This template project is an attempt to demonstrate annotation based AOP in a minimalist way using vanilla AspectJ. It uses AspectJ runtime to weave the aspects in a post-compile fashion and doesn't require Spring or a similar Java library.

## Basic Idea

**Scenario**: There is a new feature being added to our product and we want to analyze the response of our users.

**Usage**: This new feature can be used via a [REST endpoint](src/main/java/com/segment/resource/Endpoints.java#L14-L23)

**Tracking**: We want to track activity of requests hitting this endpoint. We have different types of users, for example: Basic, Advanced, Premium, etc. and we want to know which user segment is more responsive to this newly introduced feature. We will use [Segment.io](https://segment.com/), a platform that collects user events from mobile and web applications to analyze customer behavior to achieve this.


### Application

To run this app end-to-end, you will need an account on [Segment.io](https://segment.com/). Once you have that, set an environment variable `SEGMENT_WRITE_KEY` with value as your Segment write key and run the gradle task as follows. You should be able to see the events triggered on your Segment dashboard. 

- UNIX
  ```shell
  ./gradlew run
  ```

- Windows
  ```shell
  gradlew.bat run
  ```
