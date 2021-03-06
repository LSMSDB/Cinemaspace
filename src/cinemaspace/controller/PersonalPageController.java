package cinemaspace.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import cinemaspace.model.CinemaSpaceArchive;
import cinemaspace.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PersonalPageController {
	//Switch page controller
	private Parent root;
	private Stage stage;
		
	//Parameter
	private User user;
	
	//FXML elements
	@FXML private Text username;
	@FXML private Text email;
	@FXML private Text gender;
	@FXML private Text dateOfBirth;
	@FXML private TextField minField;
	@FXML private TextField maxField;
	@FXML private PieChart pieChart;
	
	private double min;
	private double max;
	private Map<String, Integer> pieElement;
	
	public PersonalPageController() {
		super();
	}
	
	@FXML protected void initialize() {
		pieChart.setVisible(false);
	}
	
	@FXML protected void handleHomeButtonAction (ActionEvent event) {
		try {
			String address = new File("target/classes/cinemaspace/view/home.fxml").getAbsolutePath();
			FXMLLoader load = new FXMLLoader(new File(address).toURI().toURL());
			root = load.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			HomePageController controller = load.<HomePageController>getController();
			controller.homePage();
			if(user != null) {
				controller.initUser(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@FXML protected void handleHighestRatedButtonAction (ActionEvent event) {
		try {
			String address = new File("target/classes/cinemaspace/view/home.fxml").getAbsolutePath();
			FXMLLoader load = new FXMLLoader(new File(address).toURI().toURL());
			root = load.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			HomePageController controller = load.<HomePageController>getController();
			controller.highestRated();
			if(user != null) {
				controller.initUser(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@FXML protected void handleMostPopularButtonAction (ActionEvent event) {
		try {
			String address = new File("target/classes/cinemaspace/view/home.fxml").getAbsolutePath();
			FXMLLoader load = new FXMLLoader(new File(address).toURI().toURL());
			root = load.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			HomePageController controller = load.<HomePageController>getController();
			controller.mostPopular();
			if(user != null) {
				controller.initUser(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@FXML protected void handleProfileButtonAction (ActionEvent event) {
		try {
			String address = new File("target/classes/cinemaspace/view/profile.fxml").getAbsolutePath();
			FXMLLoader load = new FXMLLoader(new File(address).toURI().toURL());
			root = load.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			PersonalPageController controller = load.<PersonalPageController>getController();
			controller.initUser(user);
			if(user != null) {
				controller.initUser(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@FXML protected void handleDisconnectionButtonAction (ActionEvent event) {
		try {
			String address = new File("target/classes/cinemaspace/view/welcome.fxml").getAbsolutePath();
			FXMLLoader load = new FXMLLoader(new File(address).toURI().toURL());
			root = load.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@FXML protected void handleCloseAccountButtonAction (ActionEvent event) {
		if(CinemaSpaceArchive.deleteUser(user)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialogue");
			alert.setHeaderText(null);
			alert.setContentText("Your account has been successfully closed.");
			alert.showAndWait();
			try {
				String address = new File("target/classes/cinemaspace/view/welcome.fxml").getAbsolutePath();
				FXMLLoader load = new FXMLLoader(new File(address).toURI().toURL());
				root = load.load();
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialogue");
			alert.setHeaderText(null);
			alert.setContentText("A problem occurred during the closure of your account. Please try again !");
			alert.showAndWait();
		}
	}
	
	@FXML protected void handleConfirmPieChartButtonAction (ActionEvent event) {		
		if(minField.getText().isEmpty()) {
			minField.setText("0");
		}
		if(maxField.getText().isEmpty()) {
			maxField.setText("5");
		}
		else if(!minField.getText().isEmpty() && !maxField.getText().isEmpty()) {
			if (isValidValueMinMax(minField.getText()) && isValidValueMinMax(maxField.getText())) {
				min = Double.parseDouble(minField.getText());
				max = Double.parseDouble(maxField.getText());
				if(min > max) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialogue");
					alert.setHeaderText(null);
					alert.setContentText("Your minimum value must be between 0 and 5 and must be lower than the maximum value.");
					alert.showAndWait();
					minField.setText("");
					maxField.setText("");
				}
				else if(min < 0 || min > 5) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialogue");
					alert.setHeaderText(null);
					alert.setContentText("Your minimum value must be between 0 and 5 and must be lower than the maximum value.");
					alert.showAndWait();
					minField.setText("");
				}
				else if(max < 0 || max > 5) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialogue");
					alert.setHeaderText(null);
					alert.setContentText("Your minimum value must be between 0 and 5 and must be lower than the maximum value.");
					alert.showAndWait();
					maxField.setText("");
				}
				else {
					pieChart.setVisible(true);
					pieChart.setTitle("Most Recurrent Genres From " + min + " to " + max);
					pieElement = CinemaSpaceArchive.generateMostRecurrentGenresByRatingIntervals(user, min, max);
					
					int totalRatings = 0;
					for(int numberOfRatings : pieElement.values())
						totalRatings += numberOfRatings;

					if(pieChart.getData() != null) {
						pieChart.getData().clear();
					}
					for(Map.Entry<String,Integer> element : pieElement.entrySet()) {
						String label = element.getKey() + " " + (element.getValue()*100)/totalRatings + "%";
						pieChart.getData().add(new PieChart.Data(label, element.getValue()));
					}
					
					maxField.setText("");
					minField.setText("");
				}
			}
			else if (!isValidValueMinMax(minField.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialogue");
				alert.setHeaderText(null);
				alert.setContentText("Your minimum value must be between 0 and 5 and must be lower than the maximum value.");
				alert.showAndWait();
				minField.setText("");
			}
			else if (!isValidValueMinMax(maxField.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialogue");
				alert.setHeaderText(null);
				alert.setContentText("Your maximum value must be between 0 and 5 and must be higher than the minimum value.");
				alert.showAndWait();
				maxField.setText("");
			}
		}
	}
	
	
	
	public static boolean isValidValueMinMax(String value)
    {
        String emailRegex = "^[0-9.]+$";

        Pattern pat = Pattern.compile(emailRegex);
        if (value == null)
            return false;
        return pat.matcher(value).matches();
    }
	
	public void initUser(User user) {
		this.user = user;
		username.setText(user.getUsername());;
		email.setText(user.getEmail());
		gender.setText(user.getGender());;
		dateOfBirth.setText(user.getDateOfBirth());;
		
	}
}
