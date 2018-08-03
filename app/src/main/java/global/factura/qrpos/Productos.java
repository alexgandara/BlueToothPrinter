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

/**
 * Created by Alejandro on 3/19/2018.
 */

public class Productos extends Activity {
    // variables para table productos
    public static final String ID_PRODUCTO = "producto";
    public static final String DESCRIPCION_PRODUCTO = "descripcion_producto";
    public static final String PRECIO_PRODUCTO = "precio";
    public static final String PRECIO_PRODUCTO_MAYOREO = "precio_mayoreo";
    public static final String IGV_PRODUCTO = "igv";


    private static final String TABLE_PRODUCTOS = "productos";

    public  int _cabecera_id;
    public String Mproducto;
    public String Mdescripcion_producto;
    public Double Mprecio;
    public Double Mprecio_Mayoreo;
    public Double Migv;


    EditText editText_Producto, editText_Descripcion_Producto, editText_Precio, editText_Precio_Mayoreo, editText_igv;

    int _myId;
    connectionDB db;
    Button Modificar, Eliminar, Salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_qr);

        Bundle b = getIntent().getExtras();
        if (b!=null) {
            _myId = b.getInt("id");

        }

        db = new connectionDB(this);
        Cursor cursor =  db.getReg_Productos(_myId);
        if (cursor.moveToFirst()) {
            do {

                editText_Producto = (EditText) findViewById(R.id.editText_Producto);
                editText_Descripcion_Producto = (EditText) findViewById(R.id.editText_Descripcion_Producto);
                editText_Precio = (EditText) findViewById(R.id.editText_Precio);
                editText_Precio_Mayoreo = (EditText) findViewById(R.id.editText_Precio_Mayoreo);
                editText_igv = (EditText) findViewById(R.id.editText_igv);


                Mproducto = cursor.getString(1);
                Mdescripcion_producto = cursor.getString(2);
                Mprecio = cursor.getDouble (3);
                Mprecio_Mayoreo = cursor.getDouble (4);
                Migv = cursor.getDouble(5);


                editText_Producto.setText(Mproducto);
                editText_Descripcion_Producto.setText(Mdescripcion_producto);
                editText_Precio.setText(Mprecio.toString());
                editText_Precio_Mayoreo.setText(Mprecio_Mayoreo.toString());
                editText_igv.setText(Migv.toString());


                //  Toast.makeText(Modificar.this,"Razon Social :"+Mrazon_social, Toast.LENGTH_SHORT ).show();

            } while (cursor.moveToNext());

        }


        Modificar = (Button) findViewById(R.id.button_Modificar);
        Eliminar = (Button) findViewById(R.id.button_Eliminar);
        Salir = (Button) findViewById(R.id.button_Salir);

        editText_Producto = (EditText) findViewById(R.id.editText_Producto);
        editText_Descripcion_Producto = (EditText) findViewById(R.id.editText_Descripcion_Producto);
        editText_Precio = (EditText) findViewById(R.id.editText_Precio);
        editText_Precio_Mayoreo = (EditText) findViewById(R.id.editText_Precio_Mayoreo);
        editText_igv = (EditText) findViewById(R.id.editText_igv);

        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar(_myId,
                        editText_Producto.getText().toString(),
                        editText_Descripcion_Producto.getText().toString(),
                        editText_Precio.getText().toString(),
                        editText_Precio_Mayoreo.getText().toString(),
                        editText_igv.getText().toString());


            }
        });


        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar(_myId);

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

    private void Modificar(int _id,
                           String _editText_Producto,
                           String _editText_Descripcion_Producto,
                           String _editText_Precio,
                           String _editText_Precio_Mayoreo,
                           String _editText_igv) {

        ContentValues valoresProductos = new ContentValues();
        valoresProductos.put(ID_PRODUCTO, _editText_Producto);
        valoresProductos.put(DESCRIPCION_PRODUCTO, _editText_Descripcion_Producto);
        valoresProductos.put(PRECIO_PRODUCTO, _editText_Precio);
        valoresProductos.put(PRECIO_PRODUCTO_MAYOREO, _editText_Precio_Mayoreo);
        valoresProductos.put(IGV_PRODUCTO, _editText_igv);



        db = new connectionDB(this);

        String _alcance = "WHERE _id="+_id;

        db.getWritableDatabase().update("productos", valoresProductos, "_id" + "=?",new String[] { String.valueOf(_id) });
        Toast.makeText(Productos.this,"Se Modifico el Reg: :"+_id, Toast.LENGTH_SHORT ).show();

        Intent intent = new Intent(this,MainActivity_Productos.class );
        startActivity(intent);

    }

    private void Eliminar(int _id) {



        db = new connectionDB(this);



        db.getWritableDatabase().delete("productos","_id" + "=?",new String[] { String.valueOf(_id) });
        Toast.makeText(Productos.this,"Se Elimino el Reg: :"+_id, Toast.LENGTH_SHORT ).show();

        Intent intent = new Intent(this,MainActivity_Productos.class );
        startActivity(intent);

    }



}
