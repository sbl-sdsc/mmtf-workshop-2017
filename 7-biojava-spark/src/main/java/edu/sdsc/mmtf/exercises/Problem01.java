package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import edu.sdsc.mmtf.spark.filters.ContainsLProteinChain;
import edu.sdsc.mmtf.spark.io.MmtfReader;
import edu.sdsc.mmtf.spark.mappers.StructureToBioJava;
import edu.sdsc.mmtf.spark.mappers.StructureToPolymerChains;

public class Problem01 {

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

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem01.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

        // take a 0.5% random sample
        double fraction = 0.005;
		long seed = 123;

		// TODO write a comment after each line
		MmtfReader
				.readSequenceFile(path, fraction, seed, sc) // ...
				.filter(new ContainsLProteinChain()) // ...
				.flatMapToPair(new StructureToPolymerChains()) // ...
				.filter(new ContainsLProteinChain()) // ...
				.mapValues(new StructureToBioJava()) // ...
				.mapValues(new BioJavaStructureToDssp()) // ...
				.foreach(t -> System.out.println(t)); // ...
		
		sc.close();
	}
}
