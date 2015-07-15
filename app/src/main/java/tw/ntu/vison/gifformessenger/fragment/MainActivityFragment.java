package tw.ntu.vison.gifformessenger.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import tw.ntu.vison.gifformessenger.GoogleSearchString;
import tw.ntu.vison.gifformessenger.ImageSearchTask;
import tw.ntu.vison.gifformessenger.R;
import tw.ntu.vison.gifformessenger.adapter.ImageGridAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ImageGridAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // do something with view
        Button button = (Button) view.findViewById(R.id.search_button);
        button.setOnClickListener(new OnSearchListener());

        // set adapter to gridView
        GridView gridView = (GridView) view.findViewById(R.id.grid_view);

        mAdapter = new ImageGridAdapter(this.getActivity());
        gridView.setAdapter(mAdapter);

        /* TEST */
        /*
        ArrayList<String> imageUrls = new ArrayList<String>();
        String gifUrl = "https://33.media.tumblr.com/b2e57e2aa59015eda08936d82b84d0a4/tumblr_mz7fs6lvYn1six1cfo1_500.gif";
        imageUrls.add(gifUrl);
        imageUrls.add(gifUrl);
        imageUrls.add(gifUrl);
        imageUrls.add(gifUrl);
        imageUrls.add(gifUrl);
        imageUrls.add(gifUrl);
        mAdapter.setImageUrls(imageUrls);
        */

        return view;
    }

    private class OnSearchListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String q = "sunrise";
            String url = new GoogleSearchString().setQuery(q).setFileType("gif").getUrl();
            ImageSearchTask task = new ImageSearchTask(new ImageSearchTask.TaskCallback() {
                @Override
                public void onTaskComplete(ArrayList<String> results) {
                    for (int i=0;i<results.size();i++) {
                        Log.i("IMAGE URL", results.get(i));
                    }
                    mAdapter.setImageUrls(results);
                }
            });
            task.execute(url);
        }
    }

}
