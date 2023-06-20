package com.example.worldexplorer;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FlagsAdapter extends RecyclerView.Adapter<FlagsAdapter.ViewHolder> {
    private String[] flags;
    private int[] imageIds;

    // Constructor
    public FlagsAdapter(String[] captions, int[] imageIds) {
        this.flags = captions;
        this.imageIds = imageIds;
    }

    // Create new views, creates the cardview in runtime.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate: converts it from xml to java object.
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        // builds it as ui component
        return new ViewHolder(v);
    }

    // Data is determined here of the cardview.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imageView);
        Drawable dr = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);

        imageView.setImageDrawable(dr);

        TextView txt = (TextView) cardView.findViewById(R.id.textView2);
        txt.setText(flags[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }

    // helps in lazy loading
    @Override
    public int getItemCount() {
        return flags.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

    }
}
