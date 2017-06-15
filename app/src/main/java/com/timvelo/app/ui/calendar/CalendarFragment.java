package com.timvelo.app.ui.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.timvelo.app.R;
import com.timvelo.app.domain.models.Race;
import com.timvelo.app.ui.base.BaseLceFragment;
import com.timvelo.app.ui.raceList.RaceAdapter;
import com.timvelo.app.widget.EndlessScrollListener;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import timber.log.Timber;

/**
 * Created by admin on 15/06/2017.
 */

public class CalendarFragment extends BaseLceFragment<RecyclerView, ArrayList<Race>, CalendarView, CalendarPresenter>
        implements CalendarView, RaceAdapter.RaceViewHolder.OnRaceSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.contentView) RecyclerView recyclerView;
    @BindView(R.id.race_empty) TextView empty;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout refresh;
    private RaceAdapter adapter;
    private CalendarFragment.TimveloScrollListener scrollListener;

    public static CalendarFragment newInstance() {

        Bundle args = new Bundle();

        CalendarFragment fragment = new CalendarFragment();
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
        refresh.setOnRefreshListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RaceAdapter(this, getActivity()));
        scrollListener = new TimveloScrollListener(layoutManager);
        recyclerView.addOnScrollListener(scrollListener);

        if (null == adapter) {
            adapter = new RaceAdapter(this, getActivity());
            recyclerView.setAdapter(adapter);
            loadData(false);
        } else {
            recyclerView.setAdapter(adapter);
            showContent();
        }
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        scrollListener.resetState();
        loadData(true);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_calendar;
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
    public CalendarPresenter createPresenter() {
        return new CalendarPresenter();
    }

    @Override
    public void setData(ArrayList<Race> data) {
        refresh.setRefreshing(false);
        adapter.addRace(data);
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() == 0) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadRaces(1, false);
    }

    @Override
    public void onRaceSelected(RaceAdapter.RaceViewHolder holder) {
        // TODO - on race selected
    }

    private class TimveloScrollListener extends EndlessScrollListener {

        TimveloScrollListener(LinearLayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
            Timber.d("Loading page " + page);
            getPresenter().loadNextPage(page);
        }
    }
}
