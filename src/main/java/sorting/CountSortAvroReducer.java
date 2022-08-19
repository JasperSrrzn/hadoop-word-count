package sorting;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CountSortAvroReducer<K> extends Reducer<AvroKey<K>, AvroValue<K>, AvroKey<K>, NullWritable> {

    @Override
    protected void reduce(AvroKey<K> key, Iterable<AvroValue<K>> values, Reducer<AvroKey<K>, AvroValue<K>, AvroKey<K>, NullWritable>.Context context) throws IOException, InterruptedException {
        for (AvroValue<K> value: values) {
            context.write(new AvroKey<>(value.datum()), NullWritable.get());
        }
    }
}
