package com.timvelo.app.ui.classement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.timvelo.app.R;
import com.timvelo.app.domain.models.Classement;
import com.timvelo.app.ui.base.BaseLceFragment;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by admin on 15/06/2017.
 */

public class ClassementFragment extends BaseLceFragment<RecyclerView, ArrayList<Classement>, ClassementView, ClassementPresenter>
        implements ClassementView, ClassementAdapter.ClassementViewHolder.OnRaceSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.contentView) RecyclerView recyclerView;
    @BindView(R.id.classement_empty) TextView empty;
    private ClassementAdapter adapter;

    public static ClassementFragment newInstance() {

        Bundle args = new Bundle();

        ClassementFragment fragment = new ClassementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.race_toolbar_title);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ClassementAdapter(this));

        if (null == adapter) {
            adapter = new ClassementAdapter(this);
            recyclerView.setAdapter(adapter);
            loadData(false);
        } else {
            recyclerView.setAdapter(adapter);
            showContent();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_classement;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();
            try {
                JSONObject obj = new JSONObject(body.string());
                return obj.getString("message");
            } catch (Exception exc) {
                return e.getMessage();
            }
        }
        return e.getMessage();
    }

    @Override
    public ClassementPresenter createPresenter() {
        return new ClassementPresenter();
    }

    @Override
    public void setData(ArrayList<Classement> data) {
        adapter.addClassement(data);
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() == 0) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadClassement(pullToRefresh);
    }

    @Override
    public void onRaceSelected(ClassementAdapter.ClassementViewHolder holder) {
        // TODO - On classement selected
    }
}
