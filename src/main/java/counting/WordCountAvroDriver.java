package counting;

import org.apache.avro.Schema;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyOutputFormat;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountAvroDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 2){
            System.err.printf("Usage: %s [generic options]  <input path> <output path> ", getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        Job job = Job.getInstance(getConf(), "word count");
        job.setJarByClass(getClass());

        job.getConfiguration().setBoolean(Job.MAPREDUCE_JOB_USER_CLASSPATH_FIRST, true);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        AvroJob.setMapOutputKeySchema(job, Schema.create(Schema.Type.STRING));
        AvroJob.setMapOutputValueSchema(job, WordCountRecord.getClassSchema());
        AvroJob.setOutputKeySchema(job, WordCountRecord.getClassSchema());

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(AvroKeyOutputFormat.class);

        job.setMapperClass(WordCountAvroMapper.class);
        job.setReducerClass(WordCountAvroReducer.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }


    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new WordCountAvroDriver(), args);
        System.exit(exitCode);
    }
}
