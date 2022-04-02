package com.example.eaterydemo.adapter;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.DonHangFMDirections;
import com.example.eaterydemo.fragments.TrangChuFMDirections;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    List<DonHang> arr;
    DonHang donHang;
    DonHangChiTiet donHangChiTiet;
    Context context;
    SimpleDateFormat sfd = new SimpleDateFormat("dd-MM");
    static NavController navController;
    View _view;
    DecimalFormat df = new DecimalFormat("#,###");

    public DonHangAdapter(List<DonHang> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenNhaHang, tvTienDH, tvNgayDH, tvTrangThaiDH, tvSoLuongMon;
        ImageView ivDoneDonHang;
        RelativeLayout Rl_donhang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenNhaHang = itemView.findViewById(R.id.tvTenNhaHangDatHang);
            tvTienDH = itemView.findViewById(R.id.tvTienDatHang);
            tvNgayDH = itemView.findViewById(R.id.tvNgayDatHang);
            tvTrangThaiDH = itemView.findViewById(R.id.tvTrangThaiDatHang);
            tvSoLuongMon = itemView.findViewById(R.id.tvSoMon);
            ivDoneDonHang = itemView.findViewById(R.id.ivDoneDonHang);
            Rl_donhang = itemView.findViewById(R.id.Rl_donhang);

        }

        public TextView getTvTenNhaHang() {
            return tvTenNhaHang;
        }

        public TextView getTvTienDH() {
            return tvTienDH;
        }

        public TextView getTvNgayDH() {
            return tvNgayDH;
        }

        public ImageView getIvDoneDonHang() {
            return ivDoneDonHang;
        }

        public TextView getTvSoLuongMon() {
            return tvSoLuongMon;
        }

        public RelativeLayout getRl_donhang() {
            return Rl_donhang;
        }

        public TextView getTvTrangThaiDH() {
            return tvTrangThaiDH;
        }


    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_dathang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        _view = parent;
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DonHangAdapter.ViewHolder holder, int position) {
        DonHang model = arr.get(position);
        initNavController(_view);
        RelativeLayout rlDH = holder.getRl_donhang();
        rlDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = DonHangFMDirections.actionDonHangFMToDonHangChiTietFM(model);
                navController.navigate(action);

            }
        });
        TextView tvTenNhaHang = holder.getTvTenNhaHang();
        TextView tvTienDH = holder.getTvTienDH();
        TextView tvNgayDH = holder.getTvNgayDH();
        TextView tvTrangThai = holder.getTvTrangThaiDH();
        TextView tvSoLuongMon = holder.getTvSoLuongMon();
        ImageView ivDoneDonHang = holder.getIvDoneDonHang();

        tvTenNhaHang.setText(model.getNameRes());
        tvTienDH.setText(df.format(model.getTongTien())+ "đ");
        tvSoLuongMon.setText(model.getCountSL() + " món");
        tvNgayDH.setText( model.getNgayMua());


//
//        try {
//            tvNgayDH.setText(sfd.parse(model.getNgayMua()) + "");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        int TT = model.getTrangThaiDH();
        if (TT == 3) {
            tvTrangThai.setText("Hủy bỏ");
            tvTrangThai.setTextColor(Color.RED);
            ivDoneDonHang.setImageResource(R.drawable.dahuydonhang);
        } else if (TT == 2){
            tvTrangThai.setText("Đã giao");
            tvTrangThai.setTextColor(Color.GREEN);
            ivDoneDonHang.setImageResource(R.drawable.donhangdagiao);
        }else if (TT == 1){
            tvTrangThai.setText("Chờ xác nhận");
            tvTrangThai.setTextColor(Color.CYAN);
            ivDoneDonHang.setImageResource(R.drawable.trangthaichoxacnhan);
        }

    }
//    private void getAllDonHang() {
//        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
//        Call call = serviceAPI.GetALLDonHang("user1");
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                arr = (List<DonHang>) response.body();
//                Log.d("arr", arr.size() + "");
//                for (DonHang item: arr) {
//                    item.getNameRes()
//                    for (DonHangChiTiet item2: item.getDONHANGCHITIETs()) {
//                        item2.
//                    }
//                }
//                adapter = new DonHangAdapter(arr, getContext());
//                fmBinding.rvDonHang.setAdapter(adapter);
//                fmBinding.rvDonHang.setLayoutManager(new LinearLayoutManager(getContext()));
//                dismissProgressDialog();
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                dismissProgressDialog();
//                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
