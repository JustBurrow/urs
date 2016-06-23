/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_DAO_SAVE_AND_FLUSH;
import static kr.lul.urs.util.Asserts.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.dao.AgentPlatformDao;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.repository.AgentPlatformRepository;
import kr.lul.urs.core.service.context.CreateAgentPlatformCtx;
import kr.lul.urs.core.service.context.UpdateAgentPlatformCtx;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@Service
class AgentPlatformInternalServiceImpl implements AgentPlatformInternalService {
  @Value("${" + KEY_DAO_SAVE_AND_FLUSH + "}")
  private boolean                 saveAndFlush;

  @Autowired
  private AgentPlatformDao        agentPlatformDao;
  @Autowired
  private AgentPlatformRepository agentPlatformRepository;

  private AgentPlatform save(AgentPlatformEntity platform) {
    if (this.saveAndFlush) {
      return this.agentPlatformRepository.saveAndFlush(platform);
    } else {
      return this.agentPlatformRepository.save(platform);
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>AgentPlatformInternalService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public AgentPlatform create(CreateAgentPlatformCtx ctx) {
    notNull(ctx, "context");

    AgentPlatform platform = new AgentPlatformEntity(ctx.getOwner(), ctx.getCode(), ctx.getLabel(),
        ctx.getDescription());
    platform = this.agentPlatformDao.insert(platform);

    return platform;
  }

  @Override
  public AgentPlatform read(int id) {
    if (0 >= id) {
      return null;
    }
    AgentPlatform platform = this.agentPlatformDao.select(id);
    return platform;
  }

  @Override
  public List<AgentPlatform> list() {
    return this.agentPlatformDao.list();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.internal.AgentPlatformInternalService#list(kr.lul.urs.core.domain.Operator)
   * @since 2016. 6. 9.
   */
  @Override
  public List<AgentPlatform> list(Operator owner) {
    notNull(owner, "owner");

    return this.agentPlatformDao.list(owner);
  }

  @Override
  public AgentPlatform update(AgentPlatform platform, UpdateAgentPlatformCtx ctx) {
    notNull(ctx, "context");
    notNull(platform, "platform");

    platform.setLabel(ctx.getLabel());
    platform.setDescription(ctx.getDescription());

    AgentPlatform updated = this.save((AgentPlatformEntity) platform);
    return updated;
  }
}
