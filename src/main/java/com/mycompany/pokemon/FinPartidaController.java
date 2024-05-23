package com.mycompany.pokemon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FinPartidaController {
    
    
    
    @FXML
    private Button botonMenu;
    
    @FXML
    private Button botonMenu2;
    

    @FXML
    private TextArea lblLogs;
    
    
    
    Singleton sin = Singleton.getInstancia();
    @FXML
    private void initialize() throws SQLException
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        //logs = new ScrollPane();
        
        sin.setLogsJuego("la dificultad elejida es: " + sin.getdif());
        String texto = sin.getLogsJuego();
        
        lblLogs.setText(texto);
        
    }
    
    @FXML
    private void menuGuardado() {
    try {
        sin.setLogs("moviendose al menú");
        
        saveTextToFile(lblLogs.getText(), "partida.txt");

        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menu.fxml"));

        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("MENU");
        sin.setLogs("moviendose al menu");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false); 
        stage.show();

        // Cerrar la ventana actual
        Stage stageAcerrar = (Stage) botonMenu.getScene().getWindow();
        stageAcerrar.close();
        
    } catch (IOException e) {
        e.printStackTrace();
        sin.setLogs("Error al moverse al menú: " + e.getMessage());
    }
}

    
    @FXML
    private void menu()
        
    {
        try {
                sin.setLogs("moviendose al menú");
                
                //saveTextToFile(lblLogs.getText(), "partida.txt");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("menu.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                
                stage.setTitle("MENU");
                sin.setLogs("moviendose al menu");
                stage.setScene(scene);
                stage.resizableProperty().setValue(false); 
                stage.show();

               
                Stage stageAcerrar = (Stage) botonMenu.getScene().getWindow();

                stageAcerrar.close();
                
               
            } catch (IOException e) {}
        
    }
    
     private void saveTextToFile(String content, String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        writer.write(content);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

        
    
  
}
