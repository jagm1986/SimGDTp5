/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5;

/**
 *
 * @author Matias
 */
public interface IFila {

    void CalcularPrimeraFila();

    void CalcularNuevaFila(double contadorN, double promedio, double varianza);

    double getContadorN();

    double getFin();

    double getAcumulador();

    void setContadorN(double contadorN);

    void setPromedio(double promedio);

    double getRandomA1();

    double getRandomA2();

    double getRandomA3();

    double getRandomA4();

    double getRandomA5();

    double getTiempoA1();

    double getTiempoA2();

    double getTiempoA3();

    double getTiempoA4();

    double getTiempoA5();

    double getFinA4();

    double getPromedio();

    void setVarianza(double varianza);

    double getVarianza();

    void setAcumulador(double acumulador);

    long getSemilla();

    void setSemilla(long s);

    void setProb45(int p);

    int getProb45();

    void settStudentFormula(double t);

    double gettStudentFormula();

    double getTiempoMasTardioA1();

    double getTiempoMasTardioA2();

    double getTiempoMasTardioA3();

    double getTiempoMasTardioA4();

    double getTiempoMasTardioA5();

    double[] getContadorCC();

    void setContadorCC(double[] cc);

}
