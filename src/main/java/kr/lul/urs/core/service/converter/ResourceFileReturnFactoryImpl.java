/**
 *
 */
package kr.lul.urs.core.service.converter;

import static kr.lul.urs.util.Asserts.notNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.ResourceFileRevision;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.dto.ResourceFileDto;
import kr.lul.urs.spring.tx.Return;

/**
 * @since 2016. 5. 20.
 * @author Just Burrow just.burrow@lul.kr
 */
@Service
class ResourceFileReturnFactoryImpl extends AbstractReturnFactory implements ResourceFileReturnFactory {
  private Type listType;

  @PostConstruct
  private void postConstruct() throws Exception {
    this.initializeMapper(new PropertyMap<ResourceFileEntity, ResourceFileDto>() {
      @Override
      protected void configure() {
        this.map().setId(this.source.getId());
        this.map().setOwner(this.source.getOwner().getId());
        this.map().setClientPlatform(this.source.getClientPlatform().getId());
        this.map().setName(this.source.getName());

        ResourceFileRevision revision = this.source.getCurrentRevision();
        this.map().setCurrentRevision(revision.getRevision());
        this.map().setCurrentSha1(revision.getSha1());

        if (!ResourceFileReturnFactoryImpl.this.saveAndFlush) {
          this.skip().setCreate(null);
          this.skip().setUpdate(null);
        }
      }
    });
    this.listType = new TypeToken<List<ResourceFileDto>>() {
      private static final long serialVersionUID = -7439708380947066764L;
    }.getType();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFileReturnFactory
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.tx.ReturnFactory#converter(java.lang.Object)
   * @since 2016. 5. 20.
   */
  @Override
  public Return<ResourceFileDto> converter(ResourceFile domain) {
    notNull(domain);

    return () -> this.mapper.map(domain, ResourceFileDto.class);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.tx.ReturnFactory#converter(java.util.List)
   * @since 2016. 5. 20.
   */
  @Override
  public Return<List<ResourceFileDto>> converter(List<ResourceFile> list) {
    notNull(list);

    return list.isEmpty()
        ? () -> new ArrayList<>()
        : () -> this.mapper.map(list, this.listType);
  }
}
