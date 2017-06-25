package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class Problem06 {

	/**
	 * Calculate the sum of values in JavaPairRDD.
	 *
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem06.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		List<Tuple2<String, List<Integer>>> data = Arrays.asList(
				new Tuple2("first", Arrays.asList(1, 2)),
				new Tuple2("second", Arrays.asList(5, 3)));

		JavaRDD rdd = sc.parallelize(data);
		JavaPairRDD pairRdd = JavaPairRDD.fromJavaRDD(rdd);

		// TODO
		// calculate the sum of values in JavaPairRDD
		

		sc.close();
	}
}
