package GoogleAPIs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;

public class GetMyPlaceServiceImpl implements GetMyPlaceService {

	public void registerPlaceFromTextFile() {

		File file = new File("/Users/i304223/Desktop/TempDataFile.txt");
		try {
			FileReader fr = new FileReader(file);
			char[] data = new char[1024];
			fr.read(data);
			String jsonData = String.valueOf(data);
			parseJson(jsonData);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parseJson(String jsonData) {
		JSONObject json = new JSONObject(jsonData);

		String status = json.getString("status");
		if (status.equalsIgnoreCase("OK")) {
			JSONArray results = json.getJSONArray("results");
			for (int i = 0; i < results.length(); i++) {
				JSONObject result = results.getJSONObject(i);
				String name = result.getString("name");
				String types = result.getString("types");
				Places place = new Places();
				place.setName(name);
				place.setTypes(types);
				
				registerPlace(place);

			}
		}

	}

	private void registerPlace(Places place) {
		Session session = new AnnotationConfiguration().configure()
				.buildSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.persist(place);
		t.commit();
		session.close();
	}

	public Places getTypePlace(String type) {
		Session session = new AnnotationConfiguration().configure()
				.buildSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		return new Places();
	}

//	public static final String API_URL = "https://maps.googleapis.com/maps/api/place/";
//
//	public void getNearbyPlaces() {
//		try {
//			String uri = "https://maps.googleapis.com/maps/api/place/search/json?location=12.9149149,77.5584133&radius=50000&types=hindu_temple&sensor=false&key=AIzaSyCmZXJYXKQ2qQjnS4t4a3BkZblVkjsts18";
//			getPlaces(uri);
//		} catch (Exception e) {
//
//		}
//	}
//
//	@RequestMapping("/temple")
//	private void getPlaces(String uri) throws IOException {
//
//		HttpGet get = new HttpGet(uri);
//		HttpClient client = HttpClientBuilder.create().build();
//		readString(client.execute(get));
//	}
//
//	private String readString(HttpResponse response) throws IOException {
//		String str = IOUtils.toString(response.getEntity().getContent(),
//				"UTF-8");
//		if (str == null || str.trim().length() == 0) {
//			return null;
//		}
//		return str.trim();
//	}
//
//	public static void main(String[] args) {
//		GetMePlaces gmp = new GetMePlaces();
//		gmp.getNearbyPlaces();
//	}

}
