package app.cpe.mushroom.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DEV on 20/9/2560.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = setupLayout();
        if(layoutResId == 0) {
            new RuntimeException("Null layout");
        }
        setContentView(layoutResId);
        setUpView();
        if(savedInstanceState == null){
            attachView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public abstract int setupLayout();

    public abstract void setUpView();

    public abstract void attachView();

    public abstract void detachView();
}
