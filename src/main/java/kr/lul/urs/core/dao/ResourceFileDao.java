/**
 *
 */
package kr.lul.urs.core.dao;

import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.ResourceFile;

/**
 * DB의 {@link ResourceFile}에 접근하기 위한 쿼리 생성을 담당한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
public interface ResourceFileDao {
  /**
   * @param resourceFile
   * @return
   */
  public ResourceFile insert(ResourceFile resourceFile);

  /**
   * DB에 데이터가 있는지 여부를 확인한다.
   *
   * @param clientPlatform
   * @param name
   * @return 데이터가 있으면 <code>true</code>.
   */
  public boolean isExists(ClientPlatform clientPlatform, String name);
}
