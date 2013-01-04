/*----------------------------------------------------------------------------*
 * PhazeBasicWriter
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import java.io.*;
import java.util.*;

public class PhazeBasicWriter implements PhazeWriter {

	private PhazeBoilerPlate bp_;
	private PrintWriter types_;
	private PrintWriter header_;

	public PhazeBasicWriter() {
		bp_ = new PhazeBoilerPlateC();
	} // PhazeBasicWriter

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

		/*----------------------------------------------------------------------*
		 * header setup
		 *----------------------------------------------------------------------*/

		header_ = new PrintWriter(path + "/phaze.h");

		header_.print(String.format(bp_.genericHeader(inputFile)));

		header_.print("\n#ifndef phaze_h\n");
		header_.print("#define phaze_h\n\n");

		header_.print("#define _include_phaze_h\n");
		header_.print("#include <phztypes.h>\n\n");

		/*----------------------------------------------------------------------*
		 * types setup
		 *----------------------------------------------------------------------*/

		types_ = new PrintWriter(path + "/phztypes.h");

		types_.print(String.format(bp_.genericHeader(inputFile)));

		types_.print("\n#ifndef phztypes_h\n");
		types_.print("#define phztypes_h\n\n");
		types_.print("#if !defined(_include_phaze_h)\n");
		types_.print("#error \"Error: do not include this file directly");
		types_.print(", use #include <phaze.h>\"\n");
		types_.print("#endif\n\n");

		/*----------------------------------------------------------------------*
		 * Options
		 *----------------------------------------------------------------------*/

/*
		Iterator<PhazeVariable> ita = statics.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();

			header_.println(var.staticString());
		} // while
*/

		// check dimension
		int dim = line.hasOption("D") ?
			Integer.parseInt(line.getOptionValue("D")) : 3;

		boolean aos = true;
		
		if(line.hasOption("l")) {
			String value = line.getOptionValue("l");

			if(value.equals("SoA")) {
				aos = false;
			}
		} // if

		/*----------------------------------------------------------------------*
		 * Output cell_t
		 *----------------------------------------------------------------------*/

		types_.print(bp_.startComment());
		types_.println(" * cell_t structure prototype");
		types_.println(bp_.endComment());
		types_.println("struct cell_t {");

		if(aos) {
			PhazeCUtils.printAsLocals(types_,
				structs.get("cell").variables(), dim);
		}
		else {
			PhazeCUtils.printAsPointers(types_,
				structs.get("cell").variables());
		} // if

		types_.println("\tchar _phz_private[32];");
		types_.println("}; // struct cell_t\n");

		types_.println("typedef cell_t * phz_cell;\n");

		/*----------------------------------------------------------------------*
		 * Output material_t
		 *----------------------------------------------------------------------*/

		types_.print(bp_.startComment());
		types_.println(" * material_t structure prototype");
		types_.println(bp_.endComment());
		types_.println("struct material_t {");

		if(aos) {
			PhazeCUtils.printAsLocals(types_,
				structs.get("material").variables(), dim);
		}
		else {
			PhazeCUtils.printAsPointers(types_,
				structs.get("material").variables());
		} // if

		types_.println("\tchar _phz_private[32];");
		types_.println("}; // struct material_t\n");

		types_.println("typedef material_t * phz_material;\n");

		/*----------------------------------------------------------------------*
		 * Output composition_t
		 *----------------------------------------------------------------------*/

		types_.print(bp_.startComment());
		types_.println(" * composition_t structure prototype");
		types_.println(bp_.endComment());
		types_.println("struct composition_t {");

		if(aos) {
			PhazeCUtils.printAsLocals(types_,
				structs.get("composition").variables(), dim);
		}
		else {
			PhazeCUtils.printAsPointers(types_,
				structs.get("composition").variables());
		} // if

		types_.println("\tchar _phz_private[32];");
		types_.println("}; // struct composition_t\n");

		types_.println("typedef material_t * phz_composition;");

//
//
//

		// static interface
		header_.print(bp_.staticInterface());

		/*----------------------------------------------------------------------*
		 * Finalize header
		 *----------------------------------------------------------------------*/

		header_.print("\n#endif // phaze_h\n");
		header_.close();

		/*----------------------------------------------------------------------*
		 * Finalize types
		 *----------------------------------------------------------------------*/

		types_.print("\n#endif // phztypes_h\n");
		types_.close();
	} // writeHeader

} // class PhazeBasicWriter
