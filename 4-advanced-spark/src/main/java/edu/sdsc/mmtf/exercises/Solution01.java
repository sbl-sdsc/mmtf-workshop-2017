package edu.sdsc.mmtf.exercises;

import java.io.IOException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import edu.sdsc.mmtf.spark.datasets.CustomReportService;

/**
 * Problem01: Create and query a dataset of PDB annotations
 * 
 * @author Peter Rose
 *
 */
public class Solution01 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {    
		long start = System.nanoTime();

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution01.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		// TODO retrieve PDB annotation: 
		// Binding affinities (Ki, Kd)
		// group name (hetId) and SMILES string of the ligand
		Dataset<Row> ds = CustomReportService.getDataset("Ki","Kd","hetId","ligandSmiles");

		// TODO show the schema of this dataset
		ds.printSchema();

		// TODO select structures that have a Ki *and* Kd value(s) 
		// and print the first 25 records.
		ds = ds.filter("(Ki IS NOT NULL AND Kd IS NOT NULL)");
		ds.show(25);

		// TODO print the size of the dataset
		System.out.println("Dataset size: " + ds.count());
		
		long end = System.nanoTime();
		System.out.println("Time:     " + (end-start)/1E9 + "sec.");

		sc.close();
	}
}
