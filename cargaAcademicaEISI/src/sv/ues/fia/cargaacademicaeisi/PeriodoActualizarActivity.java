package sv.ues.fia.cargaacademicaeisi;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PeriodoActualizarActivity extends Activity implements OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnActIdPeriodo;
	private EditText edtActFechaIni;
	private EditText edtActFechaFin;
	private List<String> idPeriodos;
	private ArrayAdapter<String> adapter;
	//****************
	private String año;
	private String ciclo1;
	private Button boton1_fechainicio;
	private Button boton1_fechafin;
	private TextView fechaini;
	private TextView fechafin;
	private String fecha_ini;
	private String fecha_fin;
	private int year;
	private int month;
	private int day;
	private int year2;
	private int month2;
	private int day2;
	static final int DATE_DIALOG_ID = 999;
	private int var;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_periodo_actualizar);
		helper = new ControlDB(this);
		spnActIdPeriodo = (Spinner) findViewById(R.id.spin_Act_Per);
		//edtActFechaIni = (EditText) findViewById(R.id.edtFechaIniDate);
		//edtActFechaFin= (EditText) findViewById(R.id.edtfechaFinDate);
		
		helper.abrir();
		idPeriodos = helper.getAllIdPeriodos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idPeriodos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActIdPeriodo.setAdapter(adapter);
		spnActIdPeriodo.setOnItemSelectedListener(this);
		
		
		this.boton1_fechainicio =(Button) findViewById(R.id.button1_fechainicio3);
		this.boton1_fechafin =(Button) findViewById(R.id.button2_fechafin3);
		this.fechaini= (TextView) findViewById(R.id.textView1_inicio3);
		this.fechafin= (TextView) findViewById(R.id.textView2_fin3);
		addListenerOnButton();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.periodo_actualizar, menu);
		return true;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2 ,long arg3) {
		String idPeriodo = arg0.getItemAtPosition(arg2).toString();
		helper.abrir();
		PERIODO periodo = helper.consultarPeriodo(idPeriodo);
		helper.cerrar();
		if (periodo == null) {
			Toast.makeText(
					this,
					"Identificador de periodo: " + idPeriodo
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			fechaini.setText(periodo.getFechaIni());
			fechafin.setText(periodo.getFechaFin());
		}
 }
	
    public void actualizarPeriodo(View v) {
    	fecha_ini=fechaini.getText().toString();
		fecha_fin=fechafin.getText().toString();
    	
		if (fecha_ini.equalsIgnoreCase("") || fecha_fin.equalsIgnoreCase("")) {
			String msj = "Importante: Todos los campos son obligatorios!";
			Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
		} else {
			
					//******************************
				PERIODO periodo = new PERIODO();
				periodo.setIdPeriodo(spnActIdPeriodo.getSelectedItem().toString());
				periodo.setFechaIni(fechaini.getText().toString());
				periodo.setFechaFin(fechafin.getText().toString());
				helper.abrir();
				String estado = helper.actualizar(periodo);
				helper.cerrar();
				Toast.makeText(this, estado, Toast.LENGTH_LONG).show();

	   }//fin else validacion campos vacios

	}
    @Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}
	
  //**********************************************************************
  		//METODOS DE AGREGAR FECHA
  			public void addListenerOnButton() {
  				final Calendar c = Calendar.getInstance();
  				year = c.get(Calendar.YEAR);
  				month = c.get(Calendar.MONTH);
  				day = c.get(Calendar.DAY_OF_MONTH);

  				boton1_fechainicio.setOnClickListener(new OnClickListener() {

  					@Override
  					public void onClick(View v) {
  						showDialog(DATE_DIALOG_ID);
  						var=0;
  					}

  				});
  				boton1_fechafin.setOnClickListener(new OnClickListener() {

  					@Override
  					public void onClick(View v) {
  						showDialog(DATE_DIALOG_ID);
  						var=1;
  					}

  				});

  			}//fin addListenerOnButton

  			@Override
  			protected Dialog onCreateDialog(int id) {
  				switch (id) {
  				case DATE_DIALOG_ID:
  					// set date picker as current date
  					return new DatePickerDialog(this, datePickerListener, year, month,
  							day);	
  				}
  				return null;
  			}//fin onCreateDialog

  			private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

  				// when dialog box is closed, below method will be called.
  				public void onDateSet(DatePicker view, int selectedYear,
  						int selectedMonth, int selectedDay) {

  					// set selected date into textview
  					if(var==0){
  						year = selectedYear;
  						month = selectedMonth;
  						day = selectedDay;
  						fechaini.setText(new StringBuilder().append(day)
  							.append("/").append(month + 1).append("/").append(year)
  							.append(""));
  					}
  					else{
  						year2 = selectedYear;
  						month2 = selectedMonth;
  						day2 = selectedDay;
  						fechafin.setText(new StringBuilder().append(day2)
  								.append("/").append(month2 + 1).append("/").append(year2)
  								.append(""));		
  					}
  				}
  			};
    
    
    

}