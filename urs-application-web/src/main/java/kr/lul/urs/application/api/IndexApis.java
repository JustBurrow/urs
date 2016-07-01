/**
 *
 */
package kr.lul.urs.application.api;

import static kr.lul.urs.util.Asserts.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

import kr.lul.urs.application.api.IndexApiConstants.C;
import kr.lul.urs.spring.mvc.ApiParam;
import kr.lul.urs.spring.mvc.BasicWebApi;
import kr.lul.urs.spring.mvc.WebApi;

/**
 * @since 2016. 6. 30.
 * @author Just Burrow just.burrow@lul.kr
 */
public enum IndexApis implements WebApi {
  ROOT(new BasicWebApi(GET, C.PREFIX, C.ROOT)),
  INDEX(new BasicWebApi(GET, C.PREFIX, C.INDEX));

  private WebApi api;

  private IndexApis(WebApi api) {
    notNull(api, "api");
    this.api = api;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>WebApi
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getMethod()
   * @since 2016. 6. 30.
   */
  @Override
  public RequestMethod getMethod() {
    return this.api.getMethod();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getUriTemplate()
   * @since 2016. 6. 30.
   */
  @Override
  public String getUriTemplate() {
    return this.api.getUriTemplate();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getParams()
   * @since 2016. 6. 30.
   */
  @Override
  public List<ApiParam<?>> getParams() {
    return this.api.getParams();
  }
}
