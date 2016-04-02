#!/bin/sh
set -x
java_target_file="SortTester.java";
javac_files="$java_target_file"
java_target=`basename -s .java $java_target_file`;
#javac -verbose $javac_files 2>&1 | tr ',' "\n";
javac $javac_files
if [ $? -ne 0 ]; then
  echo "javac failed";
  echo "perhaps source setup.sh first?";
  exit;
fi
# $CLASSPATH should already be set; no need for 'java -cp path:path' 
java org.junit.runner.JUnitCore $java_target;

# sample complex build:
# sh -c  'buildfile=MySymbolicDriverForBST; javac -verbose -cp .:/home/yoinkbird/workspace/jpf/jpf-core/build/jpf.jar:/home/yoinkbird/workspace/jpf/jpf-symbc/build/jpf-symbc.jar:/home/yoinkbird/workspace/jpf/jpf-symbc/build/jpf-symbc-classes.jar -g ${buildfile}.java && java -jar ~/workspace/jpf/jpf-core/build/RunJPF.jar $buildfile.jpf' |& tr ',' "\n"
