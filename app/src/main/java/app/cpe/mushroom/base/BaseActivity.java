package app.cpe.mushroom.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by DEV on 20/9/2560.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = setupLayout();
        if(layoutResId == 0) {
            new RuntimeException("Null layout");
        }
        setContentView(layoutResId);
        unbinder = setUpView();
        if(savedInstanceState == null){
            attachView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
    }

    public abstract int setupLayout();

    public abstract Unbinder setUpView();

    public abstract void attachView();

    public abstract void detachView();
}
