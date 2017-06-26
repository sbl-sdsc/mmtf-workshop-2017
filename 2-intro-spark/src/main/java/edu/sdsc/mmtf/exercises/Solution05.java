package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Solution05 {

	/** Create JavaPairRDD.
	 * 
	 * @author Yana Valasatava
	 */

	private String getDataFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource("data.txt").getFile();
	}

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution05.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		Solution05 s = new Solution05();
		String path = s.getDataFile();

        // TODO 
		// create JavaPairRDD from JavaRDD and print out the number of words in each line
		JavaRDD<String> lines = sc.textFile(path);

		JavaPairRDD<Integer, String> wordsCount = lines
				.mapToPair(line -> new Tuple2<Integer, String>(line.split(" ").length, line));

		wordsCount.foreach(e -> System.out.println("Number of words in '"+ e._2+ "' is "+ e._1));

		sc.close();
	}
}
