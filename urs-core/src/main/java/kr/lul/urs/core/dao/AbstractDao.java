/**
 *
 */
package kr.lul.urs.core.dao;

import org.springframework.beans.factory.annotation.Value;

import kr.lul.urs.core.configuration.InjectionConstants.Properties;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
abstract class AbstractDao {
  @Value("${" + Properties.KEY_DAO_SAVE_AND_FLUSH + "}")
  protected boolean saveAndFlush;
}
