package edu.sdsc.mmtf.excercises;

import java.io.IOException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import edu.sdsc.mmtf.spark.filters.ContainsDSaccharideChain;
import edu.sdsc.mmtf.spark.filters.ContainsLProteinChain;
import edu.sdsc.mmtf.spark.filters.ContainsPolymerChainType;
import edu.sdsc.mmtf.spark.filters.NotFilter;
import edu.sdsc.mmtf.spark.io.MmtfReader;
import edu.sdsc.mmtf.spark.rcsbfilters.AdvancedQuery;
import edu.sdsc.mmtf.spark.rcsbfilters.ChemicalStructureQuery;

public class Problem02 {

	/**
	 * Problem02: Find PDB entries with reported protein-ligand binding affinities
	 * in the 0 - 10 nM range that contain ligands with a pyrimidine substructure.
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

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem02.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

        long start = System.nanoTime();
	
        // TODO
		// Find PDB entries with reported protein-ligand binding affinities
		// in the 0 - 10 nM range that contain ligands with a pyrimidine substructure.
        String query = "";
        String smiles = "";
		
        
        
		long end = System.nanoTime();
		System.out.println((end-start)/1E9 + " sec.");
		
		sc.close();
	}
}
