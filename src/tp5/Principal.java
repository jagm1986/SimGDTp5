/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5;

//TODO Agregar validaciones para parametros normal y exponencial
//TODO Ultimo punto con los 15 intervalos
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
    double lambdaLl = 0;
    private IActividad auxA1;
    private IActividad auxA2;
    private IActividad auxA3;
    private IActividad auxA4;
    private IActividad auxA5;
    private Fila aux1;
    private Fila aux2;
    private double promedio = 0;
    private double maximo = 0;
    private double minimo = 0;
    private int counterProbabilidad45dias = 0;
    long a = 0;
    long c = 0;
    long m = 0;
    long semilla = 0;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    DecimalFormat df = new DecimalFormat("#.####");

    private JFrame pantallaActual;

    private Object[] filaAImprimir = new Object[44];
    private Object[] columna = {"Nro Sim", "Reloj", "Evento", "Nro Material", "Proximo Mat", "Rnd Llegada", "T Entre Llegadas", "Prox Llegada", "Estado A1", "Material A1", "rnd A1", "T Atencion A1", "Prox fin A1", "Cola A1", "Estado A2", "Material A2", "rnd A2", "T Atencion A2", "Prox fin A2", "Cola A2", "Estado A3", "Material A3", "rnd A3", "T Atencion A3", "Prox fin A3", "Cola A3", "Estado A4", "Material A4", "rnd A4", "T Atencion A4", "Prox fin A4", "Cola A4", "Estado A5", "Material A5", "rnd A5", "T Atencion A5", "Prox fin A5", "Cola A2 (5)", "Cola A4 (5)", "Cola Terminados A3", "Cola Terminados A5", "Tareas terminadas", "Acum T Ensambles", "Prom T ensambles"};

    public Principal(IActividad auxA1, IActividad auxA2, IActividad auxA3, IActividad auxA4, IActividad auxA5, Fila aux1, Fila aux2, JFrame pantallaActual, double masBajo, JButton BtnSimular, JTable Tabla, JButton btnEvaluar, ButtonGroup buttonGroup1, ButtonGroup buttonGroup2, ButtonGroup buttonGroup3, ButtonGroup buttonGroup4, ButtonGroup buttonGroup5, ButtonGroup buttonGroup6, JLabel jLabel1, JLabel jLabel10, JLabel jLabel11, JLabel jLabel12, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15, JLabel jLabel16, JLabel jLabel17, JLabel jLabel18, JLabel jLabel19, JLabel jLabel2, JLabel jLabel20, JLabel jLabel21, JLabel jLabel22, JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JLabel jLabel26, JLabel jLabel27, JLabel jLabel28, JLabel jLabel29, JLabel jLabel3, JLabel jLabel30, JLabel jLabel31, JLabel jLabel32, JLabel jLabel33, JLabel jLabel34, JLabel jLabel35, JLabel jLabel4, JLabel jLabel5, JLabel jLabel6, JLabel jLabel7, JLabel jLabel8, JLabel jLabel9, JScrollPane jScrollPane1, JTextField lambdaLlegadas, JLabel lblIntegrantes, JRadioButton radioButtonExpA1, JRadioButton radioButtonExpA2, JRadioButton radioButtonExpA3, JRadioButton radioButtonExpA4, JRadioButton radioButtonExpA5, JRadioButton radioButtonNormalA1, JRadioButton radioButtonNormalA2, JRadioButton radioButtonNormalA3, JRadioButton radioButtonNormalA4, JRadioButton radioButtonNormalA5, JRadioButton radioButtonUniformeA1, JRadioButton radioButtonUniformeA2, JRadioButton radioButtonUniformeA3, JRadioButton radioButtonUniformeA4, JRadioButton radioButtonUniformeA5, JTextField textLambdaA1, JTextField textLambdaA2, JTextField textLambdaA3, JTextField textLambdaA4, JTextField textLambdaA5, JTextField textMuA1, JTextField textMuA2, JTextField textMuA3, JTextField textMuA4, JTextField textMuA5, JTextField textSigmaA1, JTextField textSigmaA2, JTextField textSigmaA3, JTextField textSigmaA4, JTextField textSigmaA5, JTextField textaA1, JTextField textaA2, JTextField textaA3, JTextField textaA4, JTextField textaA5, JTextField textbA1, JTextField textbA2, JTextField textbA3, JTextField textbA4, JTextField textbA5, JTextField txtCantidadFilas, JTextField txtDesde, JTextField txtHasta) throws HeadlessException {
        this.auxA1 = auxA1;
        this.auxA2 = auxA2;
        this.auxA3 = auxA3;
        this.auxA4 = auxA4;
        this.auxA5 = auxA5;
        this.aux1 = aux1;
        this.aux2 = aux2;
        this.pantallaActual = pantallaActual;
        this.masBajo = masBajo;
        this.BtnSimular = BtnSimular;
        this.Tabla = Tabla;

        this.buttonGroup1 = buttonGroup1;
        this.buttonGroup2 = buttonGroup2;
        this.buttonGroup3 = buttonGroup3;
        this.buttonGroup4 = buttonGroup4;
        this.buttonGroup5 = buttonGroup5;
        this.buttonGroup6 = buttonGroup6;
 
        this.jScrollPane1 = jScrollPane1;
        this.lambdaLlegadas = lambdaLlegadas;
        this.lblIntegrantes = lblIntegrantes;
        
        this.txtCantidadFilas = txtCantidadFilas;
        this.txtDesde = txtDesde;
        this.txtHasta = txtHasta;
    }
    
    private String llenarTiempo(double a){
        if (a == 999999.0){
                return "-";}
        return convertirAReloj(a);
        
    }
    
    private String convertirAReloj(double a){
        if(a == 0.0){
            return "-";
        }
        int parteEntera = (int) a;
        int horas =(int) a / 60;
        int min = parteEntera%60;
        double resto = a-parteEntera;
        int seg = (int)(resto*60);
        
        String horasForm = String.format("%02d", horas);
        String minForm = String.format("%02d", min);
        String segForm = String.format("%02d", seg);
        return horasForm + ":" + minForm + ":" + segForm;
    }
    
    private void llenarFila(Fila aux) {
        filaAImprimir[0] = (int) aux.getContadorN();
        filaAImprimir[1] = convertirAReloj(aux.getReloj());
        filaAImprimir[2] = aux.getE().toString();
        filaAImprimir[3] = aux.getMaterial();
        filaAImprimir[4] = aux.getProximoMaterial();
        filaAImprimir[5] = df.format(aux.getRndPedido());
        filaAImprimir[6] = convertirAReloj(aux.getTiempoEntreLlegadas());
        filaAImprimir[7] = llenarTiempo(aux.getProxLlegada());
        filaAImprimir[8] = aux.getA1().getE().toString();
        filaAImprimir[9] = aux.getA1().getMaterial();
        filaAImprimir[10] = df.format(aux.getA1().getRnd());
        filaAImprimir[11] = convertirAReloj(aux.getA1().getTiempoAtencion());
        filaAImprimir[12] = llenarTiempo(aux.getA1().getProxFin());
        filaAImprimir[13] = aux.getA1().getColaUno();
        filaAImprimir[14] = aux.getA2().getE().toString();
        filaAImprimir[15] = aux.getA2().getMaterial();
        filaAImprimir[16] = df.format(aux.getA2().getRnd());
        filaAImprimir[17] = convertirAReloj(aux.getA2().getTiempoAtencion());
        filaAImprimir[18] = llenarTiempo(aux.getA2().getProxFin());
        filaAImprimir[19] = aux.getA2().getColaUno();
        filaAImprimir[20] = aux.getA1().getE().toString();
        filaAImprimir[21] = aux.getA3().getMaterial();
        filaAImprimir[22] = df.format(aux.getA3().getRnd());
        filaAImprimir[23] = convertirAReloj(aux.getA3().getTiempoAtencion());
        filaAImprimir[24] = llenarTiempo(aux.getA3().getProxFin());
        filaAImprimir[25] = aux.getA3().getColaUno();
        filaAImprimir[26] = aux.getA4().getE().toString();
        filaAImprimir[27] = aux.getA4().getMaterial();
        filaAImprimir[28] = df.format(aux.getA4().getRnd());
        filaAImprimir[29] = convertirAReloj(aux.getA4().getTiempoAtencion());
        filaAImprimir[30] = llenarTiempo(aux.getA4().getProxFin());
        filaAImprimir[31] = aux.getA4().getColaUno();
        filaAImprimir[32] = aux.getA5().getE().toString();
        filaAImprimir[33] = aux.getA5().getMaterial();
        filaAImprimir[34] = df.format(aux.getA5().getRnd());
        filaAImprimir[35] = convertirAReloj(aux.getA5().getTiempoAtencion());
        filaAImprimir[36] = llenarTiempo(aux.getA5().getProxFin());
        filaAImprimir[37] = aux.getA5().getColaUno();
        filaAImprimir[38] = aux.getA5().getColaDos();
        filaAImprimir[39] = aux.getColaA3();
        filaAImprimir[40] = aux.getColaA5();
        filaAImprimir[41] = aux.getTareasTerminadas();
        filaAImprimir[42] = convertirAReloj(aux.getAcumEnsamble());
        filaAImprimir[43] = convertirAReloj(aux.getPromedioEnsamble());

    }

    
     /*private void llenarFila(Fila aux) {
        filaAImprimir[0] = (int) aux.getContadorN();
        filaAImprimir[1] = df.format(aux.getReloj());
        filaAImprimir[2] = aux.getE().toString();
        filaAImprimir[3] = aux.getMaterial();
        filaAImprimir[4] = aux.getProximoMaterial();
        filaAImprimir[5] = df.format(aux.getRndPedido());
        filaAImprimir[6] = convertirAReloj(aux.getTiempoEntreLlegadas());
        filaAImprimir[7] = llenarTiempo(aux.getProxLlegada());
        filaAImprimir[8] = aux.getA1().getE().toString();
        filaAImprimir[9] = aux.getA1().getMaterial();
        filaAImprimir[10] = df.format(aux.getA1().getRnd());
        filaAImprimir[11] = aux.getA1().getTiempoAtencion();
        filaAImprimir[12] = llenarTiempo(aux.getA1().getProxFin());
        filaAImprimir[13] = aux.getA1().getColaUno();
        filaAImprimir[14] = aux.getA2().getE().toString();
        filaAImprimir[15] = aux.getA2().getMaterial();
        filaAImprimir[16] = df.format(aux.getA2().getRnd());
        filaAImprimir[17] = aux.getA2().getTiempoAtencion();
        filaAImprimir[18] = llenarTiempo(aux.getA2().getProxFin());
        filaAImprimir[19] = aux.getA2().getColaUno();
        filaAImprimir[20] = aux.getA1().getE().toString();
        filaAImprimir[21] = aux.getA3().getMaterial();
        filaAImprimir[22] = df.format(aux.getA3().getRnd());
        filaAImprimir[23] = aux.getA3().getTiempoAtencion();
        filaAImprimir[24] = llenarTiempo(aux.getA3().getProxFin());
        filaAImprimir[25] = aux.getA3().getColaUno();
        filaAImprimir[26] = aux.getA4().getE().toString();
        filaAImprimir[27] = aux.getA4().getMaterial();
        filaAImprimir[28] = df.format(aux.getA4().getRnd());
        filaAImprimir[29] = aux.getA4().getTiempoAtencion();
        filaAImprimir[30] = llenarTiempo(aux.getA4().getProxFin());
        filaAImprimir[31] = aux.getA4().getColaUno();
        filaAImprimir[32] = aux.getA5().getE().toString();
        filaAImprimir[33] = aux.getA5().getMaterial();
        filaAImprimir[34] = df.format(aux.getA5().getRnd());
        filaAImprimir[35] = aux.getA5().getTiempoAtencion();
        filaAImprimir[36] = llenarTiempo(aux.getA5().getProxFin());
        filaAImprimir[37] = aux.getA5().getColaUno();
        filaAImprimir[38] = aux.getA5().getColaDos();
        filaAImprimir[39] = aux.getColaA3();
        filaAImprimir[40] = aux.getColaA5();
        filaAImprimir[41] = aux.getTareasTerminadas();

    }*/
    
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

    public Principal() {

        initComponents();
        pantallaActual = new JFrame();

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
        lblIntegrantes = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lambdaLlegadas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        Tabla.setBackground(new java.awt.Color(204, 204, 255));
        Tabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        Tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Tabla.setGridColor(new java.awt.Color(0, 0, 0));
        Tabla.setShowGrid(true);
        jScrollPane1.setViewportView(Tabla);

        BtnSimular.setText("Simular");
        BtnSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimularActionPerformed(evt);
            }
        });

        jLabel2.setText("Cantidad de tareas a Simular");

        txtCantidadFilas.setText("100");
        txtCantidadFilas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadFilasActionPerformed(evt);
            }
        });

        jLabel3.setText("Desde");

        jLabel4.setText("Hasta");

        txtDesde.setText("0");

        txtHasta.setText("1000");

        lblIntegrantes.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        lblIntegrantes.setText("Integrantes: Dominguez Ariel, Gudin Andres, Juarez Diego, Paglia Matias, Toia Lucia");

        jLabel34.setText("Pedidos que llegan por hora:");

        lambdaLlegadas.setText("3");
        lambdaLlegadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lambdaLlegadasActionPerformed(evt);
            }
        });

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
                                .addComponent(lambdaLlegadas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidadFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnSimular, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lblIntegrantes, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
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
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lambdaLlegadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblIntegrantes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    double masBajo;
    private void BtnSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimularActionPerformed
        // TODO add your handling code here:

        N = Integer.parseInt(txtCantidadFilas.getText());
        desde = Integer.parseInt(txtDesde.getText());
        hasta = Integer.parseInt(txtHasta.getText());
        lambdaLl = Double.parseDouble(lambdaLlegadas.getText());

        lambdaLl = lambdaLl / 60;

        

        tabla = new DefaultTableModel();
        tabla.setColumnIdentifiers(columna);

        aux1 = new Fila();
       // aux2 = new Fila();

        aux1.CalcularPrimeraFila(lambdaLl);
        //aux2 = new Fila(aux1.getE(), aux1.getMaterial(), aux1.getProximoMaterial(), aux1.getRndPedido(), aux1.getTiempoEntreLlegadas(), aux1.getProxLlegada(), A1, A2, A3, A4, A5, aux1.getColaA3(), aux1.getColaA5(), aux1.getTareasTerminadas(), aux1.getContadorN(), aux1.getLlegadaActividadCalc());
        for (int i = 0; i < N; i++) {

            if ((aux1.getContadorN() >= desde && aux1.getContadorN() <= hasta) || aux1.getContadorN() == N) {
                
                llenarFila(aux1);
                tabla.addRow(filaAImprimir);
                
            }
            aux1.CalcularNuevaFila();
        }

        Tabla.setModel(tabla);

        Tabla.getColumn("Rnd Llegada").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.blue : Color.yellow);
                return this;
            }
        });

        Tabla.getColumn("T Entre Llegadas").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.blue : Color.yellow);
                return this;
            }
        });

        Tabla.getColumn("Prox Llegada").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.blue : Color.yellow);
                return this;
            }
        });

        Tabla.getColumn("Estado A1").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("Material A1").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("rnd A1").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("T Atencion A1").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("Prox fin A1").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("Cola A1").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
         Tabla.getColumn("Estado A2").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.BLUE : Color.CYAN);
                return this;
            }
        });
        
        Tabla.getColumn("Material A2").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                  setBackground(isSelected ? Color.BLUE : Color.CYAN);
                return this;
            }
        });
        
        Tabla.getColumn("rnd A2").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                   setBackground(isSelected ? Color.BLUE : Color.CYAN);
                return this;
            }
        });
        
        Tabla.getColumn("T Atencion A2").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                   setBackground(isSelected ? Color.BLUE : Color.CYAN);
                return this;
            }
        });
        
        Tabla.getColumn("Prox fin A2").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                   setBackground(isSelected ? Color.BLUE : Color.CYAN);
                return this;
            }
        });
        
        Tabla.getColumn("Cola A2").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                   setBackground(isSelected ? Color.BLUE : Color.CYAN);
                return this;
            }
        });
        
          Tabla.getColumn("Estado A4").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("Material A4").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("rnd A4").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("T Atencion A4").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("Prox fin A4").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("Cola A4").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.red : Color.GREEN);
                return this;
            }
        });
        
        Tabla.getColumn("Cola Terminados A5").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
                return this;
            }
        });
        
         Tabla.getColumn("Cola Terminados A3").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
                return this;
            }
        });
         
          Tabla.getColumn("Tareas terminadas").setCellRenderer(
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                setText(value.toString());
                setBackground(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
                return this;
            }
        });
    }//GEN-LAST:event_BtnSimularActionPerformed

    private void lambdaLlegadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lambdaLlegadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lambdaLlegadasActionPerformed

    private void txtCantidadFilasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadFilasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadFilasActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lambdaLlegadas;
    private javax.swing.JLabel lblIntegrantes;
    private javax.swing.JTextField txtCantidadFilas;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    // End of variables declaration//GEN-END:variables
}
