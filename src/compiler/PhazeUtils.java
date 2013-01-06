/*----------------------------------------------------------------------------*
 * PhazeCUtils
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;

public class PhazeUtils {

	public static String targetDir(String inputFile, CommandLine line) {
		int slash = inputFile.lastIndexOf('/');
		String inputDir = slash == -1 ? "./" : inputFile.substring(0, slash);

		String path = line.hasOption("d") ?
		line.getOptionValue("d") : inputDir;

		return path;
	} // targetDir

} // class PhazeUtils
