package edu.sdsc.mmtf.exercises;

import java.io.IOException;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.align.util.AtomCache;

/**
 * 
 * Traverse BioJava structures.
 * 
 * @author Aleix Lafita
 *
 */
public class Problem02 {

	public static void main(String[] args) throws IOException, StructureException {

		// Configuration of the structure loading
		AtomCache cache = new AtomCache();
		cache.setUseMmtf(true); // default

		// Download the hemoglobin structure 2HHB
		Structure hemoglobin = cache.getStructure("2HHB");

		// TODO obtain the number of models in the structure
		int models = 0;

		// TODO obtain the number of chains of the first model
		int chains = 0;

		// TODO obtain the number of amino acid groups in chain A
		int aminoacids = 0;

		// TODO obtain the total number of oxygens in the aminoacid groups of
		// chain A
		int oxygens = 0;

		// Print the results to the console
		System.out.println(String.format(
				"The AU of the hemoglobin structure 2HHB contains: "
				+ "%d models, %d polymer chains in model 1, and "
				+ "%d amino acids and %d oxygen atoms in chain A.",
				models, chains, aminoacids, oxygens));

	}

}
