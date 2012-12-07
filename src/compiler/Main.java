/*----------------------------------------------------------------------------*
 * Main phaze driver
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

	public static void main(String [] args) throws Exception {

		/*----------------------------------------------------------------------*
		 * Set up command-line options
		 *----------------------------------------------------------------------*/

		Options opts = new Options();

		opts.addOption("h", "help", false,
			"print this message and exit.");

		opts.addOption(OptionBuilder.withArgName("3")
			.withLongOpt("dimension")
			.hasArg()
			.withDescription("specify the dimension [1,2,3]")
			.create("d"));

		opts.addOption(OptionBuilder.withArgName("AoS")
			.withLongOpt("layout")
			.hasArg()
			.withDescription("specify the data layout [AoS,SoA]")
			.create("l"));

		// check command-line arguments
		if(args.length < 1) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("phaze", opts);
			System.exit(1);
		} // if

		/*----------------------------------------------------------------------*
		 * Parse command-line arguments
		 *----------------------------------------------------------------------*/

		CommandLineParser gnu = new GnuParser();
		CommandLine line = null;

		try {
			line = gnu.parse(opts, args);
		}
		catch(ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
			System.exit(1);
		} // try

		// set the input file
		String inputFile = args[args.length-1];

		// create an input stream for antlr
		InputStream is = new FileInputStream(inputFile);

		// set the basename to use for output
		String baseName = inputFile.substring(0, inputFile.lastIndexOf('.'));

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

		// create a tree walker
		ParseTreeWalker walker = new ParseTreeWalker();

		// create phaze handler
		PhazeHandler phzHandler = new PhazeHandler(baseName, line);

		// process the input
		walker.walk(phzHandler, tree);

		// write output
		phzHandler.write();
	} // main

} // class Main
