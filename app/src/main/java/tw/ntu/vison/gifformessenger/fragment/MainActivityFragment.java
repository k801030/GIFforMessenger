package tw.ntu.vison.gifformessenger.fragment;

import android.graphics.Movie;
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

import tw.ntu.vison.gifformessenger.GoogleSearchAPI;
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
    GoogleSearchAPI googleSearchAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // do something with view
        Button buttonSearch = (Button) view.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new OnSearchListener());
        Button buttonLoadMore = (Button) view.findViewById(R.id.button_load_more);
        buttonLoadMore.setOnClickListener(new OnLoadMoreListener());
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
            // reset byte data
            mAdapter.clearImageData();
            String q = mSearchText.getText().toString();
            googleSearchAPI = new GoogleSearchAPI();

            String url = googleSearchAPI.setQuery(q).setFileType("gif").getUrl();
            if (url == null) { // there is no string in the search bar
                return;
            }
            ImageSearchTask task = new ImageSearchTask(new CustomTaskCallback());
            task.execute(url);
        }
    }

    private class OnLoadMoreListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (googleSearchAPI != null) {
                String url = googleSearchAPI.getNextPageUrl();
                if (url == null) {
                    return;
                }
                ImageSearchTask task = new ImageSearchTask(new CustomTaskCallback());
                task.execute(url);
            }
        }
    }

    private class CustomTaskCallback implements ImageSearchTask.TaskCallback {
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
    }
}
