/**
 *
 */
package kr.lul.urs.application.web.controller;

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

import kr.lul.urs.application.api.AgentPlatformApiConfiguration;
import kr.lul.urs.application.api.AuthApiConfiguration;
import kr.lul.urs.application.web.request.CreateAgentPlatformReq;
import kr.lul.urs.application.web.view.AgentPlatformView;
import kr.lul.urs.core.ClientPlatformApiUtils;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.ClientPlatformService;
import kr.lul.urs.util.Randoms;

/**
 * @since 2016. 6. 6.
 * @author Just Burrow just.burrow@lul.kr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class AgentPlatformControllerTest extends AbstractMvcTest {
  @Autowired
  private ClientPlatformService agentPlatformService;

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
    String urlPattern = fromUriString("**/" + AuthApiConfiguration.PREFIX + AuthApiConfiguration.LOGIN_SPEC).build()
        .toUriString();
    this.mock.perform(get(AgentPlatformApiConfiguration.PREFIX + AgentPlatformApiConfiguration.CREATE_FORM))
        // .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrlPattern(urlPattern));
  }

  @Test
  public void testReadCreateForm() throws Exception {
    // Given
    this.setOperatorAsRandom();

    // When
    this.mock.perform(get(AgentPlatformApiConfiguration.PREFIX + AgentPlatformApiConfiguration.CREATE_FORM)
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
    CreateAgentPlatformReq req = ClientPlatformApiUtils.createReq();

    // When
    MockHttpServletResponse response = this.mock
        .perform(post(AgentPlatformApiConfiguration.PREFIX + AgentPlatformApiConfiguration.CREATE)
            .with(user(this.getDetails(this.operator)))
            .with(csrf())
            .param("code", req.getCode())
            .param("label", req.getLabel())
            .param("description", req.getDescription()))
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
    List<ClientPlatformDto> platforms = IntStream.range(1, Randoms.in(3, 10)).mapToObj(i -> {
      return ClientPlatformApiUtils.create(this.operator, this.agentPlatformService);
    }).collect(Collectors.toList());

    // When
    this.mock.perform(get(AgentPlatformApiConfiguration.PREFIX + AgentPlatformApiConfiguration.LIST)
        .with(user(this.getDetails(this.operator)))
        .with(csrf()))
        // .andDo(print())

        // Then
        .andExpect(status().isOk())
        .andExpect(view().name(AgentPlatformView.TPL_LIST))
        .andExpect(model().attribute("platforms", IsIterableContainingInOrder
            .<ClientPlatformDto> contains(platforms.toArray(new ClientPlatformDto[platforms.size()]))));
  }

  @Test
  public void testUpdateForm() throws Exception {
    // Given
    this.setOperatorAsRandom();
    final ClientPlatformDto platform = ClientPlatformApiUtils.create(this.operator, this.agentPlatformService);

    // When
    this.mock.perform(get(AgentPlatformApiConfiguration.PREFIX + "/" + platform.getId() + "/update")
        .with(user(this.getDetails(this.operator)))
        .with(csrf()))
        // Then
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attribute("platform", platform))
        .andExpect(view().name(AgentPlatformView.TPL_UPDATE));
  }

  @Test
  public void testUpdate() throws Exception {
    // Given
    this.setOperatorAsRandom();
    ClientPlatformDto platform = ClientPlatformApiUtils.create(this.operator, this.agentPlatformService);

    String label;
    String description;
    do {
      label = "test " + RandomStringUtils.randomAlphanumeric(Randoms.in(2, 5));
      description = "test description : " + RandomStringUtils.randomAlphanumeric(Randoms.in(5, 10));
    } while (platform.getLabel().equals(label) && platform.getDescription().equals(description));

    // When
    this.mock.perform(patch(AgentPlatformApiConfiguration.PREFIX + "/" + platform.getId())
        .with(user(this.getDetails(this.operator)))
        .with(csrf())
        .param("label", label)
        .param("description", description))
        // Then
        .andDo(print())
        .andExpect(status().is3xxRedirection());
  }
}
