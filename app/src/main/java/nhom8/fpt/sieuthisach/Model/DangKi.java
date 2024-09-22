package nhom8.fpt.sieuthisach.Model;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import nhom8.fpt.sieuthisach.DAO.TaiKhoanDAO;
import nhom8.fpt.sieuthisach.DangNhap;
import nhom8.fpt.sieuthisach.Model.TaiKhoan;
import nhom8.fpt.sieuthisach.R;

public class DangKi extends AppCompatActivity {

    private TextView tvDkTitle;
    private TextInputEditText tiedt_dk_tk, tiedt_dk_ps, tiedt_dk_reps, tiedt_dk_ht, tiedt_dk_sdt, tiedt_dk_dc;
    private Button btn_dk_dk, btn_dk_tv;
    private String tvstrDK = "Đăng Kí Tài Khoản";
    private int soKiTu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        // Find UI components
        tvDkTitle = findViewById(R.id.tv_dk_title);
        tiedt_dk_tk = findViewById(R.id.tiedt_dk_tk);
        tiedt_dk_ps = findViewById(R.id.tiedt_dk_ps);
        tiedt_dk_reps = findViewById(R.id.tiedt_dk_reps);
        tiedt_dk_ht = findViewById(R.id.tiedt_dk_ht);
        tiedt_dk_sdt = findViewById(R.id.tiedt_dk_sdt);
        tiedt_dk_dc = findViewById(R.id.tiedt_dk_dc);
        btn_dk_dk = findViewById(R.id.btn_dk_dk);
        btn_dk_tv = findViewById(R.id.btn_dk_tv);

        // Set input type for password fields to mask the text
        tiedt_dk_ps.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tiedt_dk_reps.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        // Start animating the title text
        animateText();

        // Set up button click event for registering
        btn_dk_dk.setOnClickListener(view -> {
            String taikhoan = tiedt_dk_tk.getText().toString();
            String matkhau = tiedt_dk_ps.getText().toString();
            String matkhau_confirm = tiedt_dk_reps.getText().toString();
            String hoten = tiedt_dk_ht.getText().toString();
            String sdt = tiedt_dk_sdt.getText().toString();
            String diachi = tiedt_dk_dc.getText().toString();
            String loaitaikhoan = "khách hàng";

            // Check conditions for registration
            if (taikhoan.isEmpty()) {
                tiedt_dk_tk.setError("Vui lòng nhập tên tài khoản !");
                return;
            }
            if (matkhau.isEmpty()) {
                tiedt_dk_ps.setError("Vui lòng nhập mật khẩu !");
                return;
            }
            if (matkhau_confirm.isEmpty()) {
                tiedt_dk_reps.setError("Vui lòng nhập lại mật khẩu !");
                return;
            }
            if (hoten.isEmpty()) {
                tiedt_dk_ht.setError("Vui lòng nhập họ tên !");
                return;
            }
            if (sdt.isEmpty()) {
                tiedt_dk_sdt.setError("Vui lòng nhập số điện thoại !");
                return;
            }
            if (diachi.isEmpty()) {
                tiedt_dk_dc.setError("Vui lòng nhập địa chỉ !");
                return;
            }
            if (!matkhau.equals(matkhau_confirm)) {
                tiedt_dk_reps.setError("Không trùng mật khẩu !");
                return;
            }

            TaiKhoan taiKhoan = new TaiKhoan(taikhoan, matkhau, hoten, sdt, diachi, loaitaikhoan);

            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(DangKi.this);
            if (taiKhoanDAO.checkDangKi(taiKhoan)) {
                Toast.makeText(DangKi.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DangKi.this, DangNhap.class));
            } else {
                Toast.makeText(DangKi.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        // Navigate back to login screen
        btn_dk_tv.setOnClickListener(view -> {
            Intent intent = new Intent(DangKi.this, DangNhap.class);
            startActivity(intent);
        });
    }

    // Animating title text
    private void animateText() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (soKiTu < tvstrDK.length()) {
                    String vanBanHT = tvstrDK.substring(0, soKiTu + 1);
                    tvDkTitle.setText(vanBanHT);
                    soKiTu++;
                    animateText();
                }
            }
        }, 200);
    }
}
