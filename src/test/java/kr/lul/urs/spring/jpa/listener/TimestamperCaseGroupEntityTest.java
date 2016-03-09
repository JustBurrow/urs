package kr.lul.urs.spring.jpa.listener;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static kr.lul.urs.util.Tests.exceptException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.spring.jpa.annotation.Timestamp;
import kr.lul.urs.spring.jpa.annotation.Timestamps;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;
import lombok.Data;

public class TimestamperCaseGroupEntityTest {
  @Data
  class NonEntity {
    @Timestamp(trigger = PrePersist.class)
    private Instant aaa;
  }

  @Entity
  @Data
  class NoTimestampEntity {
    private Instant bbb;
  }

  @Entity
  @Timestamp(trigger = PrePersist.class, name = "create")
  @Data
  class TypeCreateEntity {
    private Instant create;
  }

  @Entity
  @Timestamps({ @Timestamp(trigger = PrePersist.class, name = "create"),
      @Timestamp(trigger = PreUpdate.class, name = "update") })
  @Data
  class TypeCreateUpdateEntity {
    private Instant create;
    private Instant update;
  }

  @Entity
  @Data
  class FieldCreateUpdateEntity {
    @Timestamp(trigger = PrePersist.class)
    private Instant create;
    @Timestamp(trigger = PreUpdate.class)
    private Instant update;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  private Timestamper listener;
  private Instant     now;

  @Before
  public void setUp() throws Exception {
    this.listener = new Timestamper();
    this.now = Instant.now();

    Thread.sleep(Randoms.in(100L, 500L));
  }

  @Test
  public void testWithNonEntity() throws Exception {
    // Given
    final NonEntity entity = new NonEntity();
    assertNull(entity.getAaa());

    // When & Then
    exceptException(AssertionException.class, () -> this.listener.prePersist(entity));
    assertNull(entity.getAaa());

    exceptException(AssertionException.class, () -> this.listener.preUpdate(entity));
    assertNull(entity.getAaa());

    exceptException(AssertionException.class, () -> this.listener.postLoad(entity));
    assertNull(entity.getAaa());
  }

  @Test
  public void testWithNoTimestampEntity() throws Exception {
    // Given
    final NoTimestampEntity entity = new NoTimestampEntity();
    assertNull(entity.getBbb());

    // When & Then
    this.listener.prePersist(entity);
    assertNull(entity.getBbb());

    this.listener.preUpdate(entity);
    assertNull(entity.getBbb());

    this.listener.postLoad(entity);
    assertNull(entity.getBbb());

    Thread.sleep(10L);

    assertNull(entity.getBbb());
    assertNull(entity.getBbb());
    assertNull(entity.getBbb());
  }

  @Test
  public void testWithTypeCreateEntity() throws Exception {
    // Given
    final TypeCreateEntity entity = new TypeCreateEntity();
    assertNull(entity.getCreate());

    // When & Then
    this.listener.preUpdate(entity);
    assertNull(entity.getCreate());

    this.listener.postLoad(entity);
    assertNull(entity.getCreate());

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    assertThat(create, after(this.now));
    Thread.sleep(10L);
    assertEquals(create, entity.getCreate());
  }

  @Test
  public void testWithTypeCreateUpdateEntity() throws Exception {
    // Given
    final TypeCreateUpdateEntity entity = new TypeCreateUpdateEntity();
    assertNull(entity.getCreate());
    assertNull(entity.getUpdate());

    // When & Then
    this.listener.postLoad(entity);
    assertNull(entity.getCreate());
    assertNull(entity.getUpdate());

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    final Instant update = entity.getUpdate();
    assertEquals(update, create);
    assertThat(create, after(this.now));
    assertThat(update, after(this.now));
    assertEquals(entity.getCreate(), create);
    assertEquals(entity.getUpdate(), update);

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    Instant update2 = entity.getUpdate();
    assertEquals(create, entity.getCreate());
    assertThat(update2, after(update));
    Thread.sleep(10L);
    assertEquals(create, entity.getCreate());
    assertEquals(update2, entity.getUpdate());
  }

  @Test
  public void testFieldCreateUpdateEntity() throws Exception {
    // Given
    final FieldCreateUpdateEntity entity = new FieldCreateUpdateEntity();
    assertNull(entity.getCreate());
    assertNull(entity.getUpdate());

    // When & Then
    this.listener.postLoad(entity);
    assertNull(entity.getCreate());
    assertNull(entity.getUpdate());

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    final Instant update = entity.getUpdate();
    assertThat(create, after(this.now));
    assertThat(update, after(this.now));
    assertEquals(create, update);

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    Instant update2 = entity.getUpdate();
    assertEquals(create, entity.getCreate());
    assertThat(update2, after(update));
    Thread.sleep(10L);
    assertEquals(create, entity.getCreate());
    assertEquals(update2, entity.getUpdate());
  }
}
