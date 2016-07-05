package kr.lul.urs.core.test;

import static kr.lul.urs.core.test.OperatorDtoUtils.command;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.AbstractCoreTest;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.test.OperatorDtoUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class OperatorDtoUtilsTest extends AbstractDtoTest {
  @Before
  public void setUp() throws Exception {
    this.setNow();
  }

  @Test
  public void testConstruct() {
    assertThatThrownBy(() -> new OperatorDtoUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  /**
   * @see http://tinyurl.com/za9pdbn
   */
  @Test
  public void testCommand() {
    // When
    final CreateOperatorCmd cmd = command();

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getEmail()).isNotNull().is(AbstractCoreTest.IS_EMAIL);
    assertThat(cmd.getPassword()).matches(".{6,10}");
  }
}
