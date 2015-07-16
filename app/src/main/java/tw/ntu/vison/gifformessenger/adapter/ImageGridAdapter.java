package tw.ntu.vison.gifformessenger.adapter;

import android.content.Context;
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

/**
 * Created by Vison on 2015/7/15.
 */
public class ImageGridAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<byte[]> mBigmapDatas;

    public ImageGridAdapter(Context context) {
        mContext = context;
        mBigmapDatas = new ArrayList<byte[]>();
    }

    public void appendImageData(byte[] bigmapData) {
        mBigmapDatas.add(bigmapData);
        notifyDataSetChanged();
    }

    public void clearImageData() {
        mBigmapDatas = new ArrayList<byte[]>();
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
        GifImageView gifImageView;

        if (view == null) { // if view is not recycled, initialize it
            gifImageView = new GifImageView(mContext);
        } else {
            gifImageView = (GifImageView) view;
        }

        GridView gridView = (GridView) parentView;
        int column = gridView.getNumColumns();
        int fullWidth = gridView.getWidth();
        int padding = 8;
        int width = calculateWidthForImageView(column, fullWidth, padding);
        gifImageView.setLayoutParams(new GridView.LayoutParams(width, width));
        gifImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        gifImageView.setPadding(padding, padding, padding, padding);

        // set image resource
        gifImageView.setBytes(mBigmapDatas.get(i));
        gifImageView.startAnimation();


        return gifImageView;
    }

    private Integer calculateWidthForImageView(int column, int fullWidth, int padding) {
        int width = fullWidth/column - 2 * padding;
        return width;
    }
}
