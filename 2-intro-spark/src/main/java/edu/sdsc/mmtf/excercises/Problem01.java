package edu.sdsc.mmtf.excercises;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;


public class Problem01 {

	/**
	 * Problem01: Parallelize a list of items.
	 * 
	 * @author 
	 *
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem01.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        // TODO 
		// parallelize list as JavaRDD and print the numbers
		

		sc.close();
	}
}
