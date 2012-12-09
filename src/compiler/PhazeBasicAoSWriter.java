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

	public PhazeBasicAoSWriter() {
		bp_ = new PhazeBoilerPlate();
	} // PhazeBasicAoSWriter

	public void writeHeader(Hashtable<String, PhazeStruct> structs,
		String baseName, CommandLine line) throws Exception {

		/*----------------------------------------------------------------------*
		 * Keep track of static variables. These will become globals
		 * in the library output.
		 *----------------------------------------------------------------------*/

		Set<PhazeVariable> statics = new TreeSet<PhazeVariable>();

		Enumeration e = structs.keys();
		while(e.hasMoreElements()) {
			Set<PhazeVariable> vars = structs.get(e.nextElement()).variables();

			Iterator<PhazeVariable> ita = vars.iterator();
			while(ita.hasNext()) {
				PhazeVariable var = ita.next();

				if(var.isStatic) {
					statics.add(var);
				} // if
			} // while
		} // while

		/*----------------------------------------------------------------------*
		 * Create file and write generic comment
		 *----------------------------------------------------------------------*/

		file_ = new PrintWriter(baseName + ".h");

		file_.print(String.format(bp_.genericHeader,
			"Phaze interface for " + baseName + " input file"));

		file_.print("\n#ifndef phaze_h\n");
		file_.print("#define phaze_h\n\n");

		/*----------------------------------------------------------------------*
		 *
		 *----------------------------------------------------------------------*/

/*
		Iterator<PhazeVariable> ita = statics.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();

			file_.println(var.staticString());
		} // while
*/

		/*----------------------------------------------------------------------*
		 *
		 *----------------------------------------------------------------------*/

		Set<PhazeVariable> vars = structs.get("cell").variables();

		file_.print(bp_.commentLineStart);
		file_.println(" * cell_t structure prototype");
		file_.println(bp_.commentLineEnd);

		file_.println("struct cell_t {");

// FIXME: NEED TO HANDLE STORAGE CLASSES

		// check dimension
		int dim = line.hasOption("d") ?
			Integer.parseInt(line.getOptionValue("d")) : 3;

		/*----------------------------------------------------------------------*
		 * Iterate variables
		 *----------------------------------------------------------------------*/

		ita = vars.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();

			switch(var.type) {
				case position:
				case vector:
					file_.println("\tdouble " + var.id + "[" + dim + "];");
					break;
				default:
// FIXME: make sure toString gives valid output
					file_.println("\t" + var.toString());
					break;
			} // switch
		} // while

		file_.println("}; // struct cell_t\n");

		/*----------------------------------------------------------------------*
		 *
		 *----------------------------------------------------------------------*/

		vars = structs.get("material").variables();

		file_.print(bp_.commentLineStart);
		file_.println(" * material_t structure prototype");
		file_.println(bp_.commentLineEnd);

		file_.println("struct material_t {");

// FIXME: NEED TO HANDLE STORAGE CLASSES

		ita = vars.iterator();
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

		file_.println("}; // struct material_t\n");

//
//
//

		// static interface
		file_.print(bp_.staticInterface);

		// header file finalization
		file_.print("\n#endif // phaze_h\n");

		file_.close();
	} // writeHeader

} // class PhazeBasicAoSWriter
