#!/usr/bin/env bash
set -e

dir=$(cd "$( dirname "$0" )" && pwd)
pom_file=${dir}/pom.xml

version=$(mvn -f ${pom_file} -q -Dexec.executable="echo" -Dexec.args='${project.version}' --non-recursive exec:exec)
mvn -f ${pom_file} package

cp ${dir}/target/wttrcli-${version}.jar /usr/local/bin/wttrcli.jar
cp ${dir}/wttrcli /usr/local/bin