#! /usr/bin/env sh

# this will build and run the examples :)
case "$1" in
    -b|--build)
        echo "-> building project"
        ./gradlew build || exit 1 # -q examples:build
    ;;
esac

echo "-> running examples"
java -jar examples/build/libs/examples.jar
