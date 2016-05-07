package kr.lul.urs.core.service.converter;

import static kr.lul.urs.util.Asserts.notNull;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.spring.tx.Return;

@Service
class ClientPlatformReturnFactoryImpl extends AbstractReturnFactory implements ClientPlatformReturnFactory {
  private Type listType;

  @PostConstruct
  private void postConstruct() throws Exception {
    this.initializeMapper(new PropertyMap<ClientPlatformEntity, ClientPlatformDto>() {
      @Override
      protected void configure() {
        this.map().setOwner(this.source.getOwner().getId());
        if (!ClientPlatformReturnFactoryImpl.this.saveAndFlush) {
          this.skip().setCreate(null);
          this.skip().setUpdate(null);
        }
      }
    });
    this.listType = new TypeToken<List<ClientPlatformDto>>() {
      private static final long serialVersionUID = -1143891363443103897L;
    }.getType();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ClientPlatformReturnFactory
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Return<ClientPlatformDto> converter(final ClientPlatform clientPlatform) {
    notNull(clientPlatform);
    return () -> this.mapper.map(clientPlatform, ClientPlatformDto.class);
  }

  @Override
  public Return<List<ClientPlatformDto>> converter(final List<ClientPlatform> list) {
    notNull(list);
    return () -> this.mapper.map(list, this.listType);
  }
}
