package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.context.CreateResourceFileCtx;
import kr.lul.urs.core.test.AbstractDomainTest;
import kr.lul.urs.core.test.OperatorDomainUtils;
import kr.lul.urs.core.test.ResourceFileDomainUtils;
import kr.lul.urs.util.AssertionException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ResourceFileInternalServiceTest extends AbstractDomainTest {
  @Autowired
  private ResourceFileInternalService resourceFileInternalService;

  @Before
  public void setUp() throws Exception {
    this.setAgentPlatformAsRandom();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.resourceFileInternalService.create(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreateWithIllegalOwner() throws Exception {
    // Given
    final CreateResourceFileCtx ctx = ResourceFileDomainUtils.createCtx(this.platform,
        this.resourceFileInternalService);
    Operator operator;
    do {
      operator = OperatorDomainUtils.create(this.operatorInternalService);
    } while (null != operator && ctx.getOwner().equals(operator));
    ctx.setOwner(operator);

    // When
    OwnershipException e = (OwnershipException) Assertions
        .catchThrowable(() -> this.resourceFileInternalService.create(ctx));

    // Then
    assertThat(e).isInstanceOf(OwnershipException.class)
        .hasMessageContaining("no permission");
    assertThat(e.getExpected()).isEqualTo(operator.getId());
    assertThat(e.getActual()).isEqualTo(this.platform.getOwner().getId());
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final CreateResourceFileCtx ctx = ResourceFileDomainUtils.createCtx(this.platform,
        this.resourceFileInternalService);

    // When
    final ResourceFile resourceFile = this.resourceFileInternalService.create(ctx);

    // Then
    assertThat(resourceFile).isNotNull();
    assertThat(resourceFile.getOwner()).isEqualTo(this.operator);
    assertThat(resourceFile.getAgentPlatform()).isEqualTo(this.platform);
    assertThat(resourceFile.getName()).isEqualTo(ctx.getName());
    assertThat(resourceFile.getHistory()).isEmpty();
    assertThat(resourceFile.getCurrentRevisionNumber()).isEqualTo(0);
    assertThat(resourceFile.getCurrentRevision()).isNull();
    if (this.saveAndFlush) {
      assertThat(resourceFile.getId()).isGreaterThan(0);
      assertThat(resourceFile.getCreate()).isGreaterThanOrEqualTo(this.now).isEqualTo(resourceFile.getUpdate());
    } else {
      assertThat(resourceFile.getId()).isEqualTo(0);
      assertThat(resourceFile.getCreate()).isEqualTo(resourceFile.getUpdate()).isNull();
    }
  }

  @Test
  public void testReadWith0() throws Exception {
    assertThat(this.resourceFileInternalService.read(0)).isNull();
  }

  @Test
  public void testReadWithId() throws Exception {
    // Given
    final ResourceFileEntity rf1 = ResourceFileDomainUtils.create(this.platform, this.resourceFileInternalService);

    // When
    final ResourceFile rf2 = this.resourceFileInternalService.read(rf1.getId());

    // Then
    assertThat(rf2).isEqualTo(rf1);
  }
}
