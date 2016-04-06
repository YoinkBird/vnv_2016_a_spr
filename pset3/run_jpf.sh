#!/bin/sh
set -x
export JPF_HOME=`echo ~/projects/jpf`
sh -cx  'buildfile=LListTesterJPF; javac -cp .:${JPF_HOME}/jpf-core/build/jpf.jar:${JPF_HOME}/jpf-symbc/build/jpf-symbc.jar:${JPF_HOME}/jpf-symbc/build/jpf-symbc-classes.jar -g ${buildfile}.java && java -cp . -jar ${JPF_HOME}/jpf-core/build/RunJPF.jar $buildfile.jpf'
# | grep add | sed 's|addFirst|0|g' | sed 's|addLast|1|g' |  sort 
