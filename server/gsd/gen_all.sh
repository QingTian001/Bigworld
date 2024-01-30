#!/bin/sh -e
set -xe 
GEN_JAR=../tool/perfect_gen.jar
CSV_DIR=csv
MSG_DIR=./msg

java -cp $GEN_JAR perfect.gen.rpc.Main -x $MSG_DIR/gs/msg.xml -o gen/msg -l java -r gsServer 

java -Xms1g -cp $GEN_JAR perfect.gen.cfg.Main -xml $CSV_DIR/cfg_sc.xml -c gen/cfg -g server -l java -d ./config -sl 1

java -cp $GEN_JAR perfect.gen.xdb.Main -x xdb/gsd.xdb.xml -o gen/xdb -l java

echo "succ"
