package me.navik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerMainActivity extends AppCompatActivity {
    private ArrayList<productCat> movieList = new ArrayList<>();
    //    private ArrayList<productCat> movieList1 = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;
    private ArrayList interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        interests = (ArrayList) this.getIntent().getSerializableExtra("interests");






        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RecyclerAdapter(movieList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                productCat movie = movieList.get(position);
                String str;
//                Toast.makeText(getApplicationContext(), movie.getStalls() + " is selected!", Toast.LENGTH_SHORT).show();
                    if(movie.getStalls().equals("Stall_1"))
                    {
                         str="850_450";

                    }
                    else if(movie.getStalls().equals("Stall_2"))
                    {
                         str="1750_850";
                    }
                    else if (movie.getStalls().equals("Stall_3"))
                    {
                         str="2650_1450";
                    }
                    else if (movie.getStalls().equals("Stall_5"))
                    {
                       str="2150_1850";

                    }
                    else
                    {
                         str="850_1450";
                    }





                Intent intent = new Intent(RecyclerMainActivity.this,Track.class);

                intent.putExtra("destination", str);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        for (int counter = 0; counter < interests.size(); counter++) {
            {String test = (String) interests.get(counter);
             prepareMovieData(test);}
    }}

    /**
     * Prepares sample data to provide data set to adapter
     */
    private void prepareMovieData(String abc) {
        if(abc.equalsIgnoreCase("food")) {
            productCat food = new productCat("Food", "Stall_1", "rated best");
            movieList.add(food);

             food = new productCat("Food", "Stall_3", "rated best");
            movieList.add(food);




        }
        if(abc.equalsIgnoreCase("fashion")) {
            productCat fashion = new productCat("fashion", "Stall_2", "rated best");
            movieList.add(fashion);



            // notify adapter about data set changes
            // so that it will render the list with new data

        }
        if(abc.equalsIgnoreCase("tech")) {
            productCat fashion = new productCat("tech", "Stall_4", "rated best");
            movieList.add(fashion);



        }
        if(abc.equalsIgnoreCase("homeapp")) {
            productCat fashion = new productCat("homeapp", "Stall_5", "rated best");
            movieList.add(fashion);


        }
        mAdapter.notifyDataSetChanged();
    }

}
