package sv.ues.fia.cargaacademicaeisi;

import java.io.File;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CamaraActivity extends Activity {
	Button TomarFoto;
	ImageView image;
	final int FOTOGRAFIA = 654;
	Uri file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camara);
		TomarFoto = (Button) findViewById(R.id.mainbttomarfoto);
		image = (ImageView) findViewById(R.id.mainimage);
		TomarFoto.setOnClickListener(onClick);
		if (savedInstanceState != null) {
			if (savedInstanceState.getString("Foto") != null) {
				image.setImageURI(Uri.parse(savedInstanceState
						.getString("Foto")));
				file = Uri.parse(savedInstanceState.getString("Foto"));
			}
		}
	}

	public void onSaveInstanceState(Bundle bundle) {
		if (file != null) {
			bundle.putString("Foto", file.toString());
		}
		super.onSaveInstanceState(bundle);
	}

	View.OnClickListener onClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			File photo = new File(Environment.getExternalStorageDirectory(),
					String.valueOf(Calendar.getInstance().getTimeInMillis())
							+ ".jpg");
			file = Uri.fromFile(photo);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
			startActivityForResult(intent, FOTOGRAFIA);
		}
	};

	@Override
	public void onActivityResult(int RequestCode, int ResultCode, Intent intent) {
		if (RequestCode == FOTOGRAFIA) {
			if (ResultCode == RESULT_OK) {
				image.setImageURI(file);
			} else {
				Toast.makeText(getApplicationContext(), "fotografia No tomada",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camara, menu);
		return true;
	}

}
