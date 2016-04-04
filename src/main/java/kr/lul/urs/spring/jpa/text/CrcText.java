/**
 *
 */
package kr.lul.urs.spring.jpa.text;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.zip.CRC32;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 완전 일치하는 문자열을 빠르게 찾기 위한 유틸리티 클래스.
 * 정렬은 지원하지 않는다.
 * {@link CrcText} 인스턴스가 있다면, 원문 텍스트는 <code>null</code>일 수 없다. 따라서 <code>null</code> 문자열은 지원하지 않는다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 4.
 */
@Embeddable
public class CrcText implements Serializable {
  private static final long  serialVersionUID = -7326109115323167959L;

  public static final String ATTR_CRC         = "crc";
  public static final String ATTR_TEXT        = "text";

  @Column(nullable = false)
  private long               crc;
  @Column(nullable = false)
  private String             text;

  public CrcText(String text) {
    if (null == text) {
      throw new IllegalArgumentException("text is null.");
    }
    try {
      CRC32 crc32 = new CRC32();
      crc32.update(text.getBytes("UTF-8"));
      this.crc = crc32.getValue();
    } catch (UnsupportedEncodingException e) {
      // ignore
    }
    this.text = text;
  }

  public long getCrc() {
    return this.crc;
  }

  public String getText() {
    return this.text;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Object
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public int hashCode() {
    return this.text.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (null != obj && obj instanceof CrcText) {
      CrcText that = (CrcText) obj;
      return this.crc == that.crc && this.text.equals(that.text);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return this.text;
  }
}
