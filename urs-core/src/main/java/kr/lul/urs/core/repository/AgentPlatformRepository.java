/**
 *
 */
package kr.lul.urs.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.domain.entity.OperatorEntity;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@Repository
public interface AgentPlatformRepository extends JpaRepository<AgentPlatformEntity, Integer> {
  /**
   * @param owner
   * @return
   * @since 2016. 6. 9.
   */
  public List<AgentPlatformEntity> findAllByOwnerOrderByIdAsc(OperatorEntity owner);
}
