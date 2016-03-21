/**
 *
 */
package kr.lul.urs.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lul.urs.core.domain.entity.OperatorEntity;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, Integer> {
}
