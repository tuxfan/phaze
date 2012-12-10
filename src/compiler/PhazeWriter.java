/*----------------------------------------------------------------------------*
 * PhazeWriter
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import java.util.*;

public interface PhazeWriter {

	public void writeHeader(String inputFile, CommandLine line,
		Hashtable<String, PhazeStruct> structs) throws Exception;
	
} // interface PhazeWriter
