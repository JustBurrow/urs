/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Randoms.in;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.repository.ClientPlatformRepository;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
public abstract class ClientPlatformUtils {
  /**
   * 임의의 인스턴스를 만들 수 있는 커맨들르 만든다.
   *
   * @return
   */
  public static CreateClientPlatformCmd createCmd(Operator owner) {
    notNull(owner);

    String code = randomAlphabetic(in(1, 3)).toLowerCase() + randomAlphanumeric(in(0, 10));
    String label = "Test%" + randomAlphanumeric(in(1, 10));
    String description = "Random Client Platform for test.";
    return new CreateClientPlatformCmd(owner.getId(), code, label, description);
  }

  /**
   * DB 저장 상태와 무관한 임의의 클라이언트 인스턴스를 만든다.
   *
   * @return
   */
  public static ClientPlatformEntity instance(Operator owner) {
    notNull(owner);

    String code = randomAlphabetic(1) + randomAlphanumeric(in(1, 10));
    String label = "Test$" + randomAlphanumeric(in(1, 10));
    String description = "Random Client Platform for test.";

    return new ClientPlatformEntity(owner, code, label, description);
  }

  /**
   * 임의의 인스턴스를 만들어 DB에 바로 저장한다.
   * TODO 중복 확인.
   *
   * @param owner
   * @param clientPlatformRepository
   * @return
   */
  public static ClientPlatformEntity saveAndFlush(Operator owner, ClientPlatformRepository clientPlatformRepository) {
    notNull(owner);
    notNull(clientPlatformRepository);

    return clientPlatformRepository.saveAndFlush(instance(owner));
  }

  /**
   * 임의의 DO를 만든다.
   *
   * @param owner
   * @param clientPlatformInternalService
   * @return
   */
  public static ClientPlatformEntity create(Operator owner,
      ClientPlatformInternalService clientPlatformInternalService) {
    notNull(owner);
    notNull(clientPlatformInternalService);

    return (ClientPlatformEntity) clientPlatformInternalService.create(createCmd(owner));
  }

  protected ClientPlatformUtils() {
    throw new UnsupportedOperationException();
  }
}
