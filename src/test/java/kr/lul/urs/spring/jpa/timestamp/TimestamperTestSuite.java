package kr.lul.urs.spring.jpa.timestamp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TimestamperCaseGroupEntityTest.class, TimestamperCaseGroupMappedSuperclassTest.class,
    TimestamperTest.class })
public class TimestamperTestSuite {
}
