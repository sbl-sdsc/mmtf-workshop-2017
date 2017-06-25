package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;


public class Solution02 {

	/** Read a text file.
	 * 
	 * @author Yana Valasatava
	 *
	 */

	private String getDataFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource("data.txt").getFile();
	}

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Solution02.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		Solution02 s = new Solution02();
		String path = s.getDataFile();

        // TODO 
		// Create JavaRDD and print out file content
		JavaRDD<String> data = sc.textFile(path);
		data.foreach(i -> System.out.println(i));

		sc.close();
	}
}
