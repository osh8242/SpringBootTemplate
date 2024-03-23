#!/usr/bin/env bash

nohup java `cat ./jvm.options | xargs` \
 -jar ./target/프로젝트명.jar \
1>> ./application.out \
2>> ./application.err & echo $! > ./application.pid &
