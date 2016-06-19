/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.command.UpdateResourceFileCmd;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.ResourceFileUpdateException;
import kr.lul.urs.core.dto.ResourceFileDto;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.spring.tx.Return;

/**
 * {@link ResourceFile}을 다루는 서비스.
 *
 * @since 2016. 5. 18.
 * @author Just Burrow just.burrow@lul.kr
 */
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER, propagation = Propagation.REQUIRES_NEW)
public interface ResourceFileService {
  /**
   * 바이너라기 없는 {@link ResourceFile}을 만든다.
   *
   * @param cmd
   * @return
   * @throws OwnershipException
   * @since 2016. 5. 18.
   */
  public Return<ResourceFileDto> create(CreateResourceFileCmd cmd) throws OwnershipException;

  /**
   * @param id
   * @return 없으면 <code>null</code>.
   * @since 2016. 5. 20.
   */
  public Return<ResourceFileDto> read(int id);

  /**
   * 등록되어있는 목록을 ID 순서로 반화한다.
   *
   * @return 없으면 빈 {@link List}.
   * @since 2016. 5. 23.
   */
  public Return<List<ResourceFileDto>> list();

  /**
   * 리소스 파일의 바이너리를 업데이트한다.
   *
   * @param cmd
   * @return
   * @throws OwnershipException
   * @throws ResourceFileUpdateException
   * @since 2016. 5. 24.
   */
  public Return<ResourceFileDto> update(UpdateResourceFileCmd cmd)
      throws OwnershipException, ResourceFileUpdateException;
}
