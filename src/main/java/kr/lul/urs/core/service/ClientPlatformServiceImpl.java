package kr.lul.urs.core.service;

import static kr.lul.urs.util.Asserts.notNull;

import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;
import kr.lul.urs.spring.tx.util.Return;

@Service
class ClientPlatformServiceImpl implements ClientPlatformService {
  @Autowired
  private ClientPlatformInternalService clientPlatformInternalService;

  private ModelMapper                   mapper;

  @PostConstruct
  public void postConstruct() {
    this.mapper = new ModelMapper();
    this.mapper.addMappings(new PropertyMap<ClientPlatformEntity, ClientPlatformDto>() {
      @Override
      protected void configure() {
        this.map().setOwner(this.source.getOwner().getId());
      }
    });
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ClientPlatformService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Return<ClientPlatformDto> create(CreateClientPlatformCmd cmd) {
    notNull(cmd);

    ClientPlatform clientPlatform = this.clientPlatformInternalService.create(cmd);

    return () -> this.mapper.map(clientPlatform, ClientPlatformDto.class);
  }

  @Override
  public Return<ClientPlatformDto> read(ReadClientPlatformCmd cmd) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Return<ClientPlatformDto>> list() {
    // TODO Auto-generated method stub
    return null;
  }
}
