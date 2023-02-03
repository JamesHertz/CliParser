#! /usr/bin/sh

# this will build and run the examples :)
./gradlew -q examples:build
java -jar examples/build/libs/examples.jar
