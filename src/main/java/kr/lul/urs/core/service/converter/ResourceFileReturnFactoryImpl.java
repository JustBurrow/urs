/**
 *
 */
package kr.lul.urs.core.service.converter;

import static kr.lul.urs.util.Asserts.notNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        this.skip().setCurrentRevision(0);
        this.skip().setCurrentSha1(null);

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
  public Return<ResourceFileDto> converter(final ResourceFile domain) {
    notNull(domain);

    final ResourceFileRevision revision = domain.getCurrentRevision();
    return () -> {
      ResourceFileDto dto = this.mapper.map(domain, ResourceFileDto.class);
      if (null == revision) {
        dto.setCurrentRevision(0);
        dto.setCurrentSha1(null);
      } else {
        dto.setCurrentRevision(revision.getRevision());
        dto.setCurrentSha1(revision.getSha1());
      }
      return dto;
    };
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.tx.ReturnFactory#converter(java.util.List)
   * @since 2016. 5. 20.
   */
  @Override
  public Return<List<ResourceFileDto>> converter(List<ResourceFile> list) {
    notNull(list);

    if (list.isEmpty()) {
      return () -> new ArrayList<>();
    }

    final Map<Integer, ResourceFileRevision> revMap = list.stream()
        .map(rf -> rf.getCurrentRevision())
        .filter(rf -> null != rf)
        .collect(Collectors.toMap(rev -> rev.getId().resourceFile(), rev -> rev));

    return () -> {
      List<ResourceFileDto> dtos = this.mapper.map(list, this.listType);
      dtos.forEach(dto -> {
        ResourceFileRevision revision = revMap.get(dto.getResourceFile());
        if (null == revision) {
          dto.setCurrentRevision(0);
          dto.setCurrentSha1(null);
        } else {
          dto.setCurrentRevision(revision.getRevision());
          dto.setCurrentSha1(revision.getSha1());
        }
      });
      return dtos;
    };
  }
}
