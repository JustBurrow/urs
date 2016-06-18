package kr.lul.urs.core.dao;

import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.notNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
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
  public boolean isExists(AgentPlatform platform, String name) {
    notNull(platform);
    assignable(platform, AgentPlatformEntity.class);
    notNull(name);

    return this.resourceFileRepository.exists(platform, name);
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
