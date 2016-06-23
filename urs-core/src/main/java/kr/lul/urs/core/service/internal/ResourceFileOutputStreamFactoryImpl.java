/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_RESOUCE_FILE_STORAGE_DIR;
import static kr.lul.urs.util.Asserts.notNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.domain.ResourceFileRevision;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 11.
 */
@Service
class ResourceFileOutputStreamFactoryImpl implements ResourceFileOutputStreamFactory {
  @Value("${" + KEY_RESOUCE_FILE_STORAGE_DIR + "}")
  private File resourceFileStorageDir;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFileRevisionFileStorageOutputStreamFactory
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public FileOutputStream getOutputStream(ResourceFileRevision key) throws IOException {
    notNull(key, "key");

    File file = FileUtils.getFile(this.resourceFileStorageDir, key.getName(), Integer.toString(key.getRevision()));
    FileOutputStream outputStream = FileUtils.openOutputStream(file);

    return outputStream;
  }
}
