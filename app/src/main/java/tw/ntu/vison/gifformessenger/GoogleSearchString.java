package tw.ntu.vison.gifformessenger;

/**
 * Created by Vison on 2015/7/15.
 */
public class GoogleSearchString {
    private final String API_URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&start=0&rsz=8";
    private String mUrl;
    private String mQuery;
    private String mFileType;

    public GoogleSearchString setQuery(String q) {
        mQuery = q;
        return this;
    }
    public GoogleSearchString setFileType(String fileType) {
        mFileType = fileType;
        return this;
    }

    public String getUrl() {
        mUrl = API_URL;
        if (mQuery!=null) {
            mUrl+= "&q=" + mQuery;
        }
        if (mFileType!=null) {
            mUrl += "+filetype:" + mFileType;
        }

        return mUrl;
    };
}
