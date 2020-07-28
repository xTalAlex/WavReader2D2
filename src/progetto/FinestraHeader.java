package progetto;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuItem;

public class FinestraHeader extends javax.swing.JFrame {

    DatiAudio datiAudio;
    byte[] header;
    
    public FinestraHeader(JMenuItem menu,DatiAudio datiAudio) {
        this.datiAudio=datiAudio;
        this.header=datiAudio.caricaHeader();
        initComponents();
        menu.setEnabled(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setEnabled(true);
            }
        });
        setTitle("Header WAV");
        setLocationRelativeTo(null);
        decodificaHeader();
    }
    
    public void decodificaHeader(){
        labelChunkID.setText((char)header[0]+""+(char)header[1]+""+(char)header[2]+""+(char)header[3]);
        labelChunkSize.setText(""+((header[4]&0xff)|(header[5]&0xff)<<8|(header[6]&0xff)<<16|(header[7]&0xff)<<24));
        labelFormat.setText((char)header[8]+""+(char)header[9]+""+(char)header[10]+""+(char)header[11]);
        labelSubchunk1ID.setText((char)header[12]+""+(char)header[13]+""+(char)header[14]+""+(char)header[15]);
        labelSubchunk1Size.setText(""+((header[16]&0xff)|(header[17]&0xff)<<8|(header[18]&0xff)<<16|(header[19]&0xff)<<24));
        labelAudioFormat.setText(((header[20]&0xff)|(header[21]&0xff)<<8)+"");
        labelNumChannels.setText((header[22]&0xff|((header[23]&0xff)<<8))+"");
        labelSampleRate.setText(((header[24]&0xff)|(header[25]&0xff)<<8|(header[26]&0xff)<<16|(header[27]&0xff)<<24)+"");
        labelByteRate.setText(""+((header[28]&0xff)|(header[29]&0xff)<<8|(header[30]&0xff)<<16|(header[31]&0xff)<<24));
        labelBlockAlign.setText(""+((header[32]&0xff|((header[33]&0xff)<<8))));
        labelBitsPerSample.setText(""+((header[34]&0xff|((header[35]&0xff)<<8))));
        labelSubchunk2ID.setText((char)header[36]+""+(char)header[37]+""+(char)header[38]+""+(char)header[39]);
        labelSubchunk2Size.setText(""+(((header[40]&0xff)|(header[41]&0xff)<<8|(header[42]&0xff)<<16|(header[43]&0xff)<<24)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelChunkID = new javax.swing.JLabel();
        labelChunkSize = new javax.swing.JLabel();
        labelFormat = new javax.swing.JLabel();
        labelSubchunk1ID = new javax.swing.JLabel();
        labelSubchunk1Size = new javax.swing.JLabel();
        labelAudioFormat = new javax.swing.JLabel();
        labelNumChannels = new javax.swing.JLabel();
        labelSampleRate = new javax.swing.JLabel();
        labelByteRate = new javax.swing.JLabel();
        labelBlockAlign = new javax.swing.JLabel();
        labelBitsPerSample = new javax.swing.JLabel();
        labelSubchunk2ID = new javax.swing.JLabel();
        labelSubchunk2Size = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("ChunkID");

        jLabel2.setText("ChunkSize");

        jLabel3.setText("Format");

        jLabel4.setText("Subchunk1ID");

        jLabel5.setText("Subchunk1Size");

        jLabel6.setText("AudioFormat");

        jLabel7.setText("NumChannels");

        jLabel8.setText("SampleRate");

        jLabel9.setText("ByteRate");

        jLabel10.setText("BlockAlign");

        jLabel11.setText("BitsPerSample");

        jLabel12.setText("Subchunk2ID");

        jLabel13.setText("Subchunk2Size");

        labelChunkID.setText("-");

        labelChunkSize.setText("-");

        labelFormat.setText("-");

        labelSubchunk1ID.setText("-");

        labelSubchunk1Size.setText("-");

        labelAudioFormat.setText("-");

        labelNumChannels.setText("-");

        labelSampleRate.setText("-");

        labelByteRate.setText("-");

        labelBlockAlign.setText("-");

        labelBitsPerSample.setText("-");

        labelSubchunk2ID.setText("-");

        labelSubchunk2Size.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelSubchunk2Size))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelSubchunk2ID))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelBitsPerSample))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelBlockAlign))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelChunkID)
                            .addComponent(labelChunkSize)
                            .addComponent(labelFormat)
                            .addComponent(labelSubchunk1ID)
                            .addComponent(labelSubchunk1Size)
                            .addComponent(labelAudioFormat)
                            .addComponent(labelNumChannels)
                            .addComponent(labelSampleRate)
                            .addComponent(labelByteRate))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelChunkID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelChunkSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelFormat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelSubchunk1ID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelSubchunk1Size))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelAudioFormat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelNumChannels))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelSampleRate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelByteRate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelBlockAlign))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelBitsPerSample))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(labelSubchunk2ID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(labelSubchunk2Size))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelAudioFormat;
    private javax.swing.JLabel labelBitsPerSample;
    private javax.swing.JLabel labelBlockAlign;
    private javax.swing.JLabel labelByteRate;
    private javax.swing.JLabel labelChunkID;
    private javax.swing.JLabel labelChunkSize;
    private javax.swing.JLabel labelFormat;
    private javax.swing.JLabel labelNumChannels;
    private javax.swing.JLabel labelSampleRate;
    private javax.swing.JLabel labelSubchunk1ID;
    private javax.swing.JLabel labelSubchunk1Size;
    private javax.swing.JLabel labelSubchunk2ID;
    private javax.swing.JLabel labelSubchunk2Size;
    // End of variables declaration//GEN-END:variables
}
