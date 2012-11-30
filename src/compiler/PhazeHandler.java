/*----------------------------------------------------------------------------*
 * PhazeHandler
 *----------------------------------------------------------------------------*/

package Phaze;

import java.io.PrintWriter;
import java.util.*;

public class PhazeHandler extends phazeBaseListener {

	/*-------------------------------------------------------------------------*
	 * Private data members
	 *-------------------------------------------------------------------------*/

	private String baseName_;
	private PrintWriter source_;
	private PrintWriter header_;
	private PhazeBoilerPlate bp_;

	boolean cellDefined_;
	boolean matDefined_;
	boolean mixDefined_;

	PhazeStruct current_;
	PhazeStruct cellStruct_;
	PhazeStruct matStruct_;
	PhazeStruct mixStruct_;

	/*-------------------------------------------------------------------------*
	 * Constructor
	 *-------------------------------------------------------------------------*/

	public PhazeHandler(String baseName, PhazeOptions opts) throws Exception {
		// member initialization
		cellDefined_ = false;
		matDefined_ = false;
		mixDefined_ = false;

		current_ = null;
		cellStruct_ = new PhazeStruct();
		matStruct_ = new PhazeStruct();
		mixStruct_ = new PhazeStruct();

		// save the base name for 'close' method
		baseName_ = baseName;

		// create output streams
		source_ = new PrintWriter(baseName + ".c");
		header_ = new PrintWriter(baseName + ".h");

		// boiler plate constants
		bp_ = new PhazeBoilerPlate();

		// source file initialization
		source_.print(String.format(bp_.genericHeader,
			"Phaze implementation of " + baseName + " input file"));
		source_.print("\n#include <" + baseName + ".h>\n");

		// header file initialization
		header_.print(String.format(bp_.genericHeader,
			"Phaze interface for " + baseName + " input file"));
		header_.print(String.format("\n#ifndef %s_h\n", baseName));
		header_.print(String.format("#define %s_h\n\n", baseName));
	} // PhazeHandler

	/*-------------------------------------------------------------------------*
	 * Kluge destructor
	 *-------------------------------------------------------------------------*/

	public void close() {

		// header file finalization
		header_.print(String.format("\n#endif // %s_h\n", baseName_));

		// close output streams
		source_.close();
		header_.close();
	} // close

	/*-------------------------------------------------------------------------*
	 * Cell functions
	 *-------------------------------------------------------------------------*/

	@Override
	public void enterCellBody(phazeParser.CellBodyContext ctx) {
		// make sure there is only one definition
		if(cellDefined_) {
			System.err.print("error: cell already defined\n");
			System.exit(1);
		} // if

		current_ = cellStruct_;
	} // enterCellBody

	@Override
	public void exitCellBody(phazeParser.CellBodyContext ctx) {
// FIXME: This needs to move into a class
		// print cell struct name
		header_.print(bp_.commentLineStart);
		header_.println(" * cell structure prototype");
		header_.println(bp_.commentLineEnd);

		header_.println("struct phz_cell {");

		Set<PhazeVariable> vars = cellStruct_.variables();

		Iterator<PhazeVariable> ita = vars.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();
			System.out.println(var.toString());

			String varStr = new String();
			switch(var.type) {
				case real:
					varStr = "double ";
					break;
				case integer:
					varStr = "int ";
					break;
			} // switch

			header_.println("\t" + varStr + var.id + ";");
		} // while

		header_.println("}; // struct phz_cell");

		cellDefined_ = true;
	} // exitCellBody

	/*-------------------------------------------------------------------------*
	 * Material functions
	 *-------------------------------------------------------------------------*/

	@Override
	public void enterMatBody(phazeParser.MatBodyContext ctx) {
		// make sure there is only one definition
		if(matDefined_) {
			System.err.print("error: material already defined\n");
			System.exit(1);
		} // if

		current_ = matStruct_;
	} // enterMatBody

	@Override
	public void exitMatBody(phazeParser.MatBodyContext ctx) {
		matDefined_ = true;
	} // exitMatBody

	/*-------------------------------------------------------------------------*
	 * Mixture functions
	 *-------------------------------------------------------------------------*/

	@Override
	public void enterMixBody(phazeParser.MixBodyContext ctx) {
	} // enterMixBody

	@Override
	public void exitMixBody(phazeParser.MixBodyContext ctx) {
	} // exitMixBody

	/*-------------------------------------------------------------------------*
	 * Variable functions
	 *-------------------------------------------------------------------------*/

	@Override
	public void enterVarDecl(phazeParser.VarDeclContext ctx) {
		if(current_ == null) {
			System.err.print("error: variable declared outside of scope\n");
			System.exit(1);
		} // if

		PhazeType type =
			PhazeVariable.stringToType(ctx.type().primitive().getText());

		for(int i=0; i<ctx.ID().size(); ++i) {
			PhazeVariable var = new PhazeVariable(type, ctx.ID(i).getText());
			current_.addVariable(new PhazeVariable(type, ctx.ID(i).getText()));
		} // for
	} // enterVarDecl

	@Override
	public void exitVarDecl(phazeParser.VarDeclContext ctx) {
	} // enterVarDecl

} // class PhazeHandler
