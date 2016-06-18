package kr.lul.urs.core.service.internal;

import static java.lang.String.format;
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

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.OperatorDomainUtils;
import kr.lul.urs.core.ResourceFileApiUtils;
import kr.lul.urs.core.ResourceFileDomainUtils;
import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.command.ReadResourceFileCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ResourceFileInternalServiceTest extends AbstractDomainEntityTest {
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
    final CreateResourceFileCmd cmd = ResourceFileApiUtils.createCmd(this.operator.getId(), this.platform.getId());
    Operator operator;
    do {
      operator = OperatorDomainUtils.create(this.operatorInternalService);
    } while (null != operator && cmd.getOwner() == operator.getId());
    cmd.setOwner(null == operator ? Randoms.positive() : operator.getId());

    // When
    OwnershipException e = (OwnershipException) Assertions
        .catchThrowable(() -> this.resourceFileInternalService.create(cmd));

    // Then
    assertThat(e)
        .isInstanceOf(OwnershipException.class)
        .hasMessageContaining("not equal")
        .hasMessageContaining("[" + cmd.getOwner() + "]")
        .hasMessageMatching(format(".+\\[\\(%d, [^@]+@.+\\)\\].+", this.operator.getId()));
    assertThat(e.getExpected()).isEqualTo(operator.getId());
    assertThat(e.getActual()).isEqualTo(this.platform.getOwner().getId());
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final CreateResourceFileCmd cmd = ResourceFileApiUtils.createCmd(this.operator.getId(), this.platform.getId());

    // When
    final ResourceFile resourceFile = this.resourceFileInternalService.create(cmd);

    // Then
    assertThat(resourceFile).isNotNull();
    assertThat(resourceFile.getOwner()).isEqualTo(this.operator);
    assertThat(resourceFile.getAgentPlatform()).isEqualTo(this.platform);
    assertThat(resourceFile.getName()).isEqualTo(cmd.getName());
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

  @Test
  public void testReadWithNullCmd() throws Exception {
    assertThatThrownBy(() -> this.resourceFileInternalService.read(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadWithReadResourceFileCmdThatIllegalOwner() throws Exception {
    // Given
    final ResourceFileEntity rf1 = ResourceFileDomainUtils.create(this.platform,
        this.resourceFileInternalService);
    int illegalOwner;
    do {
      illegalOwner = OperatorDomainUtils.create(this.operatorInternalService).getId();
    } while (rf1.getOwner().getId() == illegalOwner);
    ReadResourceFileCmd cmd = new ReadResourceFileCmd(illegalOwner, rf1.getId());

    // When & Then
    assertThatThrownBy(() -> this.resourceFileInternalService.read(cmd)).isInstanceOf(OwnershipException.class);
  }

  @Test
  public void testReadWithReadResourceFileCmd() throws Exception {
    // Given
    final ResourceFileEntity rf1 = ResourceFileDomainUtils.create(this.platform, this.resourceFileInternalService);
    ReadResourceFileCmd cmd = new ReadResourceFileCmd(this.operator.getId(), rf1.getId());

    // When
    final ResourceFile rf2 = this.resourceFileInternalService.read(cmd);

    // Then
    assertThat(rf2).isEqualTo(rf1);
  }
}
