package edu.sdsc.mmtf.exercises;

import org.apache.spark.sql.*;

import java.util.ArrayList;
import java.util.List;

public class Problem13 {

	/** Creating dataset example.
	 *
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkSession sparkSession = SparkSession.builder().master("local[*]").appName("app")
				.config("spark.driver.maxResultSize", "4g")
				.config("spark.executor.memory", "4g")
				.getOrCreate();

		List<Problem13Bean> list = new ArrayList<>();
		list.add(new Problem13Bean("one", 1, 1.0));
		list.add(new Problem13Bean("two", 2, 2.0));
		list.add(new Problem13Bean("three", 3, 3.0));

		// TODO: create a dataset from list

		sparkSession.stop();
	}
}
