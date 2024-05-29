package com.mycompany.pokemon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClaculadoraController {
    @FXML
    private Button botonMenu;
    
    @FXML
    private Button salir;
    
    @FXML
    private Label Resul;
    @FXML
    
    private ImageView image;
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;
    
    @FXML
    private TextField A;
    
     @FXML
    private TextField D;
     
     @FXML
    private TextField constante;
    
    
    @FXML
    private ChoiceBox<Double> B ;
    
    @FXML
    private ChoiceBox<Double> E;
    
    @FXML
    private ChoiceBox<Double> V;
    
    @FXML
    private ChoiceBox<Double> N;
    
    
    
    private Double[] b = {0.0, 0.25, 1.0, 1.25, 1.75};
    
    
    
    
    Double[] v = new Double[100];
        
    Singleton sin = Singleton.getInstancia();
    @FXML
    private void initialize() throws SQLException
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        logs = new ScrollPane();
        for (int i = 0; i < 100; i++) {
            v[i] = (double) (i + 1);
        }
        Double[] e = {0.0, 0.25, 0.5,1.0, 2.0, 4.0,media()};
        B.getItems().addAll( b);
        E.getItems().addAll(e);
        
        V.getItems().addAll(v);
        N.getItems().addAll(v);

        String texto = sin.getLogs();
        lblLogs.setText(texto);
        image.setImage(new Image("file:.//fotos//calculo.png"));
    }
    private double media() throws SQLException
    {
        Consultas consultas = new Consultas();
        double m = consultas.media();
        return m ;
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
    private void cuenta()
    {
        //0.04 * tablaT[tipo][tipoD] * 1 * valor * (((0.2 * nivel +1) * ataque) / (25 * d) + 2)
        
        Pokemon pokemon = new Pokemon("prueba",0,0,0,0,0,0,0,0,sin.getdif());
        int a =  Integer.parseInt(A.getText());
        int d =  Integer.parseInt(D.getText());
        int cont = Integer.parseInt(constante.getText());
        if(B.getValue() != null && E.getValue() != null && V.getValue() != null && N.getValue() != null && A.getText() != null && D.getText() != null)
        {
            double daño = cont * B.getValue()  * E.getValue() * V.getValue() * (((0.2 * N.getValue() +1) * a ) / (25 * d) + 2);
            String resul = String.valueOf(daño);
            Resul.setText(resul);
        }
        else
        {
            Resul.setText("Math Error");
        }
        
    }
    @FXML
    private void ValoresAleatorios() throws SQLException
    {
        //0.04 * tablaT[tipo][tipoD] * 1 * valor * (((0.2 * nivel +1) * ataque) / (25 * d) + 2)
        
        
        int a =  (int) (Math.random() * 500);
        int d =  (int) (Math.random() * 500);
        int porciento1 =  (int) (Math.random() * 100);
        int porciento2 =  (int) (Math.random() * 100);
        int br =  (int) (Math.random() * 5);
        int nr =  (int) (Math.random() * 5);
        double cont =  (Math.random() );
        Double[] e = {0.0, 0.25, 0.5,1.0, 2.0, 4.0,media()};
            double daño = cont * b[br]  * e[nr] * v[porciento1] * (((0.2 * v[porciento2] +1) * a ) / (25 * d) + 2);
            String resul = String.valueOf(daño);
            Resul.setText(resul);
        
        
    }
  
}
