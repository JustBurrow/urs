/**
 *
 */
package kr.lul.urs.core.service.converter;

import java.util.List;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.spring.tx.Return;
import kr.lul.urs.spring.tx.ReturnFactory;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 7.
 */
public interface ClientPlatformReturnFactory extends ReturnFactory<ClientPlatform, ClientPlatformDto> {
  @Override
  public Return<ClientPlatformDto> converter(ClientPlatform clientPlatform);

  @Override
  public Return<List<ClientPlatformDto>> converter(List<ClientPlatform> list);
}