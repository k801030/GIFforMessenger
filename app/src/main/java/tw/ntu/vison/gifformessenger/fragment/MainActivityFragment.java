package tw.ntu.vison.gifformessenger.fragment;

import android.os.AsyncTask;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;

import tw.ntu.vison.gifformessenger.HttpRequest;
import tw.ntu.vison.gifformessenger.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // do something with view
        Button button = (Button) view.findViewById(R.id.search_button);
        button.setOnClickListener(new OnSearchListener());
        return view;
    }

    private class OnSearchListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // ImageSearchTask task = new ImageSearchTask();
            String url = "https://www.google.com/search?tbm=isch&q=eat+gif";

            ImageSearchTask searchTask = new ImageSearchTask(new TaskCallback() {
                @Override
                public void onTaskComplete(String result) {

                }
            });
            searchTask.execute(url);
        }
    }
    public class ImageSearchTask extends AsyncTask<String , Integer, String> {
        TaskCallback callback;

        public ImageSearchTask(TaskCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... urls) {
            // HttpRequest object is synchronous
            HttpRequest request = HttpRequest.get(urls[0]);
            if (request.ok()) {
                request.code();
                return request.body();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            callback.onTaskComplete(result);
        }
    }

    public interface TaskCallback{
        public void onTaskComplete(String result);
    };
}
