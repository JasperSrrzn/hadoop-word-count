package sorting;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyInputFormat;
import org.apache.avro.mapreduce.AvroKeyOutputFormat;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.File;

public class CountSortAvroDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.printf(
                    "Usage: %s [generic options] <input> <output> <schema-file>\n",
                    getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        String input = args[0];
        String output = args[1];
        String schemaFile = args[2];

        Job job = new Job(getConf(), "Sort");
        job.setJarByClass(getClass());

        job.getConfiguration().setBoolean(Job.MAPREDUCE_JOB_USER_CLASSPATH_FIRST, true);

        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        AvroJob.setDataModelClass(job, GenericData.class);

        Schema schema = new Schema.Parser().parse(new File(schemaFile));
        AvroJob.setInputKeySchema(job, schema);
        AvroJob.setMapOutputKeySchema(job, schema);
        AvroJob.setMapOutputValueSchema(job, schema);
        AvroJob.setOutputKeySchema(job, schema);

        job.setInputFormatClass(AvroKeyInputFormat.class);
        job.setOutputFormatClass(AvroKeyOutputFormat.class);

        job.setOutputKeyClass(AvroKey.class);
        job.setOutputValueClass(NullWritable.class);

        job.setMapperClass(CountSortAvroMapper.class);
        job.setReducerClass(CountSortAvroReducer.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new CountSortAvroDriver(), args);
        System.exit(exitCode);
    }
}
