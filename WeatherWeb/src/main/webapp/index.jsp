<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My WeatherWeb</title>
<link rel="stylesheet" href="style2.css">
</head>
<body>
 <div class="imagb">  </div>
 

<div class="card">
     <p class="city">${CityName}</p>


	  <p class="date"> ${date}  <span> ${time} </span></p>
	  <div class="box">
	    <img src="./images/cityscape.png" >
	  </div>
	
	    
	    
	 
		 <div class="imagT">
		     <img src="./images/warm-up.png" >
		 </div>
		  <p class="temp">  ${tempreature}<sup>c</sup></p>
		  
		  <div class="imagH">
		     <img src="./images/humidity.png" >
		 </div>
		  <p class="humi">${humidity}<sup>g.m<sup>-3</sup></sup></p>
		  
		  
		   <div class="imagW">
		     <img src="./images/wind.png" >
		 </div>
		  <p class="wind"> ${windSpeed}<sup>knot</sup> </p>
		
		
		<div class="imagWea">
		     <img src="./images/weatherC.png" >
		 </div>
        <p class="weather"> ${weatherCondition} </p>
        
        <div class="imagP">
		     <img src="./images/pressure.png" >
		 </div>
        <p class="Pressure"> ${pressure} <sup>hPa</sup></p>
        
        
         <div class="imagC">
		     <img src="./images/earth.png" >
		 </div>
        <p class="country"> ${country}<sup>country</sup></p>
       
       
       
		 
	  
</div>
 

 
 
 
  
   
  
  <!-- <h3>
  
  </h3>
   -->
  
</body>
</html>