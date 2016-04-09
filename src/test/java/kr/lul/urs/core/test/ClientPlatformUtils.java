/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Randoms.in;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.repository.ClientPlatformRepository;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
public abstract class ClientPlatformUtils {
  /**
   * 저장하지 않은 임의의 클라이언트 인스턴스를 만든다.
   *
   * @return
   */
  public static ClientPlatformEntity random(Operator owner) {
    notNull(owner);

    String code = randomAlphabetic(1) + randomAlphanumeric(in(1, 10));
    String label = "Test$" + randomAlphanumeric(in(1, 10));
    String description = "Random Client Platform for test.";

    return new ClientPlatformEntity(owner, code, label, description);
  }

  public static ClientPlatformEntity create(Operator owner, ClientPlatformRepository clientPlatformRepository) {
    notNull(owner);
    notNull(clientPlatformRepository);

    return clientPlatformRepository.saveAndFlush(random(owner));
  }

  protected ClientPlatformUtils() {
    throw new UnsupportedOperationException();
  }
}
