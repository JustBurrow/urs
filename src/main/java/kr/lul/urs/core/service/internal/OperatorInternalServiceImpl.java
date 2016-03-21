/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.util.Asserts.hasLength;
import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.dao.OperatorDao;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.OperatorEntity;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Service
class OperatorInternalServiceImpl implements OperatorInternalService {
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private OperatorDao     operatorDao;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>OperatorInternalService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Operator create(CreateOperatorCmd cmd) {
    notNull(cmd);

    OperatorEntity operator = new OperatorEntity(cmd.getEmail(), this.passwordEncoder.encode(cmd.getPassword()));
    operator.setNonExpired(true);
    operator.setNonLocked(true);
    operator.setCredentialsNonExpired(true);
    operator.setEnabled(true);

    operator = (OperatorEntity) this.operatorDao.insert(operator);

    return operator;
  }

  @Override
  public Operator readByEmail(String email) {
    hasLength(email);

    return this.operatorDao.selectByEmail(email);
  }
}
