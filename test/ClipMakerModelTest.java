import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClipMakerModelTest {
  ClipMakerModel test;

  @Before
  public void setupTest() {
    this.test = new ClipMakerModel("https://therealdeal.com/2018/07/05/purplebricks-revenue-doubles-as-it-eyes-us-market-share/",
            "Bob Bawblah");
  }

  @Test
  public void testDate() {
    assertEquals("2018/07/05", test.getDate());
  }

  @Test
  public void testHeadline() {
    assertEquals(true, test.getHeadline().contains("Purplebricks"));
  }

}