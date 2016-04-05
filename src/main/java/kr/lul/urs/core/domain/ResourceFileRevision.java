/**
 *
 */
package kr.lul.urs.core.domain;

import java.io.File;
import java.io.Serializable;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 4.
 */
public interface ResourceFileRevision {
  public static interface Identifier extends Serializable {
    /**
     * @return 리소스 파일 ID.
     */
    public int resourceFile();

    /**
     * @return 리비전.
     */
    public int revision();
  }

  /**
   * @return ID.
   */
  public Identifier getId();

  /**
   * 리소스 파일을 소유한 프로덕트 관리자.
   *
   * @return 소유자.
   */
  public Operator getOwner();

  /**
   * 리소스 파일을 사용하는 클라이언트의 플랫폼.
   *
   * @return 대상 클라이언트 플랫폼.
   */
  public ClientPlatform getClientPlatform();

  /**
   * @return TODO
   */
  public ResourceFile getResourceFile();

  /**
   * @return 리소스 파일의 이름.
   */
  public String getName();

  /**
   * @return 리비전.
   */
  public int getRevision();

  /**
   * 파일을 검증할 수 있는 SHA1 해시를 반환한다.
   *
   * @return 파일 바이너리의 SHA1 해시.
   */
  public String getSha1();

  /**
   * @return
   */
  public File getFile();
}
