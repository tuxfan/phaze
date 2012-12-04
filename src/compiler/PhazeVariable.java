/*----------------------------------------------------------------------------*
 * PhazeVariable
 *----------------------------------------------------------------------------*/

package Phaze;

import java.util.*;

enum PhazeType {
	int32 { public String toString() { return "int32_t"; } },
	int64 { public String toString() { return "int64_t"; } },
	real32 { public String toString() { return "float"; } },
	real64 { public String toString() { return "double"; } },
	unknown;

	private static final Map<String, String> strmap_ =
		new HashMap<String, String>();

	static {
		strmap_.put("int", "int32");
		strmap_.put("int32_t", "int32");
		strmap_.put("int64_t", "int64");
		strmap_.put("float", "real32");
		strmap_.put("double", "real64");
	} // scope

	private static final int size_ = EnumSet.allOf(PhazeType.class).size();
	private static PhazeType[] values_ = new PhazeType[size_];

	static {
		for(PhazeType pt : EnumSet.allOf(PhazeType.class)) {
			values_[pt.ordinal()] = pt;
		} // for
	} // scope

	public static PhazeType fromInt(int i) {
		return values_[i];
	} // fromInt

	public static PhazeType fromString(String str) {
		return PhazeType.valueOf(strmap_.get(str));
	} // fromString

	public static int toInt(PhazeType t) {
		return t.ordinal();
	} // toInt

} // enum PhazeType

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

	public String toString() {
		return type.toString() + " " + id;
	} // toString

} // class PhazeVariable
