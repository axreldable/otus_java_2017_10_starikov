#!/usr/bin/env bash

MEMORY="-Xms512m -Xmx512m"
GC01="-XX:+UseSerialGC"
GC02="-XX:+UseParallelGC -XX:+UseParallelOldGC"
GC03="-XX:+UseParNewGC -XX:+UseConcMarkSweepGC"
GC04="-XX:+UseG1GC"

java $MEMORY $GC01 -jar target/HW04.jar > run01.log
java $MEMORY $GC02 -jar target/HW04.jar > run02.log
java $MEMORY $GC03 -jar target/HW04.jar > run03.log
java $MEMORY $GC04 -jar target/HW04.jar > run04.log