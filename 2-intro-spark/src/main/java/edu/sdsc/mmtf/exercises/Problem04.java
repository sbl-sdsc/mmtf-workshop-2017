package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

public class Problem04 {

	/** Map and flatMap examples: given a list of integers between 1 to 10
	 * 1. multiply every integer by 2
	 * 2. for each integer create a string containing this integer
	 * 3. for each integer compute a square and a cube value
	 * 
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem04.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		// Parallelized with 2 partitions
		JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2);

        // TODO 
		// multiply by 2 every integer between 1 to 10


		// TODO
		// for each integer create a string containing this integer


		// TODO
		// for each integer compute a square and a cube value

		sc.close();
	}
}