/**
 *
 */
package kr.lul.urs.util;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 출력 스트림을 만든다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 11.
 * @param <O>
 *          출력 스트림.
 * @param <ID>
 *          출력 스트림을 결정할 수 있는 ID.
 */
public interface OutputStreamFactory<O extends OutputStream, ID> {
  /**
   * 키를 사용해 인식 가능한 출력 스트림을 만든다.
   * 이 팩토리에서는 스트림 인스턴스를 만들기만 하며, 이후의 관리는 호출한 쪽에서 책임진다.
   *
   * @param key
   * @return
   * @throws IOException
   * @since 2016. 5. 11.
   */
  public O getOutputStream(ID key) throws IOException;
}
