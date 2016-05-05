package kr.lul.urs.core;

import static kr.lul.urs.core.OperatorUtils.command;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.core.command.CreateOperatorCmd;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class OperatorUtilsTest extends AbstractUtilsTest {
  @Before
  public void setUp() throws Exception {
    this.setNow();
  }

  @Test
  public void testConstruct() {
    assertThatThrownBy(() -> new OperatorUtils() {
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
    assertThat(cmd.getEmail()).isNotNull().is(AbstractTest.IS_EMAIL);
    assertThat(cmd.getPassword()).matches(".{6,10}");
  }
}
