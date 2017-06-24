package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import edu.sdsc.mmtf.spark.filters.ContainsDSaccharideChain;
import edu.sdsc.mmtf.spark.filters.ContainsLProteinChain;
import edu.sdsc.mmtf.spark.filters.ContainsPolymerChainType;
import edu.sdsc.mmtf.spark.filters.NotFilter;
import edu.sdsc.mmtf.spark.io.MmtfReader;

public class Solution01 {

	/**
	 * Problem01: Count the number of PDB entries that contain L-protein and
	 * D-Saccharide chains, but do not contain DNA or RNA chains.
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

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution01.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

        long start = System.nanoTime();
        
		long count = 0;
				
        // TODO
        // Count the number of PDB entries that contain L-protein and
   	    // D-Saccharide chains, but do not contain DNA and RNA chains.
		count =	MmtfReader
				.readSequenceFile(path, sc)
			    .filter(new ContainsLProteinChain())
				.filter(new ContainsDSaccharideChain())
				.filter(new NotFilter(new ContainsPolymerChainType(ContainsPolymerChainType.DNA_LINKING)))
				.filter(new NotFilter(new ContainsPolymerChainType(ContainsPolymerChainType.RNA_LINKING)))
				.count();

		System.out.println("# Complexes that contain L-peptide and D-Saccharide: " + count);
		
		long end = System.nanoTime();
		System.out.println((end-start)/1E9 + " sec.");
		
		sc.close();
	}
}
