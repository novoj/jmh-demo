#!/bin/bash
echo "mvn archetype:generate"
echo "    -DinteractiveMode=false"
echo "    -DarchetypeGroupId=org.openjdk.jmh"
echo "    -DarchetypeArtifactId=jmh-java-benchmark-archetype"
echo "    -DgroupId=net.novoj"
echo "    -DartifactId=jmh-demo"
echo "    -Dversion=1.0"
read -p "Press any key ..."
mvn archetype:generate \
          -DinteractiveMode=false \
          -DarchetypeGroupId=org.openjdk.jmh \
          -DarchetypeArtifactId=jmh-java-benchmark-archetype \
          -DgroupId=net.novoj \
          -DartifactId=jmh-demo \
          -Dversion=1.0