package edu.sdsc.mmtf.exercises;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.rcsb.mmtf.api.StructureDataInterface;

import edu.sdsc.mmtf.spark.datasets.CustomReportService;
import edu.sdsc.mmtf.spark.datasets.SecondaryStructureExtractor;
import edu.sdsc.mmtf.spark.io.MmtfReader;
import edu.sdsc.mmtf.spark.mappers.StructureToPolymerChains;

/**
 * Problem02: Join a binding affinity dataset with a secondary
 * structure dataset
 * 
 * @author Peter Rose
 *
 */
public class Problem02 {

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

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem02.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		long start = System.nanoTime();

		// retrieve PDB annotation: 
		// Binding affinities (Ki, Kd)
		// group name (hetId) and SMILES string of the ligand
		Dataset<Row> ds = CustomReportService.getDataset("Ki","Kd","hetId","ligandSmiles");

		// select structures that have a Ki *and* Kd value(s) 
		ds = ds.filter("(Ki IS NOT NULL AND Kd IS NOT NULL)");
		ds.show(10);
		
		// extract list of structureIds from this dataset
		List<Row> rows = ds.select("structureId").collectAsList();	
		List<String> pdbIds = new ArrayList<>();
        for (Row r: rows) {
        	    pdbIds.add(r.getString(0));
        }
        
        // read these PDB entries and flatmap to polymer chains
        JavaPairRDD<String, StructureDataInterface> chains = MmtfReader
        		.readSequenceFile(path, pdbIds, sc)
        		.flatMapToPair(new StructureToPolymerChains());
		
        // TODO get secondary structure (using SecondaryStructureExtractor)
        // and show 10 records
		
		
		// TODO join the binding affinity dataset with the secondary structure
		// dataset and show the first 10 records
		
		
		long end = System.nanoTime();
		System.out.println("Time:     " + (end-start)/1E9 + "sec.");

		sc.close();
	}
}
