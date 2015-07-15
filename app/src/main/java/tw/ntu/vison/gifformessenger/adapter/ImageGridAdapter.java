package tw.ntu.vison.gifformessenger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;

import tw.ntu.vison.gifformessenger.R;

/**
 * Created by Vison on 2015/7/15.
 */
public class ImageGridAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> mImageUrls;

    public ImageGridAdapter(Context context) {
        mContext = context;
        mImageUrls = new ArrayList<String>();
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        mImageUrls = imageUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImageUrls.size();
    }

    @Override
    public Object getItem(int i) {
        return mImageUrls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) { // if view is not recycled, initialize it
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(90,90));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }
        // set image resource
        UrlImageViewHelper.setUrlDrawable(imageView, mImageUrls.get(i));
        return imageView;
    }
}
