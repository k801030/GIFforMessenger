package tw.ntu.vison.gifformessenger;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

/**
 * Created by Vison on 2015/7/15.
 */
public class GoogleSearchString {
    private final String API_URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&start=0&rsz=8";
    private String mUrl;
    private String mQuery;
    private String mFileType;
    private String mImageSize;
    public final static int ICON_SIZE = 0;
    public final static int SMALL_SIZE = 2;
    public final static int MEDIUM_SIZE = 3;

    public GoogleSearchString setQuery(String q) {
        mQuery = q.replace(" ","+");
        return this;
    }
    public GoogleSearchString setFileType(String fileType) {
        mFileType = fileType;
        return this;
    }

    public GoogleSearchString setSize(int type) {;
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
        mUrl = API_URL;

        // added some
        mQuery += "+gif";
        if (mFileType!=null) {
            mQuery += "+filetype:" + mFileType;
        }

        // set size
        if (mImageSize!=null) {
            mUrl += "&imgsz=" + mImageSize;
        }

        // encode query
        mUrl += "&q=" + encode(mQuery);
        // sample result string will be
        // http://ajax.googleapis.com/ajax/services/search/images?v=1.0&start=0&rsz=8
        // &q=sunrise+gif+filetype:gif

        return mUrl;
    };
}
