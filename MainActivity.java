package com.example.tugasabdan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText namaEditText, nol1EditText, nol2EditText, nol3EditText;
    private RadioGroup radioGroup;
    private RadioButton rbGold, rbSilver, rbReguler;
    private Button bayarButton;

    // Harga barang per unit
    private static final int HARGA_FALLEN_MISFIT = 780000;
    private static final int HARGA_FADE_MONO = 1500000;
    private static final int HARGA_FIGHT_FML = 450000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi elemen UI
        namaEditText = findViewById(R.id.Nama);
        nol1EditText = findViewById(R.id.Nol1);
        nol2EditText = findViewById(R.id.Nol2);
        nol3EditText = findViewById(R.id.Nol3);
        radioGroup = findViewById(R.id.radioGroup);
        rbGold = findViewById(R.id.rbGold);
        rbSilver = findViewById(R.id.rbSilver);
        rbReguler = findViewById(R.id.rbReguler);
        bayarButton = findViewById(R.id.Bayar);

        // Menambahkan event listener pada tombol "BAYAR"
        bayarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan nilai dari EditText
                String namaPembeli = namaEditText.getText().toString();
                String nol1Value = nol1EditText.getText().toString();
                String nol2Value = nol2EditText.getText().toString();
                String nol3Value = nol3EditText.getText().toString();

                // Mendapatkan jenis member yang dipilih
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                String memberType = selectedRadioButton.getText().toString();

                // Mendapatkan jumlah barang yang dibeli
                int jumlah1 = Integer.parseInt(nol1Value);
                int jumlah2 = Integer.parseInt(nol2Value);
                int jumlah3 = Integer.parseInt(nol3Value);

                // Menghitung total pembelian
                int totalPembelian = (jumlah1 * HARGA_FALLEN_MISFIT) + (jumlah2 * HARGA_FADE_MONO) + (jumlah3 * HARGA_FIGHT_FML);

                // Menentukan diskon berdasarkan total pembelian dan jenis member
                double diskon = 0;

                if (totalPembelian > 5000000) {
                    if (memberType.equals("Gold")) {
                        diskon = 0.1;
                    } else if (memberType.equals("Silver")) {
                        diskon = 0.05;
                    }
                    // Tidak ada diskon untuk member Reguler
                }

                // Menghitung total pembayaran setelah diskon
                double totalPembayaran = totalPembelian - (totalPembelian * diskon);

                // Menampilkan hasil
                String hasil = "Nama Pembeli: " + namaPembeli +
                        "\nTotal Pembelian: Rp " + totalPembelian +
                        "\nDiskon: Rp " + (totalPembelian * diskon) +
                        "\nTotal Pembayaran Setelah Diskon: Rp " + totalPembayaran;

                Toast.makeText(MainActivity.this, hasil, Toast.LENGTH_LONG).show();
            }
        });
    }
}
