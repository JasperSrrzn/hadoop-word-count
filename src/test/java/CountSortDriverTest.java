import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;
import sorting.CountSortAvroDriver;

public class CountSortDriverTest {

    @Test
    public void test() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        conf.set("mapreduce.framework.name", "local");
        conf.set("mapreduce.task.io.sort.mb", String.valueOf(1));
        Path input = new Path("output");
        Path output = new Path("test-output");
        Path schemaFile = new Path("/Users/sarrazin/GIT/hadoop-word-count/src/main/resources/WordCountRecord.avsc");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output, true);

        CountSortAvroDriver driver = new CountSortAvroDriver();
        driver.setConf(conf);

        int exitCode = driver.run(new String[] {input.toString(), output.toString(), schemaFile.toString()});
        Assert.assertEquals(exitCode, 0);
    }
}
