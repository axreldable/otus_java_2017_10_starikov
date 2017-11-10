#!/usr/bin/env bash

MEMORY="-Xms10m -Xmx10m"
GC01="-XX:+UseSerialGC"
GC02="-XX:+UseParallelGC -XX:+UseParallelOldGC"
GC03="-XX:+UseParNewGC -XX:+UseConcMarkSweepGC"
GC04="-XX:+UseG1GC"

java $MEMORY $GC01 -Djava.util.logging.config.file=logging.properties -jar target/HW04.jar > gc_01.log
java $MEMORY $GC02 -Djava.util.logging.config.file=logging.properties -jar target/HW04.jar > gc_02.log
java $MEMORY $GC03 -Djava.util.logging.config.file=logging.properties -jar target/HW04.jar > gc_03.log
java $MEMORY $GC04 -Djava.util.logging.config.file=logging.properties -jar target/HW04.jar > gc_04.log

java -cp target/HW04.jar ru.otus.hw4.statistic.Statist