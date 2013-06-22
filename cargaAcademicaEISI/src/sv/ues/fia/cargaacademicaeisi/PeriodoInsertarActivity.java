package sv.ues.fia.cargaacademicaeisi;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PeriodoInsertarActivity extends Activity {
	ControlDB helper;
	EditText editIdPeriodo;
	EditText editFechaIni;
	EditText editFechaFin;
	//**************************
	private Button boton1_fechainicio;
	private Button boton1_fechafin;
	private String fecha_ini;
	private String fecha_fin;
	private String id_periodo;
	private String año;
	private String ciclo1;
	private int year;
	private int month;
	private int day;
	private int year2;
	private int month2;
	private int day2;
	static final int DATE_DIALOG_ID = 999;
	private int var;
	private TextView fechaini;
	private TextView fechafin;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_periodo_insertar);
		helper = new ControlDB(this);
		editIdPeriodo = (EditText) findViewById(R.id.idCArgo);
		//editFechaIni = (EditText) findViewById(R.id.fechaIni);
		//editFechaFin = (EditText) findViewById(R.id.fechaFin);
		//******************
		this.boton1_fechainicio =(Button) findViewById(R.id.button1_fechainicio2);
		this.boton1_fechafin =(Button) findViewById(R.id.button2_fechafin2);
		this.fechaini= (TextView) findViewById(R.id.textView1_fechainicio2);
		this.fechafin= (TextView) findViewById(R.id.textView2_fechafin2);
		addListenerOnButton();
	}
	public void insertarPeriodo(View v) {
		
		fecha_ini = fechaini.getText().toString();
		fecha_fin = fechafin.getText().toString();
		id_periodo = editIdPeriodo.getText().toString();
		
		if (fecha_ini.equalsIgnoreCase("") || fecha_fin.equalsIgnoreCase("") || id_periodo.equalsIgnoreCase("")) {
			String msj = "Importante: Todos los campos son obligatorios!";
			Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
		} else {
			if(year<=year2 && month<month2){
									
					String IdPeriodo=editIdPeriodo.getText().toString();
					String FechaIni=fechaini.getText().toString();
					String FechaFin=fechafin.getText().toString();
					String regInsertados;	
						PERIODO periodo=new PERIODO();
						periodo.setIdPeriodo(IdPeriodo);
						periodo.setFechaIni(FechaIni);
						periodo.setFechaFin(FechaFin);
						helper.abrir();
						regInsertados=helper.insertar(periodo);
						helper.cerrar();
						Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	
				
			
			}
			else{
				String msj2 = "Importante: La fecha de inicio debe ser Menor que la Fecha Fin!";
				Toast.makeText(this, msj2, Toast.LENGTH_SHORT).show();
				
			}//fin else validacion fecha
	   }//fin else validacion campos vacios
		
		
		
		
		
		
		
		
		
		

			
			
			
			
			
			
	}
	public void limpiarPeriodo(View v) {
		editIdPeriodo.setText("");
		editFechaIni.setText("");
		editFechaFin.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.periodo_insertar, menu);
		return true;
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