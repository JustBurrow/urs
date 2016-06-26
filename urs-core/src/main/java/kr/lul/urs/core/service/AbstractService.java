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
import kr.lul.urs.core.service.internal.AgentPlatformInternalService;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.spring.jpa.ownership.Ownable;
import kr.lul.urs.spring.jpa.ownership.Owner;

/**
 * @since 2016. 6. 8.
 * @author Just Burrow just.burrow@lul.kr
 */
abstract class AbstractService {
  @Autowired
  protected OperatorInternalService      operatorInternalService;
  @Autowired
  protected AgentPlatformInternalService agentPlatformInternalService;

  protected ModelMapper                  mapper;

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

  /**
   * 커맨드에서 지정한 운영자가 데이터의 소유자인지를 확인한다.
   *
   * @param cmd
   *          운영자 ID를 가진 커맨드.
   * @param owned
   *          확인할 데이터.
   * @return 인자로 받은 <code>owned</code>.
   * @throws OwnershipException
   *           운영자가 없거나 소유권을 가진 운영자가 아닐 때.
   * @since 2016. 6. 26.
   */
  protected <O extends Ownable<Operator>> O checkOwnership(OwnershipCmd cmd, O owned) throws OwnershipException {
    notNull(cmd, "cmd");

    if (null == owned) {
      return null;
    }

    Owner owner = this.operatorInternalService.read(cmd.getOwner());
    if (null == owner) {
      throw new OwnershipException("no owner", cmd.getOwner(), null);
    } else if (!owner.equals(owned.getOwner())) {
      throw new OwnershipException("no permission.", cmd.getOwner(), owned.getOwner().getId());
    }

    return owned;
  }
}
