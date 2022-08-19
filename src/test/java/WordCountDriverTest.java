import counting.WordCountAvroDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;



public class WordCountDriverTest {

    @Test
    public void test() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        conf.set("mapreduce.framework.name", "local");
        conf.set("mapreduce.task.io.sort.mb", String.valueOf(1));
        Path input = new Path("scripts/input/bible.txt");
        Path output = new Path("output");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output, true);

        WordCountAvroDriver driver = new WordCountAvroDriver();
        driver.setConf(conf);

        int exitCode = driver.run(new String[] {input.toString(), output.toString()});
        Assert.assertEquals(exitCode, 0);
    }
}
