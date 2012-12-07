/*----------------------------------------------------------------------------*
 * PhazeHandler
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.util.*;

public class PhazeHandler extends phazeBaseListener {

	/*-------------------------------------------------------------------------*
	 * Private data members
	 *-------------------------------------------------------------------------*/

	String baseName_;

	boolean cellDefined_;
	boolean matDefined_;
	boolean compDefined_;
// FIXME

	PhazeStruct current_;
	Hashtable<String, PhazeStruct> structs_;
	CommandLine line_;

	/*-------------------------------------------------------------------------*
	 * Constructor
	 *-------------------------------------------------------------------------*/

	public PhazeHandler(String baseName, CommandLine line) throws Exception {
		// save input parameters
		baseName_ = baseName;
		line_ = line;

		// member initialization
		cellDefined_ = false;
		matDefined_ = false;
		compDefined_ = false;

		current_ = null;
		structs_ = new Hashtable<String, PhazeStruct>();
		structs_.put("cell", new PhazeStruct());
		structs_.put("material", new PhazeStruct());
		structs_.put("composition", new PhazeStruct());
		structs_.put("isotope", new PhazeStruct());
		structs_.put("reaction", new PhazeStruct());

		// source file initialization
//		source_.print(String.format(bp_.genericHeader,
//			"Phaze implementation of " + baseName + " input file"));
//		source_.print("\n#include <" + baseName + ".h>\n");
	} // PhazeHandler

	/*-------------------------------------------------------------------------*
	 * Write output
	 *-------------------------------------------------------------------------*/

	public void write() {
		PhazeWriter writer = new PhazeBasicAoSWriter();

		try {
			writer.writeHeader(structs_, baseName_, line_);
		}
		catch(Exception e) {
			System.err.print("error: write header failed\n");
			System.exit(1);
		}
	} // write

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

		current_ = structs_.get("cell");
	} // enterCellBody

	@Override
	public void exitCellBody(phazeParser.CellBodyContext ctx) {
// FIXME: This needs to move into a class
		// print cell struct name
//		header_.print(bp_.commentLineStart);
//		header_.println(" * cell structure prototype");
//		header_.println(bp_.commentLineEnd);

//		header_.println("struct phz_cell {");

		Set<PhazeVariable> vars = structs_.get("cell").variables();

		Iterator<PhazeVariable> ita = vars.iterator();
		while(ita.hasNext()) {
			PhazeVariable var = ita.next();
//			System.out.println(var.toString());
//			header_.println("\t" + var.toString() + ";");
		} // while

//		header_.println("}; // struct phz_cell");

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

		current_ = structs_.get("material");
	} // enterMatBody

	@Override
	public void exitMatBody(phazeParser.MatBodyContext ctx) {
		matDefined_ = true;
	} // exitMatBody

	/*-------------------------------------------------------------------------*
	 * Composition functions
	 *-------------------------------------------------------------------------*/

	@Override
	public void enterCompBody(phazeParser.CompBodyContext ctx) {
	} // enterCompBody

	@Override
	public void exitCompBody(phazeParser.CompBodyContext ctx) {
	} // exitCompBody

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
			PhazeType.fromString(ctx.type().primitive().getText());

		for(int i=0; i<ctx.ID().size(); ++i) {
			PhazeVariable var = new PhazeVariable(type, ctx.ID(i).getText());
			current_.addVariable(new PhazeVariable(type, ctx.ID(i).getText()));
		} // for
	} // enterVarDecl

	@Override
	public void exitVarDecl(phazeParser.VarDeclContext ctx) {
	} // enterVarDecl

} // class PhazeHandler
