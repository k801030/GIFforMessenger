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
import tw.ntu.vison.gifformessenger.EndlessScrollListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ImageGridAdapter mAdapter;
    GridView mGridView;
    EditText mSearchText;
    GoogleSearchAPI mGoogleSearchAPI = new GoogleSearchAPI();
    ArrayList<ImageDownloadTask> mDownloadTasks = new ArrayList<ImageDownloadTask>();
    EndlessScrollListener mEndlessScrollListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // do something with view
        Button buttonSearch = (Button) view.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new OnSearchListener());
        mSearchText = (EditText) view.findViewById(R.id.search_text);

        // set adapter to gridView
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mAdapter = new ImageGridAdapter(this.getActivity());
        mGridView.setAdapter(mAdapter);
        mEndlessScrollListener = new EndlessScrollListener() {
            @Override
            public void loadMore() {
                loadMoreDataFromGoogleAPI();
            }
        };
        mGridView.setOnScrollListener(mEndlessScrollListener);

        return view;
    }

    private void loadMoreDataFromGoogleAPI() {
        if (mGoogleSearchAPI != null) {
            String url = mGoogleSearchAPI.getNextPageUrl();
            if (url == null) {
                return;
            }
            ImageSearchTask task = new ImageSearchTask(new CustomTaskCallback());
            task.execute(url);
        }
    }

    private class OnSearchListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // reset byte data
            mAdapter.clearImageData();
            // cancel current download tasks
            for (int i=0;i<mDownloadTasks.size();i++) {
                mDownloadTasks.get(i).cancel(true);
            }
            // reset load status
            mEndlessScrollListener.reset();

            String q = mSearchText.getText().toString();

            mGoogleSearchAPI.resetPage();
            String url = mGoogleSearchAPI.setQuery(q).setFileType("gif").getUrl();
            if (url == null) { // there is no string in the search bar
                return;
            }
            ImageSearchTask task = new ImageSearchTask(new CustomTaskCallback());
            task.execute(url);
        }
    }

    private class CustomTaskCallback implements ImageSearchTask.TaskCallback {
        @Override
        public void onTaskComplete(ArrayList<String> results) {
            for (int i=0;i<results.size();i++) {
                String imageUrl = results.get(i);
                Log.i("IMAGE URL", imageUrl);

                ImageDownloadTask task = new ImageDownloadTask(new ImageDownloadTask.TaskCallback() {
                    @Override
                    public void onTaskComplete(Movie movie) {
                        // notify dataset changed or re-assign adapter here
                        if (movie == null) { // can't get data
                            return;
                        }
                        mAdapter.appendImageData(movie);

                    }
                });
                task.execute(imageUrl);
                mDownloadTasks.add(task);
            }
        }
    }
}
