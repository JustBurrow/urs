/**
 *
 */
package kr.lul.urs.core.dao;

import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.hasLength;

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
class OperatorDaoImpl extends AbstractDao implements OperatorDao {
  @Autowired
  private OperatorRepository operatorRepository;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>OperatorDao
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Operator insert(Operator operator) {
    assignable(operator, OperatorEntity.class);

    if (this.saveAndFlush) {
      operator = this.operatorRepository.saveAndFlush((OperatorEntity) operator);
    } else {
      operator = this.operatorRepository.save((OperatorEntity) operator);
    }

    return operator;
  }

  @Override
  public OperatorEntity selectByEmail(String email) {
    hasLength(email);

    return this.operatorRepository.findByEmail(email);
  }

  @Override
  public OperatorEntity select(int id) {
    if (0 >= id) {
      return null;
    }

    OperatorEntity operator = this.operatorRepository.findOne(id);
    return operator;
  }
}
