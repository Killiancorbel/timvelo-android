package com.timvelo.app.ui.classement;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timvelo.app.R;
import com.timvelo.app.domain.models.Classement;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 15/06/2017.
 */

public class ClassementAdapter extends RecyclerView.Adapter<ClassementAdapter.ClassementViewHolder> {

    private final ArrayList<Classement> classement = new ArrayList<>();
    private final ClassementViewHolder.OnRaceSelectedListener listener;

    public ClassementAdapter(ClassementViewHolder.OnRaceSelectedListener listener) {
        this.listener = listener;
    }

    void addClassement(ArrayList<Classement> classements) {
        classement.addAll(classements);
    }

    void clear() {
        classement.clear();
    }

    @Override
    public ClassementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_classement, parent, false);
        return new ClassementViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ClassementViewHolder holder, int position) {
        Classement c = classement.get(position);
        Log.d("BINDING", "position " + position + ", rider : " + c.getRider().getNickname());
        holder.position.setText(String.format(Locale.getDefault(), "%d", c.getPosition()));
        holder.rider.setText(String.format(Locale.getDefault(), "%s %s", c.getRider().getForename(), c.getRider().getName()));
        holder.team.setText(c.getRider().getTeam().getName());
    }

    @Override
    public int getItemCount() {
        return classement.size();
    }

    static class ClassementViewHolder extends RecyclerView.ViewHolder {
        private final OnRaceSelectedListener listener;
        @BindView(R.id.classement_position) TextView position;
        @BindView(R.id.classement_rider) TextView rider;
        @BindView(R.id.classement_team) TextView team;

        ClassementViewHolder(View itemView, OnRaceSelectedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            itemView.setOnClickListener(v -> listener.onRaceSelected(this));
        }

        interface OnRaceSelectedListener {
            void onRaceSelected(ClassementViewHolder holder);
        }
    }
}
