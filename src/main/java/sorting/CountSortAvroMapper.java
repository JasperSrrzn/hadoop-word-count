package sorting;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CountSortAvroMapper<K> extends Mapper<AvroKey<K>, NullWritable, AvroKey<K>, AvroValue<K>> {

    @Override
    protected void map(AvroKey<K> key, NullWritable value, Mapper<AvroKey<K>, NullWritable, AvroKey<K>, AvroValue<K>>.Context context) throws IOException, InterruptedException {
        context.write(key, new AvroValue<K>(key.datum()));
    }
}
