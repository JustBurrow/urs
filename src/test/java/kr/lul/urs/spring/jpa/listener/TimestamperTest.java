package kr.lul.urs.spring.jpa.listener;

import static kr.lul.urs.util.Tests.exceptException;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.spring.jpa.annotation.Timestamp;
import kr.lul.urs.spring.jpa.annotation.Timestamps;
import lombok.Data;

public class TimestamperTest {
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
    private Instant create;
  }

  @Entity
  @Timestamp(trigger = PostLoad.class)
  class TimestampPostLoadAsDefaultName {
    private Instant read;
  }

  @Entity
  @Timestamp(trigger = PreUpdate.class)
  class TimestampPreUpdateAsDefaultName {
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
    exceptException(IllegalArgumentException.class, () -> this.listener.prePersist(null));
    exceptException(IllegalArgumentException.class, () -> this.listener.preUpdate(null));
    exceptException(IllegalArgumentException.class, () -> this.listener.postLoad(null));
  }

  @Test
  public void testWithTimestampsDefaultName() throws Exception {
    exceptException(IllegalJpaSupportConfigurationException.class,
        () -> this.listener.prePersist(new TimestampsAsDefaultName()));
    exceptException(IllegalJpaSupportConfigurationException.class,
        () -> this.listener.postLoad(new TimestampsAsDefaultName()));
    exceptException(IllegalJpaSupportConfigurationException.class,
        () -> this.listener.preUpdate(new TimestampsAsDefaultName()));
  }

  @Test
  public void testWithTimestampDefaultName() throws Exception {
    exceptException(IllegalJpaSupportConfigurationException.class,
        () -> this.listener.prePersist(new TimestampPrePersistAsDefaultName()));
    exceptException(IllegalJpaSupportConfigurationException.class,
        () -> this.listener.postLoad(new TimestampPostLoadAsDefaultName()));
    exceptException(IllegalJpaSupportConfigurationException.class,
        () -> this.listener.preUpdate(new TimestampPreUpdateAsDefaultName()));
  }
}
