package edu.sdsc.mmtf.exercises;

import java.io.IOException;
import java.io.PrintWriter;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.align.util.AtomCache;
import org.biojava.nbio.structure.io.FileParsingParameters;

/**
 * 
 * Load a structure in the different formats.
 * 
 * @author Aleix Lafita
 *
 */
public class Demo02 {

	public static void main(String[] args) throws IOException, StructureException {
		
		// Structure loading object
		AtomCache cache = new AtomCache();
		
		// File parsing parameters
		FileParsingParameters params = cache.getFileParsingParams();
		
		// Select the file format to use (mmtf is the default)
		//cache.setUseMmtf(false);
		//cache.setUseMmCif(false);
		
		// Select some other parameters
		//params.setParseSecStruc(true);
		
		// Print some of the parameters to check them
		System.out.println("Path: " + cache.getCachePath());
		System.out.println("Using MMTF: " + cache.isUseMmtf());
		System.out.println("Parse SS: " + params.isParseSecStruc());
		System.out.println("Maximum atoms: " + params.getMaxAtoms());
		
		// Measure the parsing time
		long start = System.nanoTime();
		
		// Download the lysozyme structure 148L
		Structure lysozyme = cache.getStructure("148L");
		
		System.out.println("Parsing time: " + (System.nanoTime() - start) / 1000000 + " ms");
		
		//System.out.println(lysozyme);
		
		PrintWriter out = new PrintWriter(
				"/Users/aleixlafita/Downloads/148l_biojava.pdb");
		out.println(lysozyme.toPDB());
		out.close();

	}

}
