package nhom8.fpt.sieuthisach.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom8.fpt.sieuthisach.Adapter.HoaDonKHAdapter;
import nhom8.fpt.sieuthisach.DAO.HoaDonDAO;
import nhom8.fpt.sieuthisach.Model.HoaDon;
import nhom8.fpt.sieuthisach.R;

public class FragmentHoaDon extends Fragment {

    RecyclerView recyclerHoaDon;
    ArrayList<HoaDon> list = new ArrayList<>();
    HoaDonDAO hoaDonDAO;
    int matk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hoa_don, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("ThongTinTaiKhoan", MODE_PRIVATE);
        matk = sharedPreferences.getInt("matk", 0);

        recyclerHoaDon = view.findViewById(R.id.recyclerHD);
        hoaDonDAO = new HoaDonDAO(getContext());

        loadDataHoaDon();

        return view;
    }

    public void loadDataHoaDon() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerHoaDon.setLayoutManager(linearLayoutManager);

        list = hoaDonDAO.getDSHoaDonTheoKH(matk);
        HoaDonKHAdapter hoaDonKHAdapter = new HoaDonKHAdapter(getContext(), list);
        recyclerHoaDon.setAdapter(hoaDonKHAdapter);
    }

}

