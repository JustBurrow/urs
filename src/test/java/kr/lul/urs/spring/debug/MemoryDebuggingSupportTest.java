/**
 *
 */
package kr.lul.urs.spring.debug;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.util.MapBuilder;
import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;
import kr.lul.urs.util.SystemTimeProvider;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 9.
 */
public class MemoryDebuggingSupportTest extends AbstractTest {
  private MemoryDebuggingSupport debuggingSupport;

  @Before
  public void setUp() throws Exception {
    this.setNow();

    this.debuggingSupport = new MemoryDebuggingSupport();
    this.debuggingSupport.setTimeProvider(new SystemTimeProvider());
    assertThat(this.debuggingSupport).isNotNull();
  }

  @Test
  public void testStackForNoAppend() throws Exception {
    assertThat(this.debuggingSupport.stack()).isEmpty();
  }

  @Test
  public void testClearForNoAppend() throws Exception {
    assertThatThrownBy(() -> this.debuggingSupport.clear()).isInstanceOf(IllegalStateException.class);
  }

  @Test
  public void testAppendDataWithMessage() throws Exception {
    // Given
    final String message = MemoryDebuggingSupportTest.class.getSimpleName() + "#testPushDataWithMessage "
        + RandomStringUtils.random(10);

    // When
    final DebugEntry entry = this.debuggingSupport.append(message);

    // Then
    assertThat(entry).isNotNull();
    assertThat(entry.getTimestamp()).isGreaterThanOrEqualTo(this.now);
    assertThat(entry.getThread()).isEqualTo(Thread.currentThread().getName());
    assertThat(entry.getMessage()).isEqualTo(message);
    assertThat(entry.getData()).isNull();

    final List<DebugEntry> s1 = this.debuggingSupport.stack();
    assertThat(s1).contains(entry)
        .hasSize(1)
        .isEqualTo(this.debuggingSupport.stack());
    assertThatThrownBy(() -> s1.add(new DebugEntry(Instant.now(), null, null) {
    })).isInstanceOf(Exception.class);

    final List<DebugEntry> s2 = this.debuggingSupport.clear();
    assertThat(s2).isNotSameAs(s1)
        .isEqualTo(s1);
    assertThatThrownBy(() -> s2.add(new DebugEntry(Instant.now(), null, null) {
    })).isInstanceOf(Exception.class);
    assertThat(this.debuggingSupport.stack()).isEmpty();
    assertThatThrownBy(() -> this.debuggingSupport.clear()).isInstanceOf(IllegalStateException.class);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testAppendWithData() throws Exception {
    // Given
    String k1 = "k1";
    boolean v1 = Randoms.bool();
    String k2 = "k2";
    int v2 = Randoms.positive();
    Map<String, Object> data = MapBuilder.<String, Object> linkedhash(k1, v1).put(k2, v2).build();
    Map<String, Object> backup = Collections.unmodifiableMap(data);

    // When
    DebugEntry entry = this.debuggingSupport.append(data);

    // Then
    assertThat(entry).isNotNull();
    assertThat(entry.getTimestamp()).isGreaterThanOrEqualTo(this.now);
    assertThat(entry.getThread()).isEqualTo(Thread.currentThread().getName());
    assertThat(entry.getMessage()).isNull();
    assertThat(entry.getData()).isInstanceOf(Map.class);
    assertThat((Map<String, Object>) entry.getData()).isSameAs(data)
        .containsAllEntriesOf(backup);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testAppendWithMessageAndData() throws Exception {
    // Given
    final String m1 = Strings.random(10, 50);
    final List<Object> d1 = Arrays.asList(new Object());
    final List<Object> b1 = Collections.unmodifiableList(d1);

    String m2;
    do {
      m2 = Strings.random(10, 20);
    } while (m2.equals(m1));
    final String k1 = "k1";
    final String v1 = Strings.random(10, 50);
    final String k2 = "key2";
    final int v2 = Randoms.positive();
    final Map<String, Object> d2 = MapBuilder.<String, Object> linkedhash(k1, v1).put(k2, v2).build();
    final Map<String, Object> b2 = Collections.unmodifiableMap(d2);

    // When
    final DebugEntry e1 = this.debuggingSupport.append(m1, d1);
    final Instant t1 = e1.getTimestamp();

    // Then
    assertThat(e1).isNotNull();
    assertThat(t1).isGreaterThanOrEqualTo(this.now);
    assertThat(e1.getThread()).isEqualTo(Thread.currentThread().getName());
    assertThat(e1.getMessage()).isEqualTo(m1);
    assertThat((List<Object>) e1.getData()).isEqualTo(b1)
        .hasSize(1);

    final List<DebugEntry> s1 = this.debuggingSupport.stack();
    assertThat(s1).hasSize(1).contains(e1);

    // When
    final DebugEntry e2 = this.debuggingSupport.append(m2, d2);

    // Then
    assertThat(e2).isNotNull()
        .isNotSameAs(e1)
        .isNotEqualTo(e1);
    assertThat(e2.getTimestamp()).isNotNull()
        .isGreaterThanOrEqualTo(t1);
    assertThat(e2.getThread()).isEqualTo(Thread.currentThread().getName());
    assertThat(e2.getMessage()).isEqualTo(m2);
    assertThat((Map<String, Object>) e2.getData()).isEqualTo(b2)
        .containsEntry(k1, v1)
        .containsEntry(k2, v2);

    // When
    final List<DebugEntry> s2 = this.debuggingSupport.stack();

    // Then
    assertThat(s2).hasSize(2)
        .containsExactly(e1, e2);

    // When
    final List<DebugEntry> s3 = this.debuggingSupport.clear();

    // Then
    assertThat(s3).isNotNull()
        .isNotSameAs(s2)
        .isEqualTo(s2)
        .containsExactly(e1, e2);
    assertThatThrownBy(() -> this.debuggingSupport.clear()).isInstanceOf(IllegalStateException.class);
  }
}
