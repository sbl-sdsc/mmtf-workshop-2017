package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Solution09 {

	/** FlatMapToPair() on JavaPairRDD example.
	 *
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution09.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		List<Tuple2<String, Integer>> data = Arrays.asList(
				new Tuple2<String, Integer>("first", 2),
				new Tuple2<String, Integer>("second", 15));

		JavaRDD<Tuple2<String, Integer>> rdd = sc.parallelize(data);
		JavaPairRDD<String, Integer> pairRdd = JavaPairRDD.fromJavaRDD(rdd);

		// TODO
		//
		JavaPairRDD<String, String> newPairRdd = pairRdd.flatMapToPair(new PairFlatMapFunction<Tuple2<String, Integer>, String, String>() {
					
			private static final long serialVersionUID = -1163467461714806699L;

			@Override
			public Iterator<Tuple2<String, String>> call(Tuple2<String, Integer> t) throws Exception {
				List<Tuple2<String, String>> list = new ArrayList<>();
				list.add(new Tuple2<>(t._1+"_add", String.valueOf(t._2+3)));
				list.add(new Tuple2<>(t._1+"_subtract", String.valueOf(t._2-1)));

				return list.iterator();
			}
		});
		newPairRdd.foreach(t -> System.out.println(t._1 + ": " + t._2));

		sc.close();
	}
}
