/*File: CompleteItemsTests.java
 * 
 * Authors				ID
 * Jian Wei Chong		300352789
 * 
 * Date					Author			Modification
 * 7 oct 16				Jian Wei		Created the class*/
package missing.tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class runs all the items tests*/
@RunWith(Suite.class)
@Suite.SuiteClasses({
		SourceTests.class,
		FurnitureTests.class,
		//SourceTimerTests.class,
		FoliageTests.class,
		ToolTests.class
})
public class CompleteItemsTests {

}
