package edu.sdsc.mmtf.exercises;

import java.util.Collections;
import java.util.List;

import org.apache.spark.api.java.function.Function;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.secstruc.SecStrucCalc;
import org.biojava.nbio.structure.secstruc.SecStrucState;

/**
 * Maps a BioJava structure to a DSSP secondary structure string.
 * 
 * @author Peter Rose
 *
 */
public class BioJavaStructureToDssp implements Function<Structure, String> {
	private static final long serialVersionUID = -1234631110714432408L;
	
//	private SecStrucCalc calculator = new SecStrucCalc(); // not serializable
    
	@Override
	public String call(Structure structure) throws Exception {
		
		List<SecStrucState> states = Collections.emptyList();
		
		// calculate DSSP secondary structure
		try {
			SecStrucCalc calculator = new SecStrucCalc();
			states = calculator.calculate(structure, false);
		} catch (StructureException e) {
			// this happens if there are not at least 4 consecutive residues
		}
		
		// create a DSSP string
		StringBuilder dssp = new StringBuilder();
		for (SecStrucState state: states) {
			dssp.append(state.getType().type);
		}

		return dssp.toString();
	};
}
