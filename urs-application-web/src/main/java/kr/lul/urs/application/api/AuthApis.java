/**
 *
 */
package kr.lul.urs.application.api;

import static kr.lul.urs.spring.mvc.ApiParam.Location.BODY;
import static kr.lul.urs.util.Asserts.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

import kr.lul.urs.application.api.AuthApiConstants.C;
import kr.lul.urs.application.api.AuthApiConstants.M;
import kr.lul.urs.spring.mvc.ApiParam;
import kr.lul.urs.spring.mvc.BasicApiParam;
import kr.lul.urs.spring.mvc.BasicWebApi;
import kr.lul.urs.spring.mvc.WebApi;

/**
 * @since 2016. 6. 29.
 * @author Just Burrow just.burrow@lul.kr
 */
public enum AuthApis implements WebApi {
  /**
   * 로그인 페이지.
   */
  LOGIN_FORM(new BasicWebApi(GET, C.PREFIX, C.LOGIN_FORM)),
  /**
   * 로그인 요청 처리.
   */
  LOGIN(new BasicWebApi(POST, C.PREFIX, C.LOGIN,
      new BasicApiParam<>(BODY, false, M.EMAIL, String.class),
      new BasicApiParam<>(BODY, false, M.PASSWORD, String.class)));

  private WebApi api;

  private AuthApis(WebApi api) {
    notNull(api, "api");
    this.api = api;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>WebApi
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getMethod()
   * @since 2016. 6. 29.
   */
  @Override
  public RequestMethod getMethod() {
    return this.api.getMethod();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getUriTemplate()
   * @since 2016. 6. 29.
   */
  @Override
  public String getUriTemplate() {
    return this.api.getUriTemplate();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getParams()
   * @since 2016. 6. 29.
   */
  @Override
  public List<ApiParam<?>> getParams() {
    return this.api.getParams();
  }
}
