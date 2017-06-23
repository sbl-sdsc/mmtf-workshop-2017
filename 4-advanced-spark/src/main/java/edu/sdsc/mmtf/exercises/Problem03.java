package edu.sdsc.mmtf.exercises;

import java.io.IOException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.rcsb.mmtf.api.StructureDataInterface;

import edu.sdsc.mmtf.spark.io.MmtfReader;

/**
 * Problem03: Write code to generate a dataset, then 
 * filter the dataset.
 * 
 * @author Peter Rose
 *
 */
public class Problem03 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {  
		
		String path = System.getProperty("MMTF_REDUCED");
		if (path == null) {
			System.err.println("Environment variable for Hadoop sequence file has not been set");
			System.exit(-1);
		}

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem03.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		long start = System.nanoTime();

		JavaPairRDD<String, StructureDataInterface> pdb = MmtfReader
	        		.readSequenceFile(path, sc);
			
		// TODO implement missing functionality in UnitCellExtractorProblem03
		Dataset<Row> ds = UnitCellExtractorProblem03.getDataset(pdb);
		ds.printSchema();
		ds.show(10);
		
		// TODO filter dataset: alpha, beta, gamma < 91.0

		
		ds.show(10);
		
		long end = System.nanoTime();
		System.out.println("Time:     " + (end-start)/1E9 + "sec.");

		sc.close();
	}
}
