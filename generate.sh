#!/bin/bash
mvn archetype:generate \
          -DinteractiveMode=false \
          -DarchetypeGroupId=org.openjdk.jmh \
          -DarchetypeArtifactId=jmh-java-benchmark-archetype \
          -DgroupId=net.novoj \
          -DartifactId=jmh-demo \
          -Dversion=1.0