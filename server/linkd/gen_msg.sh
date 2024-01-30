#!/bin/sh
GEN_JAR=../tool/perfect_gen.jar
MSG_DIR=./msg
java -cp $GEN_JAR perfect.gen.rpc.Main -x $MSG_DIR/msg.xml -o gen/msg -l java -r link 

echo "succ"
