package progetto;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import static progetto.Finestra.HOME_PATH;

public class FinestraMicrofono extends javax.swing.JFrame {
   
    RecorderThread recorderThread;
    
    public FinestraMicrofono(JMenuItem menu) {
        initComponents();
        setTitle("Microfono");
        setLocationRelativeTo(null);
        menu.setEnabled(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setEnabled(true);
            }
        });
        labelTempo.setText("Pronto per registrare");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTempo = new javax.swing.JLabel();
        buttonRegistra = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        labelTempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTempo.setText("0.0s");

        buttonRegistra.setText("Registra");
        buttonRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonRegistra, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(labelTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(buttonRegistra, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelTempo)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistraActionPerformed
           
            if (buttonRegistra.getText().equals("Registra")) {
                JFileChooser chooser=new JFileChooser(HOME_PATH);
                chooser.setFileFilter(new FileNameExtensionFilter("File WAVE","wav"));
                chooser.setSelectedFile(new File("micRec.wav"));
                if(chooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
                    String fileName=chooser.getSelectedFile().getName();
                    recorderThread=new RecorderThread(fileName);
                    recorderThread.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(final PropertyChangeEvent event) {
                            switch (event.getPropertyName()) {
                                case "progress":
                                    break;
                                case "state":
                                    switch ((SwingWorker.StateValue) event.getNewValue()) {
                                        case PENDING:  // Stato iniziale
                                            labelTempo.setText("Pronto per registrare");
                                            break;
                                        case DONE: // Task concluso 
                                            labelTempo.setText("Audio salvato in "+fileName);
                                            break;
                                        case STARTED: // Prima dell’esecuzione di doInBackground
                                            labelTempo.setText("Regstrazione in corso...");
                                            break;
                                    }
                                    break;
                            }
                        }
                    });
                    recorderThread.execute(); 
                    buttonRegistra.setText("Stop");
                }
            } 
            else{
                recorderThread.cancel(true);
                buttonRegistra.setText("Registra");
            }
    }//GEN-LAST:event_buttonRegistraActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton buttonRegistra;
    private javax.swing.JLabel labelTempo;
    // End of variables declaration//GEN-END:variables
}
