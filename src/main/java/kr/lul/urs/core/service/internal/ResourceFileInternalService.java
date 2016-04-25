/**
 *
 */
package kr.lul.urs.core.service.internal;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.command.ReadResourceFileCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.ResourceFile;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER, propagation = Propagation.MANDATORY)
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
   * @param clientPlatform
   * @param name
   * @return
   */
  public boolean isExists(ClientPlatform clientPlatform, String name);
}
