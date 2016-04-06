#!/bin/sh
set -x
export JUNIT_HOME=/home/yoinkbird/workspace/res/junit/4.10
export CLASSPATH=".:${JUNIT_HOME}/junit.jar"
java_target_file="LListTester.java";
javac_files="$java_target_file"
java_target=`basename -s .java $java_target_file`;
#javac -verbose $javac_files 2>&1 | tr ',' "\n";
javac $javac_files
if [ $? -ne 0 ]; then
  echo "javac failed";
  exit;
fi
# $CLASSPATH should already be set; no need for 'java -cp path:path' 
java org.junit.runner.JUnitCore $java_target;

# sample complex build:
# oneline:
#  env CLASSPATH=.:/home/yoinkbird/workspace/res/junit/4.10/junit.jar sh -cx  'buildfile=LListTester; javac -Xlint:unchecked -g ${buildfile}.java && java org.junit.runner.JUnitCore $buildfile'
