package sv.ues.fia.cargaacademicaeisi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ControladorServicio {


	public static String obtenerRespuestaPeticion(String url, Context ctx) {

		String respuesta = " ";

		// Estableciendo tiempo de espera del servicio
		HttpParams parametros = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(parametros, 3000);
		HttpConnectionParams.setSoTimeout(parametros, 5000);

		// Creando objetos de conexion
		HttpClient cliente = new DefaultHttpClient(parametros);
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpRespuesta = cliente.execute(httpGet);
			StatusLine estado = httpRespuesta.getStatusLine();
			int codigoEstado = estado.getStatusCode();
			if (codigoEstado == 200) {
				HttpEntity entidad = httpRespuesta.getEntity();
				respuesta = EntityUtils.toString(entidad);
			}
		} catch (Exception e) {
			Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG)
					.show();
			// Desplegando el error en el LogCat
			Log.v("Error de Conexion", e.toString());
		}
		return respuesta;
	}

	public static String obtenerRespuestaPost(String url, JSONObject obj,
			Context ctx) {
		String respuesta = " ";
		try {
			HttpParams parametros = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(parametros, 3000);
			HttpConnectionParams.setSoTimeout(parametros, 5000);

			HttpClient cliente = new DefaultHttpClient(parametros);
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("content-type", "application/json");
			
			StringEntity nuevaEntidad = new StringEntity(obj.toString());
			httpPost.setEntity(nuevaEntidad);
			Log.v("Peticion",url);
			Log.v("POST", httpPost.toString());
			HttpResponse httpRespuesta = cliente.execute(httpPost);
			StatusLine estado = httpRespuesta.getStatusLine();
			
			int codigoEstado = estado.getStatusCode();
			if (codigoEstado == 200) {
				respuesta = Integer.toString(codigoEstado);
				Log.v("respuesta",respuesta);
			}
			else{
				Log.v("respuesta",Integer.toString(codigoEstado));
			}
		} catch (Exception e) {
			Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG)
					.show();
			// Desplegando el error en el LogCat
			Log.v("Error de Conexion", e.toString());
		}

		return respuesta;
	}

	public static List<Materia> obtenerMateriasLocal(String json, Context ctx) {

		List<Materia> listaMaterias = new ArrayList<Materia>();

		try {
			JSONArray materiasJSON = new JSONArray(json);
			for (int i = 0; i < materiasJSON.length(); i++) {

				JSONObject obj = materiasJSON.getJSONObject(i);

				Materia materia = new Materia();
				materia.setCodigomateria(obj.getString("codigomateria"));
				materia.setNom_materia(obj.getString("nommateria"));

				listaMaterias.add(materia);
			}
			return listaMaterias;
		} catch (Exception e) {
			Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG)
					.show();
			return null;
		}

	}

	public static List<Materia> obtenerMateriasExterno(String json, Context ctx) {

		List<Materia> listaMaterias = new ArrayList<Materia>();

		try {
			JSONArray materiasJSON = new JSONArray(json);
			for (int i = 0; i < materiasJSON.length(); i++) {

				JSONObject obj = materiasJSON.getJSONObject(i);

				Materia materia = new Materia();
				materia.setCodigomateria(obj.getString("Codigomateria"));
				materia.setNom_materia(obj.getString("Nommateria"));

				listaMaterias.add(materia);
			}
			return listaMaterias;
		} catch (Exception e) {
			Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG)
					.show();
			return null;
		}

	}
	
	public static void insertarMateriaLocal(String url, JSONObject obj, Context ctx) {
		String respuesta = obtenerRespuestaPost(url, obj, ctx);
		try {
			if(respuesta.equals("200"))
				Toast.makeText(ctx, "Insercion Correcta", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
			Log.v("",respuesta);
		} catch (Exception e) {
			Toast.makeText(ctx, "Error en la respuesta JSON", Toast.LENGTH_LONG).show();
		}
	}

	public static void insertarMateriaExterno(String peticion, Context ctx) {

		String json = obtenerRespuestaPeticion(peticion, ctx);
		try {
			JSONObject resultado = new JSONObject(json);
			int respuesta = resultado.getInt("resultado");
			if (respuesta == 1)
				Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG)
						.show();
			else
				Toast.makeText(ctx, "Error registro duplicado",
						Toast.LENGTH_LONG).show();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static String obtenerPromedioJSON(String json, Context ctx) {
		try {
			JSONArray objs = new JSONArray(json);
			if (objs.length() != 0)
				return objs.getJSONObject(0).getString("promedio");
			else {
				Toast.makeText(ctx, "Error carnet no existe", Toast.LENGTH_LONG)
						.show();
				return " ";
			}
		} catch (JSONException e) {
			Toast.makeText(ctx, "Error con la respuesta JSON",
					Toast.LENGTH_LONG).show();
			return " ";
		}
	}
}
