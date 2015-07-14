package tw.ntu.vison.gifformessenger;

import org.junit.runners.model.InitializationError;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;

import java.util.Properties;

/**
 * Created by Vison on 2015/7/14.
 */
public class CustomRobolectricRunner extends RobolectricGradleTestRunner {
    public CustomRobolectricRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected int pickSdkVersion(Config config, AndroidManifest manifest) {
        // current Robolectric supports not the latest android SDK version
        // so we must downgrade to simulate the latest supported version.
        config = overwriteConfig(config, "sdk", "18");
        return super.pickSdkVersion(config, manifest);
    }


    protected Config.Implementation overwriteConfig(
            Config config, String key, String value) {
        Properties properties = new Properties();
        properties.setProperty(key, value);
        return new Config.Implementation(config,
                Config.Implementation.fromProperties(properties));
    }
}
