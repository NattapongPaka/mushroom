package app.cpe.mushroom.ui.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.cpe.mushroom.R;
import app.cpe.mushroom.base.BaseFragment;
import app.cpe.mushroom.ui.activity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DEV on 1/10/2560.
 */

public class FragmentStatus extends BaseFragment {

    public static final String TAG = FragmentStatus.class.getSimpleName();


    MainActivity mainActivity;
    FragmentStatePagerAdapter fragmentStatePagerAdapter;

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    public static FragmentStatus newInstance() {
        return new FragmentStatus();
    }

    @Override
    public int setupLayout() {
        return R.layout.fragment_status;
    }

    @Override
    public Unbinder bindView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setUpToolBar(TAG, true);
        setupView();
    }

    private void setupView() {
        tab.setupWithViewPager(viewPager);
        String[] titles = new String[]{getString(R.string.tabBaked),getString(R.string.tabPlant)};
        fragmentStatePagerAdapter = new FragmentPagerAdapter(getChildFragmentManager(), titles);
        viewPager.setAdapter(fragmentStatePagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
    }
}
