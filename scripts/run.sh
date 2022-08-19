rm -r sortedOutput
rm -r output
hadoop fs -rm -r /user/sarrazin/output
hadoop fs -rm -r /user/sarrazin/sortedOutput
hadoop fs -rm /input.txt
hadoop fs -copyFromLocal $1 /input.txt
hadoop jar hadoop-word-count.jar counting/WordCountAvroDriver -libjars avro-mapred-1.9.2-hadoop2.jar -conf src/main/resources/hadoop-localhost.xml /input.txt output
#hadoop fs -copyToLocal /user/sarrazin/output/ .
#hadoop jar hadoop-word-count.jar sorting/CountSortAvroDriver -conf src/main/resources/hadoop-localhost.xml output sortedOutput src/main/resources/WordCountRecord.avsc
#hadoop fs -copyToLocal /user/sarrazin/sortedOutput/ .