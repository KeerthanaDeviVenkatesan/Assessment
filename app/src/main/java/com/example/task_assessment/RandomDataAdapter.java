package com.example.task_assessment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task_assessment.model.CategoriesItem;

import java.util.List;

public class RandomDataAdapter extends RecyclerView.Adapter<RandomDataAdapter.ViewHolder>{

    private List<CategoriesItem> list;
    private OnClickListener onClickListener;

    public RandomDataAdapter(List<CategoriesItem> randomDataList) {
        this.list = randomDataList;
    }



    @NonNull
    @Override
    public RandomDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.remote_data, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RandomDataAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoriesItem randomData=list.get(position);

        holder.textView1.setText(randomData.getIdCategory());
        holder.textView2.setText(randomData.getStrCategory());
        Glide.with(holder.itemView.getContext())

                .load(list.get(position).getStrCategoryThumb())

                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.onClick(position, list);
                }
            }
        });

    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
   public interface OnClickListener{
        void onClick(int position, List<CategoriesItem> randomDataList);
   }
    @Override
    public int getItemCount() {

        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.textView1);
            textView2=itemView.findViewById(R.id.textView2);
            imageView=itemView.findViewById(R.id.imageView);

        }
    }
}
