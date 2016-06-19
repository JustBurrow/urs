package kr.lul.urs.spring.jpa.timestamp;

import static kr.lul.urs.util.Tests.exceptException;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.spring.AbstractSpringTest;
import kr.lul.urs.util.Randoms;
import lombok.Data;

public class TimestamperCaseGroupEntityTest extends AbstractSpringTest {
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
    assertThat(entity.getAaa()).isNull();

    // When & Then
    exceptException(IllegalArgumentException.class, () -> this.listener.prePersist(entity));
    assertThat(entity.getAaa()).isNull();

    exceptException(IllegalArgumentException.class, () -> this.listener.preUpdate(entity));
    assertThat(entity.getAaa()).isNull();

    exceptException(IllegalArgumentException.class, () -> this.listener.postLoad(entity));
    assertThat(entity.getAaa()).isNull();
  }

  @Test
  public void testWithNoTimestampEntity() throws Exception {
    // Given
    final NoTimestampEntity entity = new NoTimestampEntity();
    assertThat(entity.getBbb()).isNull();

    // When & Then
    this.listener.prePersist(entity);
    assertThat(entity.getBbb()).isNull();

    this.listener.preUpdate(entity);
    assertThat(entity.getBbb()).isNull();

    this.listener.postLoad(entity);
    assertThat(entity.getBbb()).isNull();

    for (int i = Randoms.in(3, 10); i > 0; i--) {
      Thread.sleep(10L);
      assertThat(entity.getBbb()).isNull();
    }
  }

  @Test
  public void testWithTypeCreateEntity() throws Exception {
    // Given
    final TypeCreateEntity entity = new TypeCreateEntity();
    assertThat(entity.getCreate()).isNull();

    // When & Then
    this.listener.preUpdate(entity);
    assertThat(entity.getCreate()).isNull();

    this.listener.postLoad(entity);
    assertThat(entity.getCreate()).isNull();

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    assertThat(create).isGreaterThanOrEqualTo(this.now);
    Thread.sleep(10L);
    assertThat(entity.getCreate()).isEqualTo(create);
  }

  @Test
  public void testWithTypeCreateUpdateEntity() throws Exception {
    // Given
    final TypeCreateUpdateEntity entity = new TypeCreateUpdateEntity();
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getUpdate()).isNull();

    // When & Then
    this.listener.postLoad(entity);
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getUpdate()).isNull();

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    final Instant update = entity.getUpdate();
    assertThat(create).isGreaterThanOrEqualTo(this.now).isEqualTo(update).isEqualTo(entity.getCreate())
        .isEqualTo(entity.getUpdate());

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    Instant update2 = entity.getUpdate();
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(update2).isGreaterThan(create).isGreaterThan(update);

    Thread.sleep(10L);
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(entity.getUpdate()).isEqualTo(update2);
  }

  @Test
  public void testFieldCreateUpdateEntity() throws Exception {
    // Given
    final FieldCreateUpdateEntity entity = new FieldCreateUpdateEntity();
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getUpdate()).isNull();

    // When & Then
    this.listener.postLoad(entity);
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getUpdate()).isNull();

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    final Instant update = entity.getUpdate();
    assertThat(create).isGreaterThanOrEqualTo(this.now).isEqualTo(update);

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    Instant update2 = entity.getUpdate();
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(update2).isGreaterThan(create).isGreaterThan(update);

    Thread.sleep(10L);
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(entity.getUpdate()).isEqualTo(update2);
  }
}
