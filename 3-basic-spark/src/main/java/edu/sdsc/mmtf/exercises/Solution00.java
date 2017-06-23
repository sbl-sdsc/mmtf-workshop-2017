package edu.sdsc.mmtf.exercises;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.rcsb.mmtf.api.StructureDataInterface;

import edu.sdsc.mmtf.spark.io.MmtfReader;

public class Solution00 {

	/**
	 * Problem00: Reading Hadoop Sequence files and downloading files from mmtf.rcsb.org
	 * 
	 * @author Peter Rose
	 *
	 */

	public static void main(String[] args) {

		String path = System.getProperty("MMTF_REDUCED");
		if (path == null) {
			System.err.println("Environment variable for Hadoop sequence file has not been set");
			System.exit(-1);
		}

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution00.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);
		
        // TODO
        // count the number of PDB entries in an MMTF Hadoop Sequence file.
	    long count = MmtfReader
				.readSequenceFile(path, sc)
				.count();
	    
	    System.out.println("# PDB entries in " + path + ": " + count);
	    
	    // read a list of PDB entries and print the keys (PDB Ids)
	    List<String> pdbIds = Arrays.asList("1AQ1","1B38","1B39","1BUH");
	        
	    MmtfReader
	    .readSequenceFile(path, pdbIds, sc)
	    .keys()
	    .foreach(k -> System.out.println(k));

	    // download the same list of PDB IDs from mmtf.rcsb.org
	    // and create a JavaPairRDD
	    JavaPairRDD<String, StructureDataInterface> pdb = MmtfReader
	    		.downloadMmtfFiles(pdbIds, sc);

	    // then get the keys (PDB IDs) and create a list from the JavaPairRDD
	    List<String> keys = pdb.keys().collect();

		System.out.println("# PDB entries in list : " + keys);

		sc.close();
	}
}
