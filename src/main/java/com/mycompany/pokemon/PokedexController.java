package com.mycompany.pokemon;

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

public class PokedexController {
    @FXML
    private Button botonMenu;
  
    @FXML
    private ImageView img;
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;
    
    @FXML
    private Label lbl;
    
    @FXML
    private TextArea InfoPokemon;

    @FXML
    private ChoiceBox<String> nombres ;
    
    
    String nombre[]= new String [1];
    Singleton sin = Singleton.getInstancia();
    Consultas consultas = new Consultas();
    @FXML
    private void initialize() throws SQLException
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        logs = new ScrollPane();
        String texto = sin.getLogs();
        lblLogs.setText(texto);
        
        int i = 0;
                for(i =1 ; i <= 151 ; i++)
                {
                    nombre [i-1] = consultas.PokemonNombre(i);
                    if( i < 151)
                    {
                        nombre = Arrays.copyOf(nombre,nombre.length+1);
                    }
                    
                }
 
        nombres.getItems().addAll(nombre);  
         nombres.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar();
                        lbl.setText("");
                        String id = consultas.pokemonId(nombres.getValue());
                        int idNum = Integer.parseInt(id); 
                        List<Map<String, String>> info = consultas.PokemonCompleto(idNum);
                        
                        // Iterador para recorrer la lista
                        for (Map<String, String> pokemonInfo : info) {
                            // Iterador para recorrer el mapa de información de cada pokemon
                            for (Map.Entry<String, String> entry : pokemonInfo.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                                lbl.setText(lbl.getText() + "\n" +" "+ key+ ": " + value);
                            }
                        }
                       
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
    }
    
    @FXML
    private void menu()
        
    {
        try {
                sin.setLogs("moviendose al menú");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("menu.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
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
    private void mostrar() throws SQLException
    {

        if (nombres.getValue() != null)
        {
            String numeroEscrito = consultas.pokemonId(nombres.getValue());
            sin.setLogs("pokemon " +nombres.getValue()+ " seleccionado");
            lblLogs.setText(sin.getLogs());
            int numero = Integer.parseInt(numeroEscrito);
            
                     
            if(numero < 10)
            {
                numeroEscrito ="00"+numeroEscrito;
                img.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else if(numero < 100)
            {
                numeroEscrito ="0"+numeroEscrito;
                img.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else
            {
                img.setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
        }
        else
        {
            sin.setLogs("1 pokemon inexistete");
            lblLogs.setText(sin.getLogs());
        }
        
       
        
    }
  
}


