package global.factura.qrpos;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import zj.com.cn.bluetooth.sdk.R;

public class Sincronizar extends Activity {

    Button _sincronizar, Salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar);


        _sincronizar = (Button) findViewById(R.id.Button_Sincronizar);
        Salir= (Button) findViewById(R.id.Button_Salir);


        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Salir();


            }
        });




    }


    private void _Salir() {
        Intent intent = new Intent(this, init_alfilPOS.class);
        startActivity(intent);
    }




}
