/*----------------------------------------------------------------------------*
 * Phaze cell handler
 *----------------------------------------------------------------------------*/

package Phaze;

public class PhazeCell extends phazeBaseListener {

	@Override
	public void enterInit(phazeParser.InitContext ctx) {
		System.out.print('"');
	} // enterInit

	@Override
	public void exitInit(phazeParser.InitContext ctx) {
		System.out.print('"');
	} // exitInit

	@Override
	public void enterValue(phazeParser.ValueContext ctx) {
		// Assumes no nested array initializers
		int value = Integer.valueOf(ctx.INT().getText());
		System.out.printf("\\u%04x", value);
	} // enterValue

} // class PhazeCell
