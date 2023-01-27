package banks.hw12;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BanksController {

    private int n;
    private double limit;
    private boolean valid = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnRun;

    @FXML
    private TextArea tfieldInformation;

    @FXML
    private TextArea tfieldOutput;

    @FXML
    private TextField txtLimit;

    @FXML
    private TextField txtN;


    @FXML
    void btnQuitOnAction(ActionEvent event) {
        System.exit(0);
    }

    void validation() {
        if(!txtN.getText().isEmpty()) {
            n = Integer.parseInt(txtN.getText());
        }
        else {
            tfieldOutput.setText("Enter N!");
            valid = false;
        }
        if(!txtLimit.getText().isEmpty()) {
            limit = Double.parseDouble(txtLimit.getText());
        }
        else {
            tfieldOutput.setText("Enter Limit!");
            valid = false;
        }
        if(tfieldInformation.getText().isEmpty()) {
            tfieldOutput.setText("Enter information about the banks!");
            valid = false;
        }
    }

    List<Integer> checkForUnsafeBanks(double[][] banks) {
        List<Integer> unsafeBanks = new ArrayList<>();
        boolean hasCheckingEnded = false;
        while(!hasCheckingEnded) {
            hasCheckingEnded = true;
            for (int i = 0; i < n; i++) {
                double totalAssets = 0;
                for (int j = 0; j < n; j++) {
                    totalAssets += banks[i][j];
                }
                if (totalAssets < limit && !unsafeBanks.contains(i)) {
                    for (int j = 0; j < n; j++) {
                        if(banks[j][i] != 0) {
                            banks[j][i] = 0;
                            hasCheckingEnded = false;
                        }
                    }
                    unsafeBanks.add(i);
                }
            }
        }
        return unsafeBanks;
    }

    @FXML
    void btnRunOnAction(ActionEvent event) {
        validation();
        if(valid) {
            double[][] banks = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    banks[i][j] = 0.0;
                }
            }
            int bankIndex = -1;
            for (String line : tfieldInformation.getText().split("\\n")) {
                bankIndex++;
                String[] nums = line.split(" ");
                banks[bankIndex][bankIndex] = Double.parseDouble(nums[0]);
                int numOfLoans = Integer.parseInt(nums[1]);
                int indexInNums = 2;
                for (int i = 0; i < numOfLoans; i++) {
                    banks[bankIndex][Integer.parseInt(nums[indexInNums++])] = Double.parseDouble(nums[indexInNums++]);
                }
            }
            List<Integer> unsafeBanks = checkForUnsafeBanks(banks);
            tfieldOutput.setText(String.format("Unsafe banks are %s", unsafeBanks));
        }
    }

    @FXML
    void initialize() {
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'banks-view.fxml'.";
        assert btnRun != null : "fx:id=\"btnRun\" was not injected: check your FXML file 'banks-view.fxml'.";
        assert tfieldInformation != null : "fx:id=\"tfieldInformation\" was not injected: check your FXML file 'banks-view.fxml'.";
        assert tfieldOutput != null : "fx:id=\"tfieldOutput\" was not injected: check your FXML file 'banks-view.fxml'.";
        assert txtLimit != null : "fx:id=\"txtLimit\" was not injected: check your FXML file 'banks-view.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'banks-view.fxml'.";

    }

}
