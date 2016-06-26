/**
 *
 */
package kr.lul.urs.spring.jpa.util;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class SortUtils {
  /**
   * @param properties
   * @return
   * @since 2016. 6. 26.
   */
  public static Sort asc(String... properties) {
    return new Sort(Direction.ASC, properties);
  }

  /**
   * @param properties
   * @return
   * @since 2016. 6. 26.
   */
  public static Sort asc(List<String> properties) {
    return new Sort(Direction.ASC, properties);
  }

  /**
   * @param properties
   * @return
   * @since 2016. 6. 26.
   */
  public static Sort desc(String... properties) {
    return new Sort(Direction.DESC, properties);
  }

  /**
   * @param properties
   * @return
   * @since 2016. 6. 26.
   */
  public static Sort desc(List<String> properties) {
    return new Sort(Direction.DESC, properties);
  }

  protected SortUtils() {
    throw new UnsupportedOperationException();
  }
}
