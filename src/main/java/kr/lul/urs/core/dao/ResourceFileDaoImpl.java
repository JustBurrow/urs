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
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.domain.mapping.ResourceFileMapping.Entity;
import kr.lul.urs.core.repository.ResourceFileRepository;

@Service
class ResourceFileDaoImpl extends AbstractDao implements ResourceFileDao {
  @Autowired
  private ResourceFileRepository resourceFileRepository;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFileDao
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public ResourceFile insert(ResourceFile resourceFile) {
    notNull(resourceFile);
    assignable(resourceFile, ResourceFileEntity.class);

    if (this.saveAndFlush) {
      resourceFile = this.resourceFileRepository.saveAndFlush((ResourceFileEntity) resourceFile);
    } else {
      resourceFile = this.resourceFileRepository.save((ResourceFileEntity) resourceFile);
    }
    return resourceFile;
  }

  @Override
  public ResourceFile select(int id) {
    return this.resourceFileRepository.findOne(id);
  }

  @Override
  public boolean isExists(ClientPlatform clientPlatform, String name) {
    notNull(clientPlatform);
    assignable(clientPlatform, ClientPlatformEntity.class);
    notNull(name);

    return this.resourceFileRepository.exists(clientPlatform, name);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.dao.ResourceFileDao#list()
   * @since 2016. 5. 23.
   */
  @Override
  public List<ResourceFile> list() {

    List<ResourceFileEntity> list = this.resourceFileRepository.findAll(new Sort(Direction.ASC, Entity.ID));

    return new ArrayList<>(list);
  }
}
