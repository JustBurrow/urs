/**
 *
 */
package kr.lul.urs.spring.util;

import static java.lang.String.format;
import static kr.lul.urs.util.Asserts.notNull;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {@link RequestMapping}에 사용하는 URI 템플릿 관련 유틸리티.
 *
 * @since 2016. 6. 29.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class UriTemplateUtils {
  /**
   * URI 템플릿에서 사용하는 변수의 정규표현식.
   */
  public static final String  URI_TEMPLATE_VARIABLE_REGEX   = "\\{([^:}]+)(:([^:}]+))?\\}";

  /**
   * URI 템플릿 변수의 패턴.
   */
  public static final Pattern URI_TEMPLATE_VARIABLE_PATTERN = Pattern.compile(URI_TEMPLATE_VARIABLE_REGEX);

  /**
   * URI 템플릿에 변수를 바인딩해 실재 URI를 만든다.
   *
   * @param uriTemplate
   * @param params
   * @return
   * @since 2016. 6. 29.
   */
  public static String build(String uriTemplate, Map<String, Object> params) {
    notNull(uriTemplate, "uriTemplate");
    notNull(params, "params");

    StringTokenizer st = new StringTokenizer(uriTemplate, "/", true);
    StringBuilder uri = new StringBuilder();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if ("/".equals(token)) {
        uri.append('/');
        continue;
      }

      Matcher matcher = URI_TEMPLATE_VARIABLE_PATTERN.matcher(token);
      if (!matcher.find()) {
        uri.append(token);
        continue;
      }

      matcher.reset();
      int lastMatch = 0;
      while (matcher.find()) {
        String[] temp = new String[matcher.groupCount()];
        for (int i = 0; i < temp.length; i++) {
          temp[i] = matcher.group(i);
        }

        String key = matcher.group(1);
        if (!params.containsKey(key)) {
          throw new IllegalArgumentException(String.format("param map has no value for variable [%s].", key));
        }
        String value = params.get(key).toString();
        String regex = matcher.group(3);
        if (null != regex && !value.matches(regex)) {
          throw new IllegalArgumentException(
              format("no parameter : pattern=%s, uriTemplate=%s, params=%s", regex, uriTemplate, params));
        }
        uri.append(token.substring(lastMatch, matcher.start())).append(value);
        lastMatch = matcher.end();
      }
      uri.append(token.substring(lastMatch, token.length()));
    }

    return uri.toString();
  }

  protected UriTemplateUtils() {
    throw new UnsupportedOperationException();
  }
}
