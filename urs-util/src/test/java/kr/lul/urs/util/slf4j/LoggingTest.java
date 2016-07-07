/**
 *
 */
package kr.lul.urs.util.slf4j;

import static kr.lul.urs.util.slf4j.Logging.debug;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @since 2016. 7. 6.
 * @author Just Burrow just.burrow@lul.kr
 */
public class LoggingTest {
  @Test
  public void testDebugWithMessage() throws Exception {
    // Given
    String message = RandomStringUtils.randomAlphabetic(10);

    // When
    debug(message);

    // Then
    // TODO ???
  }
}
