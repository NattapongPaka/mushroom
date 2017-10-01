package app.cpe.mushroom.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;

import app.cpe.mushroom.R;
import app.cpe.mushroom.base.BaseFragment;
import app.cpe.mushroom.ui.activity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DEV on 1/10/2560.
 */

public class FragmentHistory extends BaseFragment {

    public static final String TAG = FragmentHistory.class.getSimpleName();

    @BindView(R.id.graph)
    GraphView graph;
    @BindView(R.id.spinner)
    AppCompatSpinner spinner;
    @BindView(R.id.edtStartDate)
    EditText edtStartDate;
    @BindView(R.id.edtEndDate)
    EditText edtEndDate;
    @BindView(R.id.btnOK)
    Button btnOK;
    Unbinder unbinder;

    MainActivity mainActivity;

    public static FragmentHistory newInstance() {
        return new FragmentHistory();
    }

    @Override
    public int setupLayout() {
        return R.layout.fragment_history;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @Override
    public Unbinder bindView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setUpToolBar(TAG, true);
    }
}
