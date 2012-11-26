/*----------------------------------------------------------------------------*
 * Main phaze driver
 *----------------------------------------------------------------------------*/

package Phaze;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

	public static void main(String [] args) throws Exception {
		String inputFile = null;

		// check command-line arguments
		if(args.length > 0) {
			inputFile = args[0];
		} // if

		// set the default input
		InputStream is = System.in;

		if(inputFile != null) {
			is = new FileInputStream(inputFile);
		} // if

		// create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(is);

		// create a lexer that feeds off of input CharStream
		phazeLexer lexer = new phazeLexer(input);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// create a parser that feeds off the tokens buffer
		phazeParser parser = new phazeParser(tokens);

		// begin parsing at init rule
		ParseTree tree = parser.init();

		ParseTreeWalker walker = new ParseTreeWalker();

		walker.walk(new PhazeCell(), tree);

		// print LISP-style tree
		System.out.println();
	} // main

} // class Main
