package com.camilo.formulario;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextInputEditText tiNombreCompleto;
    TextInputEditText tiFechaNacimiento;
    TextInputEditText tiEmail;
    TextInputEditText tiTelefono;
    TextInputEditText tiDescripcionContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tiNombreCompleto = findViewById(R.id.ti_nonbre_completo);
        this.tiFechaNacimiento = findViewById(R.id.ti_fecha_nacimiento);
        this.tiEmail = findViewById(R.id.ti_email);
        this.tiTelefono = findViewById(R.id.ti_telefono);
        this.tiDescripcionContacto = findViewById(R.id.ti_descripcion_contacto);

        this.tiFechaNacimiento.setOnClickListener((v) -> {
            mostrarDatePicker();
        });

        Bundle parametros = getIntent().getExtras();

        if (parametros != null) {
            String nombreCompleto = parametros.getString(getString(R.string.extra_nombre_contacto));
            String fechaNacimiento = parametros.getString(getString(R.string.extra_fecha_nacimiento));
            String telefono = parametros.getString(getString(R.string.extra_telefono));
            String correo = parametros.getString(getString(R.string.extra_correo));
            String descripcion = parametros.getString(getString(R.string.extra_descripcion));

            this.tiNombreCompleto.setText(nombreCompleto);
            this.tiFechaNacimiento.setText(fechaNacimiento);
            this.tiTelefono.setText(telefono);
            this.tiEmail.setText(correo);
            this.tiDescripcionContacto.setText(descripcion);
        }

    }

    private void mostrarDatePicker() {
        Calendar fecha = Calendar.getInstance();
        if (this.tiFechaNacimiento.getText() != null && !this.tiFechaNacimiento.getText().toString().isEmpty()) {
            String[] temp = this.tiFechaNacimiento.getText().toString().split("/");
            fecha.set(Integer.valueOf(temp[2]), Integer.valueOf(temp[1]) - 1, Integer.valueOf(temp[0]));
            fecha.getTime();
        }
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(fecha, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                final String fechaSelccionada = dia + "/" + (mes + 1) + "/" + ano;
                tiFechaNacimiento.setText(fechaSelccionada);
            }
        });
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void abrirConfirmacion(View view) {
        Intent intent = new Intent(this, ConfirmacionActivity.class);
        if (this.tiNombreCompleto.getText() != null) {
            intent.putExtra(getString(R.string.extra_nombre_contacto), this.tiNombreCompleto.getText().toString());
        }
        if (this.tiFechaNacimiento.getText() != null) {
            intent.putExtra(getString(R.string.extra_fecha_nacimiento), this.tiFechaNacimiento.getText().toString());
        }
        if (this.tiTelefono.getText() != null) {
            intent.putExtra(getString(R.string.extra_telefono), this.tiTelefono.getText().toString());
        }
        if (this.tiEmail.getText() != null) {
            intent.putExtra(getString(R.string.extra_correo), this.tiEmail.getText().toString());
        }
        if (this.tiDescripcionContacto.getText() != null) {
            intent.putExtra(getString(R.string.extra_descripcion), this.tiDescripcionContacto.getText().toString());
        }
        startActivity(intent);
        finish();
    }
}
