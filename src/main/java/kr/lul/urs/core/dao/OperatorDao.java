/**
 *
 */
package kr.lul.urs.core.dao;

import kr.lul.urs.core.domain.Operator;

/**
 * 프로덕트 운영자 DAO.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
public interface OperatorDao {
  /**
   * @param operator
   * @return
   */
  public Operator insert(Operator operator);
}
