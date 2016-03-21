package kr.lul.urs.spring.jpa.timestamp;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.spring.jpa.timestamp.Timestamp;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;
import kr.lul.urs.util.Randoms;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class TimestamperCaseGroupMappedSuperclassTest {
  private Timestamper listener;
  private Instant     now;

  @Before
  public void setUp() throws Exception {
    this.listener = new Timestamper();
    this.now = Instant.now();
    Thread.sleep(Randoms.in(100L, 500L));
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @MappedSuperclass
  @Data
  class FieldCreateUpdateSuperclass {
    @Timestamp(trigger = PrePersist.class)
    private Instant create;
    @Timestamp(trigger = PreUpdate.class)
    private Instant update;
  }

  @Entity
  @Data
  @EqualsAndHashCode(callSuper = true)
  class InheritFieldEntity extends FieldCreateUpdateSuperclass {
  }

  @Test
  public void testWithInheritFieldEntity() throws Exception {
    // Given
    final InheritFieldEntity entity = new InheritFieldEntity();
    assertNull(entity.getCreate());
    assertNull(entity.getUpdate());

    // When & Then
    this.listener.postLoad(entity);
    assertNull(entity.getCreate());
    assertNull(entity.getUpdate());

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    final Instant update = entity.getUpdate();
    assertEquals(create, update);
    assertThat(create, after(this.now));
    assertThat(update, after(this.now));

    this.listener.postLoad(entity);
    assertEquals(create, entity.getCreate());
    assertEquals(update, entity.getUpdate());

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    final Instant update2 = entity.getUpdate();
    assertNotNull(update2);
    assertEquals(create, entity.getCreate());
    assertThat(update2, after(update));
    assertEquals(update2, entity.getUpdate());
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @MappedSuperclass
  @Timestamp(trigger = PrePersist.class, name = "create")
  @Data
  class TypeCreateSuperclass {
    private Instant create;
  }

  @Entity
  @Timestamp(trigger = PreUpdate.class, name = "update")
  @Data
  @EqualsAndHashCode(callSuper = true)
  class InheritCreateUpdateEntity extends TypeCreateSuperclass {
    private Instant update;
  }

  @Entity
  @Timestamp(trigger = PreUpdate.class, name = "update")
  @Data
  @EqualsAndHashCode(callSuper = true)
  class ComplexEntity extends TypeCreateSuperclass {
    @Timestamp(trigger = PostLoad.class)
    private Instant read;
    private Instant update;
  }

  @Test
  public void testWithInheritCreateUpdateEntity() throws Exception {
    // Given
    final InheritCreateUpdateEntity entity = new InheritCreateUpdateEntity();
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
    assertEquals(create, update);

    this.listener.postLoad(entity);
    assertEquals(create, entity.getCreate());
    assertEquals(update, entity.getUpdate());

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    final Instant update2 = entity.getUpdate();
    assertEquals(create, entity.getCreate());
    assertThat(update2, after(update));
    Thread.sleep(10L);
    assertEquals(create, entity.getCreate());
    assertEquals(update2, entity.getUpdate());
  }

  @Test
  public void testWithComplexEntity() throws Exception {
    // Given
    final ComplexEntity entity = new ComplexEntity();
    assertNull(entity.getCreate());
    assertNull(entity.getRead());
    assertNull(entity.getUpdate());

    // When & Then
    this.listener.postLoad(entity);
    final Instant read = entity.getRead();
    assertNull(entity.getCreate());
    assertThat(read, after(this.now));
    assertNull(entity.getUpdate());

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    Instant update = entity.getUpdate();
    assertEquals(create, update);
    assertEquals(read, entity.getRead());
    assertThat(create, after(this.now));

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.postLoad(entity);
    final Instant read2 = entity.getRead();
    assertEquals(create, entity.getCreate());
    assertThat(read2, after(read));
    assertEquals(update, entity.getUpdate());

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    final Instant update2 = entity.getUpdate();
    assertEquals(create, entity.getCreate());
    assertEquals(read2, entity.getRead());
    assertThat(update2, after(update));

    Thread.sleep(10L);

    assertEquals(create, entity.getCreate());
    assertEquals(read2, entity.getRead());
    assertEquals(update2, entity.getUpdate());
  }
}
