/**
 *
 */
package kr.lul.urs.application.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

/**
 * @since 2016. 6. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
public class AgentPlatformApiConfigurationTest {
  @Test
  public void testConstructor() throws Exception {
    assertThatThrownBy(() -> new AgentPlatformApiConstants() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
