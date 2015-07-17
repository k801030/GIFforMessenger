package tw.ntu.vison.gifformessenger.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vison on 2015/7/17.
 */
public class GifView extends View {
    private Movie mMovie;
    private long mMovieStart = 0;

    public GifView(Context context) {
        super(context);
    }

    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        long now = android.os.SystemClock.uptimeMillis();
        if (mMovieStart == 0) {
            mMovieStart = now;
        }
        if (mMovie != null) {
            int duration = mMovie.duration();
            if (duration ==0) {
                duration = 1000;
            }
            int relTime = (int)((now - mMovieStart) % duration);
            mMovie.setTime(relTime);
            mMovie.draw(canvas, getWidth() - mMovie.width(),
                    getHeight() - mMovie.height());
            invalidate();
        }
    }
}
