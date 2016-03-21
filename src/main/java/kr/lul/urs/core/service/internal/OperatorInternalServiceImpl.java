/**
 *
 */
package kr.lul.urs.core.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.dao.OperatorDao;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Service
class OperatorInternalServiceImpl implements OperatorInternalService {
  @Autowired
  private OperatorDao operatorDao;
}
