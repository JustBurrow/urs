/**
 *
 */
package kr.lul.urs.core.dao;

import static kr.lul.urs.util.Asserts.assignable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.OperatorEntity;
import kr.lul.urs.core.repository.OperatorRepository;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Service
class OperatorDaoImpl implements OperatorDao {
  @Autowired
  private OperatorRepository operatorRepository;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>OperatorDao
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Operator insert(Operator operator) {
    assignable(operator, OperatorEntity.class);

    return this.operatorRepository.save((OperatorEntity) operator);
  }
}
