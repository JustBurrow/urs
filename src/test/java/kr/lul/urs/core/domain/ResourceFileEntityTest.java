/**
 *
 */
package kr.lul.urs.core.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.TestConfig;
import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.application.configuration.InjectionConstants.Properties;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;
import kr.lul.urs.core.service.internal.ResourceFileInternalServiceUtils;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ResourceFileEntityTest extends AbstractDomainTest {
  public static final String          CLASS_NAME = ResourceFileEntityTest.class.getSimpleName();

  @Value("${" + Properties.KEY_RESOUCE_FILE_STORAGE_DIR + "}")
  private File                        storage;

  @Autowired
  private ResourceFileInternalService resourceFileInternalService;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setClientPlatformAsRandom();
  }

  @After
  public void tearDown() throws Exception {
    FileUtils.cleanDirectory(this.storage);
  }

  @Test
  public void testConstructorWithNullAndNull() throws Exception {
    assertThatThrownBy(() -> new ResourceFileEntity(null, null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testConstructorWithNullAndEmpty() throws Exception {
    assertThatThrownBy(() -> new ResourceFileEntity(null, "")).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testConstructor() throws Exception {
    // Given
    StringBuilder name = new StringBuilder();
    do {
      name.append('/').append(Strings.from(Randoms.in(1, 3), 'a', 'z'))
          .append(RandomStringUtils.randomAlphanumeric(Randoms.in(0, 10)));
    } while (0 >= Randoms.notNegative(10));

    // When
    final ResourceFileEntity rf = new ResourceFileEntity(this.clientPlatform, name.toString());

    // Then
    assertThat(rf).isNotNull();
    assertThat(rf.getOwner()).isEqualTo(this.operator);
    assertThat(rf.getClientPlatform()).isEqualTo(this.clientPlatform);
    assertThat(rf.getName()).isEqualTo(name.toString());
    assertThat(rf.getCurrentRevision()).isNull();
    assertThat(rf.getCurrentRevisionNumber()).isEqualTo(0);
    assertThat(rf.getHistory()).isEmpty();
    assertThat(rf.getCreate()).isNull();
    assertThat(rf.getUpdate()).isNull();
  }

  @Test
  public void testUpdateForNewInstance() throws Exception {
    // Given
    final ResourceFileEntity resourceFile = ResourceFileInternalServiceUtils.create(this.clientPlatform,
        this.resourceFileInternalService);
    final File file = FileUtils.getFile(TestConfig.TEST_RESOURCE_BASE_PATH, CLASS_NAME, "testUpdateForNewInstance");
    assertThat(file).exists();

    // When
    final ResourceFileRevision resourceFileRevision = resourceFile.update(file);

    // Then
    assertThat(resourceFile.getCurrentRevision()).isEqualTo(resourceFileRevision);
    assertThat(resourceFile.getCurrentRevisionNumber()).isEqualTo(1);
    assertThat(resourceFile.getHistory()).hasSize(1)
        .contains(resourceFileRevision);

    assertThat(resourceFileRevision).isNotNull();
    assertThat(resourceFileRevision.getResourceFile()).isEqualTo(resourceFile);
    assertThat(resourceFileRevision.getOwner()).isEqualTo(this.operator);
    assertThat(resourceFileRevision.getName()).isEqualTo(resourceFile.getName());
    assertThat(resourceFileRevision.getRevision()).isEqualTo(1);
    assertThat(resourceFileRevision.getSha1()).isEqualTo(DigestUtils.sha1Hex(new FileInputStream(file)));
    assertThat(resourceFileRevision.getCreate()).isNull();

    assertThat(FileUtils.getFile(this.storage, resourceFileRevision.getName()))
        .isDirectory()
        .exists();
    assertThat(FileUtils.getFile(this.storage, resourceFileRevision.getName(),
        Integer.toString(resourceFileRevision.getRevision())))
            .exists()
            .isFile();
  }
}
