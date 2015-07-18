package tw.ntu.vison.gifformessenger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Vison on 2015/7/15.
 */
public class GoogleSearchAPI {
    private final String API_URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0";
    private final int PAGE_SIZE = 8;
    private int startItem = 0;
    private String mUrl;
    private String mQuery;
    private String mFileType;
    private String mImageSize;

    public final static int ICON_SIZE = 0;
    public final static int SMALL_SIZE = 1;
    public final static int MEDIUM_SIZE = 2;

    public GoogleSearchAPI setQuery(String q) {
        mQuery = q.replace(" ","+");
        return this;
    }
    public GoogleSearchAPI setFileType(String fileType) {
        mFileType = fileType;
        return this;
    }

    public GoogleSearchAPI setSize(int type) {;
        switch (type) {
            case ICON_SIZE:
                mImageSize = "icon";
                break;
            default:
                break;
        };
        return this;
    }
    private String encode(String s) {
        try {
            s = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String getUrl() {
        if (mQuery == null) {
            return null;
        }

        // make search perfectly
        mQuery += "+gif";

        // make url and encode query
        mUrl = API_URL;
        mUrl += "&start="+ startItem + "&rsz=" + PAGE_SIZE ;
        mUrl += "&q=" + encode(mQuery) + "&as_filetype=" + mFileType + "&imgsz=" + mImageSize;;
        // sample result string will be
        // http://ajax.googleapis.com/ajax/services/search/images?v=1.0&start=0&rsz=8
        // &q=sunrise+gif+filetype:gif

        return mUrl;
    }

    public String getNextPageUrl() {
        startItem += PAGE_SIZE;
        return getUrl();
    }

    public void resetPage() {
        startItem = 0;
    }
}
