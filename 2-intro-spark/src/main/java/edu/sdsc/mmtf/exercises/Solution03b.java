package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;

public class Solution03b {

	/** Filter example: get even numbers from a sequence of integers ranging from 1 to 10
	 * 
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution03b.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		// Parallelized with 2 partitions
		JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2);

        // TODO 
		// Get only even numbers from a sequence of integers ranging from 1 to 10

		//Filter Predicate
		Function<Integer, Boolean> filterPredicate = e -> e % 2 == 0;

		rdd.filter( filterPredicate ).foreach(e -> System.out.println(e));

		sc.close();
	}
}