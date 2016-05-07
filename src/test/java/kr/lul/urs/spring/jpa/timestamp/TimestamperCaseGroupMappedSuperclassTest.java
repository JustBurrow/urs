package kr.lul.urs.spring.jpa.timestamp;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.core.domain.entity.OperatorEntity;
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
  class FieldCreateUpdateSuperclass implements Updatable {
    @Timestamp(trigger = PrePersist.class)
    private Instant create;
    @Timestamp(trigger = PreUpdate.class)
    private Instant update;

    @Override
    public Instant getCreate() {
      return this.create;
    }

    @Override
    public Instant getUpdate() {
      return this.update;
    }
  }

  @Entity
  class InheritFieldEntity extends FieldCreateUpdateSuperclass {
  }

  @Test
  public void testWithInheritFieldEntity() throws Exception {
    // Given
    final InheritFieldEntity entity = new InheritFieldEntity();
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getUpdate()).isNull();

    // When & Then
    this.listener.postLoad(entity);
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getUpdate()).isNull();

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    final Instant update = entity.getUpdate();
    assertThat(create).isEqualTo(update).isGreaterThanOrEqualTo(this.now);

    this.listener.postLoad(entity);
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(entity.getUpdate()).isEqualTo(update);

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(entity.getUpdate()).isGreaterThan(update);
  }

  @Test
  public void testWithOperatorEntity() throws Exception {
    // Given
    final String email = "aa@bb.cc";
    final String password = RandomStringUtils.randomAlphanumeric(10);
    final OperatorEntity operator = new OperatorEntity(email, password);

    // When
    this.listener.prePersist(operator);

    // Then
    assertThat(operator.getCreate()).isNotNull().isEqualTo(operator.getUpdate()).isGreaterThanOrEqualTo(this.now);
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
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getUpdate()).isNull();

    // When & Then
    this.listener.postLoad(entity);
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getUpdate()).isNull();

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    final Instant update = entity.getUpdate();
    assertThat(create).isEqualTo(update).isGreaterThanOrEqualTo(this.now);

    this.listener.postLoad(entity);
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(entity.getUpdate()).isEqualTo(update);
    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    final Instant update2 = entity.getUpdate();
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(update2).isGreaterThan(update);

    for (int i = Randoms.in(5, 10); i > 0; i--) {
      Thread.sleep(10L);
      assertThat(entity.getCreate()).isEqualTo(create);
      assertThat(entity.getUpdate()).isEqualTo(update2);
    }
  }

  @Test
  public void testWithComplexEntity() throws Exception {
    // Given
    final ComplexEntity entity = new ComplexEntity();
    assertThat(entity.getCreate()).isNull();
    assertThat(entity.getRead()).isNull();
    assertThat(entity.getUpdate()).isNull();

    // When & Then
    this.listener.postLoad(entity);
    final Instant read = entity.getRead();
    assertThat(entity.getCreate()).isNull();
    assertThat(read).isGreaterThanOrEqualTo(this.now);
    assertThat(entity.getUpdate()).isNull();

    this.listener.prePersist(entity);
    final Instant create = entity.getCreate();
    Instant update = entity.getUpdate();

    assertThat(create).isEqualTo(update).isGreaterThanOrEqualTo(this.now);
    assertThat(entity.getRead()).isEqualTo(read);

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.postLoad(entity);
    final Instant read2 = entity.getRead();
    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(read2).isGreaterThanOrEqualTo(read);
    assertThat(entity.getUpdate()).isEqualTo(update);

    Thread.sleep(Randoms.in(500L, 1000L));

    this.listener.preUpdate(entity);
    final Instant update2 = entity.getUpdate();

    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(entity.getRead()).isEqualTo(read2);
    assertThat(update2).isGreaterThan(update);

    Thread.sleep(10L);

    assertThat(entity.getCreate()).isEqualTo(create);
    assertThat(entity.getRead()).isEqualTo(read2);
    assertThat(entity.getUpdate()).isEqualTo(update2);
  }
}
