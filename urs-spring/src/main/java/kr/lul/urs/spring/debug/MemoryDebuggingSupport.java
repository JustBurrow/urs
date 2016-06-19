/**
 *
 */
package kr.lul.urs.spring.debug;

import static kr.lul.urs.util.Asserts.notNull;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kr.lul.urs.util.TimeProvider;
import lombok.EqualsAndHashCode;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 9.
 */
public class MemoryDebuggingSupport implements DebuggingSupport {
  @EqualsAndHashCode(callSuper = true)
  public static class Entry extends DebugEntry {
    private Entry(Instant timestamp, String message, Object data) {
      super(timestamp, message, data);
    }
  }

  private ThreadLocal<List<DebugEntry>> thread;
  private TimeProvider                  timeProvider;

  public MemoryDebuggingSupport() {
    this.thread = new ThreadLocal<>();
  }

  public void setTimeProvider(TimeProvider timeProvider) {
    notNull(timeProvider);
    this.timeProvider = timeProvider;
  }

  /**
   * @param entry
   * @since 2016. 5. 9.
   */
  private void doAppend(Entry entry) {
    List<DebugEntry> list = this.thread.get();
    if (null == list) {
      list = new LinkedList<>();
      this.thread.set(list);
    }
    list.add(entry);
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>DebuggingSupport
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public DebugEntry append(Object data) {
    Entry entry;
    if (data instanceof String) {
      entry = new Entry(this.timeProvider.now(), (String) data, null);
    } else {
      entry = new Entry(this.timeProvider.now(), null, data);
    }
    this.doAppend(entry);
    return entry;
  }

  @Override
  public DebugEntry append(String message, Object data) {
    Entry entry = new Entry(this.timeProvider.now(), message, data);
    this.doAppend(entry);
    return entry;
  }

  @Override
  public List<DebugEntry> stack() {
    List<DebugEntry> stack = this.thread.get();
    if (null == stack) {
      return Collections.emptyList();
    } else {
      return Collections.unmodifiableList(stack);
    }
  }

  @Override
  public List<DebugEntry> clear() throws IllegalStateException {
    List<DebugEntry> stack;
    synchronized (this.thread) {
      stack = this.thread.get();
      if (null == stack) {
        throw new IllegalStateException("stack does not exist.");
      }
      this.thread.remove();
    }
    return Collections.unmodifiableList(stack);
  }
}
