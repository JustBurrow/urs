/**
 *
 */
package kr.lul.urs.core.domain;

import java.util.List;

import kr.lul.urs.spring.jpa.timestamp.Updatable;

/**
 * 클라이언트가 다운로드하는 파일.
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
  public ResourceFileRevision getLastRevision();

  /**
   * @return 최신 리비전. 1 부터 시작.
   */
  public int getCurrentRevision();

  /**
   * 오름차순으로 정렬한 전체 히스토리.
   *
   * @return 전체 히스토리.
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
