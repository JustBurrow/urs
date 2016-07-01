/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.application.api.AgentPlatformApis.CREATE;
import static kr.lul.urs.application.api.AgentPlatformApis.CREATE_FORM;
import static kr.lul.urs.application.api.AgentPlatformApis.LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.lul.urs.application.Runner;
import kr.lul.urs.application.api.AgentPlatformApiConstants.C;
import kr.lul.urs.application.api.AgentPlatformApiConstants.M;
import kr.lul.urs.application.web.request.CreateAgentPlatformReq;
import kr.lul.urs.application.web.view.AgentPlatformView;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.service.AgentPlatformService;
import kr.lul.urs.core.test.AgentPlatformDtoUtils;
import kr.lul.urs.util.Randoms;

/**
 * @since 2016. 6. 6.
 * @author Just Burrow just.burrow@lul.kr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { Runner.class })
public class AgentPlatformControllerTest extends AbstractMvcTest {
  @Autowired
  private AgentPlatformService agentPlatformService;

  /**
   * @throws java.lang.Exception
   * @since 2016. 6. 6.
   */
  @Before
  public void setUp() throws Exception {
    this.setUpMockMvc();
  }

  @Test
  public void testReadCreateFormBeforeLogin() throws Exception {
    String urlPattern = fromUriString("**/" + kr.lul.urs.application.api.AuthApiConstants.C.PREFIX
        + kr.lul.urs.application.api.AuthApiConstants.C.LOGIN_FORM).build()
            .toUriString();
    this.mock.perform(get(CREATE_FORM.getUriTemplate()))
        // .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrlPattern(urlPattern));
  }

  @Test
  public void testReadCreateForm() throws Exception {
    // Given
    this.setOperatorAsRandom();

    // When
    this.mock.perform(get(CREATE_FORM.getUriTemplate())
        .with(user(this.getDetails(this.operator))))
        // .andDo(print())
        // Then
        .andExpect(status().isOk())
        .andExpect(view().name(AgentPlatformView.TPL_CREATE));

  }

  @Test
  public void testCreate() throws Exception {
    // Given
    this.setOperatorAsRandom();
    CreateAgentPlatformReq req = AgentPlatformWebUtils.createReq();

    // When
    MockHttpServletResponse response = this.mock
        .perform(post(CREATE.getUriTemplate())
            .with(user(this.getDetails(this.operator)))
            .with(csrf())
            .param(M.CODE, req.getCode())
            .param(M.LABEL, req.getLabel())
            .param(M.DESCRIPTION, req.getDescription()))
        // .andDo(print())

        // Then
        .andExpect(status().is3xxRedirection())
        // .andExpect(view().name(""))
        .andExpect(redirectedUrlPattern("/platform/*"))
        .andReturn().getResponse();
    String location = (String) response.getHeaderValue("Location");
    assertThat(location).matches("/platform/\\d+$");
  }

  @Test
  public void testListAfterCreate() throws Exception {
    // Given
    this.setOperatorAsRandom();
    List<AgentPlatformDto> platforms = IntStream.range(1, Randoms.in(3, 10)).mapToObj(i -> {
      return AgentPlatformDtoUtils.create(this.operator, this.agentPlatformService);
    }).collect(Collectors.toList());

    // When
    this.mock.perform(get(LIST.getUriTemplate())
        .with(user(this.getDetails(this.operator)))
        .with(csrf()))
        .andDo(print())

        // Then
        .andExpect(status().isOk())
        .andExpect(view().name(AgentPlatformView.TPL_LIST))
        .andExpect(model().attribute(M.PLATFORMS, IsIterableContainingInOrder
            .<AgentPlatformDto> contains(platforms.toArray(new AgentPlatformDto[platforms.size()]))));
  }

  @Test
  public void testUpdateForm() throws Exception {
    // Given
    this.setOperatorAsRandom();
    final AgentPlatformDto platform = AgentPlatformDtoUtils.create(this.operator, this.agentPlatformService);

    // When
    this.mock.perform(get(C.PREFIX + "/" + platform.getId() + "/update") // TODO uri builder
        .with(user(this.getDetails(this.operator)))
        .with(csrf()))
        // Then
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attribute(M.PLATFORM, platform))
        .andExpect(view().name(AgentPlatformView.TPL_UPDATE));
  }

  @Test
  public void testUpdate() throws Exception {
    // Given
    this.setOperatorAsRandom();
    AgentPlatformDto platform = AgentPlatformDtoUtils.create(this.operator, this.agentPlatformService);

    String label;
    String description;
    do {
      label = "test " + RandomStringUtils.randomAlphanumeric(Randoms.in(2, 5));
      description = "test description : " + RandomStringUtils.randomAlphanumeric(Randoms.in(5, 10));
    } while (platform.getLabel().equals(label) && platform.getDescription().equals(description));

    // When
    this.mock.perform(patch(C.PREFIX + "/" + platform.getId())// TODO uri builder
        .with(user(this.getDetails(this.operator)))
        .with(csrf())
        .param(M.LABEL, label)
        .param(M.DESCRIPTION, description))
        // Then
        .andDo(print())
        .andExpect(status().is3xxRedirection());
  }
}
