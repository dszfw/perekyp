package org.maxi.booter.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.maxi.booter.CarSubscriptionCorrespondingTest;

@RunWith(Suite.class)
@SuiteClasses({ BodyTypeRepositoryTest.class, CarRepositoryTest.class,
		EngineTypeRepositoryTest.class, SubscriptionRepositoryTest.class,
		CarSubscriptionCorrespondingTest.class })
public class AllTests {

}
