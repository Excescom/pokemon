/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

/**
 *
 * @author danie
 */
public class Tux  extends Pokemon{
    //pre dificultad
    //crea a tux
     Tux(String difi)
    {
        super();
        nombre = "Tux";
        tipo = (int) (Math.random() * 17 +1 );
        
        
        if(difi == "pesadilla")
        {
            nivel = 100;
            vida =444;
            vidaTotal = vida;
            ataque = 372;
            ataqueS = 372;
            defensa = 372;
            defensaS = 372;
            velocidad = 372;
            
            
        }
        else if(difi == "veterano")
        {
            nivel = 75;
            vida =350;
            vidaTotal = vida;
            ataque = 220;
            ataqueS = 220;
            defensa = 220;
            defensaS = 220;
            velocidad = 220;
        }
        else if(difi == "marine")
        {
            nivel = 50;
            vida =227;
            vidaTotal = vida;
            ataque = 189;
            ataqueS = 189;
            defensa = 189;
            defensaS = 189;
            velocidad = 189;
        }
        else if (difi == "recluta")
        {
            nivel = 25;
            vida =180;
            vidaTotal = vida;
            ataque = 112;
            ataqueS = 112;
            defensa = 112;
            defensaS = 112;
            velocidad = 112;
        }
    }
}
