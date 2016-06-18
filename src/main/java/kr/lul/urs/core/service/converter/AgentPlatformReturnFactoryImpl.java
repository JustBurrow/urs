package kr.lul.urs.core.service.converter;

import static kr.lul.urs.util.Asserts.notNull;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.spring.tx.Return;

@Service
class AgentPlatformReturnFactoryImpl extends AbstractReturnFactory implements AgentPlatformReturnFactory {
  private Type listType;

  @PostConstruct
  private void postConstruct() throws Exception {
    this.initializeMapper(new PropertyMap<AgentPlatformEntity, AgentPlatformDto>() {
      @Override
      protected void configure() {
        this.map().setOwner(this.source.getOwner().getId());
        if (!AgentPlatformReturnFactoryImpl.this.saveAndFlush) {
          this.skip().setCreate(null);
          this.skip().setUpdate(null);
        }
      }
    });
    this.listType = new TypeToken<List<AgentPlatformDto>>() {
      private static final long serialVersionUID = -1143891363443103897L;
    }.getType();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>AgentPlatformReturnFactory
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Return<AgentPlatformDto> converter(final AgentPlatform agentPlatform) {
    notNull(agentPlatform);
    return () -> this.mapper.map(agentPlatform, AgentPlatformDto.class);
  }

  @Override
  public Return<List<AgentPlatformDto>> converter(final List<AgentPlatform> list) {
    notNull(list);
    return () -> this.mapper.map(list, this.listType);
  }
}
