package com.timvelo.app.ui.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.timvelo.app.R;
import com.timvelo.app.domain.models.Result;
import com.timvelo.app.ui.base.BaseLceFragment;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by admin on 13/06/2017.
 */

public class ResultFragment extends BaseLceFragment<NestedScrollView, ArrayList<Result>, ResultView, ResultPresenter>
        implements ResultView{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.result_name) TextView raceName;
    @BindView(R.id.coordinator) CoordinatorLayout coordinator;
    private int id;
    private TextView[] positions = new TextView[10];
    private TextView[] names = new TextView[10];
    private TextView[] teams = new TextView[10];

    public static ResultFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("ID_KEY", id);

        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_result;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public ResultPresenter createPresenter() {
        return new ResultPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("ID_KEY");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.race_toolbar_title);
        setHasOptionsMenu(true);
        initPositions();
        initNames();
        initTeams();
        loadData(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(ArrayList<Result> data) {
        raceName.setText(data.get(0).getRace().getName());
        for (int i = 0; i < 10; i++) {
            positions[i].setText(String.format(Locale.getDefault(), "%d", data.get(i).getPosition()));
            names[i].setText(String.format(Locale.getDefault(), "%s %s",
                    data.get(i).getRider().getForename(), data.get(i).getRider().getName()));
            teams[i].setText(data.get(i).getRider().getTeam().getNickname());
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadResult(id, pullToRefresh);
    }

    private void initPositions() {
        positions[0] = (TextView) coordinator.findViewById(R.id.result1_position);
        positions[1] = (TextView) coordinator.findViewById(R.id.result2_position);
        positions[2] = (TextView) coordinator.findViewById(R.id.result3_position);
        positions[3] = (TextView) coordinator.findViewById(R.id.result4_position);
        positions[4] = (TextView) coordinator.findViewById(R.id.result5_position);
        positions[5] = (TextView) coordinator.findViewById(R.id.result6_position);
        positions[6] = (TextView) coordinator.findViewById(R.id.result7_position);
        positions[7] = (TextView) coordinator.findViewById(R.id.result8_position);
        positions[8] = (TextView) coordinator.findViewById(R.id.result9_position);
        positions[9] = (TextView) coordinator.findViewById(R.id.result10_position);
    }

    private void initNames() {
        names[0] = (TextView) coordinator.findViewById(R.id.result1_rider);
        names[1] = (TextView) coordinator.findViewById(R.id.result2_rider);
        names[2] = (TextView) coordinator.findViewById(R.id.result3_rider);
        names[3] = (TextView) coordinator.findViewById(R.id.result4_rider);
        names[4] = (TextView) coordinator.findViewById(R.id.result5_rider);
        names[5] = (TextView) coordinator.findViewById(R.id.result6_rider);
        names[6] = (TextView) coordinator.findViewById(R.id.result7_rider);
        names[7] = (TextView) coordinator.findViewById(R.id.result8_rider);
        names[8] = (TextView) coordinator.findViewById(R.id.result9_rider);
        names[9] = (TextView) coordinator.findViewById(R.id.result10_rider);
    }

    private void initTeams() {
        teams[0] = (TextView) coordinator.findViewById(R.id.result1_team);
        teams[1] = (TextView) coordinator.findViewById(R.id.result2_team);
        teams[2] = (TextView) coordinator.findViewById(R.id.result3_team);
        teams[3] = (TextView) coordinator.findViewById(R.id.result4_team);
        teams[4] = (TextView) coordinator.findViewById(R.id.result5_team);
        teams[5] = (TextView) coordinator.findViewById(R.id.result6_team);
        teams[6] = (TextView) coordinator.findViewById(R.id.result7_team);
        teams[7] = (TextView) coordinator.findViewById(R.id.result8_team);
        teams[8] = (TextView) coordinator.findViewById(R.id.result9_team);
        teams[9] = (TextView) coordinator.findViewById(R.id.result10_team);
    }
}
