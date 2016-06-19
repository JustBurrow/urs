/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.positive;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.application.web.security.OperatorDetails;
import kr.lul.urs.application.web.security.OperatorDetailsService;
import kr.lul.urs.core.dto.OperatorDto;

/**
 * 웹 MVC 애플리케이션의 테스트용 공통 로직.
 *
 * @since 2016. 6. 6.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AbstractMvcTest extends AbstractTest {
  @Autowired
  protected WebApplicationContext ctx;
  @Autowired
  private OperatorDetailsService  operatorDetailsService;

  protected MockMvc               mock;

  /**
   * MVC 목업 생성.
   *
   * @since 2016. 6. 6.
   */
  protected void setUpMockMvc() {
    this.mock = MockMvcBuilders.webAppContextSetup(this.ctx).apply(springSecurity()).build();
  }

  protected OperatorDetails getDetails(OperatorDto operator) {
    notNull(operator, "operator");
    positive(operator.getId(), "operator.id");

    OperatorDetails details = (OperatorDetails) this.operatorDetailsService.loadUserByUsername(operator.getEmail());
    return details;
  }
}
