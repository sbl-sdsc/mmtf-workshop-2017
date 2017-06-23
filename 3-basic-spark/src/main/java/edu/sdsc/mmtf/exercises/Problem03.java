package edu.sdsc.mmtf.exercises;

import java.io.IOException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.rcsb.mmtf.api.StructureDataInterface;

import edu.sdsc.mmtf.spark.filters.ContainsLProteinChain;
import edu.sdsc.mmtf.spark.io.MmtfReader;
import edu.sdsc.mmtf.spark.mappers.StructureToPolymerChains;

public class Problem03 {

	/**
	 * Problem03: Count the total number of L-protein chains, the total
	 * number of groups (residues) in those chains, and calculate the 
	 * average number of groups per chain.
	 * 
	 * @author Peter Rose
	 * @throws IOException 
	 *
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

        // TODO
        // Count the total number of L-protein chains, the total
   	    // number of groups (residues) in those chains, and calculate the 
   	    // average number of groups per chain.	

		
		long numChains = 0;
		
		long numGroups = 0;

        System.out.println("# L-protein chains:  " + numChains);
        System.out.println("# groups (residues): " + numGroups);
        System.out.println("ave. groups/chain:   " + numGroups/(double)numChains);
        
		long end = System.nanoTime();
		System.out.println((end-start)/1E9 + " sec.");
		
		sc.close();
	}
}
