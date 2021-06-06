package ru.mirea.fedorova.mireaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<HistoryElement> historyElements;

    RecyclerViewAdapter(Context context, List<HistoryElement> historyElements) {
        this.historyElements = historyElements;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryElement historyElement = historyElements.get(position);
        holder.headingView.setText(historyElement.getHeading());
        holder.dateTimeView.setText(historyElement.getDateTime());
        holder.textView.setText(historyElement.getText());
    }

    @Override
    public int getItemCount() {
        return historyElements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView headingView, dateTimeView, textView;
        ViewHolder(View view){
            super(view);
            headingView = (TextView) view.findViewById(R.id.heading);
            dateTimeView = (TextView) view.findViewById(R.id.dateTime);
            textView = (TextView) view.findViewById(R.id.text);
        }
    }
}
