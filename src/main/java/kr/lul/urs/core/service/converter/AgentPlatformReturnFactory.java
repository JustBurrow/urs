/**
 *
 */
package kr.lul.urs.core.service.converter;

import java.util.List;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.spring.tx.Return;
import kr.lul.urs.spring.tx.ReturnFactory;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 7.
 */
public interface AgentPlatformReturnFactory extends ReturnFactory<AgentPlatform, AgentPlatformDto> {
  @Override
  public Return<AgentPlatformDto> converter(AgentPlatform platform);

  @Override
  public Return<List<AgentPlatformDto>> converter(List<AgentPlatform> list);
}