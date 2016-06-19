package kr.lul.urs.util;

import static kr.lul.urs.util.Asserts.notNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author justburrow
 */
public class MapBuilder<K, V> {
  /**
   * {@link HashMap}을 사용한 빌더를 만든다.
   *
   * @return 맵 빌더.
   */
  public static <K, V> MapBuilder<K, V> hash() {
    return new MapBuilder<>(new HashMap<>());
  }

  /**
   * 메서드 인자로 초기화한 {@link HashMap}을 사용한 빌더를 만든다.
   *
   * @return 맵 빌더.
   */
  public static <K, V> MapBuilder<K, V> hash(K key, V value) {
    return new MapBuilder<>(new HashMap<K, V>()).put(key, value);
  }

  /**
   * {@link LinkedHashMap}을 사용한 빌더를 만든다.
   *
   * @return 맵 빌더.
   */
  public static <K, V> MapBuilder<K, V> linkedhash() {
    return new MapBuilder<>(new LinkedHashMap<>());
  }

  /**
   * 메서드 인자로 초기화한 {@link LinkedHashMap}을 사용한 빌더를 만든다.
   *
   * @return 맵 빌더.
   */
  public static <K, V> MapBuilder<K, V> linkedhash(K key, V value) {
    return new MapBuilder<>(new LinkedHashMap<K, V>()).put(key, value);
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private boolean   expired;
  private Map<K, V> map;

  private MapBuilder(Map<K, V> map) {
    notNull(map);

    this.expired = false;
    this.map = map;
  }

  /**
   * TODO {@link Conditions}, {@link Asserts}, {@link ConditionalExceptions}에 <code>boolean</code> 도구 추가.
   */
  private void check() throws IllegalStateException {
    if (this.expired) {
      throw new IllegalStateException("builder is expired.");
    }
  }

  /**
   * 빌더에서 관리하는 {@link Map}에 엔트리를 추가한다.
   *
   * @param key
   *          키.
   * @param value
   *          값.
   * @return <code>this</code>
   */
  public MapBuilder<K, V> put(K key, V value) {
    this.check();
    notNull(key);
    this.map.put(key, value);
    return this;
  }

  /**
   * 빌더과 관리중이던 {@link Map}을 반환하고 빌더를 사용 불가능한 상태로 바꾼다.
   *
   * @return 빌더로 만든 {@link Map}.
   */
  public Map<K, V> build() {
    this.check();
    Map<K, V> map = this.map;
    this.expired = true;
    this.map = null;
    return map;
  }
}
