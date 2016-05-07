/**
 *
 */
package kr.lul.urs.core.service.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Value;

import kr.lul.urs.application.configuration.InjectionConstants.Properties;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 7.
 */
abstract class AbstractReturnFactory {
  @Value("${" + Properties.KEY_DAO_SAVE_AND_FLUSH + "}")
  protected boolean     saveAndFlush;

  protected ModelMapper mapper;

  protected void initializeMapper(PropertyMap<?, ?>... propertyMaps) throws Exception {
    this.mapper = new ModelMapper();
    for (PropertyMap<?, ?> propertyMap : propertyMaps) {
      this.mapper.addMappings(propertyMap);
    }
  }
}
