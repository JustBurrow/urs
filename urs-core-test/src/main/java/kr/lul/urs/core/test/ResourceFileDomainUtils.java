/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.notNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.context.CreateResourceFileCtx;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;
import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
public abstract class ResourceFileDomainUtils {
  private static final Logger log = LoggerFactory.getLogger(ResourceFileDomainUtils.class);

  /**
   * @param platform
   * @param resourceFileInternalService
   * @return
   * @since 2016. 6. 23.
   */
  public static CreateResourceFileCtx createCtx(AgentPlatform platform,
      ResourceFileInternalService resourceFileInternalService) {

    StringBuilder name = new StringBuilder();
    do {
      name.append('/').append(Strings.from(1, 10, 'a', 'z'));
    } while (1 > Randoms.notNegative(10) && resourceFileInternalService.isExists(platform, name.toString()));

    return new CreateResourceFileCtx(platform.getOwner(), platform, name.toString());
  }

  /**
   * 에이전트(클라이언트)의 {@link ResourceFile}을 임의로 만들어 반환한다.
   *
   * @param platform
   * @param resourceFileInternalService
   * @return
   * @throws OwnershipException
   */
  public static ResourceFileEntity create(AgentPlatform platform,
      ResourceFileInternalService resourceFileInternalService) throws OwnershipException {
    notNull(platform);
    assignable(platform, AgentPlatformEntity.class);
    notNull(resourceFileInternalService);

    ResourceFileEntity file = null;
    do {
      CreateResourceFileCtx ctx = createCtx(platform, resourceFileInternalService);
      try {
        file = (ResourceFileEntity) resourceFileInternalService.create(ctx);
      } catch (DuplicateKeyException e) {
        file = null;
        if (log.isDebugEnabled()) {
          log.debug(ctx.toString(), e);
        }
      }
    } while (null == file);

    return file;
  }

  protected ResourceFileDomainUtils() {
    throw new UnsupportedOperationException();
  }
}
