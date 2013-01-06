/*----------------------------------------------------------------------------*
 * Main phaze driver
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.File;
import java.nio.file.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

	public static void main(String [] args) throws Exception {

		/*----------------------------------------------------------------------*
		 * Set up command-line options
		 *----------------------------------------------------------------------*/

		Options opts = new Options();

		// help
		opts.addOption("h", "help", false,
			"print this message and exit");

		// output build files
		opts.addOption("b", "buildfiles", false,
			"output build files");

		// output directory
		opts.addOption(OptionBuilder.withArgName("input directory")
			.withLongOpt("directory")
			.hasArg()
			.withDescription("specify the output directory")
			.create("d"));

		// dimension
		opts.addOption(OptionBuilder.withArgName("3")
			.withLongOpt("dimension")
			.hasArg()
			.withDescription("specify the dimension [1,2,3]")
			.create("D"));

		// data layout
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
		PhazeHandler phzHandler = new PhazeHandler(inputFile, line);

		// process the input
		walker.walk(phzHandler, tree);

		// get the output path
		String path = PhazeUtils.targetDir(inputFile, line);

		// create output src directory
		(new File(path + "/src")).mkdir();

		// write output
		phzHandler.write();

		// handle build system
		if(line.hasOption("b")) {
			String baseDir = System.getenv("PHAZE_BASE_DIRECTORY");

			// check that the base installation is set
			if(baseDir == null) {
				System.err.println("error: PHAZE_BASE_DIRECTORY " +
					"environment variable not set");
				System.err.println("Build system not created");
			} // if

			String shareDir = baseDir + "/share";

			Path sourceDir = Paths.get(shareDir);
			Path destDir = Paths.get(path);

			if(!Files.exists(destDir)) {
System.out.println("dir does not exist");
			} // if

			Files.walkFileTree(sourceDir, new PhazeCopyFileVisitor(destDir));

//			Files.createDirectory(dir);

//			File sourceDir = new File(shareDir);
//			File destDir = new File(path);

			// check that dirs exist
//			if(!sourceDir.exists() || !destDir.exists()) {
//				System.err.print("error: directory creation failed");
//				System.err.println("Build system not created");
//			} // if

//			try{
//				FileUtils.copyDirectory(sourceDir, destDir);
//			}
//			catch(Exception e) {
//				System.err.println("error: copyDirectory failed");
//				System.err.println("Build system not created");
//			} // try
		} // if
	} // main

} // class Main
