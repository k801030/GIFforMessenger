package tw.ntu.vison.gifformessenger;

import android.graphics.Movie;
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
public class ImageDownloadTask extends AsyncTask<String, Integer, Movie> {
    public interface TaskCallback{
        public void onTaskComplete(Movie movie);
    };
    TaskCallback callback;

    public ImageDownloadTask(TaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Movie doInBackground(String... strings) {
        URL url;
        InputStream is = null;
        Movie movie = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            url = new URL(strings[0]);
            is = url.openStream();
            movie = Movie.decodeStream(is);
            is.close();
        }
        catch (IOException e) {
            System.err.printf ("Failed while reading bytes from url: %s", e.getMessage());
            e.printStackTrace ();
        }
        return movie;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        super.onPostExecute(movie);
        callback.onTaskComplete(movie);
    }
}
