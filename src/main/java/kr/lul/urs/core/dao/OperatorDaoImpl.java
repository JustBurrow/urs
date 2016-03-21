/**
 *
 */
package kr.lul.urs.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.repository.OperatorRepository;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Service
class OperatorDaoImpl implements OperatorDao {
  @Autowired
  private OperatorRepository operatorRepository;
}
