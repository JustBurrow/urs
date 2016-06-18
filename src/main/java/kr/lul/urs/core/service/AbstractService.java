/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.util.Asserts.notNull;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.core.command.OwnershipCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.service.internal.OperatorInternalService;

/**
 * @since 2016. 6. 8.
 * @author Just Burrow just.burrow@lul.kr
 */
abstract class AbstractService {
  @Autowired
  protected OperatorInternalService operatorInternalService;

  protected ModelMapper             mapper;

  /**
   * 컨텍스트 매퍼를 초기화한다.
   *
   * @param propertyMaps
   * @since 2016. 6. 9.
   */
  protected void initializeMapper(PropertyMap<?, ?>... propertyMaps) {
    this.mapper = new ModelMapper();

    for (PropertyMap<?, ?> propertyMap : propertyMaps) {
      this.mapper.addMappings(propertyMap);
    }
  }

  /**
   * 운영자 정보를 반환한다.
   *
   * @param cmd
   * @return 없으면 <code>null</code>.
   * @since 2016. 6. 8.
   */
  protected Operator getOperator(OwnershipCmd cmd) {
    notNull(cmd, "cmd");

    return this.operatorInternalService.read(cmd.getOwner());
  }
}
