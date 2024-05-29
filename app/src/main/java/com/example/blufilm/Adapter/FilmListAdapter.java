package com.example.blufilm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.blufilm.Activity.detailActivity;
import com.example.blufilm.Api.API;
import com.example.blufilm.Domain.ListFilm;
import com.example.blufilm.R;

import java.util.List;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
    ListFilm items;
   // API item;
    Context context;
    List<API> item;

    public FilmListAdapter(List<API> item) {

        this.item=item;
    }

    @NonNull
    @Override
    public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholderfilm_horizantal,parent,false);
        context=parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


       holder.titleHorizantal.setText(item.get(position).getName());
        holder.rateHorizantal.setText(String.valueOf(item.get(position).getRating()));
        holder.yearHorizantal.setText(String.valueOf(item.get(position).getYear()));
        String geners = String.valueOf(item.get(position).getGenre());
        String dataWithoutBrackets1 = geners.substring(2, geners.length() - 2);
        String[] dataArray1 = dataWithoutBrackets1.split(",");
        for (int i = 0; i < dataArray1.length; i++) {
            dataArray1[i] = dataArray1[i].replaceAll("\"", "").trim();
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        for (String data : dataArray1) {
            stringBuilder1.append(data).append(",");
        }
      holder.genersHorizantal.setText(stringBuilder1.toString());

        Glide.with(holder.itemView.getContext())
                        .load(item.get(position).getThumburl())
                                .into(holder.picHorizantal);


        holder.itemView.setOnClickListener(v -> {
            Intent intent =new Intent(holder.itemView.getContext(), detailActivity.class);

            String name= String.valueOf(item.get(position).getName());
            intent.putExtra("name",name);

            String cover= item.get(position).getThumburl();
            intent.putExtra("cover",cover);

            Double Rate= Double.valueOf(String.valueOf(item.get(position).getRating()));
            intent.putExtra("rate",Rate);

            Integer date=Integer.valueOf(String.valueOf(item.get(position).getYear()));
            intent.putExtra("date",date);

            String desc= item.get(position).getDesc();
            intent.putExtra("desc",desc);

            List<String> actors= item.get(position).getActors();
            intent.putExtra("actors", String.valueOf(actors));

            List<String> director= item.get(position).getDirectors();
            intent.putExtra("directors", String.valueOf(director));

            List<String> genre= item.get(position).getGenre();
            intent.putExtra("genre", String.valueOf(genre));

            String url=item.get(position).getImdburl();
            intent.putExtra("url",url);




            Log.d("TAG", "onCreate:ge"+genre);




            holder.itemView.getContext().startActivity(intent);
        });
        holder.favBtn.setOnClickListener(v -> {

            String name= String.valueOf(item.get(position).getName());
            String cover= item.get(position).getThumburl();
            Double Rate= Double.valueOf(String.valueOf(item.get(position).getRating()));
            Integer date=Integer.valueOf(String.valueOf(item.get(position).getYear()));
            String desc= item.get(position).getDesc();
            List<String> actors= item.get(position).getActors();
            List<String> director= item.get(position).getDirectors();
            List<String> genre= item.get(position).getGenre();
            String url=item.get(position).getImdburl();



        });



    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private API api;
        TextView titleTxt,scoreTxt;
        TextView titleHorizantal, genersHorizantal,rateHorizantal,yearHorizantal;
        ImageView picHorizantal,favBtn;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleHorizantal=itemView.findViewById(R.id.nameMovieTvHolder);
            genersHorizantal =itemView.findViewById(R.id.genrsMovieTvHolder);
            rateHorizantal=itemView.findViewById(R.id.movieRateTVHolder);
            yearHorizantal=itemView.findViewById(R.id.movieDateTvHolder);
            picHorizantal=itemView.findViewById(R.id.picHorizantal);
            favBtn=itemView.findViewById(R.id.favBtn);



        }
    }
}
