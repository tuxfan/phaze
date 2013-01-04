/*----------------------------------------------------------------------------*
 * PhazeWriter
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import java.util.*;

public interface PhazeWriter {

	public void writeHeaders(String inputFile, CommandLine line,
		Hashtable<String, PhazeStruct> structs) throws Exception;
	
	public void writeSources(String inputFile, CommandLine line,
		Hashtable<String, PhazeStruct> structs) throws Exception;
	
} // interface PhazeWriter
