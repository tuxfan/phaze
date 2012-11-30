/*----------------------------------------------------------------------------*
 * PhazeStruct
 *----------------------------------------------------------------------------*/

package Phaze;

import java.util.*;

public class PhazeStruct {

	private SortedSet<PhazeVariable> vars_;

	public PhazeStruct() {
		vars_ = new TreeSet<PhazeVariable>();
	} // PhazeStruct

	void addVariable(PhazeVariable var) {
		vars_.add(var);
	} // addVariable

	Set<PhazeVariable> variables() {
		return vars_;
	} // variables

} // class PhazeStruct
