package edu.sdsc.mmtf.exercises;

import org.apache.spark.sql.SparkSession;

public class Problem14 {

	/** Querying dataset example.
	 *
	 * @author Yana Valasatava
	 */

	public static void main(String[] args) {

		SparkSession sparkSession = SparkSession.builder()
				.master("local[*]").appName("app")
				.getOrCreate();

		// TODO:

		// Register the DataFrame as a SQL temporary view and run SQL query

		// Use Dataset API

		sparkSession.stop();
	}
}
