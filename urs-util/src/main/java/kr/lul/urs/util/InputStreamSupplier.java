/**
 *
 */
package kr.lul.urs.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 20.
 */
public interface InputStreamSupplier<S extends InputStream> {
  /**
   * 입력 스트림을 열고, 입력 스트림 인스턴스를 반환한다.
   *
   * @return 새로 연 입력 스트림 인스턴스.
   * @throws IllegalStateException
   *           이미 열린 입력 스트림이 있을 때.
   * @throws IOException
   *           입력 스트림을 여는 도중의 에러.
   */
  public S open() throws IllegalStateException, IOException;

  /**
   * 현재 열려있는 스트림을 반환한다.
   * <p class="caution">
   * 관리중인 입력 스트림에서 데이터를 읽는 코드가 2개 이상일 경우, <b>특히 사용에 주의할 것</b>.
   * </p>
   *
   * @return 현재 열려있는 스트림. not null.
   * @throws IllegalStateException
   *           입력 스트림이 열려있지 않을 때.
   */
  public S get() throws IllegalStateException;

  /**
   * 관리중인 입력 스트림을 닫는다.
   *
   * @throws IllegalStateException
   *           열려있는 스트림이 없을 때.
   * @throws IOException
   *           관리중인 입력 스트림을 닫는 도중의 에러.
   */
  public void close() throws IllegalStateException, IOException;
}
