package dk.nine.kickoff.barsim.model;

import org.junit.Assert;
import org.junit.Test;

public class BartenderBuilderTest {

  @Test
  public void testBuilder() {
    Bartender bartender = new BartenderBuilder(1000,9000).build();

    Assert.assertNotNull(bartender.getName() != null );
    Assert.assertTrue(bartender.getMaximumDrinkPrepTime()>=bartender.getMinimumDrinkPrepTime());
    Assert.assertTrue(bartender.getMaximumDrinkPrepTime()<=9000+bartender.getMinimumDrinkPrepTime());
    Assert.assertTrue(bartender.getMinimumDrinkPrepTime()>=0);
  }
}
