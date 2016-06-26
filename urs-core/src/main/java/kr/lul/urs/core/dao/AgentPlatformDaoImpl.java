/**
 *
 */
package kr.lul.urs.core.dao;

import static kr.lul.urs.spring.jpa.util.SortUtils.asc;
import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.notNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.domain.entity.OperatorEntity;
import kr.lul.urs.core.domain.mapping.AgentPlatformMapping.Entity;
import kr.lul.urs.core.repository.AgentPlatformRepository;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@Service
class AgentPlatformDaoImpl extends AbstractDao implements AgentPlatformDao {
  @Autowired
  private AgentPlatformRepository agentPlatformRepository;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>AgentPlatformDao
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public AgentPlatform insert(AgentPlatform platform) {
    notNull(platform);
    assignable(platform, AgentPlatformEntity.class);

    if (this.saveAndFlush) {
      platform = this.agentPlatformRepository.saveAndFlush((AgentPlatformEntity) platform);
    } else {
      platform = this.agentPlatformRepository.save((AgentPlatformEntity) platform);
    }

    return platform;
  }

  @Override
  public AgentPlatform select(int id) {
    if (0 >= id) {
      return null;
    }
    return this.agentPlatformRepository.findOne(id);
  }

  @Override
  public List<AgentPlatform> list() {
    List<AgentPlatformEntity> list = this.agentPlatformRepository.findAll(asc(Entity.ID));
    return new ArrayList<>(list);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.dao.AgentPlatformDao#list(kr.lul.urs.core.domain.Operator)
   * @since 2016. 6. 9.
   */
  @Override
  public List<AgentPlatform> list(Operator owner) {
    notNull(owner, "owner");
    assignable(owner, OperatorEntity.class, "owner");

    List<AgentPlatformEntity> platforms = this.agentPlatformRepository.findAllByOwner(owner, asc(Entity.ID));

    return Collections.unmodifiableList(platforms);
  }
}
