package tw.ntu.vison.gifformessenger;

import android.provider.SyncStateContract;
import android.widget.Button;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

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


}
