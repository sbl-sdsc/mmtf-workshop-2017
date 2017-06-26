package edu.sdsc.mmtf.exercises;

import org.apache.spark.sql.SparkSession;

public class Problem12 {

	/** Creating dataset example.
	 *
	 * @author Yana Valasatava
	 */
	
	public static void main(String[] args) {

		SparkSession sparkSession = SparkSession.builder().master("local[*]").appName("app")
				.config("spark.driver.maxResultSize", "4g")
				.config("spark.executor.memory", "4g")
				.config("spark.debug.maxToStringFields", 80)
				.getOrCreate();

		// TODO: create a dataset by reading a .csv file

		// TODO: use filter function to get genes living on negative strand


		sparkSession.stop();
	}
}
