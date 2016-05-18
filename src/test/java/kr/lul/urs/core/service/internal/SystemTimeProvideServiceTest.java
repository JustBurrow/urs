/**
 *
 */
package kr.lul.urs.core.service.internal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class SystemTimeProvideServiceTest extends AbstractTest {
  @Test
  public void testSetApplicationContext() {
    assertThat(Timestamper.getTimeProvider()).isNotNull();
  }
}
