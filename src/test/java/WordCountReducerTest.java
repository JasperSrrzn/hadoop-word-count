//import counting.WordCountAvroReducer;
//import org.apache.avro.generic.GenericRecord;
//import org.apache.avro.mapred.AvroKey;
//import org.apache.avro.mapred.AvroValue;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.NullWritable;
//import org.apache.hadoop.io.Text;
//
//import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//public class WordCountReducerTest {
//
//     @Test
//     public void returnCounts() throws IOException {
//
//        new ReduceDriver<AvroKey<String>, AvroValue<GenericRecord>, AvroKey<GenericRecord>, NullWritable>()
//                .withReducer(new WordCountAvroReducer())
//                .withInput(new Text("ik"),  Arrays.asList(new IntWritable(1), new IntWritable(1)))
//                .withInput(new Text("ben"), Arrays.asList(new IntWritable(1)))
//                .withInput(new Text("jasper"), Arrays.asList(new IntWritable(1)))
//                .withOutput(new Text("ik"), new IntWritable(2))
//                .withOutput(new Text("ben"), new IntWritable(1))
//                .withOutput(new Text("jasper"), new IntWritable(1))
//                .runTest();
//
//    }
//}
//