/*----------------------------------------------------------------------------*
 * PhazeVariable
 *----------------------------------------------------------------------------*/

package Phaze;

import java.util.*;

/*----------------------------------------------------------------------------*
 * PhazeType enumeration
 *----------------------------------------------------------------------------*/

enum PhazeType {
	int32 { public String toString() { return "int32_t"; }},
	int64 { public String toString() { return "int64_t"; }},
	real32 { public String toString() { return "float"; }},
	real64 { public String toString() { return "double"; }},
	pos32 { public String toString() { return "pos32_t"; }},
	pos64 { public String toString() { return "pos64_t"; }},
	vec32 { public String toString() { return "vec32_t"; }},
	vec64 { public String toString() { return "vec64_t"; }},
	unknown;

	private static final Map<String, String> strmap_ =
		new HashMap<String, String>();

	static {
		strmap_.put("int", "int32");
		strmap_.put("int32_t", "int32");
		strmap_.put("int64_t", "int64");
		strmap_.put("float", "real32");
		strmap_.put("double", "real64");
		strmap_.put("pos32_t", "pos32");
		strmap_.put("pos64_t", "pos64");
		strmap_.put("vec32_t", "vec32");
		strmap_.put("vec64_t", "vec64");
	} // scope

	private static final int size_ = EnumSet.allOf(PhazeType.class).size();
	private static PhazeType[] values_ = new PhazeType[size_];

	static {
		for(PhazeType pt : EnumSet.allOf(PhazeType.class)) {
			values_[pt.ordinal()] = pt;
		} // for
	} // scope

	/*-------------------------------------------------------------------------*
	 * fromInt
	 *-------------------------------------------------------------------------*/

	public static PhazeType fromInt(int i) {
		return values_[i];
	} // fromInt

	/*-------------------------------------------------------------------------*
	 * fromString
	 *-------------------------------------------------------------------------*/

	public static PhazeType fromString(String str) {
		return PhazeType.valueOf(strmap_.get(str));
	} // fromString

	/*-------------------------------------------------------------------------*
	 * toInt
	 *-------------------------------------------------------------------------*/

	public static int toInt(PhazeType t) {
		return t.ordinal();
	} // toInt

} // enum PhazeType

public class PhazeVariable implements Comparable<PhazeVariable> {

	public PhazeType type;
	public String id;
	public boolean isStatic;
	public int arraySize;
	public String scope;

	PhazeVariable(PhazeType type_, String id_) {
		this(type_, id_, false, 0, "default");
	}

	PhazeVariable(PhazeType type_, String id_, boolean isStatic_,
		int arraySize_, String scope_) {
		type = type_;
		id = id_;
		isStatic = isStatic_;
		arraySize = arraySize_;
		scope = scope_;
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
		return (isStatic ? "static " : "") + type.toString() + " " + id;
	} // toString

	public String staticString() {
		return "const " + type.toString() + " " +
			"phz_" + scope + "_constant_" + id + ";\n";
	} // staticString

} // class PhazeVariable
