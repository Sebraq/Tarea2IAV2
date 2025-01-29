/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package enfermedadv2;

import java.awt.Color;
import javax.swing.*;
/**
 *
 * @author ACER
 */
public class Pantalla extends javax.swing.JFrame {

    /**
     * Creates new form Pantalla
     */
    private int index=0;
    private String[] preguntas ={
        "¿Ha experimentado hinchazon o distension abdominal ?",
        "¿Tienes diarrea ?",
        "¿Tienes gases ?",
        "¿Ha sentido nauseas ?",
        "¿Ha tenido dolor en el abdomen ?",
        "¿Ha escuchando ruidos estomacales ?",
        "¿Tuvo vomito ?",
        "¿Ha sentido acidez estomacal ?",
        "¿Tiene dificultad al tragar ?",
        "¿Sufre de perdida de peso ?",
        "¿Sufre de perdida de apetito ?",
        "¿Ha notado heces con sangre ?",
        "¿Sufre de ictericia ?",
        "¿Se siente con fatiga ?",
        "¿Tiene dolor estomacal ?",
        "¿Ha notado estrechamiento de heces ?",
    };
    
    private String[] intolLactosa ={"hinchazon o distension abdominal","diarrea","gases","nauseas","dolor en el abdomen","ruidos estomacales","vomito"};
    private String[] cancerEstomago = {"nauseas","vomito","acidez estomacal","dificultad al tragar","perdida de peso","perdida de apetito","hinchazon o distension abdominal","heces con sangre","ictericia","fatiga","dolor estomacal"};
    private String [] cancerColon = {"diarrea","estrechamiento de heces","heces con sangre","dolor en el abdomen","fatiga","perdida de peso"};
    
    //Contadores para cada enfermedad
    private int contadorIntolLactosa = 0;
    private int contadorCancerEstomago = 0;
    private int contadorCancerColon = 0;
    private int contadorSintomasTotales=0;
    
    public Pantalla() {
        initComponents();
        mostrarPregunta();
        Color colorFondo = Color.decode("#D0DDD0");
        getContentPane().setBackground(colorFondo);
    }

    
    private void mostrarPregunta(){
        if(index<preguntas.length){
            jTextArea1.setText(preguntas[index]);
        }else{
            //Mostramos resultado
            mostrarResultados();
        }
    };
    
    private void siguientePregunta(){
        index++;
        mostrarPregunta();
    };
    
    private void verificarSintoma(String respuesta){
        if (index >= preguntas.length) {
            return;
        }
        contadorSintomasTotales++;//Contador de sintomas totales.
        String preguntaAct = preguntas[index].toLowerCase();
        
        //Intolerancia Lactosa
        for (String sintoma : intolLactosa) {
            if (preguntaAct.contains(sintoma.toLowerCase()) && respuesta.equals("Sí")) {
                contadorIntolLactosa++;
                break;
            }
        }
        //Cancer Estomago
        for (String sintoma : cancerEstomago) {
            if (preguntaAct.contains(sintoma.toLowerCase()) && respuesta.equals("Sí")) {
                contadorCancerEstomago++;
                break;
            }
        }

        // Verificar si la pregunta contiene algún síntoma de cáncer de colon
        for (String sintoma : cancerColon) {
            if (preguntaAct.contains(sintoma.toLowerCase()) && respuesta.equals("Sí")) {
                contadorCancerColon++;
                break;
            }
        }
    };
    private double calcularPorcentaje(int sintomasIdentificados,int totalSintomas){
      if(totalSintomas == 0)return 0; //Evitamos la division por 0
      double calculo = ((double)sintomasIdentificados/totalSintomas) * 100;
//      System.out.println(sintomasIdentificados);
//      System.out.println(totalSintomas);
//      System.out.println(calculo);
      return calculo;
    };
    
    private void mostrarResultados() {
        double porcIntoLactosa = calcularPorcentaje(contadorIntolLactosa,contadorSintomasTotales);
        double porcCanEstomago = calcularPorcentaje(contadorCancerEstomago,contadorSintomasTotales);
        double porcCancerColon = calcularPorcentaje(contadorCancerColon,contadorSintomasTotales);
        
        
//        double porcIntoLactosa = calcularPorcentaje(contadorIntolLactosa,intolLactosa.length);
//        double porcCanEstomago = calcularPorcentaje(contadorCancerEstomago,cancerEstomago.length);
//        double porcCancerColon = calcularPorcentaje(contadorCancerColon,cancerColon.length);
        
        String resultado = "***Resultados***:\n" +
                "- Intolerancia a la lactosa: " + contadorIntolLactosa + " de " + contadorSintomasTotales + " síntomas (" + String.format("%.2f", porcIntoLactosa) + "%)\n" +
                "- Cáncer de estómago: " + contadorCancerEstomago + " de " + contadorSintomasTotales + " síntomas (" + String.format("%.2f", porcCanEstomago) + "%)\n" +
                "- Cáncer de colon: " + contadorCancerColon + " de " + contadorSintomasTotales + " síntomas (" + String.format("%.2f", porcCancerColon) + "%)\n\n";
        
//        String resultado = "***Resultados***:\n" +
//                "- Intolerancia a la lactosa: " + contadorIntolLactosa + " de " + intolLactosa.length + " síntomas (" + String.format("%.2f", porcIntoLactosa) + "%)\n" +
//                "- Cáncer de estómago: " + contadorCancerEstomago + " de " + cancerEstomago.length + " síntomas (" + String.format("%.2f", porcCanEstomago) + "%)\n" +
//                "- Cáncer de colon: " + contadorCancerColon + " de " + cancerColon.length + " síntomas (" + String.format("%.2f", porcCancerColon) + "%)\n\n";
//        
        
        // Determinar la enfermedad con más síntomas
        if (contadorIntolLactosa > contadorCancerEstomago && contadorIntolLactosa > contadorCancerColon) {
            resultado += "Posible diagnóstico: Intolerancia a la lactosa";
        } else if (contadorCancerEstomago > contadorIntolLactosa && contadorCancerEstomago > contadorCancerColon) {
            resultado += "Posible diagnóstico: Cáncer de estómago";
        } else if (contadorCancerColon > contadorIntolLactosa && contadorCancerColon > contadorCancerEstomago) {
            resultado += "Posible diagnóstico: Cáncer de colon";
        } else {
            resultado += "No se pudo determinar un diagnóstico claro. Intentelo de nuevo. ";
        }

        jTextArea1.setText(resultado); // Mostrar los resultados en jTextArea1
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_si = new javax.swing.JButton();
        btn_no = new javax.swing.JButton();
        btn_rep = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(50, 130, 184));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(114, 125, 115));
        jLabel1.setText("Asistente de Diagnóstico Clínico");

        btn_si.setBackground(new java.awt.Color(100, 200, 100));
        btn_si.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btn_si.setForeground(new java.awt.Color(0, 0, 0));
        btn_si.setText("Si");
        btn_si.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_si.setName(""); // NOI18N
        btn_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siActionPerformed(evt);
            }
        });

        btn_no.setBackground(new java.awt.Color(200, 70, 70));
        btn_no.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btn_no.setForeground(new java.awt.Color(0, 0, 0));
        btn_no.setText("No");
        btn_no.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_noActionPerformed(evt);
            }
        });

        btn_rep.setBackground(new java.awt.Color(204, 204, 0));
        btn_rep.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btn_rep.setForeground(new java.awt.Color(0, 0, 0));
        btn_rep.setText("Repetir");
        btn_rep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_si, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btn_no, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(69, 69, 69))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_si, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_no, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btn_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siActionPerformed
        // TODO add your handling code here:
        verificarSintoma("Sí");
        siguientePregunta();
    }//GEN-LAST:event_btn_siActionPerformed

    private void btn_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_noActionPerformed
        // TODO add your handling code here:
        siguientePregunta();
    }//GEN-LAST:event_btn_noActionPerformed

    private void btn_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repActionPerformed
        // TODO add your handling code here:
        index = 0;
        contadorIntolLactosa = 0;
        contadorCancerEstomago = 0;
        contadorCancerColon = 0;
        contadorSintomasTotales=0;
        mostrarPregunta();
    }//GEN-LAST:event_btn_repActionPerformed

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
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_no;
    private javax.swing.JButton btn_rep;
    private javax.swing.JButton btn_si;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
