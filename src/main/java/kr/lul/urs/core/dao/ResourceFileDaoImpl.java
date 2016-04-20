package kr.lul.urs.core.dao;

import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.repository.ResourceFileRepository;

@Service
class ResourceFileDaoImpl implements ResourceFileDao {
  @Autowired
  private ResourceFileRepository resourceFileRepository;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFileDao
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public ResourceFile insert(ResourceFile resourceFile) {
    notNull(resourceFile);
    assignable(resourceFile, ResourceFileEntity.class);

    resourceFile = this.resourceFileRepository.save((ResourceFileEntity) resourceFile);
    return resourceFile;
  }

  @Override
  public boolean isExists(ClientPlatform clientPlatform, String name) {
    notNull(clientPlatform);
    assignable(clientPlatform, ClientPlatformEntity.class);
    notNull(name);

    return this.resourceFileRepository.exists(clientPlatform, name);
  }
}
