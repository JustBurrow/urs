/**
 *
 */
package kr.lul.urs.core.service;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.EMails;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class OperatorServiceTest extends AbstractServiceTest {
  @Before
  public void setUp() throws Exception {
    assertNotNull(this.operatorService);
    this.setNow();
    this.setOperatorAsRandom();
  }

  @Test(expected = AssertionException.class)
  public void testCreateWithNull() throws Exception {
    // When
    this.operatorService.create(null);

    // Then
    fail();
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final String email = EMails.random();
    final CreateOperatorCmd cmd = new CreateOperatorCmd(email, Strings.random(4, 20));

    // When
    final OperatorDto operator = this.operatorService.create(cmd).value();

    // Then
    assertThat(operator.getId(), greaterThan(0));
    assertEquals(email, operator.getEmail());
    assertThat(operator.getCreate(), after(this.now));
  }

  @Test
  public void testCreateAndRead() throws Exception {
    // Given
    final CreateOperatorCmd cmd = new CreateOperatorCmd(EMails.random(), Strings.random(4, 20));
    final OperatorDto o1 = this.operatorService.create(cmd).value();

    // When
    final OperatorDto o2 = this.operatorService.read(o1.getId()).value();
    final OperatorDto o3 = this.operatorService.read(o2.getId()).value();

    // Then
    assertNotNull(o2);
    assertEquals(o1.getId(), o2.getId());
    assertEquals(o1.getEmail(), o2.getEmail());
    assertEquals(o1.getCreate(), o2.getCreate());
    assertEquals(o2, o3);
    assertNotSame(o1, o2);
    assertNotSame(o1, o3);
  }
}
