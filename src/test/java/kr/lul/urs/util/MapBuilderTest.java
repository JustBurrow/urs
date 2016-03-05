package kr.lul.urs.util;

import static kr.lul.urs.util.Tests.exceptException;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class MapBuilderTest {
  class Key {
  }

  class Value {
  }

  @Test
  public void testHashmap() throws Exception {
    MapBuilder<String, BigDecimal> builder = MapBuilder.<String, BigDecimal>hash();
    Map<String, BigDecimal> map = builder.build();

    assertNotNull(builder);
    assertTrue(map.isEmpty());
    assertThat(map, allOf(notNullValue(), instanceOf(HashMap.class)));
  }

  @Test
  public void testHashmapWithEntry() throws Exception {
    // Given
    String key = RandomStringUtils.random(5);
    BigDecimal value = BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.valueOf(Long.MAX_VALUE));

    // When
    MapBuilder<String, BigDecimal> builder = MapBuilder.<String, BigDecimal>hash(key, value);
    Map<String, BigDecimal> map = builder.build();

    // Then
    assertNotNull(builder);
    assertThat(map, allOf(notNullValue(), instanceOf(HashMap.class), hasEntry(key, value)));
    assertEquals(1, map.size());

    exceptException(IllegalStateException.class, () -> builder.put(RandomStringUtils.random(10), BigDecimal.TEN));
  }

  @Test
  public void testLinkedhashmap() throws Exception {
    // When
    MapBuilder<Key, Value> builder = MapBuilder.<Key, Value>linkedhash();
    Map<Key, Value> map = builder.build();

    // Then
    assertNotNull(builder);
    assertTrue(map.isEmpty());
    assertThat(map, allOf(notNullValue(), instanceOf(LinkedHashMap.class)));
  }

  @Test
  public void testLinkedhashmapWithEntry() throws Exception {
    // Given
    Key k1 = new Key();
    Value v1 = new Value();
    Key k2 = new Key();
    Value v2 = new Value();

    // When
    MapBuilder<Key, Value> builder = MapBuilder.<Key, Value>linkedhash(k1, v1).put(k2, v2);
    Map<Key, Value> map = builder.build();

    // Then
    assertNotNull(builder);
    assertThat(map, allOf(notNullValue(), instanceOf(LinkedHashMap.class), hasEntry(k1, v1), hasEntry(k2, v2)));
    assertEquals(2, map.size());

    Iterator<Entry<Key, Value>> iterator = map.entrySet().iterator();
    Entry<Key, Value> entry = iterator.next();
    assertEquals(k1, entry.getKey());
    assertEquals(v1, entry.getValue());

    entry = iterator.next();
    assertEquals(k2, entry.getKey());
    assertEquals(v2, entry.getValue());

    exceptException(IllegalStateException.class, () -> builder.put(new Key(), new Value()));
    exceptException(IllegalStateException.class, () -> builder.build());
  }
}
