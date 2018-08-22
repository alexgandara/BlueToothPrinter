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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zj.com.cn.bluetooth.sdk.R;

public class init_alfilPOS  extends Activity {

    ListView lista;

    Button Agragar_Documento, Mnt_Empresas, Mnt_Prdductos, Mnt_Series, Mnt_Clientes, Mnt_Unidades, Salir, Archivados, Agregar_Documento_notas;

    connectionDB db;
    List<String> item = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the window layout
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.device_list_doctos);
       getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.custom_title);


        lista = (ListView) findViewById(R.id.listView_Lista);

        showNotes();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int _id= Integer.parseInt(item.get(i).split(" ")[0]);
                String _naturaleza = item.get(i).split(" ")[1];


                if (_naturaleza.equals("01")) {
                    Intent intent = new Intent(init_alfilPOS.this,Modificar2.class);
                    intent.putExtra("id",_id);
                    startActivity(intent);
                }


                if (_naturaleza.equals("03")) {
                    Intent intent = new Intent(init_alfilPOS.this,Modificar2.class);
                    intent.putExtra("id",_id);
                    startActivity(intent);
                }

                if (_naturaleza.equals("07")) {
                    Intent intent = new Intent(init_alfilPOS.this,Modificar2.class);
                    intent.putExtra("id",_id);
                    startActivity(intent);
                }

                if (_naturaleza.equals("08")) {
                    Intent intent = new Intent(init_alfilPOS.this,Modificar2.class);
                    intent.putExtra("id",_id);
                    startActivity(intent);
                }



            }
        });


        Agragar_Documento = (Button) findViewById(R.id.Button_Agregar_Documento);
        Agregar_Documento_notas = (Button) findViewById(R.id.Button_Agregar_Documento_notas);



        Mnt_Empresas = (Button) findViewById(R.id.Button_Empresas);
        Mnt_Prdductos= (Button) findViewById(R.id.Button_Productos);
        Mnt_Clientes= (Button) findViewById(R.id.Button_Clientes);
        Mnt_Series= (Button) findViewById(R.id.Button_Series);
        Mnt_Unidades = (Button) findViewById(R.id.Button_Unidades_Medida);
        Archivados= (Button) findViewById(R.id.Button_Archivados);

        Salir= (Button) findViewById(R.id.Button_Salir);



        Agragar_Documento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(init_alfilPOS.this, addDatos.class);
                startActivity(intent);
              //  return true;
            }
        });



        Agregar_Documento_notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(init_alfilPOS.this, addDatos_Notas.class);
                startActivity(intent);
                //  return true;
            }
        });


        Mnt_Empresas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(init_alfilPOS.this, Empresas.class);
                startActivity(intent);
                //  return true;
            }
        });


        Mnt_Prdductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(init_alfilPOS.this, MainActivity_Productos.class);
                startActivity(intent);
                //  return true;
            }
        });


        Mnt_Clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(init_alfilPOS.this, MainActivity_Clientes.class);
                startActivity(intent);
                //  return true;
            }
        });


        Mnt_Unidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(init_alfilPOS.this, MainActivity_Unidades.class);
                startActivity(intent);
                //  return true;
            }
        });







        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });



        Mnt_Series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(init_alfilPOS.this, MainActivity_Series.class);
                startActivity(intent);
                //  return true;
            }
        });

        Archivados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(init_alfilPOS.this, init_alfilPOS_archivadas.class);
                startActivity(intent);
                //  return true;
            }
        });






    }



    private void showNotes() {


        db = new connectionDB(this);
        Cursor c = db.getNotes_Activas();
        item = new ArrayList<String>();
        String serie = "",  ruc="", razon_social = "", fecha="", naturaleza ="", serie_rel;
        int id, folio, folio_rel, archivada;

        if (c.moveToFirst()) {
            do {
                id = c.getInt(0);
                serie = c.getString(1);
                folio = c.getInt(2);
                ruc = c.getString(3);
                razon_social = c.getString(4);
                fecha = c.getString(5);
                serie_rel = c.getString(6);
                folio_rel = c.getInt(7);
                archivada = c.getInt(8);


                naturaleza =  db.get_Naturaleza(serie);
                item.add(id+" "+naturaleza+" "+serie+" "+folio+" <"+archivada+"> "+ruc+" "+razon_social+" "+fecha+" "+serie_rel+" "+folio_rel);


            } while (c.moveToNext());

        }





        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,item);
        lista.setAdapter(adaptador);

    }








}
