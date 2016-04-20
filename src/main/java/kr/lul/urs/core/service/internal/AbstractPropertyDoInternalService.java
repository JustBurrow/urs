/**
 *
 */
package kr.lul.urs.core.service.internal;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 여러 DO에서 속성값으로 사용하는 DO와 관련이 있는 컴포넌트 정보와 유틸리티 메서드를 제공한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 18.
 */
class AbstractPropertyDoInternalService {
  @Autowired
  protected OperatorInternalService       operatorInternalService;
  @Autowired
  protected ClientPlatformInternalService clientPlatformInternalService;
}
