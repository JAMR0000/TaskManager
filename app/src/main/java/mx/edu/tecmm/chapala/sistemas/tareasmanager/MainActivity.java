package mx.edu.tecmm.chapala.sistemas.tareasmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView todo;
    private EditText taskValue;
    private ArrayList<String> listaValores;
    private ArrayAdapter<String> adapterValores;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todo = (ListView) findViewById(R.id.taskList);
        todo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                verficar(i);
            }
        });
        taskValue = (EditText) findViewById(R.id.taskValue);
        listaValores = new ArrayList<>();
        builder = new AlertDialog.Builder(this);
        adapterValores = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1,listaValores);
        todo.setAdapter(adapterValores);
    }

    public void agregarTarea(View v){
        listaValores.add(taskValue.getText().toString());
        adapterValores.notifyDataSetChanged();
        taskValue.setText("");
    }
    public void verficar(int id){

        builder.setMessage("Tarea -> "+listaValores.get(id));
        builder.setCancelable(false);
        builder.setPositiveButton("Terminada", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listaValores.remove(id);
                adapterValores.notifyDataSetChanged();
                todo.setAdapter(adapterValores);
                Toast.makeText(getApplicationContext(),"Tarea Terminada",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Cancelado, Tarea Pendiente",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.setTitle("Deseas finalizar esta tarea?");
        alert.show();
    }
}