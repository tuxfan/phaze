/*----------------------------------------------------------------------------*
 * PhazeWriterFactory
 *----------------------------------------------------------------------------*/

package Phaze;

//import org.apache.commons.cli.*;
import java.util.*;

public class PhazeWriterFactory {

	Hashtable<String, PhazeWriter> hash_;

	// Meyer's singleton
	public static PhazeWriterFactory instance() {
		return new PhazeWriterFactory();
	} // instance

	PhazeWriterFactory() {
		hash_ = new Hashtable<String, PhazeWriter>();
	} // PhazeWriterFactory
	
	public boolean registerClass(String type, PhazeWriter writer) {
		hash_.put(type, writer);
		return true;
	} // registerClass
} // interface PhazeWriter
