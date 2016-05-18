package kr.lul.urs.spring.jpa.timestamp;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.AbstractTest;
import lombok.Data;

public class TimestamperTest extends AbstractTest {
  @Entity
  @Timestamps({ @Timestamp(trigger = PrePersist.class), @Timestamp(trigger = PreUpdate.class),
      @Timestamp(trigger = PostLoad.class) })
  @Data
  class TimestampsAsDefaultName {
    private Instant create;
    private Instant read;
    private Instant update;
  }

  @Entity
  @Timestamp(trigger = PrePersist.class)
  class TimestampPrePersistAsDefaultName {
    @SuppressWarnings("unused")
    private Instant create;
  }

  @Entity
  @Timestamp(trigger = PostLoad.class)
  class TimestampPostLoadAsDefaultName {
    @SuppressWarnings("unused")
    private Instant read;
  }

  @Entity
  @Timestamp(trigger = PreUpdate.class)
  class TimestampPreUpdateAsDefaultName {
    @SuppressWarnings("unused")
    private Instant update;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  private Timestamper listener;

  @Before
  public void setUp() throws Exception {
    this.listener = new Timestamper();
  }

  @Test
  public void testWithNull() throws Exception {
    assertThatThrownBy(() -> this.listener.prePersist(null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> this.listener.preUpdate(null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> this.listener.postLoad(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testWithTimestampsDefaultName() throws Exception {
    assertThatThrownBy(() -> this.listener.prePersist(new TimestampsAsDefaultName()))
        .isInstanceOf(IllegalJpaSupportConfigurationException.class);
    assertThatThrownBy(() -> this.listener.postLoad(new TimestampsAsDefaultName()))
        .isInstanceOf(IllegalJpaSupportConfigurationException.class);
    assertThatThrownBy(() -> this.listener.preUpdate(new TimestampsAsDefaultName()))
        .isInstanceOf(IllegalJpaSupportConfigurationException.class);

  }

  @Test
  public void testWithTimestampDefaultName() throws Exception {
    assertThatThrownBy(() -> this.listener.prePersist(new TimestampPrePersistAsDefaultName()))
        .isInstanceOf(IllegalJpaSupportConfigurationException.class);
    assertThatThrownBy(() -> this.listener.postLoad(new TimestampPostLoadAsDefaultName()))
        .isInstanceOf(IllegalJpaSupportConfigurationException.class);
    assertThatThrownBy(() -> this.listener.preUpdate(new TimestampPreUpdateAsDefaultName()))
        .isInstanceOf(IllegalJpaSupportConfigurationException.class);
  }
}
