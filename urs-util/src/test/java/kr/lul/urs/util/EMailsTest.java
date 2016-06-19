/**
 *
 */
package kr.lul.urs.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 28.
 */
public class EMailsTest extends AbstractTest {
  public static final int COUNT = 1000;

  private EmailValidator  validator;

  @Before
  public void setUp() throws Exception {
    this.validator = new EmailValidator();
  }

  @Test
  public void testNew() {
    assertThatThrownBy(() -> new EMails() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testRandom() throws Exception {
    final Set<String> emails = new HashSet<>();

    for (int i = 0; i < COUNT; i++) {
      String email = EMails.random();

      assertThat(email).isNotNull();
      assertThat(this.validator.isValid(email, null)).isEqualTo(true);
      assertThat(emails).doesNotContain(email);

      emails.add(email);
    }
  }
}
