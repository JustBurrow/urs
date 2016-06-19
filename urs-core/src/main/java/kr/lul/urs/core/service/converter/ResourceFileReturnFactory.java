/**
 *
 */
package kr.lul.urs.core.service.converter;

import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.dto.ResourceFileDto;
import kr.lul.urs.spring.tx.ReturnFactory;

/**
 * @since 2016. 5. 20.
 * @author Just Burrow just.burrow@lul.kr
 */
public interface ResourceFileReturnFactory extends ReturnFactory<ResourceFile, ResourceFileDto> {
}
