#!/bin/sh
sh -cx  'buildfile=LListTesterJPF; javac -cp .:/home/yoinkbird/workspace/jpf/jpf-core/build/jpf.jar:/home/yoinkbird/workspace/jpf/jpf-symbc/build/jpf-symbc.jar:/home/yoinkbird/workspace/jpf/jpf-symbc/build/jpf-symbc-classes.jar -g ${buildfile}.java && java -cp . -jar ~/workspace/jpf/jpf-core/build/RunJPF.jar $buildfile.jpf'
# | grep add | sed 's|addFirst|0|g' | sed 's|addLast|1|g' |  sort 
