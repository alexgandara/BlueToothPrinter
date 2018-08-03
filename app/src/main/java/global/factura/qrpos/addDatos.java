package global.factura.qrpos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

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

    List<String> _lista_series = null;

    Spinner spinner_Series;


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


            spinner_Series = (Spinner) findViewById(R.id.spinner_Series);


        // llenar datos del spiner de series
        showNotes_Series();
        ArrayAdapter<String> adapter_precios = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_dropdown_item, _lista_series);
        spinner_Series.setAdapter(adapter_precios);


        spinner_Series.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {
                String _serie_elegida = _lista_series.get(i).split("/")[0];

                serie.setText(_serie_elegida);




            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });




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



    private void showNotes_Series() {


        db = new connectionDB(this);
        Cursor c = db.getNotes_series();
        _lista_series = new ArrayList<String>();
        String _serie="", _naturaleza="";




        // {TABLE_ID,ID_PRODUCTO, DESCRIPCION_PRODUCTO, UNIDAD, PRECIO_PRODUCTO, IGV_PRODUCTO};

        int _id, _folio;
        //   _lista_productos.add("0"+"/"+
        //                        "     "+"/"+
        //                        "     "+"/"+
        //                        "     "+"/"+
        //                        "     "+"/"+
        //                       "     "+"/"+
        //                      "     "+"/");

        if (c.moveToFirst()) {
            do {
                _id = c.getInt(0);
                _serie = c.getString(1);
                _folio = c.getInt(2);
                _naturaleza = c.getString(3);

                if (_naturaleza.equals("01")) {
                    _lista_series.add(_serie+"/ "+
                            _naturaleza+"  /  "+_folio);
                }

                if (_naturaleza.equals("03")) {
                    _lista_series.add(_serie+"/ "+
                            _naturaleza+"  /  "+_folio);
                }





            } while (c.moveToNext());

        }

/*        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Salir();


            }
        });
*/

    }





}
