package es.gva.edu.iesjuandegaray.bicis;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class DatosJSon {

private static String API_URL;

private String datos = "";

private String[] values;

private int numEst;

public DatosJSon(int nE) {

    numEst = nE;

    datos = "";

    API_URL =
    "https://geoportal.valencia.es/server/rest/services/OPENDATA/Trafico/MapServer/228/query"
    + "?where=1%3D1"
    + "&outFields=*"
    + "&returnGeometry=true"
    + "&f=json";

    values = new String[numEst];

    for (int i = 0; i < numEst; i++) {
        values[i] = "";
    }
}

public void mostrarDatos(int nE) {

    numEst = nE;

    datos = "";

    API_URL =
    "https://geoportal.valencia.es/server/rest/services/OPENDATA/Trafico/MapServer/228/query"
    + "?where=1%3D1"
    + "&outFields=*"
    + "&returnGeometry=true"
    + "&f=json";
    
    if (API_URL.isEmpty()) {

        setDatos(getDatos().concat("La URL de la API no está especificada."));
        return;
    }

    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

        HttpGet request = new HttpGet(API_URL);

        HttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();

        if (entity != null) {

            String result = EntityUtils.toString(entity);

            JSONObject jsonObject = new JSONObject(result);

            JSONArray features = jsonObject.getJSONArray("features");

            System.out.println("Número de estaciones: " + features.length());

            for (int i = 0; i < numEst; i++) {

                JSONObject station = features.getJSONObject(i);

                JSONObject attributes =
                        station.getJSONObject("attributes");

                String nombre =
                        attributes.getString("name");

                String direccion = attributes.getString("address");

                int disponibles = attributes.getInt("available");

                int libres = attributes.getInt("free");

                int total = attributes.getInt("total");
                
                values[i] =
                        nombre + ";" +
                        direccion + ";" +
                        disponibles + ";" +
                        libres + ";" +
                        total;

                JSONObject geometry = station.getJSONObject("geometry");

                double x = geometry.getDouble("x");
                double y = geometry.getDouble("y");

                String coords = "";
                String lat = "";
                String lon = "";
                String[] partes;

                coords = ConversionGeoLongLat.conversion(x, y);

                partes = coords.split(",");

                lat = partes[0].trim();
                lon = partes[1].trim();
                
                System.out.println("Nombre: " + nombre);
                System.out.println("Dirección: " + direccion);
                System.out.println("Disponibles: " + disponibles);
                System.out.println("Libres: " + libres);
                System.out.println("Total: " + total);
                System.out.println("Latitud: " + lat);
                System.out.println("Longitud: " + lon);
                System.out.println();

            }
            
            for (String v : values) {
                System.out.println("VALUE = " + v);
            }
        }

    } catch (IOException e) {

        e.printStackTrace();

    }

}

public String getDatos() {
    return datos;
}

public void setDatos(String datos) {
    this.datos = datos;
}

public String[] getValues() {
    return values;
}

public void setValues(String[] values) {
    this.values = values;
}

public int getNumEst() {
    return numEst;
}

public void setNumEst(int numEst) {
    this.numEst = numEst;
}


}
