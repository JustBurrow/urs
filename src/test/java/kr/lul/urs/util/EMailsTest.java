/**
 *
 */
package kr.lul.urs.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 28.
 */
public class EMailsTest {
  public static final int COUNT = 1000;

  private EmailValidator  validator;

  @Before
  public void setUp() throws Exception {
    this.validator = new EmailValidator();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testNew() {
    new EMails() {
    };
    fail();
  }

  @Test
  public void testRandom() throws Exception {
    final Set<String> emails = new HashSet<>();

    for (int i = 0; i < COUNT; i++) {
      String email = EMails.random();

      assertNotNull(email);
      assertTrue(this.validator.isValid(email, null));
      assertFalse(emails.contains(email));

      emails.add(email);
    }
  }
}
