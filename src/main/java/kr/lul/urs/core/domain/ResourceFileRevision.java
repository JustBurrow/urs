/**
 *
 */
package kr.lul.urs.core.domain;

import java.io.File;
import java.io.Serializable;

import kr.lul.urs.spring.jpa.timestamp.Creatable;

/**
 * 클라이언트가 다운로드할 파일의 바이너리 정보를 관리하는 단위.
 * <p>
 * Unity의 경우, {@link ResourceFile}을 통해 애셋 번들의 URL을 획득하고, 리비전으로 바이너리를 다운로드할지 여부를 결정한다.
 * </p>
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 4.
 */
public interface ResourceFileRevision extends Creatable {
  public static interface Identifier extends Serializable {
    /**
     * 1 이상.
     *
     * @return 리소스 파일 ID.
     */
    public int resourceFile();

    /**
     * 1 이상.
     *
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
   * @return TODO
   */
  public ResourceFile getResourceFile();

  /**
   * 리소스 파일을 사용하는 클라이언트의 플랫폼.
   *
   * @return 대상 클라이언트 플랫폼.
   */
  public ClientPlatform getClientPlatform();

  /**
   * @return 리소스 파일의 이름.
   */
  public String getName();

  /**
   * 리소스 파일의 변경 횟수를 나타내는 리비전 번호. 1부터 시작한다.
   *
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
