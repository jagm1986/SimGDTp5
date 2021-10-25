/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.math3.distribution.TDistribution;

/**
 *
 * @author Usuario
 */
public class Fila {

    double reloj = 0;
    Evento e;
    int material;
    int proximoMaterial ;
    double rndPedido;
    double tiempoEntreLlegadas;
    double proxLlegada;
    Actividad A1;
    Actividad A2;
    Actividad A3;
    Actividad A4;
    Actividad A5;
    int colaA3;
    int colaA5;
    int tareasTerminadas;
    int contadorN;
    

    double finA4 = 0;
    IActividad llegadaActividadCalc;


    public Fila(Evento e, int material, int proximoMaterial, double rndPedido, double tiempoEntreLlegadas, double proxLlegada, Actividad A1, Actividad A2, Actividad A3, Actividad A4, Actividad A5, int colaA3, int colaA5, int tareasTerminadas, int contadorN, IActividad llegadaActividadCalc) {
        this.e = e;
        this.material = material;
        this.proximoMaterial = proximoMaterial;
        this.rndPedido = rndPedido;
        this.tiempoEntreLlegadas = tiempoEntreLlegadas;
        this.proxLlegada = proxLlegada;
        this.A1 = A1;
        this.A2 = A2;
        this.A3 = A3;
        this.A4 = A4;
        this.A5 = A5;
        this.colaA3 = colaA3;
        this.colaA5 = colaA5;
        this.tareasTerminadas = tareasTerminadas;
        this.contadorN = contadorN;
        this.llegadaActividadCalc = llegadaActividadCalc;
    }

    public Fila() {
    
    }


    public void CalcularPrimeraFila(double lambda) {
       e = e.Inicio;
       material = 0;
       proximoMaterial = 1;
       llegadaActividadCalc = new ActividadExponencial(1/lambda);
       rndPedido = Math.random();
       tiempoEntreLlegadas = llegadaActividadCalc.calcularTiempo(rndPedido);
       proxLlegada = reloj + tiempoEntreLlegadas;
       
       IActividad paraA1 = new ActividadUniforme(20,30);
       IActividad paraA2 = new ActividadUniforme(30,50);
       IActividad paraA3 = new ActividadExponencial(30);
       IActividad paraA4 = new ActividadUniforme(10,20);
       IActividad paraA5 = new ActividadExponencial(5);
       
       A1 = new Actividad(Estado.Libre,0,0, paraA1);
       A2 = new Actividad(Estado.Libre,0,0, paraA2);
       A3 = new Actividad(Estado.Libre,0,0, paraA3);
       A4 = new Actividad(Estado.Libre,0,0, paraA4);
       A5 = new Actividad(Estado.Libre,0,0, paraA5);
       
       colaA3 = 0;
       colaA5 = 0;
       tareasTerminadas = 0;
       contadorN = 1;
    }

    public void CalcularNuevaFila() {
        this.reloj = menorTiempo();
        
       if(reloj == proxLlegada){
           e = e.LlegaPedido;
       }else if(reloj == A1.getProxFin()){
           e = e.FinA1;
       }else if (reloj == A2. getProxFin()){
           e = e.FinA2;
       }else if (reloj == A3. getProxFin()){
           e = e.FinA3;
       }else if (reloj == A4. getProxFin()){
           e = e.FinA4;
       }else 
           e = e.FinA5;
       
       if (e == Evento.LlegaPedido){
           material+=1;
           proximoMaterial+=1;
           rndPedido = Math.random();
           tiempoEntreLlegadas = llegadaActividadCalc.calcularTiempo(rndPedido);
           proxLlegada += tiempoEntreLlegadas;
           
           if(A1.getE()== Estado.Libre){
               A1.setMaterial(1);
               double randA1 = Math.random();
               A1.setRnd(randA1);
               double tiempoCalculadoA1 = A1.getCalcActividad().calcularTiempo(randA1);
           A1.setTiempoAtencion(tiempoCalculadoA1);
           A1.setProxFin(reloj+tiempoCalculadoA1);
           A1.setColaUno(0);
           }
           A1.setE(Estado.Ocupado);
           
            if(A2.getE()== Estado.Libre){
               A2.setMaterial(1);
               double randA1 = Math.random();
               A2.setRnd(randA1);
               double tiempoCalculadoA1 = A2.getCalcActividad().calcularTiempo(randA1);
           A2.setTiempoAtencion(tiempoCalculadoA1);
           A2.setProxFin(reloj+tiempoCalculadoA1);
           A2.setColaUno(0);
           }
           A2.setE(Estado.Ocupado);
           
            if(A3.getE()== Estado.Libre){
               A3.setMaterial(1);
               double randA1 = Math.random();
               A3.setRnd(randA1);
               double tiempoCalculadoA1 = A3.getCalcActividad().calcularTiempo(randA1);
           A3.setTiempoAtencion(tiempoCalculadoA1);
           A3.setProxFin(reloj+tiempoCalculadoA1);
           A3.setColaUno(0);
           }
           A3.setE(Estado.Ocupado);
           
       }
       if(e == Evento.FinA1){
           double randA1 = Math.random();
           A1.setRnd(randA1);
           double tiempoCalculadoA1 = A1.getCalcActividad().calcularTiempo(randA1);
           A1.setTiempoAtencion(tiempoCalculadoA1);
           A1.setProxFin(reloj+tiempoCalculadoA1);
       }
       
       contadorN++;

    }
    
    private double menorTiempo(){
        double min = Math.min(tiempoEntreLlegadas,A1.getProxFin());
        min = Math.min(min,A2.getProxFin());
        min = Math.min(min,A3.getProxFin());
        min = Math.min(min,A4.getProxFin());
        min = Math.min(min,A5.getProxFin());
        
        return min;
    }

    public int getContadorN() {
        return contadorN;
    }

    public void setContadorN(int contadorN) {
        this.contadorN = contadorN;
    }

    public double getReloj() {
        return reloj;
    }

    public void setReloj(double reloj) {
        this.reloj = reloj;
    }

    public Evento getE() {
        return e;
    }

    public void setE(Evento e) {
        this.e = e;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public int getProximoMaterial() {
        return proximoMaterial;
    }

    public void setProximoMaterial(int proximoMaterial) {
        this.proximoMaterial = proximoMaterial;
    }

    public double getRndPedido() {
        return rndPedido;
    }

    public void setRndPedido(double rndPedido) {
        this.rndPedido = rndPedido;
    }

    public double getTiempoEntreLlegadas() {
        return tiempoEntreLlegadas;
    }

    public void setTiempoEntreLlegadas(double tiempoEntreLlegadas) {
        this.tiempoEntreLlegadas = tiempoEntreLlegadas;
    }

    public double getProxLlegada() {
        return proxLlegada;
    }

    public void setProxLlegada(double proxLlegada) {
        this.proxLlegada = proxLlegada;
    }

    public Actividad getA1() {
        return A1;
    }

    public void setA1(Actividad A1) {
        this.A1 = A1;
    }

    public Actividad getA2() {
        return A2;
    }

    public void setA2(Actividad A2) {
        this.A2 = A2;
    }

    public Actividad getA3() {
        return A3;
    }

    public void setA3(Actividad A3) {
        this.A3 = A3;
    }

    public Actividad getA4() {
        return A4;
    }

    public void setA4(Actividad A4) {
        this.A4 = A4;
    }

    public Actividad getA5() {
        return A5;
    }

    public void setA5(Actividad A5) {
        this.A5 = A5;
    }

    public int getColaA3() {
        return colaA3;
    }

    public void setColaA3(int colaA3) {
        this.colaA3 = colaA3;
    }

    public int getColaA5() {
        return colaA5;
    }

    public void setColaA5(int colaA5) {
        this.colaA5 = colaA5;
    }

    public int getTareasTerminadas() {
        return tareasTerminadas;
    }

    public void setTareasTerminadas(int tareasTerminadas) {
        this.tareasTerminadas = tareasTerminadas;
    }

    public double getFinA4() {
        return finA4;
    }

    public void setFinA4(double finA4) {
        this.finA4 = finA4;
    }

    public IActividad getLlegadaActividadCalc() {
        return llegadaActividadCalc;
    }

    public void setLlegadaActividadCalc(IActividad llegadaActividadCalc) {
        this.llegadaActividadCalc = llegadaActividadCalc;
    }

    
   

}
