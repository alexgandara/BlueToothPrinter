package global.factura.qrpos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import zj.com.cn.bluetooth.sdk.R;


/**
 * Created by Alejandro on 3/12/2018.
    alex alex 
 */

public class addDatos extends Activity {
    EditText serie, folio, ruc, razon_social, direccion, moneda;
    Button Add;
    String _serie, _folio, _ruc, _razon_social, _direccion, _moneda;
    connectionDB db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the window layout
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.inserta_datos);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.custom_title);


           Add = (Button) findViewById(R.id.button_Agregar);
           serie = (EditText) findViewById(R.id.editText_Serie);
           folio = (EditText) findViewById(R.id.editText_Folio);
           ruc = (EditText) findViewById(R.id.editText_Ruc);
           razon_social = (EditText) findViewById(R.id.editText_RazonSocial);
           direccion = (EditText) findViewById(R.id.editText_Direccion);
           moneda = (EditText) findViewById(R.id.editText_Moneda);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDatos();


            }
        });

    }


    private void addDatos() {

        _serie        = serie.getText().toString();
        _folio        = folio.getText().toString();
        _ruc          = ruc.getText().toString();
        _razon_social = razon_social.getText().toString();
        _direccion    = direccion.getText().toString();
        _moneda       = moneda.getText().toString();



        db = new connectionDB(this);
        db.addNotes(_serie,_folio, _ruc,_razon_social,_direccion,_moneda);
        //    db.close();
        Intent intent = new Intent(this,init_alfilPOS.class );
        startActivity(intent);

    }


}
