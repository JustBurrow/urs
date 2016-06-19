/**
 *
 */
package kr.lul.urs.core.initialize;

import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.internal.ResourceFileOutputStreamFactory;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 11.
 */
@Component
class ResourceFileStorageInitializer implements ApplicationContextAware {
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ApplicationContextAware
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    notNull(applicationContext);

    ResourceFileOutputStreamFactory factory = applicationContext.getBean(ResourceFileOutputStreamFactory.class);
    ResourceFileEntity.setOutputStreamFactory(factory);
  }
}
