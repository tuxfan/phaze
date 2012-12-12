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
		bp_ = new PhazeBoilerPlateC();
	} // PhazeBasicAoSWriter

	public void writeHeader(String inputFile, CommandLine line,
		Hashtable<String, PhazeStruct> structs) throws Exception {

		/*----------------------------------------------------------------------*
		 * Keep track of static variables. These will become globals
		 * in the library output.
		 *----------------------------------------------------------------------*/

/*
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
*/

		/*----------------------------------------------------------------------*
		 * Create file and write generic comment
		 *----------------------------------------------------------------------*/

// FIXME: Move up in the call chain
		int slash = inputFile.lastIndexOf('/');
		String inputDir = slash == -1 ? "./" : inputFile.substring(0, slash);

		String path = line.hasOption("d") ?
			line.getOptionValue("d") : inputDir;
// FIXME

		file_ = new PrintWriter(path + "/phaze.h");

		file_.print(String.format(bp_.genericHeader(inputFile)));

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

		// check dimension
		int dim = line.hasOption("D") ?
			Integer.parseInt(line.getOptionValue("D")) : 3;

		/*----------------------------------------------------------------------*
		 * Output cell_t
		 *----------------------------------------------------------------------*/

		file_.print(bp_.startComment());
		file_.println(" * cell_t structure prototype");
		file_.println(bp_.endComment());
		file_.println("struct cell_t {");
		PhazeCUtils.printAsLocals(file_,
			structs.get("cell").variables(), dim);
		file_.println("}; // struct cell_t\n");

		/*----------------------------------------------------------------------*
		 * Output material_t
		 *----------------------------------------------------------------------*/

		file_.print(bp_.startComment());
		file_.println(" * material_t structure prototype");
		file_.println(bp_.endComment());
		file_.println("struct material_t {");
		file_.println("\tchar _phz_private[32];");
		PhazeCUtils.printAsLocals(file_,
			structs.get("material").variables(), dim);
		file_.println("}; // struct material_t\n");

		/*----------------------------------------------------------------------*
		 * Output composition_t
		 *----------------------------------------------------------------------*/

		file_.print(bp_.startComment());
		file_.println(" * composition_t structure prototype");
		file_.println(bp_.endComment());
		file_.println("struct composition_t {");
		file_.println("\tchar _phz_private[32];");
		PhazeCUtils.printAsLocals(file_,
			structs.get("composition").variables(), dim);
		file_.println("}; // struct composition_t\n");

//
//
//

		// static interface
		file_.print(bp_.staticInterface());

		// header file finalization
		file_.print("\n#endif // phaze_h\n");

		file_.close();
	} // writeHeader

} // class PhazeBasicAoSWriter
