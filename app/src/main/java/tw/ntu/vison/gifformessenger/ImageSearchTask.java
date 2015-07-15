package tw.ntu.vison.gifformessenger;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Vison on 2015/7/15.
 */
public class ImageSearchTask extends AsyncTask<String, Integer, ArrayList<String>> {
    public interface TaskCallback{
        public void onTaskComplete(ArrayList<String> results);
    };
    TaskCallback callback;

    public ImageSearchTask(TaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected ArrayList<String> doInBackground(String... urls) {
        // HttpRequest object is synchronous
        HttpRequest request = HttpRequest.get(urls[0]);
        if (request.ok()) {
            Gson gson = new Gson();
            String string = request.body();
            try {
                JSONObject jsonObject = new JSONObject(string);
                JSONArray array = jsonObject.getJSONObject("responseData").getJSONArray("results");
                ArrayList<String> results = new ArrayList<String>();
                for (int i=0;i<array.length();i++) {
                    String s = array.getJSONObject(i).getString("unescapedUrl");
                    results.add(s);
                }
                return results;

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<String> results) {
        super.onPostExecute(results);
        callback.onTaskComplete(results);
    }
}


