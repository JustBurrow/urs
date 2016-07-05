/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.dto.ResourceFileDto;
import kr.lul.urs.core.service.ResourceFileService;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class ResourceFileDtoUtils {
  private static final Logger log = LoggerFactory.getLogger(ResourceFileDtoUtils.class);

  public static CreateResourceFileCmd createCmd(AgentPlatformDto platform) {
    return createCmd(platform.getOwner(), platform.getId());
  }

  /**
   * 에이전트(클라이언트)의 리소스 파일을 만들 수 있는 임의의 명령을 만든다.
   * 기존 {@link ResourceFile}과 중복되는지 여부는 확인하지 않는다.
   *
   * @param platform
   * @return
   */
  public static CreateResourceFileCmd createCmd(int owner, int platform) {
    notNull(platform);

    StringBuilder name = new StringBuilder();
    do {
      name.append('/').append(Strings.from(1, 10, 'a', 'z'));
    } while (1 > Randoms.notNegative(10));

    return new CreateResourceFileCmd(owner, platform, name.toString());
  }

  /**
   * 새로운 {@link ResourceFile}을 생성하, 저장후 반환한다.
   *
   * @param platform
   *          생성할 리소스 파일의 플랫폼. 소유자 정보도 여기에서 얻는다.
   * @param resourceFileService
   * @return
   * @throws OwnershipException
   * @since 2016. 5. 20.
   */
  public static ResourceFileDto create(AgentPlatformDto platform, ResourceFileService resourceFileService)
      throws OwnershipException {
    notNull(platform);
    positive(platform.getId(), "platform.id");
    positive(platform.getOwner(), "platform.owner");
    notNull(resourceFileService);

    ResourceFileDto file = null;
    do {
      CreateResourceFileCmd cmd = createCmd(platform);
      try {
        file = resourceFileService.create(cmd).value();
      } catch (DuplicateKeyException e) {
        file = null;
        if (log.isDebugEnabled()) {
          log.debug(null == cmd ? "null" : cmd.toString(), e);
        }
      }
    } while (null == file);
    return file;
  }

  protected ResourceFileDtoUtils() {
    throw new UnsupportedOperationException();
  }
}
