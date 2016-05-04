/**
 *
 */
package kr.lul.urs.core.service.internal;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class SystemTimeProviderTest {
  @Test
  public void testSetApplicationContext() {
    assertNotNull(Timestamper.getTimeProvider());
  }
}
