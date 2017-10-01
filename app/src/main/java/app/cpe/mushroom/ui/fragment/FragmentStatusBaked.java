package app.cpe.mushroom.ui.fragment;

import android.view.View;

import app.cpe.mushroom.R;
import app.cpe.mushroom.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DEV on 20/9/2560.
 */

public class FragmentStatusBaked extends BaseFragment {

    public static FragmentStatusBaked newInstance() {
        return new FragmentStatusBaked();
    }

    @Override
    public int setupLayout() {
        return R.layout.fragment_status_baked;
    }

    @Override
    public Unbinder bindView(View view) {
        return ButterKnife.bind(this, view);
    }


}
