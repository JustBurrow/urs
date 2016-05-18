/**
 *
 */
package kr.lul.urs.core.domain.entity;

import static java.lang.String.format;
import static kr.lul.urs.TestConfig.log;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
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
import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.ResourceFileDomainUtils;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ResourceFileRevisionEntityTest extends AbstractDomainEntityTest {
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
    ResourceFile resourceFile = ResourceFileDomainUtils.create(this.clientPlatform,
        this.resourceFileInternalService);
    FileInputStream input = new FileInputStream(this.f1);
    String sha1 = DigestUtils.sha1Hex(input);
    input.close();

    // When
    ResourceFileRevisionEntity rev = new ResourceFileRevisionEntity(resourceFile, 1, sha1);
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
}
