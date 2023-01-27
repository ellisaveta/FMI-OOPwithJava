package cipher;

import com.CipherInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CipherApp extends Application {
    private TextField txtUsername;
    private TextField txtPassword;
    private TextField txtCardNumber;
    private TextField txtCipher;

    private CipherInterface icipher;
    private boolean userHasRights;
    private boolean validateUserInfo;

    @Override
    public void start(Stage stage) throws Exception {
        Button btnValidateUser = new Button("Validate User");
        Button btnEncrypt = new Button("Encrypt");
        Button btnDecrypt = new Button("Decrypt");
        Button btnWriteCardInfoByCiphers = new Button("Write encrypted card\nnumbers by ciphers");
        Button btnWriteCardInfoByCards = new Button("Write encrypted card\nnumbers by card numbers");
        Label lblUsername = new Label("Username:");
        Label lblPassword = new Label("Password:");
        Label lblCardNumber = new Label("Card Number:");
        Label lblCipher = new Label("Cipher:");

        txtUsername = new TextField();
        txtPassword = new TextField();
        txtCardNumber = new TextField();
        txtCipher = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.setVgap(8);
        gridPane.setHgap(8);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(3));

        btnValidateUser.setOnAction(v -> validateUser());
        btnEncrypt.setOnAction(e -> encrypt());
        btnDecrypt.setOnAction(d -> decrypt());
        btnWriteCardInfoByCiphers.setOnAction(w -> writeCardInfoByCiphers());
        btnWriteCardInfoByCards.setOnAction(w -> writeCardInfoByCards());

        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnValidateUser, 1, 2, 2, 1);
        gridPane.add(lblCardNumber, 0, 3);
        gridPane.add(txtCardNumber, 1, 3);
        gridPane.add(lblCipher, 0, 4);
        gridPane.add(txtCipher, 1, 4);
        gridPane.add(btnEncrypt, 2, 3);
        gridPane.add(btnDecrypt, 2, 4);
        gridPane.add(btnWriteCardInfoByCiphers, 0, 5);
        gridPane.add(btnWriteCardInfoByCards, 1, 5);

        Scene scene = new Scene(gridPane, 420, 300);
        initializeRMI();

        stage.setOnCloseRequest(e -> Platform.exit());
        stage.setTitle("Encryption");
        stage.setScene(scene);
        stage.show();
    }

    protected void initializeRMI() {
        Registry registry = null;

        try {
            registry = LocateRegistry.getRegistry("localhost", 1099);
            icipher = (CipherInterface) registry.lookup("Cipher");
            System.out.println("Server object " + icipher + " found!");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void validateUser() {
        String userName = txtUsername.getText();
        String pass = txtPassword.getText();
        boolean validInput;

        try {
            validInput = icipher.validateUserInfo(userName, pass);
            if(validInput) {
                boolean[] userInfo = icipher.correctUserInfo(userName, pass);
                boolean correctUser = userInfo[0];
                if(correctUser) {
                    userHasRights = userInfo[1];
                    validateUserInfo = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Valid user info");
                    alert.setContentText("Now you can continue!");

                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Input Error");
                    alert.setContentText("Incorrect username or password!");

                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Input Error");
                alert.setContentText("Invalid user info!");

                alert.showAndWait();

                Platform.exit();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void encrypt() {
        if(validateUserInfo) {
            if(userHasRights) {
                String cardNumber = txtCardNumber.getText();
                String cipher;
                boolean validCardNumber;

                try {
                    validCardNumber = icipher.validateCardNumber(cardNumber);
                    if (validCardNumber) {
                        cipher = icipher.encrypted(cardNumber);
                        txtCipher.setText(cipher);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("Input Error");
                        alert.setContentText("Card number isn't correct");

                        alert.showAndWait();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Rights Error");
                alert.setContentText("User has no rights to encrypt!");

                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You must validate your user info first!");

            alert.showAndWait();
        }
    }

    private void decrypt() {
        if(validateUserInfo) {
            if(userHasRights) {
                String cipher = txtCipher.getText();
                String cardNumber;

                try {
                    cardNumber = icipher.decrypted(cipher);
                    txtCardNumber.setText(cardNumber);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Rights Error");
                alert.setContentText("User has no rights to encrypt!");

                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You must validate your user info first!");

            alert.showAndWait();
        }
    }

    private void writeCardInfoByCiphers() {
        if(validateUserInfo) {
            try {
                icipher.writeCardInfoByCiphers();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Card info saved!");
                alert.setContentText("Successfully saved card info in file: cardInfoByCiphers.txt!\n" +
                        "Information is ordered by ciphers!");

                alert.showAndWait();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You must validate your user info first!");

            alert.showAndWait();
        }
    }

    private void writeCardInfoByCards() {
        if(validateUserInfo) {
            try {
                icipher.writeCardIngoByCardNumbers();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Card info saved!");
                alert.setContentText("Successfully saved card info in file: cardInfoByCards.txt!\n" +
                        "Information is ordered by card numbers!");

                alert.showAndWait();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You must validate your user info first!");

            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
