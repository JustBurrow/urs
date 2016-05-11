/**
 *
 */
package kr.lul.urs.core.domain;

import java.io.File;
import java.io.IOException;
import java.util.List;

import kr.lul.urs.spring.jpa.timestamp.Updatable;

/**
 * 클라이언트가 인식하는 리소스(업데이트용) 파일.
 * 실재로 다운로드할 바이너리 정보는 버전 관리 단위인 {@link ResourceFileRevision}을 통해 관리한다.
 * <p>
 * Unity의 경우, 애셋 번들의 URL만 관리하며, 버전정보와 실재 다운로드해야 할 바이너리 정보는 {@link ResourceFileRevision}에서 관리한다.
 * </p>
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 4.
 */
public interface ResourceFile extends Updatable {
  /**
   * @return ID.
   */
  public int getId();

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
   * @return 리소스 파일의 이름.
   */
  public String getName();

  /**
   * 마지막으로 변경된 버전의 리소스 파일을 가져온다.
   *
   * @return 리소스 파일의 마지막 리비전.
   */
  public ResourceFileRevision getCurrentRevision();

  /**
   * 바이너리 데이터를 갱신한다.
   *
   * @param file
   *          최신 바이너리를 가진 파일.
   * @return 갱신한 바이너리의 정보를 가진 리비전.
   * @throws IOException
   *           리비전 업데이트 중 에러.
   */
  public ResourceFileRevision update(File file) throws IOException;

  /**
   * @return 최신 리비전. 1 부터 시작.
   */
  public int getCurrentRevisionNumber();

  /**
   * 오름차순으로 정렬한 전체 히스토리.
   *
   * @return 전체 히스토리. 수정 불가.
   */
  public List<ResourceFileRevision> getHistory();

  /**
   * 지정한 리비전부터 현재 리비전까지의 오름차순으로 정렬한 히스토리를 반환한다.
   *
   * @param from
   *          시작 리비전.
   * @return 오름차순으로 정렬한 히스토리.
   */
  public List<ResourceFileRevision> getHistory(int from);

  /**
   * 지정한 리비전부터 현재 리비전까지의 오름차순으로 정렬한 히스토리를 반환한다.
   *
   * @param from
   *          시작 리비전.
   * @param limit
   *          히스토리 최대 갯수.
   * @return 오름차순으로 정렬한 히스토리.
   */
  public List<ResourceFileRevision> getHistory(int from, int limit);
}
