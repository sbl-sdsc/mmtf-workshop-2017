package edu.sdsc.mmtf.exercises;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import edu.sdsc.mmtf.spark.io.MmtfReader;

public class Benchmark {

	/**
	 * MMTF Hadoop file reading benchmark.
	 * 
	 * @author Peter Rose
	 *
	 */

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Benchmark.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		String path = System.getProperty("MMTF_REDUCED");
		if (path == null) {
			System.err.println("Path for reduced Hadoop sequence file has not been set");
			System.exit(-1);
		}
		
        runBenchmark(sc, path, "reduced");
        
        path = System.getProperty("MMTF_FULL");
		if (path == null) {
			System.err.println("Path for full Hadoop sequence file has not been set");
			System.exit(-1);
		}
		
        runBenchmark(sc, path, "full");
        
        sc.close();
	}

	private static void runBenchmark(JavaSparkContext sc, String path, String type) {
		long start = System.nanoTime();
	    long count = MmtfReader.readSequenceFile(path, sc).count();    
		long end = System.nanoTime();
	   
		printMetrics(type, count, start, end);
	}

	private static void printMetrics(String type, long count, long start, long end) {
		int cores = Runtime.getRuntime().availableProcessors();
		String osName = System.getProperty("os.name");
	    String osType= System.getProperty("os.arch");
	    String osVersion= System.getProperty("os.version");
	    String time = String.format("%.1f", (end-start)/1E9);
	    
	    System.out.println(type + ", " + count + ", " + time + ", " 
	    + cores + ", " + osName + ", " + osType + ", " + osVersion);
	}
}
