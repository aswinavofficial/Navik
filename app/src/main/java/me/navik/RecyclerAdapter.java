package me.navik;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<productCat> moviesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product,shop,discription;

        public MyViewHolder(View view) {
            super(view);
            product = (TextView) view.findViewById(R.id.product);
            shop = (TextView) view.findViewById(R.id.shop);
            discription = (TextView) view.findViewById(R.id.dis);
        }
    }


    public RecyclerAdapter(List<productCat> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        productCat movie = moviesList.get(position);
        holder.product.setText(movie.getProduct());
        holder.shop.setText(movie.getStalls());
        holder.discription.setText(movie.getDiscription());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
