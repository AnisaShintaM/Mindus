package id.sch.smktelkom_mlg.project.xirpl101101928.mindus.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xirpl101101928.mindus.R;
import id.sch.smktelkom_mlg.project.xirpl101101928.mindus.model.Mind;

/**
 * Created by A455L on 17/11/2016.
 */

public class MindAdapter extends RecyclerView.Adapter<MindAdapter.ViewHolder> {

    ArrayList<Mind> hotelList;
    IMindAdapter mIMindAdapter;

    public MindAdapter(Context context, ArrayList<Mind> hotelList) {
        this.hotelList = hotelList;
        mIMindAdapter = (IMindAdapter) context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mind_item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mind hotel = hotelList.get(position);
        holder.tvJudul.setText(hotel.task);
        holder.tvDeskripsi.setText(hotel.deskripsi);
        holder.ivFoto.setImageURI(Uri.parse(hotel.foto));
    }

    @Override
    public int getItemCount() {
        if (hotelList != null)
            return hotelList.size();
        return 0;
    }

    public interface IMindAdapter {
        void doClick(int pos);
        void doDelete(int pos);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvJudul;
        TextView tvDeskripsi;
        Button bDelete;



        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewTask);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
            bDelete = (Button) itemView.findViewById(R.id.buttonDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIMindAdapter.doClick(getAdapterPosition());
                }
            });

            bDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    mIMindAdapter.doDelete(getAdapterPosition());
                }
            });
        }
    }
}
