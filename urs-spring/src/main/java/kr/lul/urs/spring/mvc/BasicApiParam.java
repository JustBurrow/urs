/**
 *
 */
package kr.lul.urs.spring.mvc;

import static kr.lul.urs.util.Asserts.notNull;

/**
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public class BasicApiParam<T> implements ApiParam<T> {
  private Location location;
  private String   name;
  private boolean  optional;
  private Class<T> type;
  private T        defaultValue;

  public BasicApiParam(Location location, boolean optional, String name, Class<T> type) {
    notNull(location, "location");
    notNull(name, "name");
    notNull(type, "type");

    this.location = location;
    this.optional = optional;
    this.type = type;
    this.name = name;
    this.defaultValue = null;
  }

  @SuppressWarnings("unchecked")
  public BasicApiParam(Location location, boolean optional, String name, T defaultValue) {
    notNull(location, "location");
    notNull(name, "name");
    notNull(defaultValue, "defaultValue");

    this.location = location;
    this.optional = optional;
    this.type = (Class<T>) defaultValue.getClass();
    this.name = name;
    this.defaultValue = defaultValue;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ApiParam<T>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.ApiParam#getLocation()
   * @since 2016. 6. 26.
   */
  @Override
  public Location getLocation() {
    return this.location;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.ApiParam#isOptional()
   * @since 2016. 6. 26.
   */
  @Override
  public boolean isOptional() {
    return this.optional;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.ApiParam#hasDefault()
   * @since 2016. 6. 26.
   */
  @Override
  public boolean hasDefault() {
    return null != this.defaultValue;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.ApiParam#getName()
   * @since 2016. 6. 26.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.ApiParam#getType()
   * @since 2016. 6. 26.
   */
  @Override
  public Class<T> getType() {
    return this.type;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.spring.mvc.ApiParam#getDefault()
   * @since 2016. 6. 26.
   */
  @Override
  public T getDefault() throws UnsupportedOperationException {
    if (null == this.defaultValue) {
      throw new UnsupportedOperationException();
    } else {
      return this.defaultValue;
    }
  }
}
