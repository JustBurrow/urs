/**
 *
 */
package kr.lul.urs.spring.jpa.ownership;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public interface OwnableReposigory<T, ID extends Serializable, O extends Owner> extends JpaRepository<T, ID> {
  /**
   * @param owner
   * @return
   * @since 2016. 6. 26.
   */
  public List<T> findAllByOwner(O owner);

  /**
   * @param owner
   * @param sort
   * @return
   * @since 2016. 6. 26.
   */
  public List<T> findAllByOwner(O owner, Sort sort);

  /**
   * @param owner
   * @param page
   * @return
   * @since 2016. 6. 26.
   */
  public Page<T> findAllByOwner(O owner, Pageable page);
}
