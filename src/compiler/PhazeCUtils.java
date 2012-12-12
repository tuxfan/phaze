/*----------------------------------------------------------------------------*
 * PhazeCUtils
 *----------------------------------------------------------------------------*/

package Phaze;

import java.io.*;
import java.util.*;

public class PhazeCUtils {

	public static String toLocal(PhazeVariable var, int dimension) {
		switch(var.type) {
			case pos32:
			case vec32:
				return "\tfloat" + var.id + "[" + dimension + "];";
			case pos64:
			case vec64:
				return "\tdouble " + var.id + "[" + dimension + "];";
			default:
				return "\t" + var.type.toString() + " " + var.id + ";";
		} // switch
	} // toLocal

	public static String toPointer(PhazeVariable var) {
		switch(var.type) {
			case pos32:
			case vec32:
				return "\tfloat * " + var.id + ";";
			case pos64:
			case vec64:
				return "\tdouble * " + var.id + ";";
			default:
				return "\t" + var.type.toString() + " * " + var.id + ";";
		} // switch
	} // toPointer

	static void printAsLocals(PrintWriter file,
		Set<PhazeVariable> vars, int dimension) {
		Iterator<PhazeVariable> ita = vars.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();
			file.println(PhazeCUtils.toLocal(var, dimension));
		} // while
	} // printAsLocals

	static void printAsPointers(PrintWriter file,
		Set<PhazeVariable> vars) {
		Iterator<PhazeVariable> ita = vars.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();
			file.println(PhazeCUtils.toPointer(var));
		} // while
	} // printAsPointers

} // class PhazeCUtils
