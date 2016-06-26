/**
 *
 */
package kr.lul.urs.core.repository;

import org.springframework.stereotype.Repository;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.spring.jpa.ownership.OwnableReposigory;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@Repository
public interface AgentPlatformRepository extends OwnableReposigory<AgentPlatformEntity, Integer, Operator> {
}
