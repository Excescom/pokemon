/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author danie
 */

public class Consultas {
    Singleton sin = Singleton.getInstancia();
   //pre le pasas una consulta
    //te convierte las consultas mñultiples en listas de mapas de string
  public List<Map<String, String>> lista(String c) throws SQLException 
  {
        Connection con;
        String url = sin.getDatabase();
        String usuario = sin.getUsuario();
        String password = sin.getContrasenya();
        con = DriverManager.getConnection(url, usuario, password);
        List<Map<String, String>> rs = new ArrayList<>();

        Statement declaracion = con.createStatement();

        String consulta = c;
        ResultSet resultados = declaracion.executeQuery(consulta);
        ResultSetMetaData metaData = resultados.getMetaData();
        int numColumn = metaData.getColumnCount();
        while (resultados.next()) 
        {
            Map<String, String> m = new HashMap<>();
            for (int i = 1; i < numColumn + 1; i++) 
            {
                String columna = metaData.getColumnName(i);
                String dato = resultados.getString(i);
                m.put(columna, dato);
            }

            rs.add(m);
        }
        resultados.close();
        declaracion.close();
        con.close();
        return rs;
    }
    //comprueba la conexión con la base de datos
    public Boolean comprobar(String puerto, String dir,String usuario,String contrasenya, String database, String driver )
    {
        sin.setLogs("probando conexión");
        try
        {
        Connection con;
        String url = driver+":"+"mysql"+"://" + dir + ":"+puerto+"/"+ database;
        con= DriverManager.getConnection(url,usuario,contrasenya);
        sin.setDataBase(url);
        sin.setUsuario(usuario);
        sin.setContrasenya(contrasenya);
        con.close();
        return true;
        }
        catch(Exception ex)
        {
            
            return false;
            
        }
    }
    //pre el nombre del pokemon
    //devuelve el id de 1 pokemon
    public String pokemonId(String n) throws SQLException
    {
        
        Connection con;
        con= DriverManager.getConnection(sin.getDatabase(),sin.getUsuario(),sin.getContrasenya());
        ResultSet resul = con.createStatement().executeQuery("SELECT id_pokemon FROM Pokemon where pokemon = \"" + n + "\" ");
        String resultado = "";
        
        resul.next();
        resultado = resul.getString("id_pokemon");
        
        
        con.close();

        return resultado ;  
    }
    //pre pasar id del pokemon
    //te devuelve el mobre de 1 pokemon
    public String PokemonNombre(int i) throws SQLException
    {
        
        Connection con;
        con= DriverManager.getConnection(sin.getDatabase(),sin.getUsuario(),sin.getContrasenya());
        ResultSet resul = con.createStatement().executeQuery("SELECT pokemon FROM Pokemon where id_pokemon = " + i);
        
        resul.next();
        String resultado = resul.getString("pokemon");
        con.close();
        return resultado ;
        
    }
    //pre id del pokemon
    //devuelve todos los datos del pokemon icluido el tipo
    public List<Map<String, String>> PokemonCompleto(int id) throws SQLException
    {
        String sentencia = "SELECT p.*, (SELECT t.Type FROM Types t JOIN Rel_Pokemon_Type rpt ON t.ID_Type = rpt.ID_Type WHERE rpt.ID_Pokemon = p.ID_Pokemon LIMIT 1) AS First_Type FROM Pokemon p WHERE p.ID_Pokemon =" + id ;      
        List<Map<String, String>> resul = lista(sentencia);
        return resul ;  
    }
    
    

    //pre nombre entrenador
    //devuelve el id del entrenador
    public int EntrenadorId(String n) throws SQLException
    {
        
        Connection con;
        con= DriverManager.getConnection(sin.getDatabase(),sin.getUsuario(),sin.getContrasenya());
        ResultSet resul = con.createStatement().executeQuery("SELECT ID_Trainer FROM Trainers WHERE Trainer =  \"" + n + "\" ");
        int resultado;
        
        resul.next();
        resultado = resul.getInt("ID_Trainer");       
        con.close();

        return resultado ;  
    }
    //pre id tipo
    //devuelve el tipo del pokemon por la id del tipo
     public String pokemonTipo(int id) throws SQLException
    {
        
        Connection con;
        con= DriverManager.getConnection(sin.getDatabase(),sin.getUsuario(),sin.getContrasenya());
        ResultSet resul = con.createStatement().executeQuery("SELECT type from types WHERE ID_TYPE = "+ id);
        String resultado;
        
        resul.next();
        resultado = resul.getString("type");    
        
        con.close();

        return resultado ;  
    }
    //pre id entrenador
     //devuelve el nombre de un entrenador 
    public String Entrenadornombre(int i) throws SQLException
    {
        
        Connection con;
        con= DriverManager.getConnection(sin.getDatabase(),sin.getUsuario(),sin.getContrasenya());
        ResultSet resul = con.createStatement().executeQuery("SELECT Trainer FROM Trainers WHERE ID_Trainer = " + i);
        
        resul.next();
        String resultado = resul.getString("Trainer");
        con.close();
        return resultado ;
        
    }
    
    //pre nombre de entrenador
     //devuelve los pokemons que tiene un entrenador
     public  List<Map<String, String>>  PokemonNmobreEntrenador(String nombre) throws SQLException
    {
      
        String sentencia = "SELECT Pokemon.Pokemon FROM Pokemon JOIN Rel_Trainer_Pokemon ON Pokemon.ID_Pokemon = Rel_Trainer_Pokemon.ID_Pokemon JOIN Trainers ON Rel_Trainer_Pokemon.ID_Trainer = Trainers.ID_Trainer WHERE Trainers.Trainer = \""+nombre+"\"" ;      
        List<Map<String, String>> resul = lista(sentencia);
        return resul ;  
        
        
    }
    //pre id entrenador
     //devuelve las id de los pokemones de cada entrenador
      public  List<Map<String, String>>  PokemonIDEntrenador(int id) throws SQLException
    {
      
        String sentencia = "SELECT Pokemon.ID_Pokemon FROM Pokemon JOIN Rel_Trainer_Pokemon ON Pokemon.ID_Pokemon = Rel_Trainer_Pokemon.ID_Pokemon JOIN Trainers ON Rel_Trainer_Pokemon.ID_Trainer = Trainers.ID_Trainer WHERE Trainers.ID_Trainer = " + id ;      
        List<Map<String, String>> resul = lista(sentencia);
        return resul ;   
    }
     //pre nombre tipo
      //devuelve la id del tipo 
    public  int  TipoID(String nombre) throws SQLException
    {
      
        Connection con;
        con= DriverManager.getConnection(sin.getDatabase(),sin.getUsuario(),sin.getContrasenya());
        ResultSet resul = con.createStatement().executeQuery("SELECT id_type FROM Types WHERE Type = " + "'" +nombre+"'");
        
        resul.next();
        int resultado = resul.getInt("id_type");
        con.close();
        return resultado;
  
    }
    //pre id entrenador
    //devuelve la cantidad de pokemones que tiene un entrenador
      public  int  CantidadPokemon(int id) throws SQLException
    {
      
        Connection con;
        con= DriverManager.getConnection(sin.getDatabase(),sin.getUsuario(),sin.getContrasenya());
        ResultSet resul = con.createStatement().executeQuery("SELECT COUNT(*) AS cantidad_pokemones FROM Rel_Trainer_Pokemon WHERE ID_Trainer = " + id);
        
        resul.next();
        int resultado = resul.getInt("cantidad_pokemones");
        con.close();
        return resultado;
  
    }

      
      public Double media() throws SQLException
    {
        
        Connection con;
        con= DriverManager.getConnection(sin.getDatabase(),sin.getUsuario(),sin.getContrasenya());
        ResultSet resul = con.createStatement().executeQuery("SELECT AVG(Badges) AS Media_Badges FROM Trainers");
        Double resultado;
        
        resul.next();
        resultado = resul.getDouble("Media_Badges");       
        con.close();

        return resultado ;  
    }
    
}


