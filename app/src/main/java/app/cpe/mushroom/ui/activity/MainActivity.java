package app.cpe.mushroom.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import app.cpe.mushroom.ui.fragment.FragmentMain;
import app.cpe.mushroom.R;
import app.cpe.mushroom.base.BaseActivity;
import app.cpe.mushroom.utils.LogUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int setupLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Unbinder setUpView() {
        Unbinder unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        return unbinder;
    }

    @Override
    public void attachView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentPanel, FragmentMain.newInstance(), FragmentMain.TAG)
                .commit();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            switchFragment(FragmentMain.newInstance(),FragmentMain.TAG);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpToolBar(String title, boolean homeBtn) {
        ActionBar actionBar;
        if ((actionBar = getSupportActionBar()) != null) {
            actionBar.setTitle(title);
            actionBar.setHomeButtonEnabled(homeBtn);
            actionBar.setDisplayShowHomeEnabled(homeBtn);
            actionBar.setDisplayHomeAsUpEnabled(homeBtn);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void switchFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentPanel, fragment, tag)
                .commit();
    }


}
