package edu.sdsc.mmtf.exercises;

import org.apache.spark.sql.SparkSession;

import java.io.Serializable;

public class Problem13Bean implements Serializable {

	/** Data bean.
	 *
	 * @author Yana Valasatava
	 */

	private String a;
	private int b;
	private double c;

	public Problem13Bean(String a, int b, double c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}
}
