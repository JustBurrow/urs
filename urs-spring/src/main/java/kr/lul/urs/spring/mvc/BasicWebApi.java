/**
 *
 */
package kr.lul.urs.spring.mvc;

import static kr.lul.urs.util.Asserts.notNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public class BasicWebApi implements WebApi {
  private RequestMethod     method;
  private String            uriTemplate;
  private List<ApiParam<?>> params;

  /**
   * @param method
   * @param uriTemplate
   * @param params
   */
  public BasicWebApi(RequestMethod method, String uriTemplate, ApiParam<?>... params) {
    notNull(method, "method");
    notNull(uriTemplate, "uriTemplate");

    this.method = method;
    this.uriTemplate = uriTemplate;
    this.params = Collections.unmodifiableList(Arrays.asList(params));
  }

  /**
   * @param method
   * @param uriTemplatePrefix
   * @param uriTemplate
   * @param params
   */
  public BasicWebApi(RequestMethod method, String uriTemplatePrefix, String uriTemplate, ApiParam<?>... params) {
    this(method, uriTemplatePrefix + uriTemplate, params);
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>WebApi
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getMethod()
   * @since 2016. 6. 26.
   */
  @Override
  public RequestMethod getMethod() {
    return this.method;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getUriTemplate()
   * @since 2016. 6. 26.
   */
  @Override
  public String getUriTemplate() {
    return this.uriTemplate;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getParams()
   * @since 2016. 6. 26.
   */
  @Override
  public List<ApiParam<?>> getParams() {
    return this.params;
  }

}
