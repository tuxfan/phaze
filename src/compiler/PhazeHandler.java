/*----------------------------------------------------------------------------*
 * PhazeHandler
 *----------------------------------------------------------------------------*/

package Phaze;

import java.io.PrintWriter;

public class PhazeHandler extends phazeBaseListener {

	public PhazeHandler(String baseName, PhazeOptions opts) throws Exception {
		source_ = new PrintWriter(baseName + ".c");
		header_ = new PrintWriter(baseName + ".h");
	} // PhazeHandler

	private PrintWriter source_;
	private PrintWriter header_;

	@Override
	public void enterCellBody(phazeParser.CellBodyContext ctx) {
		source_.print("// Enter Cell\n");
		source_.flush();
	} // enterCellBody

	@Override
	public void exitCellBody(phazeParser.CellBodyContext ctx) {
		source_.print("// Exit Cell\n");
		source_.flush();
	} // exitCellBody

	@Override
	public void enterVarDecl(phazeParser.VarDeclContext ctx) {
		System.out.println("type: " + ctx.type().primitive().getText());

		for(int i=0; i<ctx.ID().size(); ++i) {
			System.out.println("identifier: " + ctx.ID(i).getText());
		} // for
	} // enterVarDecl

	@Override
	public void exitVarDecl(phazeParser.VarDeclContext ctx) {
	} // enterVarDecl

} // class PhazeHandler
