package edu.sdsc.mmtf.exercises;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class Solution14 {

	/** Querying dataset example.
	 *
	 * @author Yana Valasatava
	 */

	private String getDataFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource("data.csv").getFile();
	}

	public static void main(String[] args) {

		SparkSession sparkSession = SparkSession.builder()
				.master("local[*]").appName("app")
				.getOrCreate();

		// TODO: create a dataset by reading a .csv file
		Solution14 s = new Solution14();
		String path = s.getDataFile();

		Dataset<Row> data = sparkSession.read().option("header", "true").csv(path);

		// Register the DataFrame as a SQL temporary view and run SQL query

		data.createOrReplaceTempView("table");

		sparkSession.sql("SELECT * FROM table WHERE chromosome='chr1'").show();

		sparkSession.sql("SELECT gene_name FROM table WHERE chromosome='chr1' GROUP BY gene_name").show();

		// Use Dataset API

		data.filter(col("chromosome").equalTo("chr1")).select(col("gene_name")).distinct().;

		sparkSession.stop();
	}
}
