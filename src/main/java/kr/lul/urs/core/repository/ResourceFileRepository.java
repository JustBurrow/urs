/**
 *
 */
package kr.lul.urs.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@Repository
public interface ResourceFileRepository extends JpaRepository<ResourceFileEntity, Integer> {
  /**
   * @param clientPlatform
   * @param name
   * @return
   */
  public long countByClientPlatformAndName(ClientPlatform clientPlatform, String name);

  /**
   * @param clientPlatform
   * @param name
   * @return
   */
  public default boolean exists(ClientPlatform clientPlatform, String name) {
    return 0L < countByClientPlatformAndName(clientPlatform, name);
  }
}
