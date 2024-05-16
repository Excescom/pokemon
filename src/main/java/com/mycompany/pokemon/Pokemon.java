package com.mycompany.pokemon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danie
 */
public class Pokemon {
    private String nombre;
    private int id;
    private int nivel;
    private int tipo;
    private double vida;
    private double ataque;
    private double ataqueS;
    private double defensa;
    private double defensaS;
    private double velocidad;
    private double[][] tablaT;
    
    
    
    //nombre = n, vida = v, ataque = a, ataque especial = as, defensa = d, defensa especial = ds, velocidad = vel, tipo = t y dificultad = dificultad
    Pokemon(String n, int i, double v, double a, double as, double d, double ds, double vel , int t, String dificultad)
    { 
        tablaT = TablaTipos();
        
        
        int bas = 0;
        if(n != "tux")
        {
            nombre = n;
            if(dificultad == "pesadilla")
            {
                bas = 1;
            }
            else if(dificultad == "veterano")
            {
               bas = 25;
            }
            else if(dificultad == "marine") 
            {
                bas = 50;
            }
            else if (dificultad == "recluta")
            {
               bas = 75;
            }
            
            nivel = (int) ( Math.random() * 25 + bas);
            vida = v + (Math.random() * 25 + bas);
            ataque = a + (Math.random() * 25 + bas);
            ataqueS = as + (Math.random() * 25 + bas);
            defensa = d + (Math.random() * 25 + bas);
            defensaS = ds + (Math.random() * 25 + bas);
            velocidad = vel + (Math.random() * 25 + bas);
            tipo = t;
            id = i;
        }
        else
        {
            nombre =n;
            setTux(dificultad);
        }
    }
    
    //todos los geters
    public String getNombre()
    {
        return nombre;
    }
    public int getNivel()
    {
        return nivel;
    }
    
    public double getVida()
    {
        return vida;
    }
    
    public double getDefensa()
    {
        return defensa;
    }
    
    public double getDefensaS()
    {
        return defensaS;
    }
    
    public double getVelocidad()
    {
        return velocidad;
    }
    
    
    
    //bon es la bonificación es el resultado de la tabla de tipos
    //la d es la defensa del pokemon rival
    
    public double getataque( double d, int tipoD )
    {
        double valor = 85 + Math.random() * 115 ;    
        return  0.01 * tablaT[tipo][tipoD] * 1 * valor * ((0.2 * nivel +1) * ataque / 25 * d + 2) ;
    }
    
    public double getataqueS( double d, int tipoD )
    {
        double valor = 85 + Math.random() * 115 ;    
         return  0.01 * tablaT[tipo][tipoD] * 1 * valor * ((0.2 * nivel +1) * ataqueS / 25 * d + 2) ;
    }
    //d es daño
    public void quitarvida (double d)
    {
        vida = vida - d;
    }
    
    

    
    private void setTux(String dificultad)
    {
        if(dificultad == "pesadilla")
        {
            nivel = 100;
            vida =444;
            ataque = 372;
            ataqueS = 372;
            defensa = 372;
            defensaS = 372;
            velocidad = 372;
            
        }
        else if(dificultad == "veterano")
        {
            nivel = 75;
            vida =350;
            ataque = 220;
            ataqueS = 220;
            defensa = 220;
            defensaS = 220;
            velocidad = 220;
        }
        else if(dificultad == "marine")
        {
            nivel = 50;
            vida =227;
            ataque = 189;
            ataqueS = 189;
            defensa = 189;
            defensaS = 189;
            velocidad = 189;
        }
        else if (dificultad == "recluta")
        {
            nivel = 25;
            vida =180;
            ataque = 112;
            ataqueS = 112;
            defensa = 112;
            defensaS = 112;
            velocidad = 112;
        }
    }
    
    private double[][] TablaTipos()
    {
        double[][] tabla = new double [18][18];
        for( int i=0;i<18;i++)
        {
            for(int j=0;j<18;j++)
            {
                tabla[i][j] = 1;
            }
        } 
        
        for(int i = 0;i <18;i++)
        {
            tabla[0][i] = 0;
        }
        
        tabla[1][13] = 1.25 ;
        tabla[1][14] = 0 ;
        tabla[1][17] = 1.25 ;
        tabla[2][2] = 1.25 ;
        tabla[2][3] = 1.25 ;
        tabla[2][4] = 1.75  ;
        tabla[2][6] = 1.25 ;
        tabla[2][12] = 1.75  ;
        tabla[2][13] = 1.25 ;
        tabla[2][15] = 1.25 ;
        tabla[2][17] = 1.75  ;
        tabla[3][2] = 1.75  ;
        tabla[3][3] = 1.25 ;
        tabla[3][4] = 1.25 ;
        tabla[3][9] = 1.75  ;
        tabla[3][13] = 1.75 ;
        tabla[3][15] = 1.25 ;
        tabla[4][2] = 1.25 ;
        tabla[4][3] = 1.75 ;
        tabla[4][4] = 1.25 ;
        tabla[4][8] = 1.25 ;
        tabla[4][9] = 1.75 ;
        tabla[4][10] = 1.25 ;
        tabla[4][12] = 1.25 ;
        tabla[4][13] = 1.75 ;
        tabla[4][15] = 1.25 ;
        tabla[4][17] = 1.25 ;
        tabla[5][3] = 1.75 ;
        tabla[5][4] = 1.25 ;
        tabla[5][5] = 1.25 ;
        tabla[5][9] = 0 ;
        tabla[5][10] = 1.75 ;
        tabla[5][15] = 1.25 ;
        tabla[6][2] = 1.25 ;
        tabla[6][3] = 1.25 ;
        tabla[6][4] = 1.75 ;
        tabla[6][6] = 1.25 ;
        tabla[6][9] = 1.75 ;
        tabla[6][10] = 1.75 ;
        tabla[6][15] = 1.75 ;
        tabla[6][17] = 1.25 ;
        tabla[7][1] = 1.75 ;
        tabla[7][6] = 1.75 ;
        tabla[7][8] = 1.25 ;
        tabla[7][10] = 1.25 ;
        tabla[7][11] = 1.25 ;
        tabla[7][12] = 1.25 ;
        tabla[7][13] = 1.75 ;
        tabla[7][14] = 0 ;
        tabla[7][16] = 1.75 ;
        tabla[7][17] = 1.75 ;
        tabla[8][4] = 1.75 ;
        tabla[8][8] = 1.25 ;
        tabla[8][9] = 1.25 ;
        tabla[8][13] = 1.25 ;
        tabla[8][14] = 1.25 ;
        tabla[8][17] = 0 ;
        tabla[9][2] = 1.75 ;
        tabla[9][4] = 1.25 ;
        tabla[9][5] = 1.25 ;
        tabla[9][8] = 1.75 ;
        tabla[9][10] = 0 ;
        tabla[9][12] = 1.25 ;
        tabla[9][13] = 1.75 ;
        tabla[9][17] = 1.75 ;
        tabla[10][4] = 1.75 ;
        tabla[10][5] = 1.25 ;
        tabla[10][7] = 1.75 ;
        tabla[10][12] = 1.75 ;
        tabla[10][13] = 1.25 ;
        tabla[10][17] = 1.25 ;
        tabla[11][7] = 1.75 ;
        tabla[11][8] = 1.75 ;
        tabla[11][11] = 1.25 ;
        tabla[11][16] = 0 ;
        tabla[11][17] = 1.25 ;
        tabla[12][2] = 1.25 ;
        tabla[12][4] = 1.75 ;
        tabla[12][7] = 1.25 ;
        tabla[12][8] = 1.25 ;
        tabla[12][10] = 1.25 ;
        tabla[12][11] = 1.75 ;
        tabla[12][14] = 1.25 ;
        tabla[12][16] = 1.75 ;
        tabla[12][17] = 1.25 ;
        tabla[13][2] = 1.75 ;
        tabla[13][6] = 1.75 ;
        tabla[13][7] = 1.25 ;
        tabla[13][9] = 1.25 ;     
        tabla[13][10] = 1.75 ;
        tabla[13][12] = 1.75 ;
        tabla[13][17] = 1.25 ;
        tabla[14][1] = 0 ;
        tabla[14][11] = 1.75 ;
        tabla[14][14] = 1.75 ;
        tabla[14][16] = 1.25 ;
        tabla[15][15] = 1.75 ;
        tabla[15][17] = 1.25 ;
        tabla[16][7] = 1.25 ;
        tabla[16][11] = 1.75 ;
        tabla[16][14] = 1.75 ;
        tabla[16][16] = 1.25 ;
        tabla[17][2] = 1.25 ;
        tabla[17][3] = 1.25 ;
        tabla[17][5] = 1.25 ;
        tabla[17][6] = 1.75 ;
        tabla[17][13] = 1.75 ;
        tabla[17][17] = 1.25 ;
        
        return tabla ;
    }

    
    
    
}
