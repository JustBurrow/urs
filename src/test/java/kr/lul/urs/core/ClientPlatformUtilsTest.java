/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.core.ClientPlatformUtils.createCmd;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.core.service.OperatorServiceUtils;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class ClientPlatformUtilsTest {
  @Autowired
  private OperatorService operatorService;

  @Test
  public void testCostructor() {
    assertThatThrownBy(() -> new ClientPlatformUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateCmdWithOperator() throws Exception {
    // Given
    final OperatorDto owner = OperatorServiceUtils.create(this.operatorService).value();

    // When
    final CreateClientPlatformCmd cmd = createCmd(owner);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getOwner()).isEqualTo(owner.getId());
    assertThat(cmd.getCode()).isNotNull().matches("[a-z][a-zA-Z\\d]*");
    assertThat(cmd.getLabel()).isNotNull().isNotEmpty();
    assertThat(cmd.getDescription()).isNotNull();
  }

  @Test
  @Rollback(false)
  public void testCreateCmdWithOperatorDto() throws Exception {
    // Given
    OperatorDto owner = OperatorServiceUtils.create(this.operatorService).value();

    // When
    CreateClientPlatformCmd cmd = createCmd(owner);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getOwner()).isEqualTo(owner.getId());
    assertThat(cmd.getCode()).isNotNull().matches("[a-z][a-zA-Z\\d]*");
    assertThat(cmd.getLabel()).isNotNull().isNotEmpty();
    assertThat(cmd.getDescription()).isNotNull();
  }
}
