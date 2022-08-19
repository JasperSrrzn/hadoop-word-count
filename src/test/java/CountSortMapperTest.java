//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mrunit.mapreduce.MapDriver;
//import org.junit.Test;
//import sorting.CountSortAvroMapper;
//
//import java.io.IOException;
//
//public class CountSortMapperTest {
//
//    @Test
//    public void testSwap() throws IOException {
//        Text line = new Text("a 3");
//        new MapDriver<LongWritable, Text, IntWritable, Text>()
//                .withMapper(new CountSortAvroMapper())
//                .withInput(new LongWritable(0), line)
//                .withOutput(new IntWritable(3), new Text("a"))
//                .runTest();
//    }
//}
//