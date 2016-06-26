/**
 *
 */
package kr.lul.urs.core.domain;

/**
 * 존재하지 않는 ID로 도메인 오브젝트를 읽으려 했을 때.
 *
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public class NotExistsException extends RuntimeException {
  private static final long serialVersionUID = DomainConstants.SERIAL_VERSION_UID;

  private Class<?>          type;
  private Object            id;

  public NotExistsException(Object id) {
    this.id = id;
  }

  public NotExistsException(String message, Object id) {
    super(message);
    this.id = id;
  }

  public NotExistsException(String message, Class<?> type, Object id) {
    super(message);
    this.type = type;
    this.id = id;
  }

  /**
   * @return the type
   */
  public Class<?> getType() {
    return this.type;
  }

  /**
   * @return the id
   */
  public Object getId() {
    return this.id;
  }
}
