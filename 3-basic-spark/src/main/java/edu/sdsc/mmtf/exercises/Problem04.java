package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.rcsb.mmtf.api.StructureDataInterface;

import edu.sdsc.mmtf.spark.filters.ContainsSequenceRegex;
import edu.sdsc.mmtf.spark.filters.ExperimentalMethods;
import edu.sdsc.mmtf.spark.filters.PolymerComposition;
import edu.sdsc.mmtf.spark.filters.Resolution;
import edu.sdsc.mmtf.spark.filters.Rfree;
import edu.sdsc.mmtf.spark.io.MmtfReader;
import edu.sdsc.mmtf.spark.io.MmtfWriter;
import edu.sdsc.mmtf.spark.mappers.StructureToPolymerChains;

public class Problem04 {

	/**
	 * Problem04: Write Hadoop Sequence File
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

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem04.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		// create a custom set of protein chains (high resolution, no modified amino acids)
		// that contain a Zinc finger sequence motif
		JavaPairRDD<String, StructureDataInterface> chains = MmtfReader
				.readSequenceFile(path, sc)
				.filter(new ExperimentalMethods(ExperimentalMethods.X_RAY_DIFFRACTION))
				.filter(new Resolution(0.0, 2.0))
				.filter(new Rfree(0.0, 0.2))
				.flatMapToPair(new StructureToPolymerChains())
				.filter(new PolymerComposition(PolymerComposition.AMINO_ACIDS_20))
				.filter(new ContainsSequenceRegex("C.{2,4}C.{12}H.{3,5}H"));

		// TODO
		// write to Hadoop sequence file to this path
		path += "_proteinChains";


		System.out.println("# chains written to " + path + ": " + chains.count());

		// TODO
		// read the file we just created and count the number of chains
		long count = 0;
		

		System.out.println("# chains read from " + path + ": " + count);

		sc.close();
	}
}
