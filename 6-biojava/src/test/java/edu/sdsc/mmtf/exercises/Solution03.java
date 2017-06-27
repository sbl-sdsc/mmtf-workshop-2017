package edu.sdsc.mmtf.exercises;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.cluster.SubunitClustererMethod;
import org.biojava.nbio.structure.cluster.SubunitClustererParameters;
import org.biojava.nbio.structure.gui.BiojavaJmol;
import org.biojava.nbio.structure.symmetry.axis.AxisAligner;
import org.biojava.nbio.structure.symmetry.core.QuatSymmetryDetector;
import org.biojava.nbio.structure.symmetry.core.QuatSymmetryParameters;
import org.biojava.nbio.structure.symmetry.core.QuatSymmetryResults;
import org.biojava.nbio.structure.symmetry.jmolScript.JmolSymmetryScriptGenerator;
import org.biojava.nbio.structure.symmetry.jmolScript.JmolSymmetryScriptGeneratorPointGroup;

import java.io.IOException;

/**
 * 
 * Calculate and show in Jmol the symmetry of a protein assembly.
 * 
 * @author Aleix Lafita
 * 
 */
public class Solution03 {

	public static void main(String[] args) throws IOException, StructureException {

		// TODO download the biological assembly number 1 of 1hv4 using StructureIO
		Structure structure = StructureIO.getBiologicalAssembly("1hv4", 1,true);

		// Initialize parameters to use for the symmetry calculation
		SubunitClustererParameters cp = new SubunitClustererParameters();
		QuatSymmetryParameters sp = new QuatSymmetryParameters();
		
		// TODO use structural clustering method (pseudosymmetry)
		cp.setClustererMethod(SubunitClustererMethod.STRUCTURE);
		
		// Calculate the symmetry in a single line
		QuatSymmetryResults symmetry = QuatSymmetryDetector.calcGlobalSymmetry(structure, sp, cp);
		
		// TODO print stoichiometry and symmetry of the assembly
		System.out.println(String.format("The stoichiometry is %s.", symmetry.getStoichiometry()));
		System.out.println(String.format("The symmetry is %s.", symmetry.getSymmetry()));
		
		// The code below just displays the structure in Jmol and shows the symmetry
		// Nothing is required, but play with it if you are interested
		
		// Show the structure as a cartoon
		String script = "select all; spacefill off; wireframe off; cartoon on;";

		// Objects needed for the BioJava - Jmol interaction
		AxisAligner aligner = AxisAligner.getInstance(symmetry);
		JmolSymmetryScriptGenerator scriptGenerator = JmolSymmetryScriptGeneratorPointGroup.getInstance(aligner, "g");
		
		// Orient the structure using the symmetry
		script += scriptGenerator.getOrientationWithZoom(0);
		
		// Draw the symmetric polyhedron around the structure
		script += scriptGenerator.drawPolyhedron() + "draw poly* on;";
		
		// Draw the axes of symmetry
		script += scriptGenerator.drawAxes() + "draw axes* on;";
		
		// Color the subunits of the assembly to highlight the symmetry
		script += scriptGenerator.colorBySymmetry();

		// Open a Jmol instance (window)
		BiojavaJmol jmol = new BiojavaJmol();
		
		// Send the biojava structure to the Jmol window
		jmol.setStructure(structure);

		// Apply the symmetry script we have created
		jmol.evalString(script);

	}
}
