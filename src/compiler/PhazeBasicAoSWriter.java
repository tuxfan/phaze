/*----------------------------------------------------------------------------*
 * PhazeBasicAoSWriter
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import java.io.*;
import java.util.*;

public class PhazeBasicAoSWriter implements PhazeWriter {

	private PhazeBoilerPlate bp_;
	private PrintWriter file_;

	public void writeHeader(Hashtable<String, PhazeStruct> structs,
		String baseName, CommandLine line) throws Exception {

		/*----------------------------------------------------------------------*
		 *
		 *----------------------------------------------------------------------*/

		bp_ = new PhazeBoilerPlate();
		file_ = new PrintWriter(baseName + ".h");

		file_.print(String.format(bp_.genericHeader,
			"Phaze interface for " + baseName + " input file"));

		file_.print("\n#ifndef phaze_h\n");
		file_.print("#define phaze_h\n\n");

		/*----------------------------------------------------------------------*
		 *
		 *----------------------------------------------------------------------*/

		Set<PhazeVariable> vars = structs.get("cell").variables();

		file_.print(bp_.commentLineStart);
		file_.println(" * cell_data_t structure prototype");
		file_.println(bp_.commentLineEnd);

		file_.println("struct cell_data_t {");

// FIXME: NEED TO HANDLE STORAGE CLASSES

		int dim = line.hasOption("d") ?
			Integer.parseInt(line.getOptionValue("d")) : 3;

		Iterator<PhazeVariable> ita = vars.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();

			switch(var.type) {
				case position:
				case vector:
					file_.println("\tdouble " + var.id + "[" + dim + "];");
					break;
				default:
					file_.println("\t" + var.toString());
					break;
			} // switch
		} // while

		file_.println("} // struct cell_data_t\n");

		// static interface
		file_.print(bp_.staticInterface);

		// header file finalization
		file_.print("\n#endif // phaze_h\n");

		file_.close();
	} // writeHeader

} // class PhazeBasicAoSWriter
