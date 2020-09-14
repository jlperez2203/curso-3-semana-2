package com.camilo.formulario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionActivity extends AppCompatActivity {

    TextView tvNombreCompleto;
    TextView tvFechaNacimiento;
    TextView tvTelefono;
    TextView tvCorreo;
    TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);
        Bundle parametros = getIntent().getExtras();

        this.tvNombreCompleto = findViewById(R.id.tv_nombre_completo);
        this.tvFechaNacimiento = findViewById(R.id.tv_fecha_nacimiento);
        this.tvTelefono = findViewById(R.id.tv_telefono);
        this.tvCorreo = findViewById(R.id.tv_email);
        this.tvDescripcion = findViewById(R.id.tv_descripcion);


        String nombreCompleto = parametros.getString(getString(R.string.extra_nombre_contacto));
        String fechaNacimiento = parametros.getString(getString(R.string.extra_fecha_nacimiento));
        String telefono = parametros.getString(getString(R.string.extra_telefono));
        String correo = parametros.getString(getString(R.string.extra_correo));
        String descripcion = parametros.getString(getString(R.string.extra_descripcion));


        this.tvNombreCompleto.setText(nombreCompleto);
        this.tvFechaNacimiento.setText(fechaNacimiento);
        this.tvTelefono.setText(telefono);
        this.tvCorreo.setText(correo);
        this.tvDescripcion.setText(descripcion);


    }

    public void abrirEditar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        if (this.tvNombreCompleto.getText() != null) {
            intent.putExtra(getString(R.string.extra_nombre_contacto), this.tvNombreCompleto.getText().toString());
        }
        if (this.tvFechaNacimiento.getText() != null) {
            intent.putExtra(getString(R.string.extra_fecha_nacimiento), this.tvFechaNacimiento.getText().toString());
        }
        if (this.tvTelefono.getText() != null) {
            intent.putExtra(getString(R.string.extra_telefono), this.tvTelefono.getText().toString());
        }
        if (this.tvCorreo.getText() != null) {
            intent.putExtra(getString(R.string.extra_correo), this.tvCorreo.getText().toString());
        }
        if (this.tvDescripcion.getText() != null) {
            intent.putExtra(getString(R.string.extra_descripcion), this.tvDescripcion.getText().toString());
        }
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
