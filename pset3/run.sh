#!/bin/sh
set -x
java_target_file="LListTester.java";
javac_files="LList.java $java_target_file"
java_target=`basename -s .java $java_target_file`;
javac -verbose $javac_files 2>&1 | tr ',' "\n";
if [ $? -ne 0 ]; then
  echo "javac failed";
  echo "perhaps source setup.sh first?";
  exit;
fi
# $CLASSPATH should already be set; no need for 'java -cp path:path' 
java org.junit.runner.JUnitCore $java_target;

# sample complex build:
# oneline:
#  env CLASSPATH=.:/home/yoinkbird/workspace/res/junit/4.10/junit.jar sh -cx  'buildfile=LListTester; javac -Xlint:unchecked -g ${buildfile}.java && java org.junit.runner.JUnitCore $buildfile'
