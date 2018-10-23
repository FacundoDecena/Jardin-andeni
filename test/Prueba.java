
import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maxi
 */
public class Prueba extends javax.swing.JFrame {

    /**
     * Creates new form Prueba
     */
    public Prueba() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableA = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(316, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
        Prueba p = new Prueba();
        p.setVisible(true);
        Connection c = ConexionBD.getConnection();
        Statement s = c.createStatement();
        //s.execute("DELETE FROM TUTOR WHERE DNI = 36478124");
        //s.execute("DELETE FROM PERSONA WHERE DNI = 36478124");
        ResultSet rs = s.executeQuery("SELECT * FROM PERSONA");
        DefaultTableModel dtm = new DefaultTableModel();
        p.jTableA.setModel(dtm);
        ResultSetMetaData rsMd = rs.getMetaData();
        int cantidadColumnas = rsMd.getColumnCount();
        for (int i = 0; i < cantidadColumnas; i++){//Pongo los nombres de las columnas a partir del Result Set
                String a = rsMd.getColumnLabel(i+1);
                dtm.addColumn(a);
        }
        while (rs.next()) {//Mientras que haya filas
            Object[] fila = new Object[cantidadColumnas];
            for (int i = 0; i < cantidadColumnas; i++) //Agrego filas
                fila[i]=rs.getObject(i+1);
            dtm.addRow(fila);
        }
        rs.close();
        s.close();
        ConexionBD.cerrarBD();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableA;
    // End of variables declaration//GEN-END:variables
}
