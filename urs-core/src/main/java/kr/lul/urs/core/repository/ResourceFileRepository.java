/**
 *
 */
package kr.lul.urs.core.repository;

import org.springframework.stereotype.Repository;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.spring.jpa.ownership.OwnableReposigory;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@Repository
public interface ResourceFileRepository extends OwnableReposigory<ResourceFileEntity, Integer, Operator> {
  /**
   * @param agentPlatform
   * @param name
   * @return
   */
  public long countByAgentPlatformAndName(AgentPlatform agentPlatform, String name);

  /**
   * @param agentPlatform
   * @param name
   * @return
   */
  public default boolean exists(AgentPlatform agentPlatform, String name) {
    return 0L < countByAgentPlatformAndName(agentPlatform, name);
  }
}
