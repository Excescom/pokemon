package com.mycompany.pokemon;

import java.io.IOException;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AvisoController {
    @FXML
    private Button botonMenu;
    
    @FXML
    private Button salir;
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;

    Singleton sin = Singleton.getInstancia();
    @FXML
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        logs = new ScrollPane();

        String texto = sin.getLogs();
        lblLogs.setText(texto);
    }
    
    @FXML
    //te deja en el menñu principal, sin cambiar nada
    private void menu()
        
    {
        try {
                sin.setLogs("moviendose al menú");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("menu.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                
                stage.setTitle("MENU");
                sin.setLogs("moviendose al menu");
                stage.setScene(scene);
                stage.show();

                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) botonMenu.getScene().getWindow();

                stageAcerrar.close();

            } catch (IOException e) {}
        
    }
    @FXML
    //te saca a la base de datos para poder cambiar cosas    //te saca a la base de datos para poder cambiar cosas

    private void salir()
        
    {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("baseDatos.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                
                stage.setTitle("baseDatos");
                sin.setLogs("moviendose a la base de datos");
                stage.setScene(scene);
                stage.show();

                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) salir.getScene().getWindow();

                stageAcerrar.close();

            } catch (IOException e) {}
        
    }
  
}
