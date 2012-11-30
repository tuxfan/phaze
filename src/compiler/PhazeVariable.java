/*----------------------------------------------------------------------------*
 * PhazeVariable
 *----------------------------------------------------------------------------*/

package Phaze;

import java.lang.*;

enum PhazeType {
	integer,
	real,
	unknown
}

public class PhazeVariable implements Comparable<PhazeVariable> {

	public PhazeType type;
	public String id;

	PhazeVariable(PhazeType type_, String id_) {
		type = type_;
		id = id_;
	} // PhazeVariable

	public int compareTo(PhazeVariable pv) {
		if(type == pv.type) {
			return id.compareTo(pv.id);
		}
		else {
			return type.compareTo(pv.type);
		} // if
	} // compareTo

	public static PhazeType stringToType(String str) {
		// Java 6 doesn't support switching on strings
		if(str.equals("integer")) {
			return PhazeType.integer;
		}
		else if(str.equals("real")) {
			return PhazeType.real;
		}
		else {
			System.err.println("error: unknown type " + str);
			System.exit(1);
		} // if

		return PhazeType.unknown;
	} // stringToType

	public static String typeToString(PhazeType type) {
		// Java 6 doesn't support switching on strings
		if(type == PhazeType.integer) {
			return "integer";
		}
		else if(type == PhazeType.real) {
			return "real";
		}
		else {
			System.err.println("error: unknown type");
			System.exit(1);
		} // if

		return "unknown";
	} // stringToType

	public String toString() {
		return typeToString(type) + " " + id;
	} // toString

} // class PhazeVariable
