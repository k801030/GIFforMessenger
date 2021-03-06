package tw.ntu.vison.gifformessenger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Vison on 2015/7/15.
 */
@RunWith(CustomRobolectricRunner.class)
@Config(constants = BuildConfig.class)
public class GoogleSearchStringTest {
    @Test
    public void testQueryReplace() {
        // test dataset
        String s1 = new GoogleSearchString().setQuery("df saf ff").setFileType("gif").getUrl();
        String s2 = new GoogleSearchString().setQuery("hello my,bro").getUrl();
        String s3 = new GoogleSearchString().setQuery("  ").getUrl();
        String s4 = new GoogleSearchString().setQuery("").getUrl();

        // expected dataset
        final String API_URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&start=0&rsz=8";
        final String PLUS = "%20";
        String expect_s1 = API_URL + "&q=df"+PLUS+"saf"+PLUS+"ff"+PLUS+"gif"+PLUS+"filetype:gif";
        String expect_s2 = API_URL + "&q=hello"+PLUS+"my,bro"+PLUS+"gif";
        String expect_s3 = API_URL + "&q="+PLUS+PLUS+PLUS+"gif";
        String expect_s4 = API_URL + "&q="+PLUS+"gif";

        Assert.assertEquals(expect_s1, s1);
        Assert.assertEquals(expect_s2, s2);
        Assert.assertEquals(expect_s3, s3);
        Assert.assertEquals(expect_s4, s4);

    }
}
