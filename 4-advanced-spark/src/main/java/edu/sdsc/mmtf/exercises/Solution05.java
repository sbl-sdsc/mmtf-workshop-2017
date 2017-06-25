package edu.sdsc.mmtf.exercises;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.biojava.nbio.structure.Element;
import org.rcsb.mmtf.api.StructureDataInterface;

import edu.sdsc.mmtf.spark.io.MmtfReader;

public class Solution05 {
	
	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Problem03.class.getSimpleName());
		JavaSparkContext sc = new JavaSparkContext(conf);

		List<String> pdbIds = Arrays.asList("1STP","4HHB");
		
		JavaPairRDD<String, StructureDataInterface> pdb = MmtfReader
	        		.downloadMmtfFiles(pdbIds, sc);
		
		// TODO complete the code in the getHeavyAtomMolecularWeight method (see below)
		
		
		// TODO print the molecular weight for the structures
		pdb.foreach(t -> System.out.println(t._1 + ": " + getHeavyAtomMolecularWeight(t._2)));
		
		// TODO filter by molecular weight < 20000
		pdb = pdb.filter(t -> getHeavyAtomMolecularWeight(t._2) < 20000);
		
		System.out.println("Structures with molecular weight < 20000: " + pdb.count());
	}
	
	/**
	 * Calculates the total molecular weight for all atoms in a structure.
	 * If a structure contains multiple models, only the first
	 * model is considered. Note, this method is for training
	 * purposes only. It does not take into account
	 * non-observed atoms, e.g., hydrogen atoms!
	 * 
	 * @param structure input structure
	 * @return 
	 */
	private static double getHeavyAtomMolecularWeight(StructureDataInterface structure) {
		double molecularWeight = 0.0;

		// Global indices that point into the flat (columnar) data structure
		int chainIndex = 0;
		int groupIndex = 0;

		int model = 0; // only loop over first model

		// Loop over chains in first model
		for (int j = 0; j < structure.getChainsPerModel()[model]; j++) {

			// Loop over groups in a chain
			for (int k = 0; k < structure.getGroupsPerChain()[chainIndex]; k++) {

				// Unique groups (residues) are stored only once in a dictionary. 
				// We need to get the group type to retrieve group information
				int groupType = structure.getGroupTypeIndices()[groupIndex];	

				// Loop over atoms in a group retrieved from the dictionary
				for (int m = 0; m < structure.getNumAtomsInGroup(groupType); m++) {

					// TODO get element symbol
					String elementSymbol = structure.getGroupElementNames(groupType)[m];

					// Element is an enumeration from BioJava
					Element element = Element.valueOf(elementSymbol);
					
					// consider only heavy atoms
					if (element.isHeavyAtom()) {
						molecularWeight += element.getAtomicMass();
					}
				}
				groupIndex++; // update global group index
			}
			chainIndex++; // update global chain index
		}
		
		return molecularWeight;
	}
}
