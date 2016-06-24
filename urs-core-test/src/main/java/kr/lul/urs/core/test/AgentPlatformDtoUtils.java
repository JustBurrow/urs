/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.positive;
import static kr.lul.urs.util.Randoms.in;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import java.util.Collections;
import java.util.List;

import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.command.ReadAgentPlatformCmd;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.repository.AgentPlatformRepository;
import kr.lul.urs.core.service.AgentPlatformService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class AgentPlatformDtoUtils {

  /**
   * 임의의 플랫폼을 만들 수 있는 커맨드를 만든다.
   *
   * @param owner
   * @return
   * @since 2016. 4. 28.
   */
  public static CreateAgentPlatformCmd createCmd(int owner) {
    String code = randomAlphabetic(in(1, 3)).toLowerCase() + randomAlphanumeric(in(0, 10));
    String label = "Test%" + randomAlphanumeric(in(1, 10));
    String description = "Random Agent Platform for test.";
    return new CreateAgentPlatformCmd(owner, code, label, description);
  }

  /**
   * 임의의 플랫폼을 만들 수 있는 커맨드를 만든다.
   *
   * @param owner
   * @return
   */
  public static CreateAgentPlatformCmd createCmd(OperatorDto owner) {
    notNull(owner);

    return createCmd(owner.getId());
  }

  /**
   * @param id
   * @param owner
   * @return
   * @since 2016. 5. 5.
   */
  public static ReadAgentPlatformCmd readCmd(int id, int owner) {
    ReadAgentPlatformCmd cmd = new ReadAgentPlatformCmd();
    cmd.setId(id);
    cmd.setOwner(owner);
    return cmd;
  }

  /**
   * 임의의 읽기 커맨드 오브젝트를 만든다.
   *
   * @param repository
   * @return
   * @since 2016. 5. 5.
   */
  public static ReadAgentPlatformCmd readCmd(AgentPlatformRepository repository) {
    notNull(repository);

    List<AgentPlatformEntity> list = repository.findAll();
    Collections.shuffle(list);
    AgentPlatform platform = list.get(0);

    ReadAgentPlatformCmd cmd = new ReadAgentPlatformCmd();
    cmd.setId(platform.getId());
    cmd.setOwner(platform.getOwner().getId());
    return cmd;
  }

  /**
   * @param owner
   * @param service
   * @return
   * @since 2016. 5. 16.
   */
  public static AgentPlatformDto create(OperatorDto owner, AgentPlatformService service) {
    notNull(owner);
    positive(owner.getId());
    notNull(service);

    return service.create(createCmd(owner)).value();
  }

  /**
   * @param owner
   * @param service
   * @return
   * @since 2016. 5. 17.
   */
  public static AgentPlatformDto create(int owner, AgentPlatformService service) {
    positive(owner);
    notNull(service);

    return service.create(createCmd(owner)).value();
  }

  protected AgentPlatformDtoUtils() {
    throw new UnsupportedOperationException();
  }
}
