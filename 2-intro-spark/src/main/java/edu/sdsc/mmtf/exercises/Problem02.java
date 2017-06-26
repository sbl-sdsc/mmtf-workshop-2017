package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class Problem02 {

	/** Read a text file.
	 * 
	 * @author Yana Valasatava
	 */
	
	/**
	 * Get an absolute path to the text file
	 * 
	 * @return path
	 */
	private String getDataFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource("data.txt").getFile();
	}

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem02.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		Problem02 p = new Problem02();
		String path = p.getDataFile();
		
        // TODO 
		// Create JavaRDD and print out file content
		

		sc.close();
	}
}
