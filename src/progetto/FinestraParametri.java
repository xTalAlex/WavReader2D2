package progetto;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuItem;


public class FinestraParametri extends javax.swing.JFrame {

    ParametriAudio parametri;

    public FinestraParametri(JMenuItem menu,ParametriAudio parametri) {
        initComponents();
        setTitle("Parametri Audio");
        setLocationRelativeTo(null);
        menu.setEnabled(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setEnabled(true);
            }
        });
        this.parametri=parametri;
        aggiornaValori();
    }
    
    public void aggiornaValori(){
        valSampleRate.setText(parametri.getSampleRate()+"");
        valNCanali.setText(parametri.getNCanali()+"");
        valBitDepth.setText(parametri.getBitDepth()+"");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSampleRate = new javax.swing.JLabel();
        labelNCanali = new javax.swing.JLabel();
        labelBitDepth = new javax.swing.JLabel();
        valSampleRate = new javax.swing.JLabel();
        valNCanali = new javax.swing.JLabel();
        valBitDepth = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        labelSampleRate.setText("Sample Rate:");

        labelNCanali.setText("Numero Canali:");

        labelBitDepth.setText("Bit Depth:");

        valSampleRate.setText("-");

        valNCanali.setText("-");

        valBitDepth.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNCanali)
                    .addComponent(labelBitDepth, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSampleRate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valNCanali)
                    .addComponent(valSampleRate)
                    .addComponent(valBitDepth))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSampleRate)
                    .addComponent(valSampleRate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNCanali)
                    .addComponent(valNCanali))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBitDepth)
                    .addComponent(valBitDepth))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelBitDepth;
    private javax.swing.JLabel labelNCanali;
    private javax.swing.JLabel labelSampleRate;
    private javax.swing.JLabel valBitDepth;
    private javax.swing.JLabel valNCanali;
    private javax.swing.JLabel valSampleRate;
    // End of variables declaration//GEN-END:variables
}
