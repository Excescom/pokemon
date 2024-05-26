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

public class EntrenadorCustomController {
    
    
    
    @FXML
    private Button botonMenu;
    @FXML
    private Button guardar;
  
    @FXML
    private ImageView img;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;
    
    @FXML
    private Label lbl;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    @FXML
    private Label lbl6;
    
    

    @FXML
    private ChoiceBox<String> pokemon1 ;
    @FXML
    private ChoiceBox<String> pokemon2 ;
    @FXML
    private ChoiceBox<String> pokemon3 ;
    @FXML
    private ChoiceBox<String> pokemon4 ;
    @FXML
    private ChoiceBox<String> pokemon5 ;
    @FXML
    private ChoiceBox<String> pokemon6 ;

    
    
    String nombre[]= new String [1];
    Singleton sin = Singleton.getInstancia();
    Consultas consultas = new Consultas();
    
    @FXML
    // pongo todos los listeners para cada caja
    private void initialize() throws SQLException
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        logs = new ScrollPane();
        String texto = sin.getLogs();
        lblLogs.setText(texto);
        pokemon2.setDisable(true);
        pokemon3.setDisable(true);
        pokemon4.setDisable(true);
        pokemon5.setDisable(true);
        pokemon6.setDisable(true);

        int i = 0;
                for(i =1 ; i <= 151 ; i++)
                {
                    nombre [i-1] = consultas.PokemonNombre(i);
                    if( i < 151)
                    {
                        nombre = Arrays.copyOf(nombre,nombre.length+1);
                    }
                    
                }
 
        pokemon1.getItems().addAll(nombre);  
         pokemon1.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar();
                        img6.setImage(new Image("file:.//fotos//nada"));
                        img5.setImage(new Image("file:.//fotos//nada"));
                        img4.setImage(new Image("file:.//fotos//nada"));
                        img3.setImage(new Image("file:.//fotos//nada"));
                        img2.setImage(new Image("file:.//fotos//nada"));
                        pokemon2.setDisable(false);
                        guardar.setDisable(false);
                        lbl.setText("");
                        String id = consultas.pokemonId(pokemon1.getValue());
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
         
         pokemon2.getItems().addAll(nombre);  
         pokemon2.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar2();
                        pokemon3.setDisable(false);
                        lbl2.setText("");
                        String id = consultas.pokemonId(pokemon2.getValue());
                        int idNum = Integer.parseInt(id); 
                        List<Map<String, String>> info = consultas.PokemonCompleto(idNum);
                        
                        // Iterador para recorrer la lista
                        for (Map<String, String> pokemonInfo : info) {
                            // Iterador para recorrer el mapa de información de cada pokemon
                            for (Map.Entry<String, String> entry : pokemonInfo.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                                lbl2.setText(lbl2.getText() + "\n" +" "+ key+ ": " + value);
                            }
                        }
                       
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
         
         
         pokemon3.getItems().addAll(nombre);  
         pokemon3.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar3();
                        pokemon4.setDisable(false);
                        lbl3.setText("");
                        String id = consultas.pokemonId(pokemon3.getValue());
                        int idNum = Integer.parseInt(id); 
                        List<Map<String, String>> info = consultas.PokemonCompleto(idNum);
                        
                        // Iterador para recorrer la lista
                        for (Map<String, String> pokemonInfo : info) {
                            // Iterador para recorrer el mapa de información de cada pokemon
                            for (Map.Entry<String, String> entry : pokemonInfo.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                                lbl3.setText(lbl3.getText() + "\n" +" "+ key+ ": " + value);
                            }
                        }
                       
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
         
         pokemon4.getItems().addAll(nombre);  
         pokemon4.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar4();
                        pokemon5.setDisable(false);
                        lbl4.setText("");
                        String id = consultas.pokemonId(pokemon4.getValue());
                        int idNum = Integer.parseInt(id); 
                        List<Map<String, String>> info = consultas.PokemonCompleto(idNum);
                        
                        // Iterador para recorrer la lista
                        for (Map<String, String> pokemonInfo : info) {
                            // Iterador para recorrer el mapa de información de cada pokemon
                            for (Map.Entry<String, String> entry : pokemonInfo.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                                lbl4.setText(lbl4.getText() + "\n" +" "+ key+ ": " + value);
                            }
                        }
                       
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
         
         pokemon5.getItems().addAll(nombre);  
         pokemon5.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar5();
                        pokemon6.setDisable(false);
                        lbl5.setText("");
                        String id = consultas.pokemonId(pokemon5.getValue());
                        int idNum = Integer.parseInt(id); 
                        List<Map<String, String>> info = consultas.PokemonCompleto(idNum);
                        
                        // Iterador para recorrer la lista
                        for (Map<String, String> pokemonInfo : info) {
                            // Iterador para recorrer el mapa de información de cada pokemon
                            for (Map.Entry<String, String> entry : pokemonInfo.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                                lbl5.setText(lbl5.getText() + "\n" +" "+ key+ ": " + value);
                            }
                        }
                       
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
         
         pokemon6.getItems().addAll(nombre);  
         pokemon6.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar6();
                        lbl6.setText("");
                        String id = consultas.pokemonId(pokemon6.getValue());
                        int idNum = Integer.parseInt(id); 
                        List<Map<String, String>> info = consultas.PokemonCompleto(idNum);
                        
                        // Iterador para recorrer la lista
                        for (Map<String, String> pokemonInfo : info) {
                            // Iterador para recorrer el mapa de información de cada pokemon
                            for (Map.Entry<String, String> entry : pokemonInfo.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                                lbl6.setText(lbl6.getText() + "\n" +" "+ key+ ": " + value);
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
    //te devuelve al menú
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
    //muestran los pokemons seleccionados
    private void mostrar() throws SQLException
    {

        if (pokemon1.getValue() != null)
        {
            String numeroEscrito = consultas.pokemonId(pokemon1.getValue());
            sin.setLogs("pokemon " +pokemon1.getValue()+ " seleccionado");
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
    
    @FXML
    private void mostrar2() throws SQLException
    {

        if (pokemon2.getValue() != null)
        {
            String numeroEscrito = consultas.pokemonId(pokemon2.getValue());
            sin.setLogs("pokemon " +pokemon2.getValue()+ " seleccionado");
            lblLogs.setText(sin.getLogs());
            int numero = Integer.parseInt(numeroEscrito);
            
                     
            if(numero < 10)
            {
                numeroEscrito ="00"+numeroEscrito;
                img2.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else if(numero < 100)
            {
                numeroEscrito ="0"+numeroEscrito;
                img2.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else
            {
                img2.setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
        }
        else
        {
            sin.setLogs("1 pokemon inexistete");
            lblLogs.setText(sin.getLogs());
        }
        
       
        
    }
    
     @FXML
    private void mostrar3() throws SQLException
    {

        if (pokemon3.getValue() != null)
        {
            String numeroEscrito = consultas.pokemonId(pokemon3.getValue());
            sin.setLogs("pokemon " +pokemon3.getValue()+ " seleccionado");
            lblLogs.setText(sin.getLogs());
            int numero = Integer.parseInt(numeroEscrito);
            
                     
            if(numero < 10)
            {
                numeroEscrito ="00"+numeroEscrito;
                img3.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else if(numero < 100)
            {
                numeroEscrito ="0"+numeroEscrito;
                img3.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else
            {
                img3.setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
        }
        else
        {
            sin.setLogs("1 pokemon inexistete");
            lblLogs.setText(sin.getLogs());
        }
        
       
        
    }
    
    @FXML
    private void mostrar4() throws SQLException
    {

        if (pokemon4.getValue() != null)
        {
            String numeroEscrito = consultas.pokemonId(pokemon4.getValue());
            sin.setLogs("pokemon " +pokemon4.getValue()+ " seleccionado");
            lblLogs.setText(sin.getLogs());
            int numero = Integer.parseInt(numeroEscrito);
            
                     
            if(numero < 10)
            {
                numeroEscrito ="00"+numeroEscrito;
                img4.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else if(numero < 100)
            {
                numeroEscrito ="0"+numeroEscrito;
                img4.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else
            {
                img4.setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
        }
        else
        {
            sin.setLogs("1 pokemon inexistete");
            lblLogs.setText(sin.getLogs());
        }
        
       
        
    }
    
     @FXML
    private void mostrar5() throws SQLException
    {

        if (pokemon5.getValue() != null)
        {
            String numeroEscrito = consultas.pokemonId(pokemon5.getValue());
            sin.setLogs("pokemon " +pokemon5.getValue()+ " seleccionado");
            lblLogs.setText(sin.getLogs());
            int numero = Integer.parseInt(numeroEscrito);
            
                     
            if(numero < 10)
            {
                numeroEscrito ="00"+numeroEscrito;
                img5.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else if(numero < 100)
            {
                numeroEscrito ="0"+numeroEscrito;
                img5.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else
            {
                img5.setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
        }
        else
        {
            sin.setLogs("1 pokemon inexistete");
            lblLogs.setText(sin.getLogs());
        }
        
       
        
    }
    
     @FXML
    private void mostrar6() throws SQLException
    {

        if (pokemon6.getValue() != null)
        {
            String numeroEscrito = consultas.pokemonId(pokemon6.getValue());
            sin.setLogs("pokemon " +pokemon6.getValue()+ " seleccionado");
            lblLogs.setText(sin.getLogs());
            int numero = Integer.parseInt(numeroEscrito);
            
                     
            if(numero < 10)
            {
                numeroEscrito ="00"+numeroEscrito;
                img6.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else if(numero < 100)
            {
                numeroEscrito ="0"+numeroEscrito;
                img6.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else
            {
                img6.setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
        }
        else
        {
            sin.setLogs("1 pokemon inexistete");
            lblLogs.setText(sin.getLogs());
        }
        
       
        
    }
    
    @FXML
    //añade los pokemons que has elejido, esto es para guardarlos
    private void seleccionar() throws SQLException 
    {
        sin.initEquipoNull();
        sin.setEntrenadorid(0);
        if(pokemon1.getValue()  != null)
        {
            sin.setEquipoCustom(0, pokemon1.getValue());
        }
        
        if(pokemon2.getValue()  != null)
        {
            sin.setEquipoCustom(1, pokemon2.getValue());
        }
        
        if(pokemon3.getValue()  != null)
        {
            sin.setEquipoCustom(2, pokemon3.getValue());
        }
        
        if(pokemon4.getValue()  != null)
        {
            sin.setEquipoCustom(3, pokemon4.getValue());
        }
        
        if(pokemon5.getValue()  != null)
        {
            sin.setEquipoCustom(4, pokemon5.getValue());
        }
        
        if(pokemon6.getValue()  != null)
        {
            sin.setEquipoCustom(5, pokemon6.getValue());
        }
        

    }
    
    @FXML
    //te hace una selección aleatoria de los pokemos
    private void seleccionarRandom() throws SQLException 
    {
        Consultas consultas = new Consultas();
        lbl6.setText("");
        lbl5.setText("");
        lbl4.setText("");
        lbl3.setText("");
        lbl2.setText("");
        lbl.setText("");
        sin.initEquipoNull();
        sin.setEntrenadorid(0);
        
        img6.setImage(new Image("file:.//fotos//interrogacion.png"));
        img5.setImage(new Image("file:.//fotos//interrogacion.png"));
        img4.setImage(new Image("file:.//fotos//interrogacion.png"));
        img3.setImage(new Image("file:.//fotos//interrogacion.png"));
        img2.setImage(new Image("file:.//fotos//interrogacion.png"));
        img.setImage(new Image("file:.//fotos//interrogacion.png"));
        int cantidad = (int) (Math.random() *6);
        for(int i=0; i<cantidad;i++)
        {
            sin.setEquipoCustom(i,consultas.PokemonNombre((int) (Math.random() * 151)));
        }
        
        
        guardar.setDisable(true);
        pokemon2.setDisable(true);
        pokemon3.setDisable(true);
        pokemon4.setDisable(true);
        pokemon5.setDisable(true);
        pokemon6.setDisable(true);
        
    }
}
