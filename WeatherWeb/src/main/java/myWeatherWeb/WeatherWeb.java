package myWeatherWeb;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WeatherWeb
 */
@WebServlet("/WeatherWeb")
public class WeatherWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public WeatherWeb() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//String inputdata=request.getParameter("SearchText");
		//System.out.println(inputdata);

		//API Key
        String apiKey="c8b2fe5fba1ce4bf62b3e23a95fb3573";
        //User Input
        String CityName=request.getParameter("SearchText");
        //URLSetup
        String apiUrl="https://api.openweathermap.org/data/2.5/weather?q="+CityName+"&appid="+apiKey;


        try {
        	//API Integration
        URL url=new URL(apiUrl);
        //Connection
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //Object for reading inputdata
        InputStream Inputestream=connection.getInputStream();
        InputStreamReader reader=new InputStreamReader(Inputestream);
        //reading a data using scanner
        Scanner sc=new Scanner(reader);
        StringBuilder responseContent=new StringBuilder();

        //scanning
        while(sc.hasNext()) {

        	//scanning line by line
        	responseContent.append(sc.nextLine());
        }
        sc.close();

        // Datta is getting in JSON Format(Java scrip Object Notetion)

       //System.out.println(responseContent);

        //TypeCastin String To JSON To

        Gson gson=new Gson();
        JsonObject jsonobject=gson.fromJson(responseContent.toString(),JsonObject.class);
        //System.out.println(jsonobject);

        //Separating data from JSON Object to different properties

        //date & Time
        long dateTimeTemp=jsonobject.get("dt").getAsLong()*1000;
        String date=new Date(dateTimeTemp).toString();

       // long timestamp = jsonobject.get("dt").getAsLong();

      /* // Convert timestamp to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC);
        dateTime = dateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneOffset.ofHoursMinutes(5,34)).toLocalDateTime();

        // Get the date and time from the LocalDateTime object
        String date = dateTime.toLocalDate().toString();
        String time = dateTime.toLocalTime().toString();

        // Print the date and time
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);*/


        //Temprecture
        double temperatureKelvin= jsonobject.getAsJsonObject("main").get("temp").getAsDouble();

		int tempreatureCelsius=(int) (temperatureKelvin-273.15);

		//Humidity
        int humidity=jsonobject.getAsJsonObject("main").get("humidity").getAsInt();

        //Pressure
        int pressure=jsonobject.getAsJsonObject("main").get("pressure").getAsInt();


        String country=jsonobject.getAsJsonObject("sys").get("country").toString();


        //Wind speed
        double windSpeed=jsonobject.getAsJsonObject("wind").get("speed").getAsDouble();

        //Weather Condition
       //System.out.println("Before weather condiition check");
       //String weatherCondition=jsonobject.getAsJsonObject("weather").get(0).getAsJsonObject().get("main").getAsString();
       String weatherCondition = jsonobject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
      // System.out.println(weatherCondition);

        //After getting Data into right format seating data for JSP page
        request.setAttribute("CityName", CityName);
        request.setAttribute("date", date);
        //request.setAttribute("time",time);
        request.setAttribute("tempreature", tempreatureCelsius);
      //  request.setAttribute("weatherCondition", weatherCondition);
        request.setAttribute("humidity", humidity);
        request.setAttribute("windSpeed", windSpeed);
        request.setAttribute("weatherCondition", weatherCondition );
        request.setAttribute("pressure", pressure);
        request.setAttribute("country", country);

        // closing the connection

        connection.disconnect();
        //System.out.println(date);
        request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        catch(ProtocolException e){
        	System.out.println(e);

        }


}
}
