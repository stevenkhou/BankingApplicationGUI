package coe528.project;

/**
 *
 * @author steve
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class UserInterface extends Application{
    
    @Override
    public void start(Stage primaryStage) {
        
        Button loginButton = new Button("Login");
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        
        Label usernameLabel = new Label("User: ");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Pass: ");
        PasswordField passwordField = new PasswordField();
        Label welcomeLabel = new Label("Banking With Steven $$$");
        welcomeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        Label invalidLoginLabel = new Label("Incorrect input parameters");
        
        layout.add(welcomeLabel, 0, 0, 2, 1);
        layout.add(usernameField, 1, 1);
        layout.add(usernameLabel, 0, 1);
        layout.add(passwordField, 1, 2);
        layout.add(passwordLabel, 0, 2);
        layout.add(loginButton, 2, 2);
        
        User user = new User();
        
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if ("admin".equals(username) && "admin".equals(password)) {
                managerPage(primaryStage, user);
            } else if (user.authenticate(username, password)) {
                customerPage(primaryStage, user);
            } else {
                layout.add(invalidLoginLabel, 1, 4);
            }
        });
        
        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void managerPage(Stage stage, User user) {
        Label welcomeLabel = new Label("Manager Page");
        welcomeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        Button addCustomerButton = new Button("Add Customer");
        Button deleteCustomerButton = new Button("Remove Customer");
        Button logoutButton = new Button("Logout");

        addCustomerButton.setOnAction(event -> customerAddPage(stage, user));
        
        deleteCustomerButton.setOnAction(event -> customerRemovePage(stage, user));
        
        logoutButton.setOnAction(event -> start(stage));

        HBox buttonBox = new HBox(0); 
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addCustomerButton, deleteCustomerButton, logoutButton);
        VBox layout = new VBox(20); 
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(welcomeLabel, buttonBox);

        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void customerAddPage(Stage stage, User user) {
        Label usernameLabel = new Label("User: ");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Pass: ");
        TextField passwordField = new TextField();
        Button addButton = new Button("Add Customer");
        Button backButton = new Button("Go Back");
        HBox buttonBox = new HBox(0); 
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(backButton, addButton);
    
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(0); 
        gridPane.setVgap(20); 
        gridPane.setPadding(new Insets(20));

        addButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (!user.duplicateUsername(username)) {
                user.addCustomer(username, password);
            }
        });

        backButton.setOnAction(event -> managerPage(stage, user));

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(usernameField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(buttonBox, 1, 3); 

        Scene scene = new Scene(gridPane, 600, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void customerRemovePage(Stage stage, User user) {
        Label usernameLabel = new Label("User: ");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Pass: ");
        TextField passwordField = new TextField();
        Button deleteButton = new Button("Remove Customer");
        Button backButton = new Button("Go Back");
        HBox buttonBox = new HBox(0); 
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(backButton, deleteButton);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(0); 
        gridPane.setVgap(20); 
        gridPane.setPadding(new Insets(20));

        deleteButton.setOnAction(event -> user.deleteCustomer(usernameField.getText(), passwordField.getText()));
        
        backButton.setOnAction(event -> managerPage(stage, user));

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(usernameField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(buttonBox, 1, 3); 

        Scene scene = new Scene(gridPane, 600, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
    public void customerPage(Stage stage, User user) {
        Button logoutButton = new Button("Go Back");
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button purchaseButton = new Button("Online Purchase");
        Label welcomeLabel = new Label("User: " + user.getCustomerUsername());
        Label balanceLabel = new Label("Balance: $" + user.getBalance());
        Label levelLabel = new Label("Membership Level: " + user.getMembershipLevel());
        welcomeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        balanceLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        levelLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        HBox buttonBox = new HBox(0); 
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(logoutButton, depositButton, withdrawButton, purchaseButton);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(20); 
    
        depositButton.setOnAction(event -> depositPage(stage, user));
        
        withdrawButton.setOnAction(event -> withdrawPage(stage, user));
        
        purchaseButton.setOnAction(event -> purchasePage(stage, user));
        
        logoutButton.setOnAction(event -> start(stage));
    
        gridPane.add(welcomeLabel, 0, 0, 2, 1); 
        gridPane.add(buttonBox, 0, 4, 2, 1); 
        gridPane.add(balanceLabel, 0, 2); 
        gridPane.add(levelLabel, 0, 3); 
    
        Scene scene = new Scene(gridPane, 600,600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
    public void depositPage(Stage stage, User user) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        Label amountLabel = new Label("Enter Amount:");
        TextField amountField = new TextField();
        Button depositButton = new Button("Deposit");
        Button backButton = new Button("Go Back");

        depositButton.setOnAction(event -> {
            try {
                int amount = Integer.parseInt(amountField.getText());
                    user.deposit(amount); 
                    
            } catch (NumberFormatException ex) {
            }
        });

        backButton.setOnAction(event -> customerPage(stage, user));

        gridPane.add(amountLabel, 0, 0);
        gridPane.add(amountField, 1, 0);
        gridPane.add(depositButton, 1, 1);
        gridPane.add(backButton, 0, 1);

        Scene scene = new Scene(gridPane, 600, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void withdrawPage(Stage stage, User user) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));

        Label amountLabel = new Label("Enter Amount:");
        TextField amountField = new TextField();
        Button withdrawButton = new Button("Withdraw");
        Button backButton = new Button("Go Back");

        withdrawButton.setOnAction(event -> {
            try {
                int amount = Integer.parseInt(amountField.getText());
                    user.withdraw(amount);                    
            }   
            catch (NumberFormatException ex) {
            }
        });

        backButton.setOnAction(event -> customerPage(stage, user));

        gridPane.add(amountLabel, 0, 0);
        gridPane.add(amountField, 1, 0);
        gridPane.add(withdrawButton, 1, 1);
        gridPane.add(backButton, 0, 1);

        Scene scene = new Scene(gridPane, 600, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void purchasePage(Stage stage, User user) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));

        Label amountLabel = new Label("Enter purchase total:");
        TextField amountField = new TextField();
        Button purchaseButton = new Button("Confirm Purchase");
        Button backButton = new Button("Go Back");

        purchaseButton.setOnAction(event -> {
            try {
                int amount = Integer.parseInt(amountField.getText());
                    user.purchase(amount);                    
            } 
            catch (NumberFormatException ex) {
            }
        });

        backButton.setOnAction(event -> customerPage(stage, user));

        gridPane.add(amountLabel, 0, 0);
        gridPane.add(amountField, 1, 0);
        gridPane.add(purchaseButton, 1, 1);
        gridPane.add(backButton, 0, 1);

        Scene scene = new Scene(gridPane, 600, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
