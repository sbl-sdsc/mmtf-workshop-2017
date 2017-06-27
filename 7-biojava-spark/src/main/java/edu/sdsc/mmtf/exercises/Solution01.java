package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import edu.sdsc.mmtf.spark.filters.ContainsLProteinChain;
import edu.sdsc.mmtf.spark.io.MmtfReader;
import edu.sdsc.mmtf.spark.mappers.StructureToBioJava;
import edu.sdsc.mmtf.spark.mappers.StructureToPolymerChains;

public class Solution01 {

	/**
	 * Problem01: Calculate DSSP secondary structure for protein chains.
	 * 
	 * @author Peter Rose
	 *
	 */

	public static void main(String[] args) {

		String path = System.getProperty("MMTF_FULL");
		if (path == null) {
			System.err.println("Path for full Hadoop sequence file has not been set");
			System.exit(-1);
		}

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution01.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

        // take a 0.5% random sample
        double fraction = 0.005;
		long seed = 123;

		// TODO write a comment after each line
		MmtfReader
				.readSequenceFile(path, fraction, seed, sc) // read random sample
				.filter(new ContainsLProteinChain()) // at least 1 protein chain required
				.flatMapToPair(new StructureToPolymerChains()) // split into polymer chains
				.filter(new ContainsLProteinChain()) // make sure this chain is a protein
				.mapValues(new StructureToBioJava()) // convert to a BioJava structure
				.mapValues(new BioJavaStructureToDssp()) // calculate DSSP secondary structure
				.foreach(t -> System.out.println(t)); // print <pdbId.chainName>, DSSP string pairs
		
		sc.close();
	}
}
