/**
 *
 */
package kr.lul.urs.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * 어떤 입력 스트림을 열고 닫을 때 필요한 정보를 스트림과 함께 묶어서 다룰 때 사용한다.
 * 구현 클래스는 스트림을 열 때 필요한 정보를 가지고 있어야 하며,
 * 그 정보를 사용해 관리중인 입력 스트림을 닫은 후에 새로운 입력 스트림을 다시 열 수 있어야 한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 18.
 */
public abstract class AbstractInputStreamSupplier<S extends InputStream> implements InputStreamSupplier<S> {
  private S stream;

  /**
   * 입력 스트림의 종류마다 다른 인스턴스 생성 로직을 이 메서드의 오버라이딩으로 구현한다.
   *
   * @return 관리할 입력 스트림 인스턴스.
   * @throws IOException
   */
  protected abstract S doOpen() throws IOException;

  /**
   * 관리중인 입력 스트림을 닫을 때, 별도의 처리가 필요할 경우 이 메서드를 오버라이딩한다.
   * <code>false</code>를 반환한 경우,
   * {@link #close()}는 관리중인 입력 스트림의 {@link InputStream#close()} 메서드를 호출해야 하는 것으로 판단한다.
   *
   * @param stream
   *          관리중인 입력 스트림.
   * @return 스트림 닫기를 완료한 경우엔 <code>true</code>.
   * @throws IOException
   */
  protected boolean doClose(S stream) throws IOException {
    return false;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>InputStreamSupplier<S>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * @see {@link #doOpen()} 관리할 입력 스트림을 여는 로직의 확장점.
   */
  @Override
  public S open() throws IllegalStateException, IOException {
    if (null != this.stream) {
      throw new IllegalStateException(String.format("already opened stream exists : %s", this.stream));
    }

    this.stream = this.doOpen();
    return this.stream;
  }

  @Override
  public S get() throws IllegalStateException {
    if (null == this.stream) {
      throw new IllegalStateException("stream did not open yet.");
    }

    return this.stream;
  }

  /**
   * @see {@link #doClose(InputStream)} 관리중인 입력 스트림을 닫는 로직의 확장점.
   */
  @Override
  public void close() throws IllegalStateException, IOException {
    if (null == this.stream) {
      throw new IllegalStateException("stream did not open yet.");
    }

    if (!this.doClose(this.stream)) {
      this.stream.close();
    }

    this.stream = null;
  }
}
