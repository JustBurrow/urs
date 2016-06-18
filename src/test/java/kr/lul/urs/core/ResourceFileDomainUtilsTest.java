/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.core.ResourceFileDomainUtils.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ResourceFileDomainUtilsTest extends AbstractDomainEntityTest {
  @Autowired
  private ResourceFileInternalService resourceFileInternalService;

  @Before
  public void setUp() throws Exception {
    this.setAgentPlatformAsRandom();
  }

  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new ResourceFileDomainUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateWithNullAndNull() throws Exception {
    assertThatThrownBy(() -> create(null, null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreateWithNullAndInternalService() throws Exception {
    assertThatThrownBy(() -> create(null, this.resourceFileInternalService)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreateWithDoAndNull() throws Exception {
    assertThatThrownBy(() -> create(this.platform, null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreateWithNonEntityAndInternalService() throws Exception {
    assertThatThrownBy(() -> create(new AgentPlatform() {
      @Override
      public Instant getUpdate() {
        return null;
      }

      @Override
      public Instant getCreate() {
        return null;
      }

      @Override
      public void setLabel(String label) {
      }

      @Override
      public void setDescription(String description) {
      }

      @Override
      public Operator getOwner() {
        return null;
      }

      @Override
      public String getLabel() {
        return null;
      }

      @Override
      public int getId() {
        return 0;
      }

      @Override
      public String getDescription() {
        return null;
      }

      @Override
      public String getCode() {
        return null;
      }
    }, this.resourceFileInternalService)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreateWithDoAndInternalService() throws Exception {
    // When
    final ResourceFileEntity rf = create(this.platform, this.resourceFileInternalService);

    // Then
    assertThat(rf).isNotNull();
    assertThat(rf.getOwner()).isEqualTo(this.operator);
    assertThat(rf.getAgentPlatform()).isEqualTo(this.platform);
    assertThat(rf.getName()).isNotNull().matches("(/[a-z][a-zA-Z\\d]*)+");
    if (this.saveAndFlush) {
      assertThat(rf.getCreate()).isGreaterThanOrEqualTo(this.now);
      assertThat(rf.getUpdate()).isEqualTo(rf.getCreate());
    } else {
      assertThat(rf.getCreate()).isNull();
      assertThat(rf.getUpdate()).isNull();
    }
  }
}
