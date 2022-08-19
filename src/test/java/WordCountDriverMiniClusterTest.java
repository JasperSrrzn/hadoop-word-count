//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileUtil;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.fs.PathFilter;
//import org.apache.hadoop.mapred.ClusterMapReduceTestCase;
//import org.junit.Assert;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//
//public class WordCountDriverMiniClusterTest extends ClusterMapReduceTestCase {
//
//    public static class OutputLogFilter implements PathFilter {
//        public boolean accept(Path path) {
//            return !path.getName().startsWith("_");
//        }
//    }
//
//    @Override
//    protected void setUp() throws Exception {
//        if (System.getProperty("test.build.data") == null) {
//            System.setProperty("test.build.data", "/tmp");
//        }
//        if (System.getProperty("hadoop.log.dir") == null) {
//            System.setProperty("hadoop.log.dir", "/tmp");
//        }
//        super.setUp();
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }
//
//    public void test() throws Exception {
//        Configuration conf = createJobConf();
//
//        Path localInput = new Path("input/test.txt");
//        Path input = getInputDir();
//        Path output = getOutputDir();
//
//        // Copy input data into test HDFS
//        getFileSystem().copyFromLocalFile(localInput, input);
//
//        WordCountDriver driver = new WordCountDriver();
//        driver.setConf(conf);
//
//        int exitCode = driver.run(new String[]{input.toString(), output.toString()});
//
//        Assert.assertEquals(0, exitCode);
//        // Check the output is as expected
//        Path[] outputFiles = FileUtil.stat2Paths(
//                getFileSystem().listStatus(output, new OutputLogFilter()));
//        assertThat(outputFiles.length, is(1));
//
//        InputStream in = getFileSystem().open(outputFiles[0]);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        assertThat(reader.readLine(), is("an\t1"));
//        reader.close();
//    }
//
//
//}
//
//