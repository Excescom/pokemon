package com.mycompany.pokemon;

import java.io.IOException;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JugarController {
    @FXML
    private Button botonMenu;
    
    @FXML
    private Button ataque;
    
    @FXML
    private Button ataqueS;
    
    @FXML
    private Label vida;
    
    @FXML
    private Label vidaTux;
    
    @FXML
    private ImageView tux;
    
    
    @FXML
    private ImageView pokemon1;
    @FXML
    private ImageView pokemon2;
    @FXML
    private ImageView pokemon3;
    @FXML
    private ImageView pokemon4;
    @FXML
    private ImageView pokemon5;
    @FXML
    private ImageView pokemon6;
    
   
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;
    Singleton sin = Singleton.getInstancia();
    
    Pokemon[] equipo = sin.getEquipo();

    @FXML
    private void initialize() throws SQLException
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        logs = new ScrollPane();
        tux.setImage(new Image("file:.//fotos//pokemones//tux.png"));
        Tux tux = new Tux(sin.getdif()); 
        sin.setTux(tux);
        String texto = sin.getLogs();
        lblLogs.setText(texto);
       
        Consultas consultas = new Consultas();
        int cantidad = consultas.CantidadPokemon(sin.getentrenadorid());
        ImageView equipoImage[] = {pokemon1,pokemon2,pokemon3,pokemon4,pokemon5,pokemon6};
        sin.initLogsJuego();
        sin.setLogsJuego("Empieza el juego");
        lblLogs.setText(sin.getLogsJuego());
         
        int i;
        for(i = 0;i<cantidad;i++)
        {
            
            
            int id = equipo[i].getID();
            String numero = Integer.toString(id);
            
            if(id < 10)
            {
                numero = "00"+numero;
                equipoImage[i].setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
            else if(id < 100)
            {
                numero = "0"+numero; 
                equipoImage[i].setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
            else if (id > 100)
            {
                equipoImage[i].setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }          
        }
        
        for(;i<6;i++)
        {
            equipoImage[i].setImage(new Image("file:.//fotos//pokemones//none.png"));
        }
        String vidaPokemon = Double.toString(equipo[0].getVida());
        String vidaTotal = Double.toString(equipo[0].getVidaTotal());
        vida.setText(vidaPokemon +" / "+ vidaTotal);
        
        String vidaPTux = Double.toString(sin.getTux().getVida());
        String vidaTuxTotal = Double.toString(sin.getTux().getVidaTotal());
        vidaTux.setText(vidaPTux +" / "+ vidaTuxTotal);
       
         
        
         
         cantidad = consultas.CantidadPokemon(sin.getentrenadorid());
         
            int elegido = 0;
            
       
            int daño;
            
            //empieza el juego
            if(sin.getTux().getVelocidad() > equipo[elegido].getVelocidad())
            {
                int ataca = (int) (Math.random() * 2);
                
                if(ataca == 0)
                {
                   daño = (int) sin.tux.getAtaque(equipo[elegido].getDefensa(),equipo[elegido].getTipo());
                   equipo[elegido].quitarvida(daño);
                   sin.setLogsJuego(tux.getNombre() +" Ha realizado: "+daño+" puntos de daño");
                   lblLogs.setText(sin.getLogsJuego());
                }
                else
                {
                    daño = (int) sin.tux.getAtaqueS(equipo[elegido].getDefensaS(),equipo[elegido].getTipo());
                    equipo[elegido].quitarvida(daño);
                    sin.setLogsJuego("Ataque Especial " + tux.getNombre() +" Ha realizado: "+daño+" puntos de daño");
                    lblLogs.setText(sin.getLogsJuego());
                }  
                
                vidaPokemon = Integer.toString((int) equipo[elegido].getVida());
                vidaTotal = Integer.toString((int) equipo[elegido].getVidaTotal());
                vida.setText(vidaPokemon +" / "+ vidaTotal);
                
               
            }
                ataque.setDisable(false);
                ataqueS.setDisable(false);  
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
                
                stage.setTitle("MENU");
                sin.setLogs("moviendose al menu");
                stage.setScene(scene);
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
                stage.show();
                
                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) botonMenu.getScene().getWindow();
                
                stageAcerrar.close();

            } catch (IOException e) {}
        
    }
    
    @FXML
    private void ataque() throws SQLException
    {
        
            int elegido = 0;
            double defensa = sin.getTux().getDefensa();
            int tipo = sin.getTux().getTipo();
                int daño =(int) equipo[elegido].getAtaque(defensa,tipo);
                sin.getTux().quitarvida(daño);
                String vidaPTux = Double.toString(sin.getTux().getVida());
                String vidaTuxTotal = Double.toString(sin.getTux().getVidaTotal());
                vidaTux.setText(vidaPTux +" / "+ vidaTuxTotal);
                sin.setLogsJuego( equipo[elegido].getNombre()+" Ha realizado: "+daño+" puntos de daño");
                lblLogs.setText(sin.getLogsJuego());
                ataque.setDisable(true);
                ataqueS.setDisable(true);      
  
    }
    
    @FXML
    private void ataqueS() throws SQLException
    {
        
            int elegido = 0;
            
            int daño =(int)equipo[elegido].getAtaqueS(sin.getTux().getDefensaS(),sin.getTux().getTipo());
            sin.getTux().quitarvida(daño);
            String vidaPTux = Double.toString(sin.getTux().getVida());
            String vidaTuxTotal = Double.toString(sin.getTux().getVidaTotal());
            sin.setLogsJuego( equipo[elegido].getNombre()+" Ataque Especial " +" Ha realizado: "+daño+" puntos de daño");
            lblLogs.setText(sin.getLogsJuego());
            vidaTux.setText(vidaPTux +" / "+ vidaTuxTotal);
            ataque.setDisable(true);
            ataqueS.setDisable(true);

    }
  
}
