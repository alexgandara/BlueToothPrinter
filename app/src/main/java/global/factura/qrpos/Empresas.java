package global.factura.qrpos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zj.com.cn.bluetooth.sdk.R;

public class Empresas extends Activity {

    EditText razon_social, ruc, direccion1,  telefono, correo, licencia, api_key ;


    String _razon_social, _ruc, _direccion1, _telefono, _correo, _licencia, _api_key;

    int _myId = 1;
    connectionDB db;
    Button Modificar, Salir, TicketPos, TicketPos_base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas_qr);



        db = new connectionDB(this);
        Cursor cursor =  db.getReg_Empresas(_myId);
        if (cursor.moveToFirst()) {
            do {

                razon_social = (EditText) findViewById(R.id.editText_Razon_Social);
                ruc = (EditText) findViewById(R.id.editText_Ruc);
                direccion1 = (EditText) findViewById(R.id.editText_Direccion1);
                api_key = (EditText) findViewById(R.id.editText_Api_Key);
                correo = (EditText) findViewById(R.id.editText_Correo);
                telefono = (EditText) findViewById(R.id.editText_Telefono);
                licencia = (EditText) findViewById(R.id.editText_Licencia);



                _razon_social = cursor.getString(1);
                _ruc = cursor.getString(2);
                _direccion1 = cursor.getString(3);
                _api_key = cursor.getString(4);
                _correo = cursor.getString(5);
                _telefono = cursor.getString(6);
                _licencia = cursor.getString(7);



                razon_social.setText(_razon_social);
                ruc.setText(_ruc);
                direccion1.setText(_direccion1);
                api_key.setText(_api_key);
                correo.setText(_correo);
                telefono.setText(_telefono);
                licencia.setText(_licencia);



             //    Toast.makeText(this,"La Empresas fue Modificada", Toast.LENGTH_SHORT ).show();

            } while (cursor.moveToNext());

        }




        Modificar = (Button) findViewById(R.id.button_Modificar);
        Salir = (Button) findViewById(R.id.button_Salir);
        TicketPos = (Button) findViewById(R.id.button_TicketPos);
        TicketPos_base = (Button) findViewById(R.id.button_TicketPos_Buttom);

        razon_social = (EditText) findViewById(R.id.editText_Razon_Social);
        ruc = (EditText) findViewById(R.id.editText_Ruc);
        direccion1 = (EditText) findViewById(R.id.editText_Direccion1);
        api_key = (EditText) findViewById(R.id.editText_Api_Key);
        correo = (EditText) findViewById(R.id.editText_Correo);
        telefono = (EditText) findViewById(R.id.editText_Telefono);
        licencia = (EditText) findViewById(R.id.editText_Licencia);

        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar_Empresa(_myId,
                        razon_social.getText().toString(),
                        ruc.getText().toString(),
                        direccion1.getText().toString(),
                        api_key.getText().toString(),
                        correo.getText().toString(),
                        telefono.getText().toString(),
                        licencia.getText().toString());}
        });





        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Empresas.this,init_alfilPOS.class );
                startActivity(intent);

            }
        });

       TicketPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Empresas.this,TicketPos.class );
                startActivity(intent);

            }
        });


        TicketPos_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Empresas.this,TicketPos_Base.class );
                startActivity(intent);

            }
        });



    }


    private void Modificar_Empresa(int _id,
                           String _razon_social,
                           String _ruc,
                           String _direccion1,
                           String _api_key,
                           String _correo,
                           String _telefono,
                           String _licencia
    ) {

        ContentValues valoresEmpresa = new ContentValues();

        valoresEmpresa.put("razon_social_empresa", _razon_social);
        valoresEmpresa.put("ruc_empresa", _ruc);
        valoresEmpresa.put("direccion_empresa_1", _direccion1);
        valoresEmpresa.put("api_key", _api_key);
        valoresEmpresa.put("correo_empresa", _correo);
        valoresEmpresa.put("telefono_empresa", _telefono);
        valoresEmpresa.put("licencia", _licencia);


       db = new connectionDB(this);



        db.getWritableDatabase().update("empresa", valoresEmpresa, "_id" + "=?",new String[] { String.valueOf(_id) });
        Toast.makeText(this,"Se Modifico el Reg: :"+_id, Toast.LENGTH_SHORT ).show();

        Intent intent = new Intent(this,init_alfilPOS.class );
        startActivity(intent);

    }







}
