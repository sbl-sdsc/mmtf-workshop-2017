package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class Problem05 {

	/** Create JavaPairRDD.
	 * 
	 * @author Yana Valasatava
	 */

	private String getDataFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource("data.txt").getFile();
	}

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem05.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

        // TODO 
		// create JavaPairRDD from JavaRDD and print out the number of words in each line
		

		sc.close();
	}
}
