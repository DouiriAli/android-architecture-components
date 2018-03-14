package dialtechnologies.architecture.component.dial.dialcomponentachitecture.ui.user;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.R;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public class ListFragment extends Fragment {

    private static final String TAG = ListFragment.class.getSimpleName();

    @BindView(R.id.reyclerview)
    RecyclerView mReyclerview;

    private UserAdapter mAdapter;
    private Unbinder mUnbinder;

    public static ListFragment newInstance() {

        Bundle args = new Bundle();

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.list_fragment, container, false);

        mUnbinder = ButterKnife.bind(this, mView);

        initViews();

        return mView;
    }

    private void initViews(){

        mAdapter = new UserAdapter(getActivity(), new ArrayList<>());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mReyclerview.setLayoutManager(mLayoutManager);
        mReyclerview.setItemAnimator(new DefaultItemAnimator());
        mReyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final UserViewModel mViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);

        subscribeUi(mViewModel);

    }

    private void subscribeUi(UserViewModel mViewModel){

        mViewModel.getAllUsers().observe(this,
                users -> {
                        if(users != null && !users.isEmpty()){

                            mAdapter.setItems(users);
                        }
                } );

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }
}
