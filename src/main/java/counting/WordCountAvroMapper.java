package counting;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCountAvroMapper extends Mapper<LongWritable, Text, AvroKey<String>, AvroValue<GenericRecord>> {

    private WordCountRecord record = new WordCountRecord();
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, AvroKey<String>, AvroValue<GenericRecord>>.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String cleanString = clean(line);
        StringTokenizer tokenizer = new StringTokenizer(cleanString);
        while (tokenizer.hasMoreTokens()){
            String word = tokenizer.nextToken();
            if (word.length() > 1){
                record.put("word", word);
                record.put("count", 1);
                context.write(new AvroKey<String>(word), new AvroValue<GenericRecord>(record));
            }
        }
    }

    private String clean(String line) {
        return line.toLowerCase().replaceAll("[^a-zA-Z]", " ");
    }

}
