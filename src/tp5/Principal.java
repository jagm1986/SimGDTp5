/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5;

//TODO Agregar validaciones para parametros normal y exponencial
//TODO Ultimo punto con los 15 intervalos
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.*;
import org.apache.commons.math3.distribution.TDistribution;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Usuario
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    private DefaultTableModel tabla = new DefaultTableModel();
    //A
    int N = 0;
    int desde = 0;
    int hasta = 0;
    private IActividad auxA1;
    private IActividad auxA2;
    private IActividad auxA3;
    private IActividad auxA4;
    private IActividad auxA5;
    private IFila aux1;
    private IFila aux2;
    private double promedio = 0;
    private double maximo = 0;
    private double minimo = 0;
    private int counterProbabilidad45dias = 0;
    long a = 0;
    long c = 0;
    long m = 0;
    long semilla = 0;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    DecimalFormat df = new DecimalFormat("#.###");

    private JFrame pantallaActual;

    private Object[] filaAImprimir = new Object[31];
    private Object[] columna = {"Sim", "R1", "T A1", "R2", "T A2", "R3", "T A3", "R4", "T A4", "Fin A4", "R5", "T A5", "Fin", "Acum", "Prom", "Min", "Max", "<45", "Var", "DS", "Form tst", "T In mas tardio A1", "T In mas tardio A2", "T In mas tardio A3", "T In mas tardio A4", "T In mas tardio A5", "%A1", "%A2", "%A3", "%A4", "%A5"};

    private void llenarFila(IFila aux) {
        filaAImprimir[0] = (int) aux.getContadorN();
        filaAImprimir[1] = df.format(aux.getRandomA1());
        filaAImprimir[2] = df.format(aux.getTiempoA1());
        filaAImprimir[3] = df.format(aux.getRandomA2());
        filaAImprimir[4] = df.format(aux.getTiempoA2());
        filaAImprimir[5] = df.format(aux.getRandomA3());
        filaAImprimir[6] = df.format(aux.getTiempoA3());
        filaAImprimir[7] = df.format(aux.getRandomA4());
        filaAImprimir[8] = df.format(aux.getTiempoA4());
        filaAImprimir[9] = df.format(aux.getFinA4());
        filaAImprimir[10] = df.format(aux.getRandomA5());
        filaAImprimir[11] = df.format(aux.getTiempoA5());
        filaAImprimir[12] = df.format(aux.getFin());
        filaAImprimir[13] = df.format(aux.getAcumulador());
        filaAImprimir[14] = df.format(aux.getPromedio());
        filaAImprimir[15] = df.format(minimo);
        filaAImprimir[16] = df.format(maximo);
        filaAImprimir[17] = df.format(aux.getProb45());
        filaAImprimir[18] = df.format(aux.getVarianza());
        filaAImprimir[19] = df.format(Math.sqrt(aux.getVarianza()));
        filaAImprimir[20] = df.format(aux.gettStudentFormula());
        filaAImprimir[21] = df.format(aux.getTiempoMasTardioA1());
        filaAImprimir[22] = df.format(aux.getTiempoMasTardioA2());
        filaAImprimir[23] = df.format(aux.getTiempoMasTardioA3());
        filaAImprimir[24] = df.format(aux.getTiempoMasTardioA4());
        filaAImprimir[25] = df.format(aux.getTiempoMasTardioA5());
        filaAImprimir[26] = df.format(aux.getContadorCC()[0] / aux.getContadorN());
        filaAImprimir[27] = df.format(aux.getContadorCC()[1] / aux.getContadorN());
        filaAImprimir[28] = df.format(aux.getContadorCC()[2] / aux.getContadorN());
        filaAImprimir[29] = df.format(aux.getContadorCC()[3] / aux.getContadorN());
        filaAImprimir[30] = df.format(aux.getContadorCC()[4] / aux.getContadorN());
    }

    private boolean validarNumerosNulos(String a, String b) {

        if (a.equals("") || b.equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Parametros no ingresados o deben ser mayores a 0", "Parámetros incorrectos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarNumerosNulos(String a) {

        if (a.equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Parametro no ingresado o deben ser mayor a 0", "Parámetro incorrecto", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;

    }

    private boolean obtenerDistribucionA1() {
        if (radioButtonUniformeA1.isSelected()) {
            if (!validarNumerosNulos(textaA1.getText(), textbA1.getText())) {
                return true;
            }
            double a = Double.parseDouble(textaA1.getText());
            double b = Double.parseDouble(textbA1.getText());

            auxA1 = new ActividadUniforme(a, b);
        }
        if (radioButtonNormalA1.isSelected()) {
            if (!validarNumerosNulos(textMuA1.getText(), textSigmaA1.getText())) {
                return true;
            }
            double mu = Double.parseDouble(textMuA1.getText());
            double sigma = Double.parseDouble(textSigmaA1.getText());

            auxA1 = new ActividadNormal(mu, sigma);

        }
        if (radioButtonExpA1.isSelected()) {
            if (!validarNumerosNulos(textLambdaA1.getText())) {
                return true;
            }
            double media = Double.parseDouble(textLambdaA1.getText());

            auxA1 = new ActividadExponencial(media);
        }
        return false;
    }

    private boolean obtenerDistribucionA2() {
        if (radioButtonUniformeA2.isSelected()) {
            if (!validarNumerosNulos(textaA2.getText(), textbA2.getText())) {
                return true;
            }
            double a = Double.parseDouble(textaA2.getText());
            double b = Double.parseDouble(textbA2.getText());

            auxA2 = new ActividadUniforme(a, b);
        }
        if (radioButtonNormalA2.isSelected()) {
            if (!validarNumerosNulos(textMuA2.getText(), textSigmaA2.getText())) {
                return true;
            }
            double mu = Double.parseDouble(textMuA2.getText());
            double sigma = Double.parseDouble(textSigmaA2.getText());

            auxA2 = new ActividadNormal(mu, sigma);

        }
        if (radioButtonExpA2.isSelected()) {
            if (!validarNumerosNulos(textLambdaA2.getText())) {
                return true;
            }
            double media = Double.parseDouble(textLambdaA2.getText());

            auxA2 = new ActividadExponencial(media);
        }
        return false;
    }

    private boolean obtenerDistribucionA3() {
        if (radioButtonUniformeA3.isSelected()) {
            if (!validarNumerosNulos(textaA3.getText(), textbA3.getText())) {
                return true;
            }
            double a = Double.parseDouble(textaA3.getText());
            double b = Double.parseDouble(textbA3.getText());

            auxA3 = new ActividadUniforme(a, b);
        }
        if (radioButtonNormalA3.isSelected()) {
            if (!validarNumerosNulos(textMuA3.getText(), textSigmaA3.getText())) {
                return true;
            }
            double mu = Double.parseDouble(textMuA3.getText());
            double sigma = Double.parseDouble(textSigmaA3.getText());

            auxA3 = new ActividadNormal(mu, sigma);

        }
        if (radioButtonExpA3.isSelected()) {
            if (!validarNumerosNulos(textLambdaA3.getText())) {
                return true;
            }
            double media = Double.parseDouble(textLambdaA3.getText());

            auxA3 = new ActividadExponencial(media);
        }
        return false;
    }

    private boolean obtenerDistribucionA4() {
        if (radioButtonUniformeA4.isSelected()) {
            if (!validarNumerosNulos(textaA4.getText(), textbA4.getText())) {
                return true;
            }
            double a = Double.parseDouble(textaA4.getText());
            double b = Double.parseDouble(textbA4.getText());

            auxA4 = new ActividadUniforme(a, b);
        }
        if (radioButtonNormalA4.isSelected()) {
            if (!validarNumerosNulos(textMuA4.getText(), textSigmaA4.getText())) {
                return true;
            }
            double mu = Double.parseDouble(textMuA4.getText());
            double sigma = Double.parseDouble(textSigmaA4.getText());

            auxA4 = new ActividadNormal(mu, sigma);

        }
        if (radioButtonExpA4.isSelected()) {
            if (!validarNumerosNulos(textLambdaA4.getText())) {
                return true;
            }
            double media = Double.parseDouble(textLambdaA4.getText());

            auxA4 = new ActividadExponencial(media);
        }
        return false;
    }

    private boolean obtenerDistribucionA5() {
        if (radioButtonUniformeA5.isSelected()) {
            if (!validarNumerosNulos(textaA5.getText(), textbA5.getText())) {
                return true;
            }
            double a = Double.parseDouble(textaA5.getText());
            double b = Double.parseDouble(textbA5.getText());

            auxA5 = new ActividadUniforme(a, b);
        }
        if (radioButtonNormalA5.isSelected()) {
            if (!validarNumerosNulos(textMuA5.getText(), textSigmaA5.getText())) {
                return true;
            }
            double mu = Double.parseDouble(textMuA5.getText());
            double sigma = Double.parseDouble(textSigmaA5.getText());

            auxA5 = new ActividadNormal(mu, sigma);

        }
        if (radioButtonExpA5.isSelected()) {
            if (!validarNumerosNulos(textLambdaA5.getText())) {
                return true;
            }
            double media = Double.parseDouble(textLambdaA5.getText());

            auxA5 = new ActividadExponencial(media);
        }
        return false;
    }

    public Principal() {

        initComponents();
        pantallaActual = new JFrame();

    }

    private boolean VerificarSeleccionCongruencial() {
        if (radioButtonCongurencial.isSelected() == true) {
            a = Long.parseLong(textACong.getText());
            c = Long.parseLong(textCCong.getText());
            m = Long.parseLong(textMCong.getText());
            semilla = Long.parseLong(textSemillaCong.getText());
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        BtnSimular = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCantidadFilas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        btnEvaluar = new javax.swing.JButton();
        lblMax = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        radioButtonNormalA1 = new javax.swing.JRadioButton();
        radioButtonExpA1 = new javax.swing.JRadioButton();
        radioButtonUniformeA1 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        textaA1 = new javax.swing.JTextField();
        textbA1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textLambdaA1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textMuA1 = new javax.swing.JTextField();
        textSigmaA1 = new javax.swing.JTextField();
        radioButtonNormalA2 = new javax.swing.JRadioButton();
        textMuA2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        textSigmaA2 = new javax.swing.JTextField();
        radioButtonExpA2 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        textLambdaA2 = new javax.swing.JTextField();
        radioButtonUniformeA2 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        textaA2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        textbA2 = new javax.swing.JTextField();
        radioButtonNormalA3 = new javax.swing.JRadioButton();
        textMuA3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        textSigmaA3 = new javax.swing.JTextField();
        radioButtonExpA3 = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        textLambdaA3 = new javax.swing.JTextField();
        radioButtonUniformeA3 = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        textaA3 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        textbA3 = new javax.swing.JTextField();
        radioButtonNormalA4 = new javax.swing.JRadioButton();
        textMuA4 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        textSigmaA4 = new javax.swing.JTextField();
        radioButtonExpA4 = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        textLambdaA4 = new javax.swing.JTextField();
        radioButtonUniformeA4 = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        textaA4 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        textbA4 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        radioButtonNormalA5 = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        textMuA5 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        textSigmaA5 = new javax.swing.JTextField();
        radioButtonExpA5 = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        textLambdaA5 = new javax.swing.JTextField();
        radioButtonUniformeA5 = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        textaA5 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        textbA5 = new javax.swing.JTextField();
        lblMin = new javax.swing.JLabel();
        lblProb45 = new javax.swing.JLabel();
        lblFechaMasBaja = new javax.swing.JLabel();
        lblIntegrantes = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        radioButtonSistema = new javax.swing.JRadioButton();
        radioButtonCongurencial = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        textACong = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        textCCong = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        textMCong = new javax.swing.JTextField();
        textSemillaCong = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        BtnSimular.setText("Simular");
        BtnSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimularActionPerformed(evt);
            }
        });

        jLabel2.setText("Cantidad de tareas a Simular");

        txtCantidadFilas.setText("100000");

        jLabel3.setText("Desde");

        jLabel4.setText("Hasta");

        txtDesde.setText("0");

        txtHasta.setText("1000");

        btnEvaluar.setText("Graficar");
        btnEvaluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluarActionPerformed(evt);
            }
        });

        lblMax.setText("Max:");

        jLabel1.setText("Actividad 1:");

        jLabel5.setText("Actividad 2:");

        jLabel6.setText("Actividad 3:");

        jLabel7.setText("Actividad 4:");

        buttonGroup1.add(radioButtonNormalA1);
        radioButtonNormalA1.setText("Normal");
        radioButtonNormalA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonNormalA1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioButtonExpA1);
        radioButtonExpA1.setText("Exponencial");
        radioButtonExpA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonExpA1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioButtonUniformeA1);
        radioButtonUniformeA1.setSelected(true);
        radioButtonUniformeA1.setText("Uniforme");
        radioButtonUniformeA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonUniformeA1ActionPerformed(evt);
            }
        });

        jLabel8.setText("a:");

        textaA1.setText("20");
        textaA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textaA1ActionPerformed(evt);
            }
        });

        textbA1.setText("30");

        jLabel9.setText("b:");

        jLabel10.setText("media:");

        textLambdaA1.setEnabled(false);

        jLabel11.setText("μ:");

        jLabel12.setText("σ:");

        textMuA1.setEnabled(false);

        textSigmaA1.setEnabled(false);

        buttonGroup2.add(radioButtonNormalA2);
        radioButtonNormalA2.setText("Normal");
        radioButtonNormalA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonNormalA2ActionPerformed(evt);
            }
        });

        textMuA2.setEnabled(false);

        jLabel13.setText("μ:");

        jLabel14.setText("σ:");

        textSigmaA2.setEnabled(false);

        buttonGroup2.add(radioButtonExpA2);
        radioButtonExpA2.setText("Exponencial");
        radioButtonExpA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonExpA2ActionPerformed(evt);
            }
        });

        jLabel15.setText("media:");

        textLambdaA2.setEnabled(false);

        buttonGroup2.add(radioButtonUniformeA2);
        radioButtonUniformeA2.setSelected(true);
        radioButtonUniformeA2.setText("Uniforme");
        radioButtonUniformeA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonUniformeA2ActionPerformed(evt);
            }
        });

        jLabel16.setText("a:");

        textaA2.setText("30");

        jLabel17.setText("b:");

        textbA2.setText("50");

        buttonGroup3.add(radioButtonNormalA3);
        radioButtonNormalA3.setText("Normal");
        radioButtonNormalA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonNormalA3ActionPerformed(evt);
            }
        });

        textMuA3.setEnabled(false);

        jLabel18.setText("μ:");

        jLabel19.setText("σ:");

        textSigmaA3.setEnabled(false);

        buttonGroup3.add(radioButtonExpA3);
        radioButtonExpA3.setSelected(true);
        radioButtonExpA3.setText("Exponencial");
        radioButtonExpA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonExpA3ActionPerformed(evt);
            }
        });

        jLabel20.setText("media:");

        textLambdaA3.setText("30");
        textLambdaA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLambdaA3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(radioButtonUniformeA3);
        radioButtonUniformeA3.setText("Uniforme");
        radioButtonUniformeA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonUniformeA3ActionPerformed(evt);
            }
        });

        jLabel21.setText("a:");

        textaA3.setEnabled(false);

        jLabel22.setText("b:");

        textbA3.setEnabled(false);

        buttonGroup4.add(radioButtonNormalA4);
        radioButtonNormalA4.setText("Normal");
        radioButtonNormalA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonNormalA4ActionPerformed(evt);
            }
        });

        textMuA4.setEnabled(false);

        jLabel23.setText("μ:");

        jLabel24.setText("σ:");

        textSigmaA4.setEnabled(false);

        buttonGroup4.add(radioButtonExpA4);
        radioButtonExpA4.setText("Exponencial");
        radioButtonExpA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonExpA4ActionPerformed(evt);
            }
        });

        jLabel25.setText("media:");

        textLambdaA4.setEnabled(false);

        buttonGroup4.add(radioButtonUniformeA4);
        radioButtonUniformeA4.setSelected(true);
        radioButtonUniformeA4.setText("Uniforme");
        radioButtonUniformeA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonUniformeA4ActionPerformed(evt);
            }
        });

        jLabel26.setText("a:");

        textaA4.setText("10");

        jLabel27.setText("b:");

        textbA4.setText("20");

        jLabel28.setText("Actividad 5:");

        buttonGroup5.add(radioButtonNormalA5);
        radioButtonNormalA5.setText("Normal");
        radioButtonNormalA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonNormalA5ActionPerformed(evt);
            }
        });

        jLabel29.setText("μ:");

        textMuA5.setEnabled(false);

        jLabel30.setText("σ:");

        textSigmaA5.setEnabled(false);

        buttonGroup5.add(radioButtonExpA5);
        radioButtonExpA5.setSelected(true);
        radioButtonExpA5.setText("Exponencial");
        radioButtonExpA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonExpA5ActionPerformed(evt);
            }
        });

        jLabel31.setText("media:");

        textLambdaA5.setText("5");
        textLambdaA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLambdaA5ActionPerformed(evt);
            }
        });

        buttonGroup5.add(radioButtonUniformeA5);
        radioButtonUniformeA5.setText("Uniforme");
        radioButtonUniformeA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonUniformeA5ActionPerformed(evt);
            }
        });

        jLabel32.setText("a:");

        textaA5.setEnabled(false);

        jLabel33.setText("b:");

        textbA5.setEnabled(false);

        lblMin.setText("Min:");

        lblProb45.setText("Probabilidad de completar en 45 dias o menos:");

        lblFechaMasBaja.setText("Fecha mas baja posible con 90% de confianza:");

        lblIntegrantes.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        lblIntegrantes.setText("Integrantes: Dominguez Ariel, Gudin Andres, Juarez Diego, Paglia Matias, Toia Lucia");

        jLabel34.setText("Forma de generacion de aleatorios:");

        buttonGroup6.add(radioButtonSistema);
        radioButtonSistema.setSelected(true);
        radioButtonSistema.setText("Sistema");
        radioButtonSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonSistemaActionPerformed(evt);
            }
        });

        buttonGroup6.add(radioButtonCongurencial);
        radioButtonCongurencial.setText("Congruencial");
        radioButtonCongurencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonCongurencialActionPerformed(evt);
            }
        });

        jLabel35.setText("a:");

        textACong.setEnabled(false);
        textACong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textACongActionPerformed(evt);
            }
        });

        jLabel36.setText("c:");

        textCCong.setEnabled(false);
        textCCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCCongActionPerformed(evt);
            }
        });

        jLabel37.setText("m:");

        textMCong.setEnabled(false);
        textMCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textMCongActionPerformed(evt);
            }
        });

        textSemillaCong.setEnabled(false);
        textSemillaCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSemillaCongActionPerformed(evt);
            }
        });

        jLabel38.setText("x:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioButtonSistema)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioButtonCongurencial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textACong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textCCong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textMCong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textSemillaCong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidadFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioButtonNormalA1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textMuA1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textSigmaA1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioButtonExpA1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textLambdaA1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(radioButtonUniformeA1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textaA1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textbA1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(radioButtonNormalA2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textMuA2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textSigmaA2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(radioButtonExpA2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textLambdaA2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(radioButtonUniformeA2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textaA2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textbA2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(radioButtonNormalA3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textMuA3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textSigmaA3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(radioButtonExpA3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textLambdaA3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(radioButtonUniformeA3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textaA3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textbA3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(radioButtonNormalA4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel23)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textMuA4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel24)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textSigmaA4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(radioButtonExpA4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel25)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textLambdaA4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(radioButtonUniformeA4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textaA4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel27)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textbA4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEvaluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnSimular, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonNormalA5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textMuA5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSigmaA5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonExpA5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textLambdaA5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(radioButtonUniformeA5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textaA5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textbA5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMax)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMin))
                                    .addComponent(lblProb45)
                                    .addComponent(lblFechaMasBaja))
                                .addGap(0, 808, Short.MAX_VALUE))
                            .addComponent(lblIntegrantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCantidadFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnSimular)
                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(btnEvaluar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(textSemillaCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(textMCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(textCCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(textACong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioButtonSistema)
                        .addComponent(radioButtonCongurencial)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioButtonNormalA1)
                    .addComponent(radioButtonExpA1)
                    .addComponent(radioButtonUniformeA1)
                    .addComponent(jLabel8)
                    .addComponent(textaA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(textbA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(textLambdaA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(textMuA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSigmaA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(radioButtonNormalA2)
                    .addComponent(radioButtonExpA2)
                    .addComponent(radioButtonUniformeA2)
                    .addComponent(jLabel16)
                    .addComponent(textaA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(textbA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(textLambdaA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(textMuA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSigmaA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(radioButtonNormalA3)
                    .addComponent(radioButtonExpA3)
                    .addComponent(radioButtonUniformeA3)
                    .addComponent(jLabel21)
                    .addComponent(textaA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(textbA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(textLambdaA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(textMuA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSigmaA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(radioButtonNormalA4)
                    .addComponent(radioButtonExpA4)
                    .addComponent(radioButtonUniformeA4)
                    .addComponent(jLabel26)
                    .addComponent(textaA4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(textbA4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(textLambdaA4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(textMuA4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSigmaA4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(radioButtonNormalA5)
                    .addComponent(radioButtonExpA5)
                    .addComponent(radioButtonUniformeA5)
                    .addComponent(jLabel32)
                    .addComponent(textaA5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(textbA5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(textLambdaA5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(textMuA5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSigmaA5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMax)
                    .addComponent(lblMin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProb45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaMasBaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIntegrantes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    double masBajo;
    private void BtnSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimularActionPerformed
        // TODO add your handling code here:
        TDistribution tdss = new TDistribution(1);
        System.out.println(tdss.inverseCumulativeProbability(0.975));

        N = Integer.parseInt(txtCantidadFilas.getText());
        desde = Integer.parseInt(txtDesde.getText());
        hasta = Integer.parseInt(txtHasta.getText());

        counterProbabilidad45dias = 0;

        boolean esCong = VerificarSeleccionCongruencial();

        if (obtenerDistribucionA1()) {
            return;
        }
        if (obtenerDistribucionA2()) {
            return;
        }
        if (obtenerDistribucionA3()) {
            return;
        }
        if (obtenerDistribucionA4()) {
            return;
        }
        if (obtenerDistribucionA5()) {
            return;
        }

        tabla = new DefaultTableModel();
        tabla.setColumnIdentifiers(columna);
        if (esCong == true) {
            aux1 = new FilaCongruencial(auxA1, auxA2, auxA3, auxA4, auxA5, a, c, m, semilla);
            aux2 = new FilaCongruencial(auxA1, auxA2, auxA3, auxA4, auxA5, a, c, m, semilla);
        } else {
            aux1 = new Fila(auxA1, auxA2, auxA3, auxA4, auxA5);
            aux2 = new Fila(auxA1, auxA2, auxA3, auxA4, auxA5);
        }
        aux1.CalcularPrimeraFila();

        try {
            if (aux2.getSemilla() != -1) {
                aux2.setSemilla(aux1.getSemilla());
            }
        } catch (Exception e) {
        }
        maximo = aux1.getFin();
        minimo = aux1.getFin();
        double A1 = aux1.getContadorCC()[0];
        double A2 = aux1.getContadorCC()[1];
        double A3 = aux1.getContadorCC()[2];
        double A4 = aux1.getContadorCC()[3];
        double A5 = aux1.getContadorCC()[4];
        double[] cc = new double[5];
        cc[0] = A1;
        cc[1] = A2;
        cc[2] = A3;
        cc[3] = A4;
        cc[4] = A5;

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                aux2.setContadorCC(cc);
            }
            aux2.CalcularNuevaFila(aux1.getContadorN(), aux1.getPromedio(), aux1.getVarianza());
            if (aux1.getFin() > maximo) {
                maximo = aux1.getFin();
            }
            if (aux1.getFin() < minimo) {
                minimo = aux1.getFin();
            }
            if (aux1.getFin() <= 45) {
                aux1.setProb45(1);
                counterProbabilidad45dias++;
            }
            if (aux1.getContadorN() >= desde && aux1.getContadorN() <= hasta || aux1.getContadorN() == N) {
                llenarFila(aux1);
                tabla.addRow(filaAImprimir);
                if (aux2.getContadorN() == 2) {
                    aux2.setAcumulador(aux1.getAcumulador() + aux2.getFin());
                    aux2.setPromedio(aux2.getAcumulador() / 2);
                    if (aux2.getFin() > maximo) {
                        maximo = aux2.getFin();
                    }
                    if (aux2.getFin() < minimo) {
                        minimo = aux2.getFin();
                    }
                    if (aux2.getFin() <= 45) {
                        aux2.setProb45(1);
                        counterProbabilidad45dias++;
                    }
                    llenarFila(aux2);
                    tabla.addRow(filaAImprimir);

                }
            }
            if (aux1.getContadorN() == N) {
                promedio = aux1.getPromedio();
            }
            if (i < 500) {
                dataset.addValue(aux1.getPromedio(), "", String.valueOf(i));
            }

            aux1 = aux2;

            if (i == N - 1) {
                masBajo = aux1.gettStudentFormula();
               // masBajo = Math.round(masBajo);
            }
        }
        double promedio45 = (double) counterProbabilidad45dias / (double) N;
        lblMax.setText("Maximo:" + df.format(maximo));
        lblMin.setText(" Minimo: " + df.format(minimo));
        lblProb45.setText("Probabilidad de completar en 45 dias o menos: %" + 100 * promedio45);
        Tabla.setModel(tabla);

        /* double all[] = new double[hasta];
        for (int i = 0; i < hasta; i++) {
            all[i] = Double.parseDouble(Tabla.getModel().getValueAt(i, 12).toString().replace(",", "."));
        }
        Arrays.sort(all);
        double masBajo = 999999;
        for (int i = 0; i < all.length; i++) {
            double d = all[i];
            if ((double) i / all.length > 0.9) {
                if (d < masBajo) {
                    masBajo = d;
                }
            }
        }*/
        lblFechaMasBaja.setText("Fecha mas baja posible con 90% de confianza: " + String.format("%.2f", masBajo));

    }//GEN-LAST:event_BtnSimularActionPerformed

    private void btnEvaluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvaluarActionPerformed
        // TODO add your handling code here:

        pantallaActual.dispose();
//        pantallaActual = new Resultado(promedio31, promedio32, promedio33, promedio34);
        pantallaActual.setVisible(true);
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Prueba",
                "Nro Simulacion", "Dias",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartFrame frame = new ChartFrame("Gráfico de barras", lineChart);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }//GEN-LAST:event_btnEvaluarActionPerformed

    private void radioButtonNormalA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonNormalA1ActionPerformed

        textaA1.setEnabled(false);
        textbA1.setEnabled(false);
        textLambdaA1.setEnabled(false);
        textSigmaA1.setEnabled(true);
        textMuA1.setEnabled(true);
    }//GEN-LAST:event_radioButtonNormalA1ActionPerformed

    private void radioButtonExpA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonExpA1ActionPerformed
        textaA1.setEnabled(false);
        textbA1.setEnabled(false);
        textLambdaA1.setEnabled(true);
        textSigmaA1.setEnabled(false);
        textMuA1.setEnabled(false);
    }//GEN-LAST:event_radioButtonExpA1ActionPerformed

    private void radioButtonNormalA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonNormalA2ActionPerformed

        textaA2.setEnabled(false);
        textbA2.setEnabled(false);
        textLambdaA2.setEnabled(false);
        textSigmaA2.setEnabled(true);
        textMuA2.setEnabled(true);

    }//GEN-LAST:event_radioButtonNormalA2ActionPerformed

    private void radioButtonExpA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonExpA2ActionPerformed

        textaA2.setEnabled(false);
        textbA2.setEnabled(false);
        textLambdaA2.setEnabled(true);
        textSigmaA2.setEnabled(false);
        textMuA2.setEnabled(false);
    }//GEN-LAST:event_radioButtonExpA2ActionPerformed

    private void radioButtonNormalA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonNormalA3ActionPerformed
        textaA3.setEnabled(false);
        textbA3.setEnabled(false);
        textLambdaA3.setEnabled(false);
        textSigmaA3.setEnabled(true);
        textMuA3.setEnabled(true);
    }//GEN-LAST:event_radioButtonNormalA3ActionPerformed

    private void radioButtonExpA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonExpA3ActionPerformed
        textaA3.setEnabled(false);
        textbA3.setEnabled(false);
        textLambdaA3.setEnabled(true);
        textSigmaA3.setEnabled(false);
        textMuA3.setEnabled(false);

    }//GEN-LAST:event_radioButtonExpA3ActionPerformed

    private void radioButtonNormalA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonNormalA4ActionPerformed
        textaA4.setEnabled(false);
        textbA4.setEnabled(false);
        textLambdaA4.setEnabled(false);
        textSigmaA4.setEnabled(true);
        textMuA4.setEnabled(true);
    }//GEN-LAST:event_radioButtonNormalA4ActionPerformed

    private void radioButtonExpA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonExpA4ActionPerformed
        textaA4.setEnabled(false);
        textbA4.setEnabled(false);
        textLambdaA4.setEnabled(true);
        textSigmaA4.setEnabled(false);
        textMuA4.setEnabled(false);
    }//GEN-LAST:event_radioButtonExpA4ActionPerformed

    private void radioButtonNormalA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonNormalA5ActionPerformed
        textaA5.setEnabled(false);
        textbA5.setEnabled(false);
        textLambdaA5.setEnabled(false);
        textSigmaA5.setEnabled(true);
        textMuA5.setEnabled(true);
    }//GEN-LAST:event_radioButtonNormalA5ActionPerformed

    private void radioButtonExpA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonExpA5ActionPerformed
        textaA5.setEnabled(false);
        textbA5.setEnabled(false);
        textLambdaA5.setEnabled(true);
        textSigmaA5.setEnabled(false);
        textMuA5.setEnabled(false);

    }//GEN-LAST:event_radioButtonExpA5ActionPerformed

    private void textaA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textaA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textaA1ActionPerformed

    private void textLambdaA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLambdaA3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLambdaA3ActionPerformed

    private void textLambdaA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLambdaA5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLambdaA5ActionPerformed

    private void radioButtonUniformeA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonUniformeA1ActionPerformed
        textaA1.setEnabled(true);
        textbA1.setEnabled(true);
        textLambdaA1.setEnabled(false);
        textSigmaA1.setEnabled(false);
        textMuA1.setEnabled(false);
    }//GEN-LAST:event_radioButtonUniformeA1ActionPerformed

    private void radioButtonUniformeA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonUniformeA2ActionPerformed

        textaA2.setEnabled(true);
        textbA2.setEnabled(true);
        textLambdaA2.setEnabled(false);
        textSigmaA2.setEnabled(false);
        textMuA2.setEnabled(false);
    }//GEN-LAST:event_radioButtonUniformeA2ActionPerformed

    private void radioButtonUniformeA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonUniformeA3ActionPerformed
        textaA3.setEnabled(true);
        textbA3.setEnabled(true);
        textLambdaA3.setEnabled(false);
        textSigmaA3.setEnabled(false);
        textMuA3.setEnabled(false);
    }//GEN-LAST:event_radioButtonUniformeA3ActionPerformed

    private void radioButtonUniformeA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonUniformeA4ActionPerformed

        textaA4.setEnabled(true);
        textbA4.setEnabled(true);
        textLambdaA4.setEnabled(false);
        textSigmaA4.setEnabled(false);
        textMuA4.setEnabled(false);
    }//GEN-LAST:event_radioButtonUniformeA4ActionPerformed

    private void radioButtonUniformeA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonUniformeA5ActionPerformed
        textaA5.setEnabled(true);
        textbA5.setEnabled(true);
        textLambdaA5.setEnabled(false);
        textSigmaA5.setEnabled(false);
        textMuA5.setEnabled(false);
    }//GEN-LAST:event_radioButtonUniformeA5ActionPerformed

    private void radioButtonSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonSistemaActionPerformed
        textACong.setEnabled(false);
        textCCong.setEnabled(false);
        textSemillaCong.setEnabled(false);
        textMCong.setEnabled(false);
    }//GEN-LAST:event_radioButtonSistemaActionPerformed

    private void radioButtonCongurencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonCongurencialActionPerformed
        textACong.setEnabled(true);
        textCCong.setEnabled(true);
        textSemillaCong.setEnabled(true);
        textMCong.setEnabled(true);
    }//GEN-LAST:event_radioButtonCongurencialActionPerformed

    private void textACongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textACongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textACongActionPerformed

    private void textCCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCCongActionPerformed

    private void textMCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textMCongActionPerformed

    private void textSemillaCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSemillaCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSemillaCongActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSimular;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnEvaluar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaMasBaja;
    private javax.swing.JLabel lblIntegrantes;
    private javax.swing.JLabel lblMax;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblProb45;
    private javax.swing.JRadioButton radioButtonCongurencial;
    private javax.swing.JRadioButton radioButtonExpA1;
    private javax.swing.JRadioButton radioButtonExpA2;
    private javax.swing.JRadioButton radioButtonExpA3;
    private javax.swing.JRadioButton radioButtonExpA4;
    private javax.swing.JRadioButton radioButtonExpA5;
    private javax.swing.JRadioButton radioButtonNormalA1;
    private javax.swing.JRadioButton radioButtonNormalA2;
    private javax.swing.JRadioButton radioButtonNormalA3;
    private javax.swing.JRadioButton radioButtonNormalA4;
    private javax.swing.JRadioButton radioButtonNormalA5;
    private javax.swing.JRadioButton radioButtonSistema;
    private javax.swing.JRadioButton radioButtonUniformeA1;
    private javax.swing.JRadioButton radioButtonUniformeA2;
    private javax.swing.JRadioButton radioButtonUniformeA3;
    private javax.swing.JRadioButton radioButtonUniformeA4;
    private javax.swing.JRadioButton radioButtonUniformeA5;
    private javax.swing.JTextField textACong;
    private javax.swing.JTextField textCCong;
    private javax.swing.JTextField textLambdaA1;
    private javax.swing.JTextField textLambdaA2;
    private javax.swing.JTextField textLambdaA3;
    private javax.swing.JTextField textLambdaA4;
    private javax.swing.JTextField textLambdaA5;
    private javax.swing.JTextField textMCong;
    private javax.swing.JTextField textMuA1;
    private javax.swing.JTextField textMuA2;
    private javax.swing.JTextField textMuA3;
    private javax.swing.JTextField textMuA4;
    private javax.swing.JTextField textMuA5;
    private javax.swing.JTextField textSemillaCong;
    private javax.swing.JTextField textSigmaA1;
    private javax.swing.JTextField textSigmaA2;
    private javax.swing.JTextField textSigmaA3;
    private javax.swing.JTextField textSigmaA4;
    private javax.swing.JTextField textSigmaA5;
    private javax.swing.JTextField textaA1;
    private javax.swing.JTextField textaA2;
    private javax.swing.JTextField textaA3;
    private javax.swing.JTextField textaA4;
    private javax.swing.JTextField textaA5;
    private javax.swing.JTextField textbA1;
    private javax.swing.JTextField textbA2;
    private javax.swing.JTextField textbA3;
    private javax.swing.JTextField textbA4;
    private javax.swing.JTextField textbA5;
    private javax.swing.JTextField txtCantidadFilas;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    // End of variables declaration//GEN-END:variables
}
