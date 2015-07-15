package tw.ntu.vison.gifformessenger;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.tools.ant.taskdefs.Execute;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import java.util.ArrayList;
import java.util.regex.Matcher;

import tw.ntu.vison.gifformessenger.activity.MainActivity;
import tw.ntu.vison.gifformessenger.fragment.MainActivityFragment;

/**
 * Created by Vison on 2015/7/14.
 */
@RunWith(CustomRobolectricRunner.class)
@Config(constants = BuildConfig.class)

public class MainActivityFragmentTest {
    MainActivityFragment mFragment;
    @Before
    public void setUp() {
        mFragment = new MainActivityFragment();
        FragmentTestUtil.startFragment(mFragment, MainActivity.class);
    }

    @Test
    public void testButtonExist() {
        Button button = (Button) mFragment.getView().findViewById(R.id.search_button);
        Assert.assertThat("Button does not exist", button, CoreMatchers.not(CoreMatchers.nullValue()));
    }

    @Test
    public void testHttpRequest() {
        String url = new GoogleSearchString().setQuery("pokemon").setFileType("gif").getUrl();
        HttpRequest request = HttpRequest.get(url);
        Assert.assertTrue("request 2 does not return 200", request.ok());
    }

    @Test
    public void testImageSearch() {
        String url = new GoogleSearchString().setQuery("pokemon").setFileType("gif").getUrl();

        ImageSearchTask imageSearchTask = new ImageSearchTask(new ImageSearchTask.TaskCallback() {
            @Override
            public void onTaskComplete(ArrayList<String> results) {
                Assert.assertEquals("results size is incorrect", 8, results.size());
                ArrayList<String> expectResult = new ArrayList<String>();

                for (int i=0;i<results.size();i++) {
                    Assert.assertThat(results.get(i), CoreMatchers.not(CoreMatchers.nullValue()));
                }

            }
        });
        imageSearchTask.execute(url);

        //HttpRequest request_2 = HttpRequest.get(url,false, "tbm", "isch", "q", "eat");
        //HttpRequest request_3 = HttpRequest.get(url,true, "tbm", "isch", "q", "eat");

        //Assert.assertEquals("request string is not as expected",  requestString, request.toString());

        //Assert.assertTrue("request 2 does not return 200", request_2.ok());
        //Assert.assertTrue("request 3 does not return 200", request_3.ok());

    }


}
