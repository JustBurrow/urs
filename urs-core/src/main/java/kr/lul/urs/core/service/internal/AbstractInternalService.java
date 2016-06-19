/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_DAO_SAVE_AND_FLUSH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 일반적인 도메인 엔티티를 다루는 유틸리티를 제공한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 18.
 */
class AbstractInternalService {
  @Value("${" + KEY_DAO_SAVE_AND_FLUSH + "}")
  protected boolean                      saveAndFlush;

  @Autowired
  protected OperatorInternalService      operatorInternalService;
  @Autowired
  protected AgentPlatformInternalService agentPlatformInternalService;
}
