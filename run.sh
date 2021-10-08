#!/bin/sh

# Run me via:
#   source run.sh

cd "$(dirname "$0")"
test -d target/dependency ||
  mvn clean package dependency:copy-dependencies
java -cp target/tensorflow-from-imagej-1.x-0.1.0-SNAPSHOT.jar:target/dependency/'*' foo.TensorFlow_Test
