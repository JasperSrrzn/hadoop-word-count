//import counting.WordCountAvroMapper;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mrunit.mapreduce.MapDriver;
//import org.junit.Test;
//
//import java.io.IOException;
//
//public class WordCountMapperTest {
//
//
//   @Test
//   public void processLine() throws IOException {
//       Text line = new Text("Ik ben Jasper. Ik tel woorden.");
//       new MapDriver<LongWritable, Text, Text, IntWritable>()
//               .withMapper(new WordCountAvroMapper())
//               .withInput(new LongWritable(0), line)
//               .withOutput(new Text("ik"), new IntWritable(1))
//               .withOutput(new Text("ben"), new IntWritable(1))
//               .withOutput(new Text("jasper"), new IntWritable(1))
//               .withOutput(new Text("ik"), new IntWritable(1))
//               .withOutput(new Text("tel"), new IntWritable(1))
//               .withOutput(new Text("woorden"), new IntWritable(1))
//               .runTest();
//   }
//}
//