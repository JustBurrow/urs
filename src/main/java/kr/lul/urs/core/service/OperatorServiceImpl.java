/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.util.Asserts.notNull;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.spring.tx.util.Return;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Service
class OperatorServiceImpl implements OperatorService {
  @Autowired
  private OperatorInternalService operatorInternalService;

  private ModelMapper             mapper;

  @PostConstruct
  private void postConstruct() {
    this.mapper = new ModelMapper();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>OperatorService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Return<OperatorDto> create(final CreateOperatorCmd cmd) {
    notNull(cmd);

    Operator operator = this.operatorInternalService.create(cmd);

    return () -> this.mapper.map(operator, OperatorDto.class);
  }
}
