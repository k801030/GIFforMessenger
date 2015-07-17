package tw.ntu.vison.gifformessenger.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.felipecsl.gifimageview.library.GifImageView;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import tw.ntu.vison.gifformessenger.R;
import tw.ntu.vison.gifformessenger.view.GifView;

/**
 * Created by Vison on 2015/7/15.
 */
public class ImageGridAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Movie> mBigmapDatas;

    public ImageGridAdapter(Context context) {
        mContext = context;
        mBigmapDatas = new ArrayList<Movie>();
    }

    public void appendImageData(Movie bigmapData) {
        mBigmapDatas.add(bigmapData);
        notifyDataSetChanged();
    }

    public void clearImageData() {
        mBigmapDatas.clear();
        // mBigmapDatas = new ArrayList<byte[]>();
    }

    @Override
    public int getCount() {
        return mBigmapDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mBigmapDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parentView) {
        GifView gifView;

        if (view == null) { // if view is not recycled, initialize it
            gifView = new GifView(mContext);
        } else {
            gifView = (GifView) view;
        }

        GridView gridView = (GridView) parentView;
        int column = gridView.getNumColumns();
        int fullWidth = gridView.getWidth();
        int padding = 8;
        int width = calculateWidthForImageView(column, fullWidth, padding);
        gifView.setLayoutParams(new GridView.LayoutParams(width, width));
        // gifView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        gifView.setPadding(padding, padding, padding, padding);

        // set image resource
        // gifImageView.setBytes(mBigmapDatas.get(i));

        // turn the hardware acceleration off
        gifView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        gifView.setMovie(mBigmapDatas.get(i));


        return gifView;
    }

    private Integer calculateWidthForImageView(int column, int fullWidth, int padding) {
        int width = fullWidth/column - 2 * padding;
        return width;
    }
}
