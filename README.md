
# Triangle Classifier Application - README

This application contains an endpoint which classifies triangles based on the lengths of their three sides. It can classify triangles as EQUILATERAL, ISOSCELES, or SCALENE.

The endpoint can be accessed as follows after running the application:
http://localhost:8080/triangle?a=10&b=10&c=14
## Features
- Classify a triangle given the lengths of its sides.
- Provides error handling for invalid input values.
- Full test coverage.
- Technologies used: Java, SpringBoot, Gradle, JUnit, MockMVC
## Setting Up
    1) Clone the repo
    2) Build project using Gradle
    3) Run the RevealtestApplication class (entry point)
    4) Once the application starts, you can access the triangle classifier endpoint at: http://localhost:8080/triangle?a=[side1]&b=[side2]&c=[side3]

## Run Tests
To run tests: gradle test