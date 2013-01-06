/*----------------------------------------------------------------------------*
 * PhazeCopyFileVisitor
 *----------------------------------------------------------------------------*/

package Phaze;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.StandardCopyOption;

public class PhazeCopyFileVisitor extends SimpleFileVisitor<Path> {
	private final Path targetPath;
	private Path sourcePath = null;

	public PhazeCopyFileVisitor(Path targetPath) {
		this.targetPath = targetPath;
	} // PhazeCopyFileVisitor

	@Override
	public FileVisitResult preVisitDirectory(final Path dir,
	final BasicFileAttributes attrs) throws IOException {
		if (sourcePath == null) {
			sourcePath = dir;
		}
		else {
			Files.createDirectories(targetPath.resolve(sourcePath
				.relativize(dir)));
		} // if

		return FileVisitResult.CONTINUE;
	} // preVisitDirectory

	@Override
	public FileVisitResult visitFile(final Path file,
	final BasicFileAttributes attrs) throws IOException {
		Files.copy(file, targetPath.resolve(sourcePath.relativize(file)),
			StandardCopyOption.COPY_ATTRIBUTES,
			StandardCopyOption.REPLACE_EXISTING);
	return FileVisitResult.CONTINUE;
	} // visitFile
} // PhazeCopyFileVisitor
