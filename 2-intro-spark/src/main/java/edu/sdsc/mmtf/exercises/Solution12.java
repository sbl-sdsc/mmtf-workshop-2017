package edu.sdsc.mmtf.exercises;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Solution12 {

	/** Creating dataset example.
	 *
	 * @author Yana Valasatava
	 */

	private String getDataFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource("data.csv").getFile();
	}

	public static void main(String[] args) {

		SparkSession sparkSession = SparkSession.builder().master("local[*]").appName("app")
				.config("spark.driver.maxResultSize", "4g")
				.config("spark.executor.memory", "4g")
				.config("spark.debug.maxToStringFields", 80)
				.getOrCreate();

		// TODO
		// read .csv file into dataframe

		Solution12 s = new Solution12();
		String path = s.getDataFile();

		Dataset<Row> data = sparkSession.read().option("header", "true").csv(path);

		data.printSchema();
		data.show(10);
		System.out.println(data.count());
		
		sparkSession.stop();
	}
}
