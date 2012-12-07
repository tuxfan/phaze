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
	boolean isoDefined_;
	boolean reaDefined_;

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
		isoDefined_ = false;
		reaDefined_ = false;

		current_ = null;
		structs_ = new Hashtable<String, PhazeStruct>();
		structs_.put("cell", new PhazeStruct());
		structs_.put("material", new PhazeStruct());
		structs_.put("composition", new PhazeStruct());
		structs_.put("isotope", new PhazeStruct());
		structs_.put("reaction", new PhazeStruct());
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
		// make sure there is only one definition
		if(compDefined_) {
			System.err.print("error: composition already defined\n");
			System.exit(1);
		} // if

		current_ = structs_.get("composition");
	} // enterCompBody

	@Override
	public void exitCompBody(phazeParser.CompBodyContext ctx) {
		compDefined_ = true;
	} // exitCompBody

	/*-------------------------------------------------------------------------*
	 * Isotope functions
	 *-------------------------------------------------------------------------*/

	@Override
	public void enterIsoBody(phazeParser.IsoBodyContext ctx) {
		// make sure there is only one definition
		if(isoDefined_) {
			System.err.print("error: isotope already defined\n");
			System.exit(1);
		} // if

		current_ = structs_.get("isotope");
	} // enterIsoBody

	@Override
	public void exitIsoBody(phazeParser.IsoBodyContext ctx) {
		isoDefined_ = true;
	} // exitIsoBody

	/*-------------------------------------------------------------------------*
	 * Reaction functions
	 *-------------------------------------------------------------------------*/

	@Override
	public void enterReaBody(phazeParser.ReaBodyContext ctx) {
		// make sure there is only one definition
		if(reaDefined_) {
			System.err.print("error: reaction already defined\n");
			System.exit(1);
		} // if

		current_ = structs_.get("reaction");
	} // enterReaBody

	@Override
	public void exitReaBody(phazeParser.ReaBodyContext ctx) {
		reaDefined_ = true;
	} // exitReaBody

	/*-------------------------------------------------------------------------*
	 * Variable functions
	 *-------------------------------------------------------------------------*/

	@Override
	public void enterVarDecl(phazeParser.VarDeclContext ctx) {
		if(current_ == null) {
			System.err.print("error: variable declared outside of scope\n");
			System.exit(1);
		} // if

		// check storage class
		boolean isStatic = ctx.storage() == null ? false : true;

		// get type
		PhazeType type =
			PhazeType.fromString(ctx.type().primitive().getText());

		for(int i=0; i<ctx.ID().size(); ++i) {
			// check for array decorations
			int arraySize = 0;
			if(ctx.array() != null) {
				arraySize = Integer.parseInt(ctx.array().INT().getText());
			} // if

			current_.addVariable(new PhazeVariable(type,
				ctx.ID(i).getText(), isStatic, arraySize));
		} // for
	} // enterVarDecl

	@Override
	public void exitVarDecl(phazeParser.VarDeclContext ctx) {
	} // enterVarDecl

} // class PhazeHandler
