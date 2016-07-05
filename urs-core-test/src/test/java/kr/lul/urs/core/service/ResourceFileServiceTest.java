package kr.lul.urs.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.command.UpdateResourceFileCmd;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.dto.ResourceFileDto;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.core.test.AbstractDtoTest;
import kr.lul.urs.core.test.OperatorDtoUtils;
import kr.lul.urs.core.test.ResourceFileDtoUtils;
import kr.lul.urs.spring.tx.Return;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class ResourceFileServiceTest extends AbstractDtoTest {
  @Autowired
  private ResourceFileService resourceFileService;

  @Before
  public void setUp() throws Exception {
    this.setAgentPlatformAsRandom();

    assertThat(this.resourceFileService).isNotNull();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.resourceFileService.create(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreateWithIllegalOwnership() throws Exception {
    // Given
    CreateResourceFileCmd cmd = ResourceFileDtoUtils.createCmd(this.platform);
    cmd.setOwner(1 + cmd.getOwner());

    // When & Then
    assertThatThrownBy(() -> this.resourceFileService.create(cmd)).isInstanceOf(OwnershipException.class);
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    CreateResourceFileCmd cmd = ResourceFileDtoUtils.createCmd(this.platform);

    // When
    ResourceFileDto dto = this.resourceFileService.create(cmd).value();

    // Then
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isGreaterThan(0);
    assertThat(dto.getOwner()).isEqualTo(this.operator.getId())
        .isEqualTo(cmd.getOwner());
    assertThat(dto.getAgentPlatform()).isEqualTo(this.platform.getId());
    assertThat(dto.getName()).isEqualTo(cmd.getName());
    assertThat(dto.getCurrentRevision()).isEqualTo(0);
    this.assertTimestamp(dto);
  }

  @Test
  public void testReadWithIdNegative() throws Exception {
    assertThat(this.resourceFileService.read(Randoms.negative())).isNull();
  }

  @Test
  public void testReadWithId0() throws Exception {
    assertThat(this.resourceFileService.read(0)).isNull();
  }

  @Test
  public void testReadWithId() throws Exception {
    // Given
    ResourceFileDto expected = ResourceFileDtoUtils.create(this.platform, this.resourceFileService);
    assertThat(expected).isNotNull();

    // When
    Return<ResourceFileDto> actual = this.resourceFileService.read(expected.getId());

    // Then
    assertThat(actual).isNotNull();
  }

  @Test
  public void testList() throws Exception {
    // Given
    ResourceFileDto resourceFile = ResourceFileDtoUtils.create(this.platform, this.resourceFileService);

    // When
    Return<List<ResourceFileDto>> rv = this.resourceFileService.list();
    List<ResourceFileDto> list = rv.value();

    // Then
    assertThat(list).isNotNull()
        .contains(resourceFile);
    assertThat(list.get(list.size() - 1)).isEqualTo(resourceFile)
        .isNotSameAs(resourceFile);
  }

  @Test
  public void testUpdateWithNull() throws Exception {
    assertThatThrownBy(() -> this.resourceFileService.update(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testUpdateWithIllegalOwnership() throws Exception {
    // Given
    ResourceFileDto resourceFile = ResourceFileDtoUtils.create(this.platform, this.resourceFileService);
    OperatorDto owner = OperatorDtoUtils.create(this.operatorService);
    File file = getTestFile("testUpdate1");

    UpdateResourceFileCmd cmd = new UpdateResourceFileCmd(owner.getId(), resourceFile.getId(), file);

    // When
    assertThatThrownBy(() -> this.resourceFileService.update(cmd))
        .isInstanceOf(OwnershipException.class)
        .hasMessageContaining("no permission");
  }

  @Test
  public void testUpdate() throws Exception {
    // Given
    final ResourceFileDto before = ResourceFileDtoUtils.create(this.platform, this.resourceFileService);
    File file = getTestFile("testUpdate1");
    FileInputStream input = new FileInputStream(file);
    final String sha1 = DigestUtils.sha1Hex(input);
    input.close();

    UpdateResourceFileCmd cmd = new UpdateResourceFileCmd();
    cmd.setId(before.getId());
    cmd.setOwner(before.getOwner());
    cmd.setInput(file);

    Thread.sleep(Randoms.in(10L, 100L));

    // When
    ResourceFileDto after = this.resourceFileService.update(cmd).value();

    // Then
    assertThat(after).isNotNull()
        .isNotSameAs(before);
    assertThat(after.getId()).isEqualTo(before.getId());
    assertThat(after.getOwner()).isEqualTo(before.getOwner());
    assertThat(after.getAgentPlatform()).isEqualTo(before.getAgentPlatform());
    assertThat(after.getName()).isEqualTo(before.getName());
    assertThat(after.getCurrentRevision()).isGreaterThan(before.getCurrentRevision());
    assertThat(after.getCurrentSha1()).isEqualTo(sha1);
    assertThat(after.getCreate()).isEqualTo(before.getCreate());
    assertThat(after.getUpdate()).isGreaterThan(before.getUpdate())
        .isGreaterThanOrEqualTo(this.now);
  }
}
