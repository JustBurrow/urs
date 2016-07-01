/**
 *
 */
package kr.lul.urs.spring.util;

import static java.lang.String.format;
import static kr.lul.urs.spring.util.UriTemplateUtils.build;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import kr.lul.urs.util.MapBuilder;
import kr.lul.urs.util.Randoms;

/**
 * @since 2016. 6. 29.
 * @author Just Burrow just.burrow@lul.kr
 */
public class UriTemplateUtilsTest {
  @Test
  public void testBuildWithNoParam() throws Exception {
    // Given
    String uriTemplate = "/abc/def";

    // When
    String uri = build(uriTemplate, new HashMap<>());

    // Then
    assertThat(uri).isEqualTo(uriTemplate);
  }

  @Test
  public void testBuildWithNamedParam() throws Exception {
    // Given
    String pre = "/abc/def/efg";
    String post = "/xyz";
    String name = "param";
    String val = RandomStringUtils.randomAlphanumeric(Randoms.in(5, 10));
    String uriTemplate = format("%s/{%s}/%s", pre, name, post);
    Map<String, Object> params = MapBuilder.<String, Object> hash(name, val).build();

    // When
    String uri = build(uriTemplate, params);

    // Then
    assertThat(uri).startsWith(pre)
        .endsWith(post)
        .doesNotStartWith(val)
        .doesNotEndWith(val)
        .contains(val);
  }

  @Test
  public void testBuildWithPositiveDigitPatternTemplate() throws Exception {
    // Given
    String pre = "/abc/def/efg";
    String post = "xyz";
    String name = "param";
    String uriTemplate = format("%s/{%s:[1-9]\\d*}/%s", pre, name, post);
    int value = Randoms.positive();
    String val = Integer.toString(value);

    // When
    String uri = build(uriTemplate, MapBuilder.<String, Object> hash(name, value).build());

    // Then
    assertThat(uri).startsWith(pre)
        .endsWith(post)
        .doesNotStartWith(val)
        .doesNotEndWith(val)
        .contains(val);
  }

  @Test
  public void testBuildWithSequenceSetPatternTemplate() throws Exception {
    // Given
    String name = "param";
    String regex = "(aaa)|(bbb)|(ccc)";
    String value = Arrays.asList("aaa", "bbb", "ccc").get(Randoms.in(0, 3));
    String uriTemplate = format("/abc/def/{%s:%s}/zzz", name, regex);

    // When
    String uri = build(uriTemplate, MapBuilder.<String, Object> hash(name, value).build());

    // Then
    assertThat(uri).isNotEqualTo(uriTemplate)
        .contains(value)
        .doesNotStartWith(value)
        .doesNotEndWith(value);
  }

  @Test
  public void testBuildWithMultiParamsUriTemplate() throws Exception {
    // Given
    StringBuilder uriTemplate = new StringBuilder();
    Map<String, Object> params = IntStream.range(1, Randoms.in(3, 5)).mapToObj(i -> "param" + i)
        .collect(Collectors.toMap(p -> p, p -> {
          String val = RandomStringUtils.randomAlphabetic(Randoms.in(3, 10));
          uriTemplate.append('/').append(RandomStringUtils.randomAlphabetic(3)).append('{').append(p).append(":\\D+}");
          return val;
        }));
    String param = "param" + (1 + params.size());
    int value = Randoms.positive();
    uriTemplate.append("/{").append(param).append(":\\d+}/")
        .append(RandomStringUtils.randomAlphabetic(Randoms.in(1, 5)));
    String val = Integer.toString(value);
    params.put(param, val);

    // When
    String uri = build(uriTemplate.toString(), params);

    // Then
    assertThat(uri).isNotEqualTo(uriTemplate.toString())
        .doesNotContain("param")
        .contains(params.values().stream().map(v -> v.toString()).collect(Collectors.toList()));
  }
}
