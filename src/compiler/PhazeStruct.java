/*----------------------------------------------------------------------------*
 * PhazeStruct
 *----------------------------------------------------------------------------*/

package Phaze;

import java.util.*;

public class PhazeStruct {

	private SortedSet<PhazeVariable> vars_;
	private SortedSet<PhazeVariable> staticVars_;

	public PhazeStruct() {
		vars_ = new TreeSet<PhazeVariable>();
		staticVars_ = new TreeSet<PhazeVariable>();
	} // PhazeStruct

	void addVariable(PhazeVariable var) {
		vars_.add(var);

		if(var.isStatic) {
			staticVars_.add(var);
		} // if
	} // addVariable

	Set<PhazeVariable> variables() {
		return vars_;
	} // variables

	Set<PhazeVariable> staticVariables() {
		return staticVars_;
	} // variables

} // class PhazeStruct
