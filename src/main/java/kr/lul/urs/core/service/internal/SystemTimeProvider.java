/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.util.Asserts.notNull;

import java.time.Instant;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import kr.lul.urs.spring.jpa.timestamp.Timestamper;
import kr.lul.urs.util.TimeProvider;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
@Service
public class SystemTimeProvider implements TimeProvider, ApplicationContextAware {
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ApplicationContextAware
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    notNull(applicationContext);
    Timestamper.setTimeProvider(applicationContext.getBean(TimeProvider.class));
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>TimeProvider
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Instant now() {
    return Instant.now();
  }
}
