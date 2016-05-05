/**
 *
 */
package kr.lul.urs.core.dao;

import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.notNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.domain.mapping.ClientPlatformMapping;
import kr.lul.urs.core.repository.ClientPlatformRepository;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@Service
class ClientPlatformDaoImpl extends AbstractDao implements ClientPlatformDao {
  @Autowired
  private ClientPlatformRepository clientPlatformRepository;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ClientPlatformDao
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public ClientPlatform insert(ClientPlatform clientPlatform) {
    notNull(clientPlatform);
    assignable(clientPlatform, ClientPlatformEntity.class);

    if (this.saveAndFlush) {
      clientPlatform = this.clientPlatformRepository.saveAndFlush((ClientPlatformEntity) clientPlatform);
    } else {
      clientPlatform = this.clientPlatformRepository.save((ClientPlatformEntity) clientPlatform);
    }

    return clientPlatform;
  }

  @Override
  public ClientPlatform select(int id) {
    if (0 >= id) {
      return null;
    }
    return this.clientPlatformRepository.findOne(id);
  }

  @Override
  public List<ClientPlatform> list() {
    List<ClientPlatformEntity> list = this.clientPlatformRepository
        .findAll(new Sort(Direction.ASC, ClientPlatformMapping.Entity.ID));
    return new ArrayList<>(list);
  }
}
