/**
 *
 */
package kr.lul.urs.core.domain.entity;

import static java.lang.String.format;
import static kr.lul.urs.TestConfig.log;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.TestConfig;
import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping;
import kr.lul.urs.core.service.internal.AbstractInternalServiceTest;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;
import kr.lul.urs.core.service.internal.ResourceFileInternalServiceUtils;
import kr.lul.urs.util.AbstractInputStreamSupplier;
import kr.lul.urs.util.InputStreamSupplier;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ResourceFileRevisionEntityTest extends AbstractInternalServiceTest {
  public static final File            TEST_FILE_DIR = FileUtils.getFile(TestConfig.TEST_RESOURCE_BASE_PATH,
      ResourceFileRevisionEntityTest.class.getSimpleName());

  @Autowired
  private ResourceFileInternalService resourceFileInternalService;

  private File                        f1            = FileUtils.getFile(TEST_FILE_DIR, "test-01");
  private File                        f1copy        = FileUtils.getFile(TEST_FILE_DIR, "test-01-copy");
  private File                        f2            = FileUtils.getFile(TEST_FILE_DIR, "test-01");

  @Before
  public void setUp() throws Exception {
    this.setClientPlatformAsRandom();
    assertThat(this.f1).exists().isFile();
    assertThat(this.f1copy).exists().isFile();
    assertThat(this.f2).exists().isFile();
  }

  @Test
  public void testConstruct() throws Exception {
    // Given
    ResourceFile resourceFile = ResourceFileInternalServiceUtils.create(this.clientPlatform,
        this.resourceFileInternalService);
    InputStreamSupplier<InputStream> file = new AbstractInputStreamSupplier<InputStream>() {
      @Override
      protected InputStream doOpen() throws IOException {
        return new FileInputStream(ResourceFileRevisionEntityTest.this.f1);
      }
    };

    // When
    ResourceFileRevisionEntity rev = new ResourceFileRevisionEntity(resourceFile, 1, file);
    if (log.isDebugEnabled()) {
      log.debug(format("file=%s, rFR.sha1=%s", this.f1, rev.getSha1()));
    }

    // Then
    assertThat(rev.getId()).isNotNull()
        .hasFieldOrPropertyWithValue(ResourceFileRevisionMapping.Entity.RESOURCE_FILE, resourceFile.getId())
        .hasFieldOrPropertyWithValue(ResourceFileRevisionMapping.Entity.REVISION, 1);
    assertThat(rev.getOwner()).isEqualTo(this.operator);
    assertThat(rev.getClientPlatform()).isEqualTo(resourceFile.getClientPlatform());
    assertThat(rev.getResourceFile()).isEqualTo(resourceFile);
    assertThat(rev.getRevision()).isEqualTo(1);
    assertThat(rev.getName()).isEqualTo(resourceFile.getName());
    assertThat(rev.getSha1()).isEqualTo(DigestUtils.sha1Hex(new FileInputStream(this.f1)));
  }

  @Test
  public void testConstructThrowsIOException() throws Exception {
    // Given
    final String message = RandomStringUtils.random(Randoms.in(10, 20));
    ResourceFileEntity resourceFile = ResourceFileInternalServiceUtils.create(this.clientPlatform,
        this.resourceFileInternalService);
    InputStreamSupplier<InputStream> file = new AbstractInputStreamSupplier<InputStream>() {
      @Override
      protected InputStream doOpen() throws IOException {
        throw new IOException(message);
      }
    };

    // When & Then
    assertThatThrownBy(() -> new ResourceFileRevisionEntity(resourceFile, 1, file)).isInstanceOf(IOException.class)
        .hasMessage(message);
  }
}
