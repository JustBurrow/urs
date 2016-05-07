package kr.lul.urs.core.service;

import static kr.lul.urs.util.Asserts.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.converter.ClientPlatformReturnFactory;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.spring.tx.Return;

@Service
class ClientPlatformServiceImpl implements ClientPlatformService {
  @Autowired
  private ClientPlatformInternalService clientPlatformInternalService;
  @Autowired
  private ClientPlatformReturnFactory   clientPlatformReturnFactory;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ClientPlatformService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Return<ClientPlatformDto> create(CreateClientPlatformCmd cmd) {
    notNull(cmd);

    ClientPlatform clientPlatform = this.clientPlatformInternalService.create(cmd);

    return this.clientPlatformReturnFactory.converter(clientPlatform);
  }

  @Override
  public Return<ClientPlatformDto> read(ReadClientPlatformCmd cmd) throws OwnershipException {
    notNull(cmd);
    ClientPlatform clientPlatform = this.clientPlatformInternalService.read(cmd);
    return this.clientPlatformReturnFactory.converter(clientPlatform);
  }

  @Override
  public Return<ClientPlatformDto> read(int id) {
    ClientPlatform clientPlatform = this.clientPlatformInternalService.read(id);

    if (null == clientPlatform) {
      return null;
    } else {
      return this.clientPlatformReturnFactory.converter(clientPlatform);
    }
  }

  @Override
  public Return<List<ClientPlatformDto>> list() {
    List<ClientPlatform> list = this.clientPlatformInternalService.list();

    return this.clientPlatformReturnFactory.converter(list);
  }
}
