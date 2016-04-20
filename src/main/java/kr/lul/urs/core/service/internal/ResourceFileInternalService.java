/**
 *
 */
package kr.lul.urs.core.service.internal;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.ResourceFile;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER, propagation = Propagation.MANDATORY)
public interface ResourceFileInternalService {
  /**
   * @param cmd
   * @return
   */
  public ResourceFile create(CreateResourceFileCmd cmd);

  /**
   * 등록된 리소스 파일이 있는지 여부를 반환한다.
   *
   * @param clientPlatform
   * @param name
   * @return
   */
  public boolean isExists(ClientPlatform clientPlatform, String name);
}
