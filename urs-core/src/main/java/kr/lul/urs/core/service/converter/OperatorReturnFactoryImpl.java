/**
 *
 */
package kr.lul.urs.core.service.converter;

import static kr.lul.urs.util.Asserts.notNull;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.OperatorEntity;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.spring.tx.Return;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 6.
 */
@Service
class OperatorReturnFactoryImpl extends AbstractReturnFactory implements OperatorReturnFactory {
  private Type listType;

  @PostConstruct
  private void postConstruct() throws Exception {
    this.initializeMapper(new PropertyMap<OperatorEntity, OperatorDto>() {
      @Override
      protected void configure() {
        if (!OperatorReturnFactoryImpl.this.saveAndFlush) {
          this.skip().setCreate(null);
          this.skip().setUpdate(null);
        }
      }
    });
    this.listType = new TypeToken<List<OperatorDto>>() {
      private static final long serialVersionUID = 8664992560639196797L;
    }.getType();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>OperatorReturnFactory
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Return<OperatorDto> converter(final Operator domain) {
    notNull(domain);

    return () -> this.mapper.map(domain, OperatorDto.class);
  }

  @Override
  public Return<List<OperatorDto>> converter(final List<Operator> list) {
    notNull(list);

    return () -> this.mapper.map(list, this.listType);
  }
}
