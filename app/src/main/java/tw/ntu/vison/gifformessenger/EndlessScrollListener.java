package tw.ntu.vison.gifformessenger;

import android.util.Log;
import android.widget.AbsListView;

/**
 * Created by Vison on 2015/7/18.
 */
public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

    // the minimum items we have below current scroll position before loading more data
    private int visibleThreshold = 10;
    // the total items in dataset after last load
    private int previousTotalItemCount = 0;
    // true if we still wait for the last set of data load
    private boolean isLoading = false;
    // the offset index in the dataset

    public EndlessScrollListener() {

    }

    @Override
    public void onScroll(AbsListView view,int firstVisibleItem,int visibleItemCount,int totalItemCount) {

        // check if the dataset is loaded
        if (isLoading && totalItemCount > previousTotalItemCount) {
            isLoading = false;
            previousTotalItemCount = totalItemCount;
        }

        // to load
        if (!isLoading && (visibleThreshold > totalItemCount - firstVisibleItem - visibleItemCount)) {
            Log.i("MORE","");
            loadMore();
            isLoading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    public abstract void loadMore();
}
