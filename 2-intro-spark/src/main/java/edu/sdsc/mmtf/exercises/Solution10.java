package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.spark_project.guava.collect.Iterators;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class Solution10 {

	/** Example on usage of groupByKey() transformation.
	 *
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution10.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		List<Tuple2<String, String>> data = Arrays.asList(
				new Tuple2("25/Jun/2017", "192.168.2.20"),
				new Tuple2("26/Jun/2017", "127.0.0.1"),
				new Tuple2("26/Jun/2017", "192.168.2.35"));

		JavaRDD rdd = sc.parallelize(data);
		JavaPairRDD<String, String> pairRdd = JavaPairRDD.fromJavaRDD(rdd).cache();

		// TODO
		// count the number of requests every day
		pairRdd.groupByKey()
				.mapValues(v -> Iterators.size(v.iterator()))
				.foreach(t -> System.out.println(t._1 + ": " + t._2));

		sc.close();
	}
}
