/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5;

import java.util.ArrayList;
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
    int proximoMaterial;
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
    ArrayList<Double> tiemposInicio = new ArrayList<Double>();
    double finA4 = 0;
    IActividad llegadaActividadCalc;
    double acumEnsamble;
    double promedioEnsamble;

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
        llegadaActividadCalc = new ActividadExponencial(1 / lambda);
        rndPedido = Math.random();
        tiempoEntreLlegadas = llegadaActividadCalc.calcularTiempo(rndPedido);
        proxLlegada = reloj + tiempoEntreLlegadas;

        IActividad paraA1 = new ActividadUniforme(20, 30);
        IActividad paraA2 = new ActividadUniforme(30, 50);
        IActividad paraA3 = new ActividadExponencial(30);
        IActividad paraA4 = new ActividadUniforme(10, 20);
        IActividad paraA5 = new ActividadExponencial(5);

        A1 = new Actividad(Estado.Libre, 0, 0, paraA1);
        A2 = new Actividad(Estado.Libre, 0, 0, paraA2);
        A3 = new Actividad(Estado.Libre, 0, 0, paraA3);
        A4 = new Actividad(Estado.Libre, 0, 0, paraA4);
        A5 = new Actividad(Estado.Libre, 0, 0, paraA5);

        colaA3 = 0;
        colaA5 = 0;
        tareasTerminadas = 0;
        contadorN = 1;
    }

    public void CalcularNuevaFila() {
        this.reloj = menorTiempo();

        if (reloj == proxLlegada) {
            e = e.LlegaPedido;
        } else if (reloj == A1.getProxFin()) {
            e = e.FinA1;
        } else if (reloj == A2.getProxFin()) {
            e = e.FinA2;
        } else if (reloj == A3.getProxFin()) {
            e = e.FinA3;
        } else if (reloj == A4.getProxFin()) {
            e = e.FinA4;
        } else {
            e = e.FinA5;
        }

        if (e == Evento.LlegaPedido) {
            material += 1;
            proximoMaterial += 1;
            rndPedido = Math.random();
            tiempoEntreLlegadas = llegadaActividadCalc.calcularTiempo(rndPedido);
            proxLlegada += tiempoEntreLlegadas;

            if (A1.getE() == Estado.Libre) {
                A1.setMaterial(1);
                double randA1 = Math.random();
                A1.setRnd(randA1);
                double tiempoCalculadoA1 = A1.getCalcActividad().calcularTiempo(randA1);
                A1.setTiempoAtencion(tiempoCalculadoA1);
                A1.setProxFin(reloj + tiempoCalculadoA1);
                A1.setColaUno(0);
            } else if (A1.getE() == Estado.Ocupado) {
                A1.setColaUno(A1.getColaUno() + 1);
            }
            A1.setE(Estado.Ocupado);

            if (A2.getE() == Estado.Libre) {
                A2.setMaterial(1);
                double randA1 = Math.random();
                A2.setRnd(randA1);
                double tiempoCalculadoA1 = A2.getCalcActividad().calcularTiempo(randA1);
                A2.setTiempoAtencion(tiempoCalculadoA1);
                A2.setProxFin(reloj + tiempoCalculadoA1);
                A2.setColaUno(0);
            } else if (A2.getE() == Estado.Ocupado) {
                A2.setColaUno(A2.getColaUno() + 1);
            }
            A2.setE(Estado.Ocupado);

            if (A3.getE() == Estado.Libre) {
                A3.setMaterial(1);
                double randA1 = Math.random();
                A3.setRnd(randA1);
                double tiempoCalculadoA1 = A3.getCalcActividad().calcularTiempo(randA1);
                A3.setTiempoAtencion(tiempoCalculadoA1);
                A3.setProxFin(reloj + tiempoCalculadoA1);
                A3.setColaUno(0);
            } else if (A3.getE() == Estado.Ocupado) {
                A3.setColaUno(A3.getColaUno() + 1);
            }
            A3.setE(Estado.Ocupado);
            
            tiemposInicio.add(reloj);
        }

        if (e == Evento.FinA1) {
            /*  double randA1 = Math.random();
           A1.setRnd(randA1);
           double tiempoCalculadoA1 = A1.getCalcActividad().calcularTiempo(randA1);
           A1.setTiempoAtencion(tiempoCalculadoA1);
           A1.setProxFin(reloj+tiempoCalculadoA1);*/
            if (A1.getColaUno() == 0) {
                A1.setE(Estado.Libre);
                A1.setColaUno(0);
                A1.setProxFin(999999);
                A1.setRnd(0.0);
                A1.setTiempoAtencion(0.0);
            } else if (A1.getColaUno() > 0) {
                A1.setMaterial(A1.getMaterial() + 1);
                double randA1 = Math.random();
                A1.setRnd(randA1);
                double tiempoCalculadoA1 = A1.getCalcActividad().calcularTiempo(randA1);
                A1.setTiempoAtencion(tiempoCalculadoA1);
                A1.setProxFin(reloj + tiempoCalculadoA1);
                A1.setColaUno(A1.getColaUno() - 1);
            }
            if (A4.getE() == Estado.Libre) {
                A4.setMaterial(1);
                double randA1 = Math.random();
                A4.setRnd(randA1);
                double tiempoCalculadoA1 = A4.getCalcActividad().calcularTiempo(randA1);
                A4.setTiempoAtencion(tiempoCalculadoA1);
                A4.setProxFin(reloj + tiempoCalculadoA1);
                A4.setColaUno(0);
            } else if (A4.getE() == Estado.Ocupado) {
                A4.setColaUno(A4.getColaUno() + 1);
            }
            A4.setE(Estado.Ocupado);
        }

        if (e == Evento.FinA2) {
            if (A2.getColaUno() == 0) {
                A2.setE(Estado.Libre);
                A2.setColaUno(0);
                A2.setProxFin(999999);
                A2.setRnd(0.0);
                A2.setTiempoAtencion(0.0);
            } else if (A2.getColaUno() > 0) {
                A2.setMaterial(A2.getMaterial() + 1);
                double randA2 = Math.random();
                A2.setRnd(randA2);
                double tiempoCalculadoA2 = A2.getCalcActividad().calcularTiempo(randA2);
                A2.setTiempoAtencion(tiempoCalculadoA2);
                A2.setProxFin(reloj + tiempoCalculadoA2);
                A2.setColaUno(A2.getColaUno() - 1);
            }
            if (A5.getE() == Estado.Libre) {
                A5.setColaUno(A5.getColaUno() + 1);
                A5.setE(Estado.Bloqueado);
            } else if (A5.getE() == Estado.Bloqueado) {
                if (A5.getColaDos() == 0) {
                    A5.setColaUno(A5.getColaUno() + 1);
                } else {
                    A5.setMaterial(A5.getMaterial() + 1);
                    double randA5 = Math.random();
                    A5.setRnd(randA5);
                    double tiempoCalculadoA5 = A5.getCalcActividad().calcularTiempo(randA5);
                    A5.setTiempoAtencion(tiempoCalculadoA5);
                    A5.setProxFin(reloj + tiempoCalculadoA5);
                    A5.setColaDos(A5.getColaDos() - 1);
                    A5.setE(Estado.Ocupado);
                }
            } else if (A5.getE() == Estado.Ocupado) {
                A5.setColaUno(A5.getColaUno() + 1);
            }
        }

        if (e == Evento.FinA3) {
            if (A3.getColaUno() == 0) {
                A3.setE(Estado.Libre);
                A3.setColaUno(0);
                A3.setProxFin(999999);
                A3.setRnd(0.0);
                A3.setTiempoAtencion(0.0);
            } else if (A3.getColaUno() > 0) {
                A3.setMaterial(A3.getMaterial() + 1);
                double randA3 = Math.random();
                A3.setRnd(randA3);
                double tiempoCalculadoA3 = A3.getCalcActividad().calcularTiempo(randA3);
                A3.setTiempoAtencion(tiempoCalculadoA3);
                A3.setProxFin(reloj + tiempoCalculadoA3);
                A3.setColaUno(A3.getColaUno() - 1);
            }
            if (this.colaA5 > 0) {
                this.colaA5--;
                this.tareasTerminadas++;
                double duracionEnsamble = reloj - tiemposInicio.get(tareasTerminadas-1) ;
                acumEnsamble += duracionEnsamble;
                promedioEnsamble = acumEnsamble/tareasTerminadas;
            } else {
                this.colaA3++;
            }
        }

        if (e == Evento.FinA4) {
            if (A4.getColaUno() == 0) {
                A4.setE(Estado.Libre);
                A4.setColaUno(0);
                A4.setProxFin(999999);
                A4.setRnd(0.0);
                A4.setTiempoAtencion(0.0);
            } else if (A4.getColaUno() > 0) {
                A4.setMaterial(A4.getMaterial() + 1);
                double randA4 = Math.random();
                A4.setRnd(randA4);
                double tiempoCalculadoA4 = A4.getCalcActividad().calcularTiempo(randA4);
                A4.setTiempoAtencion(tiempoCalculadoA4);
                A4.setProxFin(reloj + tiempoCalculadoA4);
                A4.setColaUno(A4.getColaUno() - 1);
            }
            if (A5.getE() == Estado.Libre) {
                A5.setColaUno(A5.getColaDos() + 1);
                A5.setE(Estado.Bloqueado);
            } else if (A5.getE() == Estado.Bloqueado) {
                if (A5.getColaUno() == 0) {
                    A5.setColaDos(A5.getColaDos() + 1);
                } else {
                    A5.setMaterial(A5.getMaterial() + 1);
                    double randA5 = Math.random();
                    A5.setRnd(randA5);
                    double tiempoCalculadoA5 = A5.getCalcActividad().calcularTiempo(randA5);
                    A5.setTiempoAtencion(tiempoCalculadoA5);
                    A5.setProxFin(reloj + tiempoCalculadoA5);
                    A5.setColaUno(A5.getColaUno() - 1);
                    A5.setE(Estado.Ocupado);
                }
            } else if (A5.getE() == Estado.Ocupado) {
                A5.setColaDos(A5.getColaDos() + 1);
            }
        }

        if (e == Evento.FinA5) {
            if (A5.getColaDos() > 0 && A5.getColaUno() == 0) {
                A5.setE(Estado.Bloqueado);
                A5.setProxFin(999999);
                A5.setRnd(0.0);
                A5.setTiempoAtencion(0.0);
            } else if (A5.getColaUno() > 0 && A5.getColaDos() == 0) {
                A5.setE(Estado.Bloqueado);
                A5.setProxFin(999999);
                A5.setRnd(0.0);
                A5.setTiempoAtencion(0.0);
            } else if (A5.getColaDos() > 0 && A5.getColaUno() > 0) {
                A5.setE(Estado.Ocupado);
                A5.setMaterial(A5.getMaterial() + 1);
                double randA5 = Math.random();
                A5.setRnd(randA5);
                double tiempoCalculadoA5 = A5.getCalcActividad().calcularTiempo(randA5);
                A5.setTiempoAtencion(tiempoCalculadoA5);
                A5.setProxFin(reloj + tiempoCalculadoA5);
                A5.setColaDos(A5.getColaDos() - 1);
                A5.setColaUno(A5.getColaUno() - 1);
            } else if (A5.getColaDos() == 0 && A5.getColaUno() == 0) {
                A5.setE(Estado.Libre);
                A5.setProxFin(999999);
                A5.setRnd(0.0);
                A5.setTiempoAtencion(0.0);
            }
            if (this.colaA3 > 0) {
                this.colaA3--;
                this.tareasTerminadas++;
                double duracionEnsamble = reloj - tiemposInicio.get(tareasTerminadas-1) ;
                acumEnsamble += duracionEnsamble;
                promedioEnsamble = acumEnsamble/tareasTerminadas;
            } else {
                this.colaA5++;
            }
        }

        contadorN++;

    }

    private double menorTiempo() {
        double min = Math.min(proxLlegada, A1.getProxFin());
        min = Math.min(min, A2.getProxFin());
        min = Math.min(min, A3.getProxFin());
        min = Math.min(min, A4.getProxFin());
        min = Math.min(min, A5.getProxFin());

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

    public ArrayList<Double> getTiemposInicio() {
        return tiemposInicio;
    }

    public void setTiemposInicio(ArrayList<Double> tiemposInicio) {
        this.tiemposInicio = tiemposInicio;
    }

    public double getAcumEnsamble() {
        return acumEnsamble;
    }

    public void setAcumEnsamble(double acumEnsamble) {
        this.acumEnsamble = acumEnsamble;
    }

    public double getPromedioEnsamble() {
        return promedioEnsamble;
    }

    public void setPromedioEnsamble(double promedioEnsamble) {
        this.promedioEnsamble = promedioEnsamble;
    }
    
    

}
