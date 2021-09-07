package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

public class ClientProtocol  {
    @FXML
    private Label errorText;
    @FXML
    private Label successText;
    @FXML
    private ImageView logoView;
    @FXML
    private TextField admissionNumber, studentName, faculty, message;
    @FXML
    private ProgressIndicator sendingProgress;


    @FXML
    protected void onSendButtonClick() throws IOException, ClassNotFoundException, InterruptedException {
        errorText.setText(" ERROR");

            /*Sets the progressBar to visible*/
            sendingProgress.setVisible(true);

            /*Creating LinkedHashMap to store input*/
            LinkedHashMap<String,String> map= new LinkedHashMap<>();//Creating HashMap
            map.put("AdmissionNumber", admissionNumber.getText());  //Put elements in Map
            map.put("studentName", studentName.getText());
            map.put("Faculty", faculty.getText());
            map.put("Message", message.getText());

            /* Creates a new instance of socket client and runs the socketClient method*/
            SocketClient socketClient = new SocketClient();
            if(socketClient.socketClient(map)){
                successText.setText(SocketClient.feedback);
            }

    }


}