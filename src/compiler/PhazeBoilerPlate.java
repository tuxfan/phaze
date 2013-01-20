/*----------------------------------------------------------------------------*
 * PhazeBoilerPlate
 *----------------------------------------------------------------------------*/

package Phaze;

public interface PhazeBoilerPlate {

	public String startComment();
	public String endComment();
	public String continueComment();

	public String genericHeader(String inputFIle);
	public String headerPrologue();
	public String headerEpilogue();

	public String staticInterfaceHeader();
	public String staticInterfaceSource();

} // class PhazeBoilerPlate
