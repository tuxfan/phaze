/*----------------------------------------------------------------------------*
 * PhazeBasicSoAWriter
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import java.io.*;
import java.util.*;

public class PhazeBasicSoAWriter implements PhazeWriter {

	private PhazeBoilerPlate bp_;
	private PrintWriter header_;

	public PhazeBasicSoAWriter() {
		bp_ = new PhazeBoilerPlateC();
	} // PhazeBasicSoAWriter

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

		header_ = new PrintWriter(path + "/phaze.h");

		header_.print(String.format(bp_.genericHeader(inputFile)));

		header_.print("\n#ifndef phaze_h\n");
		header_.print("#define phaze_h\n\n");

		/*----------------------------------------------------------------------*
		 *
		 *----------------------------------------------------------------------*/

/*
		Iterator<PhazeVariable> ita = statics.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();

			header_.println(var.staticString());
		} // while
*/

		/*----------------------------------------------------------------------*
		 *
		 *----------------------------------------------------------------------*/

		header_.print(bp_.startComment());
		header_.println(" * cell_t structure prototype");
		header_.println(bp_.endComment());

		header_.println("struct cell_t {");
		printVariables(structs.get("cell").variables());
		header_.println("}; // struct cell_t\n");

		/*----------------------------------------------------------------------*
		 *
		 *----------------------------------------------------------------------*/

		header_.print(bp_.startComment());
		header_.println(" * material_t structure prototype");
		header_.println(bp_.endComment());

		header_.println("struct material_t {");
		printVariables(structs.get("material").variables());
		header_.println("}; // struct material_t\n");

//
//
//

		// static interface
		header_.print(bp_.staticInterface());

		// header file finalization
		header_.print("\n#endif // phaze_h\n");

		header_.close();
	} // writeHeader

	void printVariables(Set<PhazeVariable> vars) {
		Iterator<PhazeVariable> ita = vars.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();

			switch(var.type) {
				case pos32:
				case vec32:
					header_.println("\tfloat * " + var.id + ";");
					break;
				case pos64:
				case vec64:
					header_.println("\tdouble * " + var.id + ";");
					break;
				default:
					header_.println("\t" + var.type.toString() +
						" * " + var.id + ";");
					break;
			} // switch
		} // while
	} // printVariables

} // class PhazeBasicSoAWriter
