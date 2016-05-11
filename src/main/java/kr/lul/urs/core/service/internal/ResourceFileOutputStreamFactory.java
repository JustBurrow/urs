/**
 *
 */
package kr.lul.urs.core.service.internal;

import java.io.OutputStream;

import kr.lul.urs.core.domain.ResourceFileRevision;
import kr.lul.urs.util.OutputStreamFactory;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 11.
 */
public interface ResourceFileOutputStreamFactory extends OutputStreamFactory<OutputStream, ResourceFileRevision> {
}
