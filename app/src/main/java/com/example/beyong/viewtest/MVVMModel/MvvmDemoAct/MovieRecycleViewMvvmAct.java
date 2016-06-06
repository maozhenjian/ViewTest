package com.example.beyong.viewtest.MVVMModel.MvvmDemoAct;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.beyong.viewtest.MVVMModel.Movie;
import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.databinding.ActivityMovieRecycleViewMvvmBinding;
import com.example.beyong.viewtest.databinding.ItemMovieBinding;

import java.util.ArrayList;
import java.util.List;

public class MovieRecycleViewMvvmAct extends AppCompatActivity {
    private ActivityMovieRecycleViewMvvmBinding binding;
    private  List<Movie> mMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_recycle_view_mvvm);
        Movie a =new Movie();
        a.setTitle("aaa");
        Movie b =new Movie();
        b.setTitle("bbb");
        Movie c =new Movie();
        c.setTitle("ccc");
        mMovies=new ArrayList<>();
        mMovies.add(a);
        mMovies.add(b);
        mMovies.add(c);
        binding.RV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.RV.setAdapter(new RVAdapter(mMovies));
    }


    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BindingHolder>{
        private List<Movie> mMovies;

        public RVAdapter(List<Movie> mMovies) {
            this.mMovies = mMovies;
        }

        @Override
        public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
            BindingHolder holder = new BindingHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(BindingHolder holder, int position) {
//            Movie movie = mMovies.get(position);
//            holder.binding.titles.setText(movie.title);

        }

        @Override
        public int getItemCount() {
            return mMovies.size()+10;
        }

        public class BindingHolder extends RecyclerView.ViewHolder {
            private ItemMovieBinding binding;
            public BindingHolder(View v) {
                super(v);
//                binding = DataBindingUtil.bind(itemView);
            }
        }

    }


}
