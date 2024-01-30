set GEN_JAR=..\tool\perfect_gen.jar
set CSV_DIR=csv
set MSG_DIR=.\msg

java -cp %GEN_JAR% perfect.gen.rpc.Main -x %MSG_DIR%/gs/msg.xml -o gen/msg -l java -r gsServer 

java -cp %GEN_JAR% perfect.gen.cfg.Main -xml %CSV_DIR%\cfg_sc.xml -c gen\cfg -g server -l java -d .\config -sl 0

java -cp %GEN_JAR% perfect.gen.xdb.Main -x xdb/gsd.xdb.xml -o gen/xdb -l java 

pause
