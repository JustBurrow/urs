/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_DAO_SAVE_AND_FLUSH;
import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.domain.entity.OperatorEntity;
import kr.lul.urs.core.repository.OperatorRepository;
import kr.lul.urs.core.service.context.CreateOperatorCtx;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Service
class OperatorInternalServiceImpl implements OperatorInternalService {
  @Value("${" + KEY_DAO_SAVE_AND_FLUSH + "}")
  private boolean            saveAndFlush;

  @Autowired
  private PasswordEncoder    passwordEncoder;
  @Autowired
  private OperatorRepository operatorRepository;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>OperatorInternalService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public OperatorEntity create(CreateOperatorCtx ctx) {
    notNull(ctx);

    OperatorEntity operator = new OperatorEntity(ctx.getEmail(), this.passwordEncoder.encode(ctx.getPassword()));
    operator.setNonExpired(true);
    operator.setNonLocked(true);
    operator.setCredentialsNonExpired(true);
    operator.setEnabled(true);

    if (this.saveAndFlush) {
      operator = this.operatorRepository.saveAndFlush(operator);
    } else {
      operator = this.operatorRepository.save(operator);
    }

    return operator;
  }

  @Override
  public OperatorEntity read(int id) {
    if (0 >= id) {
      // TODO throw
    }
    OperatorEntity operator = this.operatorRepository.findOne(id);
    return operator;
  }
}
