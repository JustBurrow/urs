/**
 *
 */
package kr.lul.urs.application.api;

import static kr.lul.urs.spring.mvc.ApiParam.Location.BODY;
import static kr.lul.urs.spring.mvc.ApiParam.Location.PATH;
import static kr.lul.urs.util.Asserts.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

import kr.lul.urs.application.api.AgentPlatformApiConstants.C;
import kr.lul.urs.application.api.AgentPlatformApiConstants.M;
import kr.lul.urs.application.web.request.CreateAgentPlatformReq;
import kr.lul.urs.application.web.request.UpdatAgentPlatformReq;
import kr.lul.urs.spring.mvc.ApiParam;
import kr.lul.urs.spring.mvc.BasicApiParam;
import kr.lul.urs.spring.mvc.BasicWebApi;
import kr.lul.urs.spring.mvc.WebApi;

/**
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public enum AgentPlatformApis implements WebApi {
  CREATE_FORM(new BasicWebApi(GET, C.PREFIX, C.CREATE_FORM)),
  CREATE(new BasicWebApi(POST, C.PREFIX, C.CREATE,
      new BasicApiParam<>(BODY, false, M.CREATE_REQ, CreateAgentPlatformReq.class))),
  READ(new BasicWebApi(GET, C.PREFIX, C.READ,
      new BasicApiParam<>(PATH, true, M.ID, Integer.TYPE))),
  LIST(new BasicWebApi(GET, C.PREFIX, C.LIST)),
  UPDATE_FORM(new BasicWebApi(GET, C.PREFIX, C.UPDATE_FORM,
      new BasicApiParam<>(PATH, true, M.ID, Integer.TYPE),
      new BasicApiParam<>(PATH, true, M.ID, Integer.TYPE))),
  UPDATE(new BasicWebApi(PATCH, C.PREFIX, C.UPDATE,
      new BasicApiParam<>(PATH, true, M.ID, Integer.TYPE),
      new BasicApiParam<>(BODY, false, M.UPDATE_REQ, UpdatAgentPlatformReq.class)));

  private WebApi api;

  private AgentPlatformApis(WebApi api) {
    notNull(api, "api");
    this.api = api;
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
    return this.api.getMethod();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getUriTemplate()
   * @since 2016. 6. 26.
   */
  @Override
  public String getUriTemplate() {
    return this.api.getUriTemplate();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.WebApi#getParams()
   * @since 2016. 6. 26.
   */
  @Override
  public List<ApiParam<?>> getParams() {
    return this.api.getParams();
  }
}
