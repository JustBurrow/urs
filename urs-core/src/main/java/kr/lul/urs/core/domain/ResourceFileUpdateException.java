/**
 *
 */
package kr.lul.urs.core.domain;

import java.io.File;

/**
 * {@link ResourceFile}을 업데이트해서 {@link ResourceFileRevision}을 생성하는 도중에 에러가 발생했을 때.
 *
 * @since 2016. 5. 25.
 * @author Just Burrow just.burrow@lul.kr
 */
public class ResourceFileUpdateException extends Exception {
  private static final long serialVersionUID = -8813173397731009713L;

  /**
   * @param id
   *          {@link ResourceFile}의 ID.
   * @param revision
   *          업데이트를 시도할 시점의 리비전.
   * @param file
   *          업데이트에 사용한 파일.
   * @param cause
   *          원인.
   */
  public ResourceFileUpdateException(int id, int revision, File file, Throwable cause) {
    super(cause);
  }
}
