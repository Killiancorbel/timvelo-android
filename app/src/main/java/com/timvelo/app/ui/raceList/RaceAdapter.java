package com.timvelo.app.ui.raceList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.timvelo.app.R;
import com.timvelo.app.domain.models.Race;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 08/06/2017.
 */

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.RaceViewHolder> {

    private final ArrayList<Race> races = new ArrayList<>();
    private final RaceViewHolder.OnRaceSelectedListener listener;
    private final Context mcontext;
    private final float scale;

    public RaceAdapter(RaceViewHolder.OnRaceSelectedListener listener, Context context) {
        this.listener = listener;
        this.mcontext = context;
        scale = context.getResources().getDisplayMetrics().density;
    }

    public void addRace(ArrayList<Race> races) { this.races.addAll(races); }

    @Override
    public RaceAdapter.RaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_race, parent, false);
        return new RaceViewHolder(v, listener);
    }

    public void clear() { races.clear(); }

    @Override
    public void onBindViewHolder(RaceAdapter.RaceViewHolder holder, int position) {
        Race race = races.get(position);
        Glide.with(mcontext).load(race.getFlag().getSrc()).override((int) (32 * scale + 0.5f), (int) (32 * scale + 0.5f)).centerCrop().into(holder.flag);
        Glide.with(mcontext).load(race.getProfile().getSrc()).override((int) (32 * scale + 0.5f), (int) (32 * scale + 0.5f)).centerCrop().into(holder.profile);
        holder.title.setText(race.getName());
        holder.classification.setText(race.getClassification().getIdent());
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+02:00'", Locale.getDefault());
        try {
            Date date = dateParser.parse(race.getDate());
            holder.date.setText(String.format(Locale.getDefault(), "%02d/%02d", date.getDate(), date.getMonth() + 1));
        } catch (ParseException e) {
            e.printStackTrace();
            holder.date.setText(race.getDate());
        }
        holder.id = race.getId();
    }

    @Override
    public int getItemCount() {
        return races.size();
    }

    public static class RaceViewHolder extends RecyclerView.ViewHolder {
        private final OnRaceSelectedListener listener;
        @BindView(R.id.race_flag) ImageView flag;
        @BindView(R.id.race_profile) ImageView profile;
        @BindView(R.id.race_title) TextView title;
        @BindView(R.id.race_classification) TextView classification;
        @BindView(R.id.race_date) TextView date;
        Integer id;

        RaceViewHolder(View itemView, OnRaceSelectedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            itemView.setOnClickListener(v -> listener.onRaceSelected(this));
        }

        public interface OnRaceSelectedListener {
            void onRaceSelected(RaceViewHolder holder);
        }
    }
}
