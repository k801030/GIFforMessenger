package tw.ntu.vison.gifformessenger;
        import android.graphics.Movie;

        import org.hamcrest.CoreMatchers;
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
public class ImageDownloadTaskTest {
    @Test
    public void testDownloadBytes() {
        String url_1 = "http://www.sport-image.fr/IMAGES/CLIPARTSPORT/RUGBY/BOY.GIF";
        new ImageDownloadTask(new ImageDownloadTask.TaskCallback() {
            @Override
            public void onTaskComplete(Movie movie) {
                Assert.assertThat(movie, CoreMatchers.not(CoreMatchers.nullValue()));
            }
        }).execute(url_1);

    }
}

