package ru.mirea.fedorova.mireaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterData extends RecyclerView.Adapter<RecyclerViewAdapterData.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<DatabaseFragment.Language> LanguagesList;

    interface OnLanguageClickListener{
        void onLanguageClick(DatabaseFragment.Language language, int position);
    }

    private final OnLanguageClickListener onClickListener;

    RecyclerViewAdapterData(Context context, List<DatabaseFragment.Language> LanguagesList,
                            OnLanguageClickListener onClickListener) {
        this.LanguagesList = LanguagesList;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_for_db, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DatabaseFragment.Language language = LanguagesList.get(position);
        holder.textViewName.setText(language.getName());
        holder.textViewYear.setText("Год выхода: " + language.getYear());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onLanguageClick(language, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return LanguagesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewName, textViewYear;
        ViewHolder(View view){
            super(view);
            textViewName = (TextView) view.findViewById(R.id.textViewName);
            textViewYear = (TextView) view.findViewById(R.id.textViewYear);
        }
    }

}
