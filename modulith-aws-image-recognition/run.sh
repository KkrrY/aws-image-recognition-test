#!/bin/bash

exec java $JAVA_OPTS -cp ".:BOOT-INF/classes:BOOT-INF/lib/*" org.springframework.boot.loader.JarLauncher