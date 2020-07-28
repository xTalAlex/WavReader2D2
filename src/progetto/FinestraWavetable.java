package progetto;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FinestraWavetable extends javax.swing.JFrame {
    
    ArrayList<Double> wavetable;
    DefaultListModel dlm;

    public FinestraWavetable(JMenuItem menu,ArrayList<Double> wavetable) {
        initComponents();
        this.wavetable=wavetable;
        setTitle("Wavetable");
        setLocationRelativeTo(null);
        menu.setEnabled(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setEnabled(true);
            }
        });
        creaTabella();
    }
    
    public void creaTabella(){
        dlm=new DefaultListModel();
        for(double e:wavetable){
            dlm.addElement(e);
        }
        listaValori.setModel(dlm);   
        labelNCampioni.setText("Numero campioni: "+wavetable.size());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaValori = new javax.swing.JList();
        labelNCampioni = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        listaValori.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaValori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaValoriMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listaValori);

        labelNCampioni.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNCampioni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNCampioni)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaValoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaValoriMouseClicked
        if(evt.getClickCount()==2&&!SwingUtilities.isRightMouseButton(evt)){
            int daModificare=listaValori.getSelectedIndex();           
            String res=JOptionPane.showInputDialog(null,"Inserire nuovo valore",dlm.getElementAt(daModificare));
            if(res!=null){
                try{
                    double newVal=Double.parseDouble(res);
                    if(newVal>1) newVal=1;
                    if(newVal<-1) newVal=-1;
                    dlm.setElementAt(newVal, daModificare);
                    wavetable.set(daModificare, newVal);
                }catch(NumberFormatException e){} 
            }
        }
    }//GEN-LAST:event_listaValoriMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelNCampioni;
    private javax.swing.JList listaValori;
    // End of variables declaration//GEN-END:variables
}
