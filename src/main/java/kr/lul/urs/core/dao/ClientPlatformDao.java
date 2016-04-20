/**
 *
 */
package kr.lul.urs.core.dao;

import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.domain.ClientPlatform;

/**
 * {@link ClientPlatform}의 DB 연동 부분에서 SQL에 해당하는 부분을 관리한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
public interface ClientPlatformDao {
  /**
   * 클라이언트 플랫폼을 저장한다.
   *
   * @param clientPlatform
   *          저장할 데이터.
   * @return 저장이 완료된 데이터.
   */
  public ClientPlatform insert(ClientPlatform clientPlatform);

  /**
   * @param id
   * @return
   */
  public ClientPlatform select(int id);
}
