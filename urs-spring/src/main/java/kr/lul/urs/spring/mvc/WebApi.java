/**
 *
 */
package kr.lul.urs.spring.mvc;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public interface WebApi {
  /**
   * @return
   * @since 2016. 6. 26.
   */
  public RequestMethod getMethod();

  /**
   * @return
   * @since 2016. 6. 26.
   */
  public String getUriTemplate();

  /**
   * @return
   * @since 2016. 6. 26.
   */
  public List<ApiParam<?>> getParams();
}
