package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;

public class Solution03c {

	/** Filter example: get even numbers from a sequence of integers ranging from 1 to 10
	 * 
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution03c.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		// Parallelized with 2 partitions
		JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2);

        // TODO 
		// Get only even numbers from a sequence of integers ranging from 1 to 10

		JavaRDD<Integer> rddFiltered = rdd.filter(new Function<Integer, Boolean>() {

			private static final long serialVersionUID = 2524460370125430748L;

			@Override
			public Boolean call(Integer e) throws Exception {
				return e % 2 == 0;
			}
		});

		rddFiltered.foreach(e -> System.out.println(e));

		sc.close();
	}
}