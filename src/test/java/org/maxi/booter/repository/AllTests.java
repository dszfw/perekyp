package org.maxi.booter.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BodyTypeRepositoryTest.class, CarRepositoryTest.class,
		EngineTypeRepositoryTest.class, SubscriptionRepositoryTest.class })
public class AllTests {

}
