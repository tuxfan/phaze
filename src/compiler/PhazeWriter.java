/*----------------------------------------------------------------------------*
 * PhazeWriter
 *----------------------------------------------------------------------------*/

package Phaze;

import org.apache.commons.cli.*;
import java.util.*;

public interface PhazeWriter {

	public void writeHeader(Hashtable<String, PhazeStruct> structs,
		String baseName, CommandLine line) throws Exception;
	
} // interface PhazeWriter
