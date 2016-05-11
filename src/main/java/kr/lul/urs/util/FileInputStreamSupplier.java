/**
 *
 */
package kr.lul.urs.util;

import static kr.lul.urs.util.Asserts.isTrue;
import static kr.lul.urs.util.Asserts.notNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 11.
 */
public class FileInputStreamSupplier<S extends InputStream> extends AbstractInputStreamSupplier<S> {
  private File file;

  public FileInputStreamSupplier(File file) {
    notNull(file);
    isTrue(file.exists(), "file is not exist.");
    isTrue(file.isFile(), "file is a directory.");
    isTrue(file.canRead(), "file is not readable.");
    this.file = file;
  }

  public File getFile() {
    return this.file;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <A>AbstractInputStreamSupplier<FileInputStream>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @SuppressWarnings("unchecked")
  @Override
  protected S doOpen() throws IOException {
    return (S) new FileInputStream(this.file);
  }
}
