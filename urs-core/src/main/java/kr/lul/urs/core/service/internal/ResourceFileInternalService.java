/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.command.ReadResourceFileCmd;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.ResourceFile;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER, propagation = Propagation.MANDATORY)
public interface ResourceFileInternalService {
  /**
   * 새로운 리소스 파일을 만든다.
   *
   * @param cmd
   * @return
   * @throws OwnershipException
   *           관리자 정보가 잘못된 경우.
   */
  public ResourceFile create(CreateResourceFileCmd cmd) throws OwnershipException;

  /**
   * 리소스 파일을 가져온다.
   *
   * @param id
   *          ID.
   * @return 리소스 파일 혹은 <code>null</code>.
   */
  public ResourceFile read(int id);

  /**
   * @param cmd
   * @return
   * @throws OwnershipException
   *           관리자 정보가 잘못된 경우.
   */
  public ResourceFile read(ReadResourceFileCmd cmd) throws OwnershipException;

  /**
   * 등록된 리소스 파일이 있는지 여부를 반환한다.
   *
   * @param platform
   * @param name
   * @return 없으면 <code>null</code>.
   */
  public boolean isExists(AgentPlatform platform, String name);

  /**
   * 등록된 리스스 파일을 ID 순서로 반환한다.
   *
   * @return 없으면 빈 {@link List}.
   * @since 2016. 5. 23.
   */
  public List<ResourceFile> list();
}
