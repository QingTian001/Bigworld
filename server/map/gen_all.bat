set GEN_JAR=..\tool\perfect_gen.jar
set CSV_DIR=..\gsd\csv
set MSG_DIR=..\gsd\msg

java -cp %GEN_JAR% perfect.gen.rpc.Main -x %MSG_DIR%/gs/msg.xml -o gen/msg -l java -r mapServer 

java -cp %GEN_JAR% perfect.gen.cfg.Main -xml %CSV_DIR%\cfg_sc.xml -c gen\cfg -g server -l java -d .\config
 
pause
