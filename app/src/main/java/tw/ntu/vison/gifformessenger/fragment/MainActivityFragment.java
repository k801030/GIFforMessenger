package tw.ntu.vison.gifformessenger.fragment;

import android.app.Activity;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

import tw.ntu.vison.gifformessenger.GoogleSearchString;
import tw.ntu.vison.gifformessenger.ImageDownloadTask;
import tw.ntu.vison.gifformessenger.ImageSearchTask;
import tw.ntu.vison.gifformessenger.R;
import tw.ntu.vison.gifformessenger.adapter.ImageGridAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ImageGridAdapter mAdapter;
    EditText mSearchText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // do something with view
        Button button = (Button) view.findViewById(R.id.search_button);
        button.setOnClickListener(new OnSearchListener());
        mSearchText = (EditText) view.findViewById(R.id.search_text);

        // set adapter to gridView
        GridView gridView = (GridView) view.findViewById(R.id.grid_view);

        mAdapter = new ImageGridAdapter(this.getActivity());
        gridView.setAdapter(mAdapter);


        return view;
    }

    private class OnSearchListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String q = mSearchText.getText().toString();

            // reset byte data
            mAdapter.clearImageData();

            String url = new GoogleSearchString().setQuery(q).setFileType("gif").getUrl();
            Log.i("Search String", url);
            if (url == null) {
                return;
            }
            ImageSearchTask task = new ImageSearchTask(new ImageSearchTask.TaskCallback() {
                @Override
                public void onTaskComplete(ArrayList<String> results) {
                    for (int i=0;i<results.size();i++) {
                        String imageUrl = results.get(i);
                        Log.i("IMAGE URL", imageUrl);

                        new ImageDownloadTask(new ImageDownloadTask.TaskCallback() {
                            @Override
                            public void onTaskComplete(Movie movie) {
                                // do adpater
                                mAdapter.appendImageData(movie);
                                Log.i("DownloadTask Complete", "");
                            }
                        }).execute(imageUrl);

                    }
                }
            });
            task.execute(url);
        }
    }

}
