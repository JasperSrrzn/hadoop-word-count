package counting;

import com.google.common.collect.Iterables;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountAvroReducer extends Reducer<AvroKey<String>, AvroValue<GenericRecord>, AvroKey<GenericRecord>, NullWritable> {

    private WordCountRecord record = new WordCountRecord();

    @Override
    protected void reduce(AvroKey<String> key, Iterable<AvroValue<GenericRecord>> values, Reducer<AvroKey<String>, AvroValue<GenericRecord>, AvroKey<GenericRecord>, NullWritable>.Context context) throws IOException, InterruptedException {
        record.put("word", key.toString());
        record.put("count", Iterables.size(values));
        context.write(new AvroKey<GenericRecord>(record), NullWritable.get());
        


    }
}
