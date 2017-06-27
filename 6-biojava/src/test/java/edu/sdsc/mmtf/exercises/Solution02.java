package edu.sdsc.mmtf.exercises;

import java.io.IOException;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Element;
import org.biojava.nbio.structure.Group;
import org.biojava.nbio.structure.GroupType;
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
public class Solution02 {

	public static void main(String[] args) throws IOException, StructureException {

		// Configuration of the structure loading
		AtomCache cache = new AtomCache();
		cache.setUseMmtf(true); // default

		// Download the hemoglobin structure 2HHB
		Structure hemoglobin = cache.getStructure("2HHB");

		// TODO obtain the number of models in the structure
		int models = hemoglobin.nrModels();

		// TODO obtain the number of polymer chains of the first model (index 0)
		int chains = hemoglobin.getPolyChains().size(); // option 1

		// TODO obtain the number of amino acids in chain A
		int aminoacids = hemoglobin.getChain("A").getAtomGroups(GroupType.AMINOACID).size();

		// TODO obtain the total number of oxygens in the aminoacid groups of
		// chain A
		int oxygens = 0;
		for (Group g : hemoglobin.getChain("A").getAtomGroups(GroupType.AMINOACID)) {
			for (Atom a : g.getAtoms()) {
				if (a.getElement() == Element.O)
					oxygens++;
			}
		}

		// Print the results to the console
		System.out.println(String.format(
				"The AU of the hemoglobin structure 2HHB contains: "
				+ "%d models, %d polymer chains in model 1, and "
				+ "%d amino acids and %d oxygen atoms in chain A.",
				models, chains, aminoacids, oxygens));

	}

}
