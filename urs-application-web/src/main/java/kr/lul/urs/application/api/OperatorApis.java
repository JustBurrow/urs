/**
 *
 */
package kr.lul.urs.application.api;

import static kr.lul.urs.util.Asserts.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

import kr.lul.urs.application.api.OperatorApiConstants.C;
import kr.lul.urs.application.api.OperatorApiConstants.M;
import kr.lul.urs.spring.mvc.ApiParam;
import kr.lul.urs.spring.mvc.ApiParam.Location;
import kr.lul.urs.spring.mvc.BasicApiParam;
import kr.lul.urs.spring.mvc.BasicWebApi;
import kr.lul.urs.spring.mvc.WebApi;

/**
 * @since 2016. 6. 30.
 * @author Just Burrow just.burrow@lul.kr
 */
public enum OperatorApis implements WebApi {
  SIGN_UP_FORM(new BasicWebApi(GET, C.PREFIX, C.SIGN_UP_SPEC)),
  SIGN_UP(new BasicWebApi(POST, C.PREFIX, C.SIGN_UP,
      new BasicApiParam<>(Location.BODY, false, M.EMAIL, String.class),
      new BasicApiParam<>(Location.BODY, false, M.PASSWORD, String.class)));

  private WebApi api;

  private OperatorApis(WebApi api) {
    notNull(api, "api");
    this.api = api;
  }

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
