package edu.sdsc.mmtf.exercises;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.rcsb.mmtf.api.StructureDataInterface;

import edu.sdsc.mmtf.spark.ml.JavaRDDToDataset;
import scala.Tuple2;

/**
 * Create a dataset with unit cell information.
 * The unit cell information consists of: 
 * Space group and 6 cell dimensions:
 * length of the unit cell a, b, and c and 
 * angles are the alpha, beta, and gamma.
 * 
 * <p>See <a href="https://github.com/rcsb/mmtf/blob/master/spec.md#unitcell">unit cell list</a>
 * 
 * <p>Example: get dataset of space group and unit cell dimensions
 * <pre>
 * {@code   
 * Dataset<Row> unitCellData = UnitCellExtractor.getDataset(pdb);
 * unitCellData.show(10);
 * }
 * </pre>
 * 
 * @author Peter Rose
 *
 */
public class UnitCellExtractorSolution03 {

	public static Dataset<Row> getDataset(JavaPairRDD<String, StructureDataInterface> structure) {
		
		JavaRDD<Row> rows = structure.flatMap(new FlatMapFunction<Tuple2<String, StructureDataInterface>, Row>() {
			private static final long serialVersionUID = -8235456885515776259L;

			public Iterator<Row> call(Tuple2<String, StructureDataInterface> t) {
				List<Row> rows = new ArrayList<>(1);
				
				String spaceGroup = t._2.getSpaceGroup();
				
				// TODO assign the unit cell dimensions:
				// a, b, c, alpha, beta, gamma
				// see https://github.com/rcsb/mmtf/blob/master/spec.md#unitcell
				float[] dimensions = t._2.getUnitCell();
				if (spaceGroup != null && dimensions != null) {
					float a = dimensions[0];
					float b = dimensions[1];
					float c = dimensions[2];
					float alpha = dimensions[3];
					float beta = dimensions[4];
					float gamma = dimensions[5];

					rows.add(RowFactory.create(t._1, spaceGroup, a, b, c, alpha, beta, gamma));
				}
				return rows.iterator();
			}
		});

		// convert JavaRDD<Row> to Dataset<Row>
		return JavaRDDToDataset.getDataset(rows,"structureId","spaceGroup","a","b","c", "alpha","beta","gamma");
	}
}
