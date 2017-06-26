package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class Solution06 {

	/** Use lambda expression to modify values in JavaPairRDD.
	 *
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution06.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		List<Tuple2<String, Integer>> data = Arrays.asList(
				new Tuple2<String, Integer>("first", 1),
				new Tuple2<String, Integer>("second", 2));

		JavaRDD<Tuple2<String, Integer>> rdd = sc.parallelize(data);
		JavaPairRDD<String, Integer> pairRdd = JavaPairRDD.fromJavaRDD(rdd);

		// TODO
		// use lambda expression to modify values in JavaPairRDD

		JavaPairRDD<String, Integer> newPairRdd = pairRdd.mapValues(v -> 2 * v);
		newPairRdd.foreach(t -> System.out.println(t._1 + ": new value is " + t._2));

		sc.close();
	}
}
