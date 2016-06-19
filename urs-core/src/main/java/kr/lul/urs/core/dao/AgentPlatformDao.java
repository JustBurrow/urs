/**
 *
 */
package kr.lul.urs.core.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;

/**
 * {@link AgentPlatform}의 DB 연동 부분에서 SQL에 해당하는 부분을 관리한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
public interface AgentPlatformDao {
  /**
   * 에이전트(클라이언트)의 플랫폼을 저장한다.
   *
   * @param platform
   *          저장할 데이터.
   * @return 저장이 완료된 데이터.
   */
  public AgentPlatform insert(AgentPlatform platform);

  /**
   * @param id
   * @return
   */
  public AgentPlatform select(int id);

  /**
   * @return ID 오름차순.
   * @since 2016. 5. 5.
   */
  public List<AgentPlatform> list();

  /**
   * 지정한 운영자의 플랫폼을 ID 오름차순으로 반환한다.
   *
   * @param owner
   *          플랫폼의 소유자(운영자)
   * @return ID 오름차순 정리한 플랫폼.
   * @since 2016. 6. 9.
   */
  public List<AgentPlatform> list(Operator owner);
}
