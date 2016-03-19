/**
 *
 */
package kr.lul.urs.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lul.urs.application.configuration.JpaConfiguration;
import kr.lul.urs.core.domain.entity.ParentEntity;

/**
 * {@link JpaConfiguration} 테스트용 레포지토리.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @see JpaConfiguration
 * @see ParentEntity
 */
@Repository
public interface ParentRepository extends JpaRepository<ParentEntity, Integer> {
}
