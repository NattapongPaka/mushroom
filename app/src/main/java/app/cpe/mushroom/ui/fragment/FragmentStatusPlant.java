package app.cpe.mushroom.ui.fragment;

import android.view.View;

import app.cpe.mushroom.R;
import app.cpe.mushroom.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DEV on 1/10/2560.
 */

public class FragmentStatusPlant extends BaseFragment {

    public static FragmentStatusPlant newInstance() {
        return new FragmentStatusPlant();
    }

    @Override
    public int setupLayout() {
        return R.layout.fragment_status_plant;
    }

    @Override
    public Unbinder bindView(View view) {
        return ButterKnife.bind(this, view);
    }
}
