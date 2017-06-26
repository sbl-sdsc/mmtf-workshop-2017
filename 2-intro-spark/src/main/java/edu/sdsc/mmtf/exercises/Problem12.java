package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;

public class Problem12 {

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


		sparkSession.stop();
	}
}
