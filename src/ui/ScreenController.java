package ui;

import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import model.*;
import model.Date;

public class ScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Menu order;

    @FXML
    private TextField maxFlights;

    @FXML
    private Button generate;
    
    @FXML
    private VBox airlineCl;

    @FXML
    private Label airline;

    @FXML
    private VBox numberCl;

    @FXML
    private Label number;

    @FXML
    private VBox cityCl;

    @FXML
    private Label city;

    @FXML
    private VBox dateCl;

    @FXML
    private Label date;

    @FXML
    private VBox hourCl;

    @FXML
    private Label hour;

    @FXML
    private VBox doorCl;

    @FXML
    private Label door;
    
    @FXML
    private MenuButton searchType;

    @FXML
    private TextField searchText;

    @FXML
    private Button search;
    
    @FXML
    private Label page;
    
    @FXML
    private MenuButton typeS;
    
    private Flight[] flights;
    
    private Flight first;
    
    private int type;
    
    private int typeSearch;

    @FXML
    void byAirline(ActionEvent event) {
    	clear();
    	Comparator<Flight> flightComparator = new FlightAirlineComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
    }

    @FXML
    void byCity(ActionEvent event) {
    	clear();
    	Comparator<Flight> flightComparator = new FlightCityComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
    }

    @FXML
    void byDate(ActionEvent event) {
    	clear();
    	Comparator<Flight> flightComparator = new FlightDateComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
    }

    @FXML
    void byDoor(ActionEvent event) {
    	clear();
    	Comparator<Flight> flightComparator = new FlightDoorComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
    }

    @FXML
    void byNumber(ActionEvent event) {
    	clear();
    	Arrays.sort(flights);
    	fill();
    }

    @FXML
    void generate(ActionEvent event) {
    	clear();
    	flights = new Flight[Integer.parseInt(maxFlights.getText())];
    	for(int i=0; i<Integer.parseInt(maxFlights.getText()); i++) {
    		
    		Random rNumber = new Random();
    		String number = rNumber.nextInt(9999)+"";
    		
    		Random rCity = new Random();
    		int option = rCity.nextInt(5)+1;
    		
    		Flight current = new Flight(createDate(), createHour(), createAirline(), number, createCity(option), option);
    		flights[i] = current;
    	}
    	
    	Comparator<Flight> flightComparator = new FlightDateComparator();
		
		Arrays.sort(flights, flightComparator);
    	fill();
    	
    	Random rNumber = new Random();
		String number = rNumber.nextInt(9999)+"";
		
		Random rCity = new Random();
		int option = rCity.nextInt(5)+1;
    	
		Flight fl = new Flight(createDate(), createHour(), createAirline(), number, createCity(option), option);

    	
    	if(first == null) {
			first = fl;
		}else {
			Flight current = first;
			while(current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(fl);
		}
    	
    	order.setDisable(false);
    	
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("Seaching Flights");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText("Se crea la lista enlazada pero lo demás sigue igual, te quiero Sara <3 JAJAJAJAJA");
    	info.show();
    }
    
    public void fill() {
    	int pages = (flights.length/17);
    	for(int j=0; j<=pages; j++) {
    		if(j+1 == Integer.parseInt(page.getText())) {
    			for(int i=(17*j); i<17+(17*j) && i<flights.length; i++) {
    				Label airline = new Label(flights[i].getAirline());
    				Label number = new Label (flights[i].getNumber()+"");
    				Label city = new Label (flights[i].getCity());
    				Label date = new Label (flights[i].getDate()+"");
    				Label hour = new Label (flights[i].getHour()+"");
    				Label door = new Label (flights[i].getDoor()+"");
    				
    				airlineCl.getChildren().add(airline);
    				numberCl.getChildren().add(number);
    				cityCl.getChildren().add(city);
    				dateCl.getChildren().add(date);
    				hourCl.getChildren().add(hour);
    				doorCl.getChildren().add(door);
    			}
    		}
    	}
    }
    
    public void clear() {
    	airlineCl.getChildren().clear();
    	airlineCl.getChildren().add(airline);
    	
    	numberCl.getChildren().clear();
    	numberCl.getChildren().add(number);
    	
    	cityCl.getChildren().clear();
    	cityCl.getChildren().add(city);
    	
    	dateCl.getChildren().clear();
		dateCl.getChildren().add(date);
		
		hourCl.getChildren().clear();
		hourCl.getChildren().add(hour);
		
		doorCl.getChildren().clear();
		doorCl.getChildren().add(door);
    	
    }
    
    public Date createDate() {
    	Random rDay = new Random();
		int day = rDay.nextInt(30)+1;
		Random rMonth = new Random();
		int month = rMonth.nextInt(11)+1;
		Random rYear = new Random();
		int year = rYear.nextInt(7)+2012;
		Date date = new Date(day, month, year);
    	return date;
    }
    
    public Hour createHour() {
    	Random rHour = new Random();
		int h = rHour.nextInt(11)+1;
		Random rMinute = new Random();
		int minute = rMinute.nextInt(59)+1;
		Random rMoment = new Random();
		int option = rMoment.nextInt(2);
		String moment = "";
		if(option == 0) {
			moment = "AM";
		}else {
			moment = "PM";
		}
		Hour hour = new Hour(h, minute, moment);
    	return hour;
    }
    
    public String createCity(int option) {
		String city = "";
		if(option == 0) {
			city = "Tokyo";
		}else if(option == 1) {
			city = "Berlin";
		}else if(option == 2) {
			city = "Dubai";
		}else if(option == 3) {
			city = "Barcelona";
		}else if(option == 4) {
			city = "Paris";
		}else if(option == 5) {
			city = "Roma";
		}
    	return city;
    }
    
    public String createAirline() {
    	Random rAirline = new Random();
		int option = rAirline.nextInt(5)+1;
    	String airline = "";
		if(option == 0) {
			airline = "Avianca";
		}else if(option == 1) {
			airline = "LATAM";
		}else if(option == 2) {
			airline = "VivaColombia";
		}else if(option == 3) {
			airline = "Nosequemascolocar Airlines";
		}else if(option == 4) {
			airline = "JairitoxLines";
		}else if(option == 5) {
			airline = "Copa Airlines";
		}
    	return airline;
    }
    
    @FXML
    void airlineSearch(ActionEvent event) {
    	searchType.setText("Airline");
    	type = 1;
    	
    }
    
    @FXML
    void citySearch(ActionEvent event) {
    	searchType.setText("City");
    	type = 2;
    	
    }

    @FXML
    void dateSearch(ActionEvent event) {
    	searchType.setText("Date");
    	type = 3;
    	
    }

    @FXML
    void doorSearch(ActionEvent event) {
    	searchType.setText("Door");
    	type = 4;
    	
    	
    }
    
    @FXML
    void hourSearch(ActionEvent event) {
    	searchType.setText("Hour");
    	type = 5;
    	
    }

    @FXML
    void numberSearch(ActionEvent event) {
    	searchType.setText("Number");
    	type = 6;
    }
    
    @FXML
    void byLineal(ActionEvent event) {
    	typeS.setText("Lineal");
    	typeSearch=1;
    	search.setDisable(false);

    }
    
    @FXML
    void byBinary(ActionEvent event) {
    	typeS.setText("Binary");
    	typeSearch=2;
    	search.setDisable(false);
    }

    @FXML
    void search(ActionEvent event) {
    	clear();
    	try {
    		if(typeSearch==1) {
    			String text = searchText.getText();
    	    	boolean stop = false;
    	    	Flight current = flights[0];
    	    	clear();
    	    	if(type == 1) {
    	    		for(int i=0; i<flights.length && !stop; i++) {
    	    			if(flights[i].getAirline().equalsIgnoreCase(text)) {
    	    				current = flights[i];
    	    				stop = true;
    	    			}
    	    		}
    	    		flights = new Flight[1];
    	    		flights[0] = current;
    	    		fill();
    	    		
    	    	}else if(type == 2) {
    	    		for(int i=0; i<flights.length && !stop; i++) {
    	    			if(flights[i].getCity().equalsIgnoreCase(text)) {
    	    				current = flights[i];
    	    				stop = true;
    	    			}
    	    		}
    	    		flights = new Flight[1];
    	    		flights[0] = current;
    	    		fill();
    	    		
    	    	}else if(type == 3) {
    	    		//Date
    	    		
    	    		
    	    	}else if(type == 4) {
    	    		for(int i=0; i<flights.length && !stop; i++) {
    	    			if(flights[i].getDoor() == Integer.parseInt(text)) {
    	    				current = flights[i];
    	    				stop = true;
    	    			}
    	    		}
    	    		flights = new Flight[1];
    	    		flights[0] = current;
    	    		fill();
    	    		
    	    	}else if(type == 5) {
    	    		//Hour
    	    		
    	    		
    	    	}else {
    	    		for(int i=0; i<flights.length && !stop; i++) {
    	    			if(flights[i].getNumber().equalsIgnoreCase(text)) {
    	    				current = flights[i];
    	    				stop = true;
    	    			}
    	    		}
    	    		flights = new Flight[1];
    	    		flights[0] = current;
    	    		fill();
    	    	}
    			
    		} else {
    			String text = searchText.getText();
    	    	boolean stop = false;
    	    	Flight current = null;
    	    	int low = 0;
    	    	int high=flights.length-1;
    			if(type==1) {
	    				
    			}else if (type==2) {
    				//city
    				
    			}else if (type==3) {
    				//date
    				
    			}else if (type==4) {
    				while(low<=high && !stop) {
    					int mid = (low+high)/2;
    					if(flights[mid].getDoor() < Integer.parseInt(text)) {
    						low= mid +1 ;
    					} else if(flights[mid].getDoor() > Integer.parseInt(text)) {
    						high= mid-1;
    					} else {
    						current = flights[mid];
    						stop=true;
    					}
    					
    				}
    				flights= new Flight[1];
    				flights[0]=current;
    				fill();
    				
    			}else if (type==5) {
    				//hour
    			}else {
    				//Number
    			}
    			
    			
    			
    		}
			
		} catch (NullPointerException e) {
			Alert info = new Alert(AlertType.WARNING);
	    	info.setTitle("Seaching Flights");
	    	info.setHeaderText(null);
	    	info.initStyle(StageStyle.UTILITY);
	    	info.setContentText("Please choose a one of type of searching");
	    	info.show();
		} 
    		
    		
    		
    	
    }

    @FXML
    void previus(ActionEvent event) {
    	int newPage = Integer.parseInt(page.getText())-1;
    	if(newPage>0) {
    		page.setText(newPage+"");
    		clear();
    		fill();
    	}
    }
    
    @FXML
    void next(ActionEvent event) {
    	int newPage = Integer.parseInt(page.getText())+1;
    	if(newPage<(flights.length/17)+2) {
    		page.setText(newPage+"");
    		clear();
    		fill();
    	}
    }

    @FXML
    void initialize() {
    	
    }
    
     
    
}

