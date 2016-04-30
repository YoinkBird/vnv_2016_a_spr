#!/bin/sh
echo_usage()
{
  cat << EOF
  Usage: cd to dir with .java file to be compiled and run this script
  0 args: script assumes main classfile is same as basename \$PWD
    e.g. run_jpf.sh
  1 args: .java file - tell script which file to run on
    e.g. run_jpf.sh Simulator
  2 args: .java file, java args - args to .java file
    e.g. run_jpf.sh Simulator 2,1


  NOTE: 
  By default, SUT output is suppressed.
  comment out 'vm.tree_output=false' in 'generate_template' to show output
EOF
}
generate_template()
{
  if [ $# -ne 0 ]; then
    fileRoot=$1;
  fi
  if [ -r ${fileRoot}.jpf ]; then
#    return
    #TODO: diff new,old
    echo "-I-: overwriting ${fileRoot}.jpf";
  fi

cat << EOF > ${fileRoot}.jpf 
# http://babelfish.arc.nasa.gov/trac/jpf/wiki/user/run#a3targetspecification
# http://babelfish.arc.nasa.gov/trac/jpf/wiki/user/config
target = {TARGET}
target.args = {TARGET.ARGS}
classpath=.
classpath+=:{CLASSPATH}

#listener = gov.nasa.jpf.symbc.sequences.SymbolicSequenceListener
#symbolic.method=\${target}.LList.addLast(sym#con)
#symbolic.method=\${target}.addLast(sym)

#symbolic.min_int=0
#symbolic.max_int=3

## http://babelfish.arc.nasa.gov/trac/jpf/wiki/user/output
# suppress output
vm.tree_output=false
EOF
}

main(){
  # print usage
  echo_usage
  #args=("$@")
  set -x
  echo $@
  # configure JPF_HOME
  if [ -z $JPF_HOME ]; then
    # default location
    export JPF_HOME=`echo ~/projects/jpf`
  fi
  if [ ! -r $JPF_HOME ]; then
    echo "-E- JPF_HOME not found: $JPF_HOME";
    exit 1;
  fi
  # get filename, classpath based on pwd and package
  curDir=`pwd`;
  fileRoot=`basename $curDir`;
  mainClassFile=`grep -Pl '\bmain\b\s*\(String.*?args.*?\)' $curDir/*`;
  mainClassFile=`basename $mainClassFile`;
  mainClassFileRoot=`echo $mainClassFile | perl -nle 'if(m|(.*?).java|g){print $1}'`
  if [ $# -ne 0 ]; then
    fileRoot=$1;
  fi
  if [ $# -gt 1 ]; then
    targetArgs=$2;
  fi
  #TODO: check that it is a file and not a dir
  if [ ! -r $fileRoot ]; then
    fileName=${fileRoot}.java;
  fi
  # if dir-based name detection doesn't work, try the experimental 'grep main' detection
  if [ ! -r $fileName ]; then
    fileName=$mainClassFile
    fileRoot=$mainClassFileRoot
  fi
  # error if file not found
  if [ ! -r $fileName ]; then
    echo "-E- file not found: $fileName";
    exit 1;
  fi
#  package=`grep -P '^\s*package\s+.*?;' $curDir/${fileName}| sed 's|package \(.*\);|\1|g'`
  package=`perl -nle 'if(m|^\s*package\s+(.*?);|g){print $1}' $curDir/${fileName}`
  # http://stackoverflow.com/questions/13589895/shell-command-to-strip-out-m-characters-from-text-file
  package=`echo $package | sed 's|||g'`
  echo $package
  packagePath=''
  target=${fileRoot};
  if [ ! -z $package ]; then
    packagePath=`echo $curDir | sed "s|/$package||g"`
    target=${package}.${target}
  fi
  #className=`grep "public class" $curDir/${fileRoot}.java | sed 's|public class \(.*\){|\1|g'`
  # generate JPF file
  generate_template ${fileRoot}
#  cp -fv Template.jpf ${fileRoot}.jpf
  sed -i "s|{CLASSPATH}|$packagePath|g"        ${fileRoot}.jpf
  sed -i "s|{TARGET}|${target}|g" ${fileRoot}.jpf
  sed -i "s|{TARGET.ARGS}|${targetArgs}|g" ${fileRoot}.jpf
  export CLASSPATH=$curDir:$packagePath;
  export BUILDFILE=$fileRoot;
  sh -cx  'buildfile=${BUILDFILE}; javac -cp .:${CLASSPATH}:${JPF_HOME}/jpf-core/build/jpf.jar:${JPF_HOME}/jpf-symbc/build/jpf-symbc.jar:${JPF_HOME}/jpf-symbc/build/jpf-symbc-classes.jar -g ${buildfile}.java && java -cp . -jar ${JPF_HOME}/jpf-core/build/RunJPF.jar $buildfile.jpf  -Xmx4096m'

}
main "$@"
