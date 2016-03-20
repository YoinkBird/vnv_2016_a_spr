#!/bin/sh
set -x
java_target_file="LListTester.java";
javac_files="LList.java $java_target_file"
java_target=`basename -s .java $java_target_file`;
javac $javac_files;
if [ $? -ne 0 ]; then
  echo "javac failed";
  exit;
fi
# $CLASSPATH should already be set; no need for 'java -cp path:path' 
java org.junit.runner.JUnitCore $java_target;

