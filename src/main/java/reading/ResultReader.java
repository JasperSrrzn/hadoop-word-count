package reading;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;

import java.io.File;
import java.io.IOException;

public class ResultReader {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/sarrazin/GIT/hadoop-word-count/test-output/part-r-00000.avro");
        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>();
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, reader);
        while (dataFileReader.hasNext()){
            System.out.println(dataFileReader.next());
        }
    }

}
