/**
 *
 */
package kr.lul.urs.core.domain.entity;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.time.Instant;

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

import kr.lul.test.TestConfig;
import kr.lul.urs.application.ApplicationTestConfig;
import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.domain.ResourceFileRevision;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;
import kr.lul.urs.core.test.AbstractInternalTest;
import kr.lul.urs.core.test.ResourceFileUtils;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(ApplicationTestConfig.ROLLBACK)
public class ResourceFileEntityTest extends AbstractInternalTest {
  public static final String          CLASS_NAME = ResourceFileEntityTest.class.getSimpleName();

  @Autowired
  private ResourceFileInternalService resourceFileInternalService;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setClientPlatformAsRandom();
    assertEquals(this.clientPlatform.getOwner(), this.operator);
  }

  @Test(expected = AssertionException.class)
  public void testConstructorWithNullAndNull() throws Exception {
    new ResourceFileEntity(null, null);
    fail();
  }

  @Test(expected = AssertionException.class)
  public void testConstructorWithNullAndEmpty() throws Exception {
    new ResourceFileEntity(null, "");
    fail();
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
    assertNotNull(rf);
    assertEquals(this.operator, rf.getOwner());
    assertEquals(this.clientPlatform, rf.getClientPlatform());
    assertEquals(name.toString(), rf.getName());
    assertNull(rf.getCurrentRevision());
    assertEquals(0, rf.getCurrentRevisionNumber());
    assertThat(rf.getHistory(), empty());
    assertNull(rf.getCreate());
    assertNull(rf.getUpdate());
  }

  @Test
  public void testUpdateForNewInstance() throws Exception {
    // Given
    final ResourceFileEntity resourceFile = ResourceFileUtils.create(this.clientPlatform,
        this.resourceFileInternalService);
    final Instant create = resourceFile.getCreate();
    final File file = FileUtils.getFile(TestConfig.TEST_RESOURCE_BASE_PATH, CLASS_NAME, "testUpdateForNewInstance");
    assertTrue(file.toString(), file.exists());

    // When
    final ResourceFileRevision resourceFileRevision = resourceFile.update(file);

    // Then
    assertNotNull(resourceFileRevision);
    assertEquals(resourceFile, resourceFileRevision.getResourceFile());
    assertEquals(this.operator, resourceFileRevision.getOwner());
    assertEquals(resourceFileRevision, resourceFile.getCurrentRevision());
    assertEquals(this.clientPlatform, resourceFileRevision.getClientPlatform());
    assertEquals(resourceFile.getName(), resourceFileRevision.getName());
    assertEquals(1, resourceFileRevision.getRevision());
    // assertTrue(resourceFileRevision.getSha1(), Conditions.matches(resourceFileRevision.getSha1(),
    // "[a-fA-F\\d]{40}"));
    // assertThat(resourceFileRevision.getCreate(), after(create));
    assertNull(resourceFileRevision.getCreate()); // 트랜잭션이 종료되지 않아 타임스탬퍼가 작동하기 전이다.

    assertEquals(1, resourceFile.getCurrentRevisionNumber());
    assertEquals(1, resourceFile.getHistory().size());
    assertThat(resourceFile.getHistory(), contains(resourceFileRevision));
    assertEquals(create, resourceFile.getCreate());
    assertThat(resourceFile.getUpdate(), after(this.now));
  }
}
