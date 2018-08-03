package global.factura.qrpos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zj.com.cn.bluetooth.sdk.R;


/**
 * Created by Alejandro on 3/19/2018.
 */

public class addDatos_Productos extends Activity {

    EditText editText_Producto, editText_Descripcion_Producto, editText_Unidad, editText_Precio, editText_Precio_Mayoreo, editText_igv;


    Button Agregar, Salir;

    public String _Producto;
    public String _Descripcion_Producto;
    public String _Unidad;
    public String _Precio;
    public String _Precio_Mayoreo;
    public String _igv;


    connectionDB db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar_productos_qr);

        Agregar = (Button) findViewById(R.id.button_Agregar);
        Salir = (Button) findViewById(R.id.button_Salir);


        editText_Producto = (EditText) findViewById(R.id.editText_Producto);
        editText_Descripcion_Producto = (EditText) findViewById(R.id.editText_Descripcion_Producto);
        editText_Unidad = (EditText) findViewById(R.id.editText_Unidad);
        editText_Precio = (EditText) findViewById(R.id.editText_Precio);
        editText_Precio_Mayoreo = (EditText) findViewById(R.id.editText_Precio_Mayoreo);
        editText_igv = (EditText) findViewById(R.id.editText_igv);


        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDatos_Productos();


            }
        });


        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Salir();
            }
        });


    }

        private void _Salir() {
            Intent intent = new Intent(this, MainActivity_Productos.class);
            startActivity(intent);
        }

        private void addDatos_Productos() {


        _Producto               = editText_Producto.getText().toString();
        _Descripcion_Producto   = editText_Descripcion_Producto.getText().toString();
        _Unidad                 = editText_Unidad.getText().toString();
        _Precio                 = editText_Precio.getText().toString();
        _Precio_Mayoreo         = editText_Precio_Mayoreo.getText().toString();
        _igv                    = editText_igv.getText().toString();



        db = new connectionDB(this);
        db.addNotes_Productos(_Producto,_Descripcion_Producto, _Unidad, _Precio, _Precio_Mayoreo, _igv);
        //    db.close();
        Intent intent = new Intent(this,MainActivity_Productos.class );
        startActivity(intent);

    }






}
