package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Problem11 {

	/** Example on usage of reduceByKey() transformation.
	 *
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem11.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		JavaRDD<String> rdd = sc.parallelize(Arrays.asList("a", "b", "a", "a", "b", "b", "b", "b"));

		JavaPairRDD<String, Integer> pairRdd = rdd.mapToPair(t -> new Tuple2<String, Integer>(t, 1));

		// reduceByKey function merges the values for each key using an associative reduce function

		// TODO
		// count how many times each letter occurs


		sc.close();
	}
}
