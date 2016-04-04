/**
 *
 */
package kr.lul.urs.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lul.urs.core.domain.entity.ClientPlatformEntity;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@Repository
public interface ClientPlatformRepository extends JpaRepository<ClientPlatformEntity, Integer> {
}
