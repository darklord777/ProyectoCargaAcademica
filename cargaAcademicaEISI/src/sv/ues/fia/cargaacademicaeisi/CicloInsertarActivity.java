package sv.ues.fia.cargaacademicaeisi;

import java.util.Calendar;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CicloInsertarActivity extends Activity {
	ControlDB helper;
	Spinner anio;
	Spinner ciclo;
	private String fecha_ini;
	private String fecha_fin;
	private String año;
	private String ciclo1;
	
	private int year;
	private int month;
	private int day;
	private int year2;
	private int month2;
	private int day2;
	private Button boton1_fechainicio;
	private Button boton1_fechafin;
	private TextView fechaini;
	private TextView fechafin;
	
	static final int DATE_DIALOG_ID = 999;
	private int var;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_insertar);
		helper = new ControlDB(this);
		anio = (Spinner) findViewById(R.id.spinner_anio_ciclo);
		ciclo = (Spinner) findViewById(R.id.spinner_ciclo);
		this.boton1_fechainicio =(Button) findViewById(R.id.button1_fechainicio);
		this.boton1_fechafin =(Button) findViewById(R.id.button2_fechafin);
		this.fechaini= (TextView) findViewById(R.id.textView1_fechainicio);
		this.fechafin= (TextView) findViewById(R.id.textView2_fechafin);
		//fecha_ini = (EditText) findViewById(R.id.editText1_fechainicio);
		//fecha_fin = (EditText) findViewById(R.id.editText2_fechafin);

		/** codigo llenado de spinner años */
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.anios_ciclo, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		anio.setAdapter(adapter);

		anio.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				año = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

		/** codigo llenado de spinner años */
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.ciclos, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ciclo.setAdapter(adapter2);

		ciclo.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				ciclo1 = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

	addListenerOnButton();
	}// fin metod oncreate

	public void insertarCiclo(View v) {
		fecha_ini=fechaini.getText().toString();
		fecha_fin=fechafin.getText().toString();


		if (fecha_ini.equalsIgnoreCase("") || fecha_fin.equalsIgnoreCase("")) {
			String msj = "Importante: Todos los campos son obligatorios!";
			Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
		} else {
			if(year<=year2 && month<month2){
				if(year==Integer.parseInt(año) && year2==Integer.parseInt(año)){
				String regInsertados;
					Ciclo ciclo = new Ciclo();
					ciclo.setAnio(año);
					ciclo.setNumero(ciclo1);
					ciclo.setFechaini(fecha_ini);
					ciclo.setFechafin(fecha_fin);
					helper.abrir();
					regInsertados = helper.insertarCiclo(ciclo);
					helper.cerrar();
					Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
				}else{
					String msj3 = "Importante: El año de fecha inicio y fin debe coincidir con el año del ciclo qselecionado anteriormente!";
					Toast.makeText(this, msj3, Toast.LENGTH_SHORT).show();
				}//fin else año correcto segun spinner
			
			}
			else{
				String msj2 = "Importante: La fecha de inicio de Ciclo debe ser Menor que la Fecha Fin!";
				Toast.makeText(this, msj2, Toast.LENGTH_SHORT).show();
				
			}//fin else validacion fecha
	   }//fin else validacion campos vacios
		
	}//fin metodo insertar

	public void limpiarTexto(View v) {

		fechaini.setText("");
		fechafin.setText("");
		anio.setSelection(0);
		ciclo.setSelection(0);

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
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_insertar, menu);
		return true;
	}

}
