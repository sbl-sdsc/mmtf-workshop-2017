package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

public class Solution04 {

	/** Map and flatMap examples
	 * 
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution04.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		// Parallelized with 2 partitions
		JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2);

        // TODO 
		// multiply by 2 every integer between 1 to 10
		System.out.println("Mupliply each number by two:");
		rdd.map( e -> 2*e )
				.foreach(e -> System.out.print(e + " "));
		System.out.println("\n");

		// TODO
		// For each integer create a string containing this integer
		System.out.println("String for each number:");
		rdd.map( e -> "Number "+ String.valueOf(e) )
				.foreach(e -> System.out.print(e + "; "));
		System.out.println("\n");

		// TODO
		// for each integer compute a square and a cube value
		System.out.println("Square and cube values for each number:");
		rdd.flatMap( e -> Arrays.asList((int)Math.pow(e, 2), (int)Math.pow(e, 3)).iterator())
				.foreach(e -> System.out.print(e + " "));

		sc.close();
	}
}