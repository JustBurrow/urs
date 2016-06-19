/**
 *
 */
package kr.lul.urs.core.service.converter;

import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_DAO_SAVE_AND_FLUSH;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 7.
 */
abstract class AbstractReturnFactory {
  @Value("${" + KEY_DAO_SAVE_AND_FLUSH + "}")
  protected boolean     saveAndFlush;

  protected ModelMapper mapper;

  protected void initializeMapper(PropertyMap<?, ?>... propertyMaps) throws Exception {
    this.mapper = new ModelMapper();
    for (PropertyMap<?, ?> propertyMap : propertyMaps) {
      this.mapper.addMappings(propertyMap);
    }
  }
}
