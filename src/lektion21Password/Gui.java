package lektion21Password;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
	Button btnLogin, btnOpret, btnscene2;
	Label lblbrugernavn, lblPassword, lblBesked;
	Label lblscene2, lblInfoBruger;
	GridPane pane1, pane2;
	Scene scene1, scene2;
	Stage thestage;	
	private PasswordField password = new PasswordField();
	private static TextField brugernavn = new TextField();

	private Server server = new Server();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		thestage = primaryStage;
		
		btnLogin = new Button("Log in");
		btnOpret = new Button("Opret");
		btnscene2 = new Button("Tilbage til log in");
		btnLogin.setOnAction(e -> ButtonClicked(e));
		btnOpret.setOnAction(e -> ButtonClicked(e));
		btnscene2.setOnAction(e -> ButtonClicked(e));
		lblbrugernavn = new Label("Navn");
		lblPassword = new Label("Password");
		lblBesked = new Label("Hello World");

		lblscene2 = new Label("Du er nu logget ind");
		lblInfoBruger = new Label("Bruger info");
		
		pane1 = new GridPane();
		pane2 = new GridPane();
		pane1.setVgap(10);
		pane2.setVgap(10);
		
		pane1.setStyle("-fx-background-color: yellow;-fx-padding: 10px;");
		pane2.setStyle("-fx-background-color: lightgreen;-fx-padding: 10px;");

		pane1.setPadding(new Insets(5));
		pane1.setHgap(10);
		pane1.setVgap(10);

		pane1.add(lblbrugernavn, 0, 0);
		pane1.add(brugernavn, 0, 1, 2, 1);
		pane1.add(lblPassword, 0, 2);
		pane1.add(password, 0, 3, 2, 1);
		pane1.add(btnLogin, 0, 4);
		pane1.add(btnOpret, 1, 4);
		pane1.add(lblBesked, 0, 5);

		pane2.setPadding(new Insets(5));
		pane2.setHgap(10);
		pane2.setVgap(10);
		
		pane2.add(lblInfoBruger, 0, 0);
		pane2.add(btnscene2, 0, 1);

		scene1 = new Scene(pane1, 200, 200);
		scene2 = new Scene(pane2, 200, 200);

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	public void ButtonClicked(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			String insertedName = brugernavn.getText();
			String insertedPassword = password.getText();
			if (server.isLoginValid(insertedName, insertedPassword)) {
				thestage.setScene(scene2);
			}
		} else if (e.getSource() == btnOpret) {
			String insertedName = brugernavn.getText();
			String insertedPassword = password.getText();

			server.createUser(insertedName, insertedPassword);

			password.clear();
			brugernavn.clear();
			System.out.println("Oprettet");
		} else {
			lblBesked.setText("");
			password.clear();
			brugernavn.clear();
			thestage.setScene(scene1);

		}
	}

}
