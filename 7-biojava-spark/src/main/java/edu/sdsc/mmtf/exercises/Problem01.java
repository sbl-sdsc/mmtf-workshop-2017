package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class Problem01 {

	/**
	 * Problem01: Count the number of PDB entries that contain L-protein and
	 * D-saccharide chains, but do not contain DNA and RNA chains.
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

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem01.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);
		
        long count = 0;
		
        // TODO
        // Count the number of PDB entries that contain L-protein and
   	    // D-Saccharide chains, but do not contain DNA and RNA chains.
//	    count = MmtfReader
//				.readSequenceFile(path, sc)

			
		System.out.println("# Complexes that contain L-peptide and D-Saccharide: " + count);

		sc.close();
	}
}
