package edu.sdsc.mmtf.exercises;

import java.io.IOException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import edu.sdsc.mmtf.spark.filters.ContainsLProteinChain;
import edu.sdsc.mmtf.spark.io.MmtfReader;
import edu.sdsc.mmtf.spark.rcsbfilters.AdvancedQuery;
import edu.sdsc.mmtf.spark.rcsbfilters.ChemicalStructureQuery;

public class Solution02 {

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

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution02.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

        long start = System.nanoTime();

        // TODO
		// Find PDB entries with reported protein-ligand binding affinities
		// in the 0 - 10 nM range that contain ligands with a pyrimidine substructure.
		String query = 
		"<orgPdbQuery>" +
	        "<queryType>org.pdb.query.simple.BindingAffinityQuery</queryType>" +
	        "<bindingAffinityComparator>between</bindingAffinityComparator>" +
	        "<bingingAffinityMin>0</bingingAffinityMin>" +
	        "<bindingAffinityMax>10</bindingAffinityMax>" +
	        "<affinityType>Ki</affinityType>" +
	    "</orgPdbQuery>";
		
		String smiles = "c1cncnc1";
		boolean exclusive = true;
		
		MmtfReader
				.readSequenceFile(path, sc)
			    .filter(new ContainsLProteinChain(exclusive)) // only protein chains
			    .filter(new AdvancedQuery(query)) // with binding affinity [0, 10] nM
			    .filter(new ChemicalStructureQuery(smiles)) // with pyrimidine substructure
				.keys()
				.foreach(k -> System.out.println(k));

		long end = System.nanoTime();
		System.out.println((end-start)/1E9 + " sec.");
		
		sc.close();
	}
}
