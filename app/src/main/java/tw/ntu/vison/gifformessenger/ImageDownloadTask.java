package tw.ntu.vison.gifformessenger;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Vison on 2015/7/15.
 */
public class ImageDownloadTask extends AsyncTask<String, Integer, byte[]> {
    public interface TaskCallback{
        public void onTaskComplete(byte[] bytes);
    };
    TaskCallback callback;

    public ImageDownloadTask(TaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected byte[] doInBackground(String... strings) {
        URL url;
        InputStream is = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            url = new URL(strings[0]);
            is = url.openStream();
            byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
            int n;
            Integer i=0;
            while ( (n = is.read(byteChunk)) > 0 ) {
                outputStream.write(byteChunk, 0, n);
                Log.i("OUTPUT_STREAM", (i++).toString());
            }
            Log.i("TOTAL_BYTES", Integer.toString(byteChunk.length));
        }
        catch (IOException e) {
            System.err.printf ("Failed while reading bytes from url: %s", e.getMessage());
            e.printStackTrace ();
            // Perform any other exception handling that's appropriate.
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputStream.toByteArray();
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        callback.onTaskComplete(bytes);
    }
}
