package global.factura.qrpos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alejandro on 3/12/2018.
 */

public class connectionDB extends SQLiteOpenHelper {




    public static final String TABLE_ID = "_id";
    public static final String SERIE = "serie";
    public static final String FOLIO = "folio";
    public static final String FECHA = "fecha";
    public static final String RUC = "ruc";
    public static final String RAZON_SOCIAL = "razon_social";
    public static final String DIRECCION = "direccion";
    public static final String MONEDA = "moneda";
    public static final String GRAVADO = "gravado";
    public static final String EXCENTO = "excento";
    public static final String INAFECTO = "inafecto";
    public static final String SUBTOTAL = "subtotal";
    public static final String IGV = "igv";
    public static final String TOTAL = "total";
    public static final String CORREO = "correo";
    public static final String SERIE_REL = "serie_rel";
    public static final String FOLIO_REL = "folio_rel";




//    public static final String TABLE_ID = "_id";


    public static final String CABECERA_ID = "cabecera_id";
    public static final String PRODUCTO = "producto";
    public static final String DESCRIPCION = "descripcion";
    public static final String UNIDAD = "unidad";
    public static final String PRECIO = "precio";
    public static final String CANTIDAD = "cantidad";
    public static final String DET_SUBTOTAL = "det_subtotal";
    public static final String DET_IGV = "det_igv";
    public static final String DET_TOTAL = "det_total";




    // variables para table empresas

    public static final String RAZON_SOCIAL_EMPRESA = "razon_social_empresa";
    public static final String RUC_EMPRESA = "ruc_empresa";
    public static final String DIRECCION_EMPRESA_1 = "direccion_empresa_1";
    public static final String DIRECCION_EMPRESA_2 = "direccion_empresa_2";
    public static final String CORREO_EMPRESA = "correo_empresa";
    public static final String TELEFONO_EMPRESA = "telefono_empresa";
    public static final String PORCENTAJE_IGV = "porcentaje_igv";
    public static final String LICENCIA = "licencia";
    public static final String LINEA01 = "linea01";
    public static final String LINEA02 = "linea02";
    public static final String LINEA03 = "linea03";
    public static final String LINEA04 = "linea04";
    public static final String LINEA05 = "linea05";
    public static final String LINEA06 = "linea06";
    public static final String BASE01 = "base01";
    public static final String BASE02 = "base02";
    public static final String BASE03 = "base03";
    public static final String BASE04 = "base04";


    // variables para table clientes

    public static final String RAZON_SOCIAL_CLIENTE = "razon_social_cliente";
    public static final String RUC_CLIENTE = "ruc_cliente";
    public static final String DIRECCION_CLIENTE = "direccion_cliente";
    public static final String CORREO_CLIENTE = "correo_cliente";
    public static final String TELEFONO_CLIENTE = "telefono_cliente";


    // variables para table unidades de medida
    public static final String ID_UNIDAD = "unidad";
    public static final String DESCRIPCION_UNIDAD = "descripcion_unidad";


    // variables para table productos
    public static final String ID_PRODUCTO = "producto";
    public static final String DESCRIPCION_PRODUCTO = "descripcion_producto";
    public static final String UNIDAD_PRODUCTO = "unidad";
    public static final String PRECIO_PRODUCTO = "precio";
    public static final String PRECIO_PRODUCTO_MAYOREO = "precio_mayoreo";
    public static final String IGV_PRODUCTO = "igv";



    private static final String DATABASE = "miniPOS";
    private static final String TABLE = "cabecera";

    // tablas
    private static final String TABLE_DET = "detalle";
    private static final String TABLE_EMPRESA = "empresa";
    private static final String TABLE_CLIENTES = "clientes";
    private static final String TABLE_UNIDADES = "unidades";
    private static final String TABLE_PRODUCTOS = "productos";



    public connectionDB(Context context) {
        super(context, DATABASE, null, 1);
    }


    public connectionDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE + " (" +
                TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SERIE +" TEXT, "+
                FOLIO +" INT DEFAULT 0,"+
                FECHA +" DATE,"+
                RUC +" TEXT,"+
                RAZON_SOCIAL +" TEXT,"+
                DIRECCION + " TEXT,"+
                MONEDA +" TEXT,"+
                CORREO +" TEXT DEFAULT 'usuario@dominio.com',"+
                SERIE_REL +" TEXT,"+
                FOLIO_REL +" TEXT,"+
                GRAVADO +" DOUBLE,"+
                EXCENTO +" DOUBLE,"+
                INAFECTO+" DOUBLE,"+
                SUBTOTAL+" DOUBLE,"+
                IGV+" DOUBLE,"+
                TOTAL+" DOUBLE)");





        // TABLE DETALLE
        db.execSQL("CREATE TABLE "+ TABLE_DET + " (" +
                TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CABECERA_ID +" INTEGER,"+
                PRODUCTO +" TEXT,"+
                DESCRIPCION +" TEXT,"+
                UNIDAD + " TEXT DEFAULT 'NIU',"+
                PRECIO +" DOUBLE,"+
                CANTIDAD +" DOUBLE,"+
                DET_SUBTOTAL+" DOUBLE,"+
                DET_IGV+" DOUBLE,"+
                DET_TOTAL+" DOUBLE,"+
                " FOREIGN KEY("+ CABECERA_ID + ") REFERENCES "+TABLE+"("+TABLE_ID+"))");


        db.execSQL("CREATE TABLE series (id INTEGER PRIMARY KEY AUTOINCREMENT, serie TEXT, naturaleza TEXT, folio INTEGER)");

        db.beginTransaction();
        try {
            ContentValues valuesSeries = new ContentValues();
            for (int i = 0; i < 1; i++) {
                valuesSeries.put("serie", "B001");
                valuesSeries.put("naturaleza", "03");
                valuesSeries.put("folio", 0);

                db.insert("series", null, valuesSeries);

                valuesSeries.put("serie", "F001");
                valuesSeries.put("naturaleza", "01");
                valuesSeries.put("folio", 0);
                db.insert("series", null, valuesSeries);


                valuesSeries.put("serie", "BNC1");
                valuesSeries.put("folio", 0);
                valuesSeries.put("naturaleza", "07");
                db.insert("series", null, valuesSeries);

                valuesSeries.put("serie", "FNC1");
                valuesSeries.put("naturaleza", "07");
                valuesSeries.put("folio", 0);
                db.insert("series", null, valuesSeries);


                valuesSeries.put("serie", "BND1");
                valuesSeries.put("naturaleza", "08");
                valuesSeries.put("folio", 0);
                db.insert("series", null, valuesSeries);

                valuesSeries.put("serie", "FND1");
                valuesSeries.put("naturaleza", "08");
                valuesSeries.put("folio", 0);
                db.insert("series", null, valuesSeries);








            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }






        db.execSQL("CREATE TABLE "+ TABLE_EMPRESA + " (" +
                TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAZON_SOCIAL_EMPRESA +" TEXT,"+
                RUC_EMPRESA +" TEXT,"+
                DIRECCION_EMPRESA_1 + " TEXT,"+
                DIRECCION_EMPRESA_2 + " TEXT,"+
                CORREO_EMPRESA + " TEXT,"+
                TELEFONO_EMPRESA + " TEXT,"+
                LICENCIA + " TEXT, "+
                LINEA01 + " TEXT, "+
                LINEA02 + " TEXT, "+
                LINEA03 + " TEXT, "+
                LINEA04 + " TEXT, "+
                LINEA05 + " TEXT, "+
                LINEA06 + " TEXT, "+
                BASE01 + " TEXT, "+
                BASE02 + " TEXT, "+
                BASE03 + " TEXT, "+
                BASE04 + " TEXT)"
        );


        db.beginTransaction();
        try {
            ContentValues valuesEmpresa = new ContentValues();
            for (int i = 0; i < 1; i++) {
                valuesEmpresa.put(RAZON_SOCIAL_EMPRESA, "MI EMPRESA SAC");
                valuesEmpresa.put(RUC_EMPRESA, "12345678901");
                valuesEmpresa.put(DIRECCION_EMPRESA_1, "DIRECCION 1");
                valuesEmpresa.put(DIRECCION_EMPRESA_2, "DIRECCION 2");
                valuesEmpresa.put(CORREO_EMPRESA, "mcorreo@miempresa.com");
                valuesEmpresa.put(TELEFONO_EMPRESA, "(+) 01 111-222-333");
                valuesEmpresa.put(LICENCIA, "PUBLICA");
                valuesEmpresa.put(LINEA01, "");
                valuesEmpresa.put(LINEA02, "");
                valuesEmpresa.put(LINEA03, "");
                valuesEmpresa.put(LINEA04, "");
                valuesEmpresa.put(LINEA05, "");
                valuesEmpresa.put(LINEA06, "");
                valuesEmpresa.put(BASE01, "");
                valuesEmpresa.put(BASE02, "");
                valuesEmpresa.put(BASE03, "");
                valuesEmpresa.put(BASE04, "");


                db.insert(TABLE_EMPRESA, null, valuesEmpresa);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }


        db.execSQL("CREATE TABLE "+ TABLE_CLIENTES + " (" +
                TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAZON_SOCIAL_CLIENTE +" TEXT,"+
                RUC_CLIENTE +" TEXT,"+
                DIRECCION_CLIENTE + " TEXT,"+
                CORREO_CLIENTE + " TEXT,"+
                TELEFONO_CLIENTE + " TEXT)");


        db.beginTransaction();
        try {
            ContentValues valuesClientes = new ContentValues();
            for (int i = 0; i < 1; i++) {
                valuesClientes.put(RAZON_SOCIAL_CLIENTE, "SIN DNI");
                valuesClientes.put(RUC_CLIENTE, "00000000");
                valuesClientes.put(DIRECCION_CLIENTE, "SIN DIRECCION");
                valuesClientes.put(CORREO_CLIENTE, "SIN CORREO-");
                valuesClientes.put(TELEFONO_CLIENTE, "SIN TELEFONO");

                db.insert(TABLE_CLIENTES, null, valuesClientes);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }



        db.execSQL("CREATE TABLE "+ TABLE_UNIDADES + " (" +
                TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ID_UNIDAD +" TEXT,"+
                DESCRIPCION_UNIDAD +" TEXT)");


        db.beginTransaction();
        try {
            ContentValues valuesUnidades = new ContentValues();
            for (int i = 0; i < 1; i++) {
                valuesUnidades.put(UNIDAD, "NIU");
                valuesUnidades.put(DESCRIPCION_UNIDAD, "Unidades");
                db.insert(TABLE_UNIDADES, null, valuesUnidades);

                valuesUnidades.put(UNIDAD, "KGM");
                valuesUnidades.put(DESCRIPCION_UNIDAD, "Kilogramo");
                db.insert(TABLE_UNIDADES, null, valuesUnidades);

                valuesUnidades.put(UNIDAD, "MTR");
                valuesUnidades.put(DESCRIPCION_UNIDAD, "Metro");
                db.insert(TABLE_UNIDADES, null, valuesUnidades);

                valuesUnidades.put(UNIDAD, "LTR");
                valuesUnidades.put(DESCRIPCION_UNIDAD, "Litro");
                db.insert(TABLE_UNIDADES, null, valuesUnidades);




            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }





        db.execSQL("CREATE TABLE "+ TABLE_PRODUCTOS + " (" +
                TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ID_PRODUCTO +" TEXT,"+
                DESCRIPCION_PRODUCTO +" TEXT,"+
                UNIDAD_PRODUCTO + " TEXT DEFAULT 'UNI',"+
                PRECIO_PRODUCTO + " TEXT,"+
                PRECIO_PRODUCTO_MAYOREO + " TEXT,"+
                IGV_PRODUCTO + " DOUBLE DEFAULT .18)");



        db.beginTransaction();
        try {
            ContentValues valuesProductos = new ContentValues();
            for (int i = 0; i < 1; i++) {
                valuesProductos.put(ID_PRODUCTO, "001");
                valuesProductos.put(DESCRIPCION_PRODUCTO, "PRODUCTO GENERICO");
                valuesProductos.put(UNIDAD_PRODUCTO, "UNI");
                valuesProductos.put(PRECIO_PRODUCTO, 1.00);
                valuesProductos.put(PRECIO_PRODUCTO_MAYOREO, 1.00);
              //  valuesProductos.put(IGV_PRODUCTO, .18);


                db.insert(TABLE_PRODUCTOS, null, valuesProductos);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }






    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(db);

    }



    public void close() {
        this.close();

    }


    public void addNotes (String _serie,
                              String _folio,
                              String _ruc,
                              String _razon_social,
                              String _direccion,
                              String _moneda) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();


        ContentValues valoresCabecera = new ContentValues();
        valoresCabecera.put(SERIE, _serie);
        valoresCabecera.put(FOLIO, _folio);
        valoresCabecera.put(FECHA, dateFormat.format(date));
        valoresCabecera.put(RUC, _ruc);
        valoresCabecera.put(RAZON_SOCIAL, _razon_social);
        valoresCabecera.put(DIRECCION, _direccion);
        valoresCabecera.put(MONEDA, _moneda);
   //     valoresCabecera.put(GRAVADO, _gravado);
   //     valoresCabecera.put(EXCENTO, _excento);
   //     valoresCabecera.put(INAFECTO, _inafecto);
   //     valoresCabecera.put(SUBTOTAL, _subtotal);
   //     valoresCabecera.put(IGV, _igv);
   //     valoresCabecera.put(TOTAL, _total);

        this.getWritableDatabase().insert(TABLE,null, valoresCabecera);

    }


    public void addNotes_Notas (String _serie,
                          String _folio,
                          String _ruc,
                          String _razon_social,
                          String _direccion,
                          String _moneda,
                                String _serie_rel,
                                String _folio_rel
                            ) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();


        ContentValues valoresCabecera = new ContentValues();
        valoresCabecera.put(SERIE, _serie);
        valoresCabecera.put(FOLIO, _folio);
        valoresCabecera.put(FECHA, dateFormat.format(date));
        valoresCabecera.put(RUC, _ruc);
        valoresCabecera.put(RAZON_SOCIAL, _razon_social);
        valoresCabecera.put(DIRECCION, _direccion);
        valoresCabecera.put(MONEDA, _moneda);
        valoresCabecera.put(SERIE_REL, _serie_rel);
        valoresCabecera.put(FOLIO_REL, _folio_rel);

        //     valoresCabecera.put(GRAVADO, _gravado);
        //     valoresCabecera.put(EXCENTO, _excento);
        //     valoresCabecera.put(INAFECTO, _inafecto);
        //     valoresCabecera.put(SUBTOTAL, _subtotal);
        //     valoresCabecera.put(IGV, _igv);
        //     valoresCabecera.put(TOTAL, _total);

        this.getWritableDatabase().insert(TABLE,null, valoresCabecera);

    }



    public void addNotes_Detalle (int _id, String _producto,
                          String _descripcion,
                          String _unidad,
                          String _cantidad,
                          String _precio,
                          String _igv

    ) {



        ContentValues valoresDetalle = new ContentValues();
        valoresDetalle.put(CABECERA_ID, _id);
        valoresDetalle.put(PRODUCTO, _producto);
        valoresDetalle.put(DESCRIPCION, _descripcion);
        valoresDetalle.put(UNIDAD, _unidad);
        valoresDetalle.put(CANTIDAD, _cantidad);
        valoresDetalle.put(PRECIO_PRODUCTO, _precio);
        valoresDetalle.put(DET_IGV, _igv);

        this.getWritableDatabase().insert(TABLE_DET,null, valoresDetalle);

    }


    public void addNotes_Clientes (String _razon_social_cliente,
                                   String _ruc_cliente,
                                   String _direccion_cliente,
                                   String _telefono_cliente,
                                   String _correo_cliente
                                   ) {



        ContentValues valoresClietes = new ContentValues();
        valoresClietes.put(RAZON_SOCIAL_CLIENTE, _razon_social_cliente);
        valoresClietes.put(RUC_CLIENTE, _ruc_cliente);
        valoresClietes.put(DIRECCION_CLIENTE, _direccion_cliente);
        valoresClietes.put(CORREO_CLIENTE, _correo_cliente);
        valoresClietes.put(TELEFONO_CLIENTE, _telefono_cliente);


        this.getWritableDatabase().insert(TABLE_CLIENTES,null, valoresClietes);

    }



    public void addNotes_Productos (String _producto,
                                   String _descripcion_producto,
                                   String _unidad,
                                   String _precio,
                                    String _precio_mayoreo,
                                   String _igv

    ) {



        ContentValues valoresProductos = new ContentValues();
        valoresProductos.put(PRODUCTO, _producto);
        valoresProductos.put(DESCRIPCION_PRODUCTO, _descripcion_producto);
        valoresProductos.put(UNIDAD_PRODUCTO, _unidad);
        valoresProductos.put(PRECIO_PRODUCTO, _precio);
        valoresProductos.put(PRECIO_PRODUCTO_MAYOREO, _precio_mayoreo);
        valoresProductos.put(IGV_PRODUCTO, _igv);



        this.getWritableDatabase().insert(TABLE_PRODUCTOS,null, valoresProductos);

    }


    public void addNotes_Unidades (String _unidad,
                                    String _descripcion_unidad

    ) {



        ContentValues valoresUnidades = new ContentValues();
        valoresUnidades.put(ID_UNIDAD, _unidad);
        valoresUnidades.put(DESCRIPCION_UNIDAD, _descripcion_unidad);



        this.getWritableDatabase().insert(TABLE_UNIDADES,null, valoresUnidades);

    }





    public Cursor getNotes() {


        String columnas[] = {TABLE_ID, SERIE, FOLIO, RUC,  RAZON_SOCIAL, FECHA, SERIE_REL, FOLIO_REL};
        Cursor c = this.getReadableDatabase().query(TABLE, columnas, null, null, null, null, " _id DESC ");
        return c;

    }

    public Cursor getNotes_detalle(int _id) {
        String columnas[] = {TABLE_ID, PRODUCTO, DESCRIPCION, UNIDAD, CANTIDAD, PRECIO_PRODUCTO,  DET_IGV};
        Cursor c = this.getReadableDatabase().query(TABLE_DET, columnas, "cabecera_id" + "=?",
                new String[] { String.valueOf(_id) },  null, null, null, null);
        //  Cursor c = this.getReadableDatabase().query(TABLE_DET, columnas, null, null, null, null, null);
        return c;
    }


    public Cursor getNotes_series() {
        String columnas[] = {"id", "serie", "folio", "naturaleza"};
        Cursor c = this.getReadableDatabase().query("series", columnas, null, null, null, null, null);
        return c;
    }


    public String get_Naturaleza(String _serie) {
        String _regreso="00";

        String columnas[] = {"id", "serie", "folio", "naturaleza"};
        Cursor c = this.getReadableDatabase().query("series", columnas, "serie" + "=?",
                new String[] { _serie }, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                _regreso = c.getString(3);

            } while (c.moveToNext());
        }
        return _regreso;
    }



    public Cursor getNotes_clientes() {
        String columnas[] = {"_id", "ruc_cliente", "razon_social_cliente"};
        Cursor c = this.getReadableDatabase().query("clientes", columnas, null, null, null, null, null);
        return c;
    }



//        db.execSQL("CREATE TABLE "+ TABLE_PRODUCTOS + " (" +
//    TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
//    ID_PRODUCTO +" TEXT,"+
//    DESCRIPCION_PRODUCTO +" TEXT,"+
//    PRECIO_PRODUCTO + " TEXT,"+
//    PRECIO_PRODUCTO_MAYOREO + " TEXT,"+
//    IGV_PRODUCTO + " DOUBLE DEFAULT .18)");




    public Cursor getNotes_productos() {
        String columnas[] = {TABLE_ID,ID_PRODUCTO, DESCRIPCION_PRODUCTO, UNIDAD_PRODUCTO, PRECIO_PRODUCTO, PRECIO_PRODUCTO_MAYOREO, IGV_PRODUCTO};
        Cursor c = this.getReadableDatabase().query("productos", columnas, null, null, null, null, null);
        return c;
    }


    public Cursor getNotes_unidades() {
        String columnas[] = {TABLE_ID,ID_UNIDAD, DESCRIPCION_UNIDAD};
        Cursor c = this.getReadableDatabase().query("unidades", columnas, null, null, null, null, null);
        return c;
    }




    public Cursor getReg(int _id) {
        String columnas[] = {TABLE_ID, SERIE, FOLIO, RUC, RAZON_SOCIAL, DIRECCION, MONEDA, FECHA, CORREO};

        Cursor c = this.getReadableDatabase().query(TABLE, columnas, TABLE_ID + "=?",
                new String[] { String.valueOf(_id) },  null, null, null, null);
        return c;


    }


    public Cursor getReg_Notas(int _id) {
        String columnas[] = {TABLE_ID, SERIE, FOLIO, RUC, RAZON_SOCIAL, DIRECCION, MONEDA, FECHA, CORREO, SERIE_REL, FOLIO_REL};

        Cursor c = this.getReadableDatabase().query(TABLE, columnas, TABLE_ID + "=?",
                new String[] { String.valueOf(_id) },  null, null, null, null);
        return c;


    }



    public Cursor getReg_Serie(int _id) {
        String columnas[] = {"id", "serie", "naturaleza", "folio" };

        Cursor c = this.getReadableDatabase().query("series", columnas, "id" + "=?",
                new String[] { String.valueOf(_id) },  null, null, null, null);
        return c;
    }



    public Cursor getReg_num_Serie(String _serie) {
        String columnas[] = {"id", "serie", "folio"};

        Cursor c = this.getReadableDatabase().query("series", columnas, "serie" + "=?",
                new String[] { String.valueOf(_serie) },  null, null, null, null);
        return c;
    }






    public Cursor getReg_Empresas(int _id) {
        String columnas[] = {"_id","razon_social_empresa", "ruc_empresa", "direccion_empresa_1", "direccion_empresa_2", "correo_empresa", "telefono_empresa", "licencia"};

        Cursor c = this.getReadableDatabase().query("empresa", columnas, null, null,  null, null, null, null);
                return c;
    }

    public Cursor getReg_TicketPos(int _id) {
        String columnas[] = {"_id","linea01", "linea02", "linea03", "linea04", "linea05", "linea06",
                "ruc_empresa",
                "base01", "base02", "base03", "base04"};

        Cursor c = this.getReadableDatabase().query("empresa", columnas, null, null,  null, null, null, null);
        return c;
    }

    public Cursor getReg_Licencia(int _id) {
        String columnas[] = {"_id","licencia"};

        Cursor c = this.getReadableDatabase().query("empresa", columnas, null, null,  null, null, null, null);
        return c;
    }


    public Cursor getReg_Clientes(int _id) {
        String columnas[] = {"_id", "ruc_cliente","razon_social_cliente","direccion_cliente","correo_cliente","telefono_cliente" };

        Cursor c = this.getReadableDatabase().query("clientes", columnas, "_id" + "=?",
                new String[] { String.valueOf(_id) },  null, null, null, null);
        return c;
    }


    public Cursor getReg_Productos(int _id) {
        String columnas[] = {"_id", "producto","descripcion_producto","precio", "precio_mayoreo", "igv", "unidad" };

        Cursor c = this.getReadableDatabase().query("productos", columnas, "_id" + "=?",
                new String[] { String.valueOf(_id) },  null, null, null, null);
        return c;
    }


    public Cursor getReg_Productos_codigo(String _producto) {
        String columnas[] = {"_id", "producto","descripcion_producto","precio", "precio_mayoreo", "igv", "unidad" };

        Cursor c = this.getReadableDatabase().query("productos", columnas, "producto" + "=?",
                new String[] { String.valueOf(_producto) },  null, null, null, null);
        return c;
    }



    public Cursor getReg_Unidades(int _id) {
        String columnas[] = {"_id", "unidad","descripcion_unidad"};

        Cursor c = this.getReadableDatabase().query("unidades", columnas, "_id" + "=?",
                new String[] { String.valueOf(_id) },  null, null, null, null);
        return c;
    }



    //public static final String CABECERA_ID = "cabecera_id";
    //public static final String PRODUCTO = "producto";
    //public static final String DESCRIPCION = "descripcion";
    //public static final String UNIDAD = "unidad";
    //public static final String PRECIO = "precio";
    //public static final String CANTIDAD = "cantidad";
    //public static final String DET_SUBTOTAL = "det_subtotal";
    //public static final String DET_IGV = "det_igv";
    //public static final String DET_TOTAL = "det_total";


    public Cursor getReg_Detalle(int _id) {
        String columnas[] = {"_id", "cabecera_id","producto", "descripcion", "unidad","cantidad", "precio","det_igv"};

        Cursor c = this.getReadableDatabase().query("detalle", columnas, "_id" + "=?",
                new String[] { String.valueOf(_id) },  null, null, null, null);
        return c;
    }


    public void addNotes_Series (String _Serie,
                                 String _Folio,
                                 String _Naturaleza

    ) {



        ContentValues valoresSeries = new ContentValues();
        valoresSeries.put("serie", _Serie);
        valoresSeries.put("folio", _Folio);
        valoresSeries.put("naturaleza", _Naturaleza);



        this.getWritableDatabase().insert("series",null, valoresSeries);

    }



}
