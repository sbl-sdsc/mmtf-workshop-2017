package edu.sdsc.mmtf.exercises;



import static org.apache.spark.sql.functions.col;

import java.io.IOException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.rcsb.mmtf.api.StructureDataInterface;

import edu.sdsc.mmtf.spark.datasets.GroupInteractionExtractor;
import edu.sdsc.mmtf.spark.io.MmtfReader;
import edu.sdsc.mmtf.spark.rcsbfilters.AdvancedQuery;

/**
 * Analyze the interactions of the terminal phosphate in ATP
 * with protein-serine/threonine kinases.
 * 
 * @author Peter Rose
 *
 */
public class Solution04 {

	/**
	 * @param args input arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String path = System.getProperty("MMTF_FULL");
	    if (path == null) {
	    	    System.err.println("Environment variable for Hadoop sequence file has not been set");
	        System.exit(-1);
	    }
	     
	    long start = System.nanoTime();
	    
	    SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution04.class.getSimpleName());
	    JavaSparkContext sc = new JavaSparkContext(conf);
	    
	    // read PDB in MMTF format
	    JavaPairRDD<String, StructureDataInterface> pdb = MmtfReader.readSequenceFile(path, sc);
	    
	    // TODO analyze protein-serine/threonine kinase structures
	    String query =
	    		"<orgPdbQuery>" +
	    		"<queryType>org.pdb.query.simple.EnzymeClassificationQuery</queryType>" +
	    		"<Enzyme_Classification>2.7.11</Enzyme_Classification>" +
	    		"</orgPdbQuery>";
	    	pdb = pdb.filter(new AdvancedQuery(query));
	    
	    // TODO find ATP interactions within 3 Angstroms
	    GroupInteractionExtractor finder = new GroupInteractionExtractor("ATP", 3);
	    Dataset<Row> interactions = finder.getDataset(pdb).cache();
	    
	    // TODO analyze interactions with the oxygens in the terminal 
	    // phosphate group of ATP (O1G, O2G, O3G)
	    // Tip: google: SQL LIKE
	    interactions = interactions.filter("atom1 LIKE('O%G')").cache();
	    
	    // TODO show the data schema of the dataset and print 20 records
        interactions.printSchema();
        interactions.show(20);
        
        // TODO get the total number of interactions
        long n = interactions.count();
        System.out.println("# interactions: " + n);
        
        
        // TODO list the top 10 interacting groups in descending order of occurrence
        System.out.println("Top interacting groups");

        Dataset<Row> topGroups = interactions
        		.groupBy("residue2")
        		.count();
        
        topGroups
        .sort(col("count").desc()) // sort descending by count
        .show(10);
        
        // TODO bonus problem: list the top 10 interacting element types
        // and add a frequency of occurrence column
        System.out.println("Top interacting element types");
 
        Dataset<Row> topElements = interactions
        		.groupBy("element2")
        		.count();

        topElements
        .withColumn("frequency", col("count").divide(n)) // add column with frequency of occurrence
        .sort(col("frequency").desc()) // sort descending
        .show(10);

        long end = System.nanoTime();
	    
	    System.out.println("Time:     " + (end-start)/1E9 + " sec.");
	    
	    sc.close();
	}
}
