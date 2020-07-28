package progetto;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Finestra extends javax.swing.JFrame {
    
    //getClass().getClassLoader().getResource("R2D2.png").getFile()
    public static final String HOME_PATH="../Progetto/file/";
    
    DatiAudio datiAudio;
    GeneratoreOnde go;
    PlayerThread playerThread;
    
    public Finestra() {  
        if(!new File(Finestra.HOME_PATH).exists())
            new File(Finestra.HOME_PATH).mkdir();
        datiAudio=new DatiAudio();
        go=new GeneratoreOnde();
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Elaboratore File WAV/RAW");
        setStatus();
    }
    
    public void setStatus(){
        if(datiAudio.campioni.isEmpty()){
            statusLabel.setText("Nessun dato caricato");            
            menuHeader.setEnabled(false);
            menuClip.setEnabled(false);
            menuFoldback.setEnabled(false);
            menuNormalize.setEnabled(false);
            menuLivelli.setEnabled(false);
            menuSalvaFile.setEnabled(false);
            menuSommaFrequenza.setEnabled(false);
            buttonRiproduci.setEnabled(false);
            menuParametriAudio.setEnabled(false);
            scrollbarGrafico.setVisibleAmount(100);
            menuTaglia.setEnabled(false);
            labelIstanteTemporale.setText("");
        }
        else{
            statusLabel.setText("Dati caricati "+"("+datiAudio.durataToString()+"s)"+": "+datiAudio.getNomeAudio());
            menuClip.setEnabled(true);
            menuFoldback.setEnabled(true);
            menuNormalize.setEnabled(true);
            menuLivelli.setEnabled(true);
            menuSalvaFile.setEnabled(true);
            if(((Grafico)grafico).getScrollBarPointerSize()>1) zoomXPiu.setEnabled(true);
            menuSommaFrequenza.setEnabled(true);
            buttonRiproduci.setEnabled(true);
            menuParametriAudio.setEnabled(true);
            scrollbarGrafico.setVisibleAmount(((Grafico)grafico).getScrollBarPointerSize());
            if(datiAudio.selezione.isSelected()) menuTaglia.setEnabled(true);
            else menuTaglia.setEnabled(false);
            if(datiAudio.isWav())
                menuHeader.setEnabled(true);
            else
                menuHeader.setEnabled(false);
        }
        if(go.wavetable.isEmpty()){
            menuWavetable.setEnabled(false);
            menuDaWavetable.setEnabled(false);
            menuSalvaWavetable.setEnabled(false);
        }
        else{
            menuWavetable.setEnabled(true);
            menuDaWavetable.setEnabled(true);
            menuSalvaWavetable.setEnabled(true);
        }      
        int ctrZoom=1+(int)(Math.log(((Grafico)grafico).getXZoom())/Math.log(2));
        labelZoom.setText("Zoom x"+ctrZoom);
    }
    
    public void resetZoom(){
        ((Grafico)grafico).setXZoom(1);
        scrollbarGrafico.setValue(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusLabel = new javax.swing.JLabel();
        buttonRiproduci = new javax.swing.JButton();
        zoomXPiu = new javax.swing.JButton();
        zoomXMeno = new javax.swing.JButton();
        grafico = new Grafico(datiAudio);
        scrollbarGrafico = new javax.swing.JScrollBar();
        labelZoom = new javax.swing.JLabel();
        labelSX = new javax.swing.JLabel();
        labelDX = new javax.swing.JLabel();
        labelIstanteTemporale = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuCaricaFile = new javax.swing.JMenuItem();
        menuCaricaWavetable = new javax.swing.JMenuItem();
        menuSalvaFile = new javax.swing.JMenu();
        menuSalvaWav = new javax.swing.JMenuItem();
        menuSalvaRaw = new javax.swing.JMenuItem();
        menuSalvaWavetable = new javax.swing.JMenuItem();
        menuModifica = new javax.swing.JMenu();
        menuClip = new javax.swing.JMenuItem();
        menuFoldback = new javax.swing.JMenuItem();
        menuNormalize = new javax.swing.JMenuItem();
        menuLivelli = new javax.swing.JMenuItem();
        menuSommaFrequenza = new javax.swing.JMenuItem();
        menuTaglia = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuParametri = new javax.swing.JMenuItem();
        menuVisualizza = new javax.swing.JMenu();
        menuMicrofono = new javax.swing.JMenuItem();
        menuFrequenzeMusicali = new javax.swing.JMenuItem();
        menuHeader = new javax.swing.JMenuItem();
        menuParametriAudio = new javax.swing.JMenuItem();
        menuWavetable = new javax.swing.JMenuItem();
        menuGenera = new javax.swing.JMenu();
        menuRumoreBianco = new javax.swing.JMenuItem();
        menuSinusoide = new javax.swing.JMenuItem();
        menuOndaQuadra = new javax.swing.JMenuItem();
        menuOndaTriangolare = new javax.swing.JMenuItem();
        menuDenteDiSega = new javax.swing.JMenuItem();
        menuDaWavetable = new javax.swing.JMenuItem();
        menuFrequenzaVariabile = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuR2D2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        statusLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        statusLabel.setText("Nessun dato caricato");

        buttonRiproduci.setText("Riproduci");
        buttonRiproduci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRiproduciActionPerformed(evt);
            }
        });

        zoomXPiu.setText("+");
        zoomXPiu.setEnabled(false);
        zoomXPiu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomXPiuActionPerformed(evt);
            }
        });

        zoomXMeno.setText("-");
        zoomXMeno.setEnabled(false);
        zoomXMeno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomXMenoActionPerformed(evt);
            }
        });

        grafico.setBackground(java.awt.Color.white);
        grafico.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                graficoMouseMoved(evt);
            }
        });
        grafico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                graficoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                graficoMouseExited(evt);
            }
        });

        scrollbarGrafico.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        scrollbarGrafico.setVisibleAmount(100);
        scrollbarGrafico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scrollbarGrafico.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollbarGraficoAdjustmentValueChanged(evt);
            }
        });

        javax.swing.GroupLayout graficoLayout = new javax.swing.GroupLayout(grafico);
        grafico.setLayout(graficoLayout);
        graficoLayout.setHorizontalGroup(
            graficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollbarGrafico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        graficoLayout.setVerticalGroup(
            graficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, graficoLayout.createSequentialGroup()
                .addGap(0, 409, Short.MAX_VALUE)
                .addComponent(scrollbarGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelZoom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelZoom.setText("Zoom x1");

        labelSX.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelSX.setForeground(new java.awt.Color(0, 0, 255));
        labelSX.setText("SX");

        labelDX.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelDX.setForeground(new java.awt.Color(255, 0, 0));
        labelDX.setText("DX");

        labelIstanteTemporale.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelIstanteTemporale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIstanteTemporale.setText("labelIstanteTemporale");
        labelIstanteTemporale.setToolTipText("");

        menuFile.setText("File");

        menuCaricaFile.setText("Carica file");
        menuCaricaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caricaFile(evt);
            }
        });
        menuFile.add(menuCaricaFile);

        menuCaricaWavetable.setText("Carica Wavetable");
        menuCaricaWavetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caricaWavetable(evt);
            }
        });
        menuFile.add(menuCaricaWavetable);

        menuSalvaFile.setText("Salva Audio");

        menuSalvaWav.setText("WAV");
        menuSalvaWav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaCampioni(evt);
            }
        });
        menuSalvaFile.add(menuSalvaWav);

        menuSalvaRaw.setText("RAW");
        menuSalvaRaw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaCampioni(evt);
            }
        });
        menuSalvaFile.add(menuSalvaRaw);

        menuFile.add(menuSalvaFile);

        menuSalvaWavetable.setText("Salva Wavetable");
        menuSalvaWavetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalvaWavetableActionPerformed(evt);
            }
        });
        menuFile.add(menuSalvaWavetable);

        menuBar.add(menuFile);

        menuModifica.setText("Modifica");

        menuClip.setText("Clip");
        menuClip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClipActionPerformed(evt);
            }
        });
        menuModifica.add(menuClip);

        menuFoldback.setText("Foldback");
        menuFoldback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFoldbackActionPerformed(evt);
            }
        });
        menuModifica.add(menuFoldback);

        menuNormalize.setText("Normalize");
        menuNormalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNormalizeActionPerformed(evt);
            }
        });
        menuModifica.add(menuNormalize);

        menuLivelli.setText("Livelli");
        menuLivelli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLivelliActionPerformed(evt);
            }
        });
        menuModifica.add(menuLivelli);

        menuSommaFrequenza.setText("Somma Frequenza");
        menuSommaFrequenza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSommaFrequenzaActionPerformed(evt);
            }
        });
        menuModifica.add(menuSommaFrequenza);

        menuTaglia.setText("Taglia");
        menuTaglia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTagliaActionPerformed(evt);
            }
        });
        menuModifica.add(menuTaglia);
        menuModifica.add(jSeparator2);

        menuParametri.setText("Parametri");
        menuParametri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parametriGenerazione(evt);
            }
        });
        menuModifica.add(menuParametri);

        menuBar.add(menuModifica);

        menuVisualizza.setText("Visualizza");

        menuMicrofono.setText("Microfono");
        menuMicrofono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMicrofonoActionPerformed(evt);
            }
        });
        menuVisualizza.add(menuMicrofono);

        menuFrequenzeMusicali.setText("Frequenze Musicali");
        menuFrequenzeMusicali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFrequenzeMusicaliActionPerformed(evt);
            }
        });
        menuVisualizza.add(menuFrequenzeMusicali);

        menuHeader.setText("Header WAV");
        menuHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizzaHeader(evt);
            }
        });
        menuVisualizza.add(menuHeader);

        menuParametriAudio.setText("Parametri Audio");
        menuParametriAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuParametriAudioActionPerformed(evt);
            }
        });
        menuVisualizza.add(menuParametriAudio);

        menuWavetable.setText("Wavetable");
        menuWavetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizzaWavetable(evt);
            }
        });
        menuVisualizza.add(menuWavetable);

        menuBar.add(menuVisualizza);

        menuGenera.setText("Genera");

        menuRumoreBianco.setText("Rumore Bianco");
        menuRumoreBianco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaRumoreBianco(evt);
            }
        });
        menuGenera.add(menuRumoreBianco);

        menuSinusoide.setText("Sinusoide");
        menuSinusoide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaSinusoide(evt);
            }
        });
        menuGenera.add(menuSinusoide);

        menuOndaQuadra.setText("Onda Quadra");
        menuOndaQuadra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaOndaQuadra(evt);
            }
        });
        menuGenera.add(menuOndaQuadra);

        menuOndaTriangolare.setText("Onda Triangolare");
        menuOndaTriangolare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaOndaTriangolare(evt);
            }
        });
        menuGenera.add(menuOndaTriangolare);

        menuDenteDiSega.setText("Dente Di Sega");
        menuDenteDiSega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaDenteDiSega(evt);
            }
        });
        menuGenera.add(menuDenteDiSega);

        menuDaWavetable.setText("Da Wavetable");
        menuDaWavetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaDaWaveTable(evt);
            }
        });
        menuGenera.add(menuDaWavetable);

        menuFrequenzaVariabile.setText("Messaggio Da R2D2");
        menuFrequenzaVariabile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFrequenzaVariabileActionPerformed(evt);
            }
        });
        menuGenera.add(menuFrequenzaVariabile);

        menuBar.add(menuGenera);

        menuHelp.setText("?");

        menuR2D2.setText("R2D2");
        menuR2D2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuR2D2ActionPerformed(evt);
            }
        });
        menuHelp.add(menuR2D2);

        menuBar.add(menuHelp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelSX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                        .addComponent(labelIstanteTemporale, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)
                        .addComponent(labelZoom)
                        .addGap(18, 18, 18)
                        .addComponent(zoomXPiu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomXMeno))
                    .addComponent(grafico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRiproduci, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSX)
                    .addComponent(labelDX)
                    .addComponent(zoomXPiu)
                    .addComponent(zoomXMeno)
                    .addComponent(labelZoom)
                    .addComponent(labelIstanteTemporale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonRiproduci, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void caricaFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caricaFile
        JFileChooser chooser=new JFileChooser(HOME_PATH);
        chooser.setFileFilter(new FileNameExtensionFilter("File WAVE e RAW","wav","raw"));
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            String filePath=chooser.getSelectedFile().getPath();
            datiAudio.parseAudioBytes(filePath);
            datiAudio.selezione.annullaSelezione();
            go.parametri.copiaParametri(datiAudio.parametri);
            resetZoom();
            setStatus();
            grafico.repaint();
        }
    }//GEN-LAST:event_caricaFile

    private void visualizzaHeader(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizzaHeader
        if(!datiAudio.campioni.isEmpty())
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FinestraHeader(menuHeader,datiAudio).setVisible(true);
            }
        });       
    }//GEN-LAST:event_visualizzaHeader

    private void zoomXPiuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomXPiuActionPerformed
        if(((Grafico)grafico).getXZoom()*2<datiAudio.campioni.size()){
            ((Grafico)grafico).setXZoom( ((Grafico)grafico).getXZoom()*2);
            zoomXMeno.setEnabled(true);
            if(((Grafico)grafico).getScrollBarPointerSize()<=1) zoomXPiu.setEnabled(false);
            scrollbarGrafico.setValue(0);
            grafico.repaint();
        }
        setStatus();
        
    }//GEN-LAST:event_zoomXPiuActionPerformed

    private void zoomXMenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomXMenoActionPerformed
        if(((Grafico)grafico).getXZoom()>1){
            ((Grafico)grafico).setXZoom( ((Grafico)grafico).getXZoom()/2);
            zoomXPiu.setEnabled(true);
            if(((Grafico)grafico).getXZoom()==1) zoomXMeno.setEnabled(false);
            scrollbarGrafico.setValue(0);
            grafico.repaint();
        }
        setStatus();
    }//GEN-LAST:event_zoomXMenoActionPerformed

    private void generaRumoreBianco(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaRumoreBianco
        double[] valori=go.richiediValori("Rumore Bianco");
        boolean parametriCorretti=true;
        for(int i=0;i<valori.length;i++){
            if(valori[i]==-1) parametriCorretti=false;
        }
        if(parametriCorretti){
            datiAudio.parametri.copiaParametri(go.parametri);
            datiAudio.setCampioni(go.generaRumoreBianco((int)valori[0],valori[1]),"RumoreBianco");
            resetZoom();
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_generaRumoreBianco

    private void parametriGenerazione(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametriGenerazione
        go.impostaParametri();
        
    }//GEN-LAST:event_parametriGenerazione

    private void salvaCampioni(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaCampioni
        String nomeFile;
        String estensione;
        if(datiAudio.getNomeAudio().equals(""))
            nomeFile="NuovoFile";
        else nomeFile=datiAudio.getNomeAudio();
        if(evt.getSource()==menuSalvaRaw){estensione="raw";}
        else {estensione="wav";}
        String parti[]=nomeFile.split("\\.");
        nomeFile=parti[0]+"."+estensione;
        JFileChooser chooser = new JFileChooser(HOME_PATH);
        chooser.setFileFilter(new FileNameExtensionFilter("File WAVE e RAW","wav","raw"));
        chooser.setSelectedFile(new File(nomeFile));
        if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            if(chooser.getSelectedFile()!=null){               
                nomeFile=chooser.getSelectedFile().getName();             
            }
            File file=new File(Finestra.HOME_PATH+nomeFile);    
            if(!file.exists()){
                try {
                    file.createNewFile();                   
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Impossibile creare il file!!!","Errore",JOptionPane.ERROR_MESSAGE);
                }
            } 
            datiAudio.salvaCampioni(file);
        }
    }//GEN-LAST:event_salvaCampioni

    private void menuClipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClipActionPerformed
        double valore=ModificaAudio.richiediValore();
        boolean parametriCorretti=true;
        if(valore==-1) parametriCorretti=false;
        if(parametriCorretti){
            datiAudio.setCampioni(ModificaAudio.clip(datiAudio,valore));
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_menuClipActionPerformed

    private void menuFoldbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFoldbackActionPerformed
        double valore=ModificaAudio.richiediValore();
        boolean parametriCorretti=true;
        if(valore==-1) parametriCorretti=false;
        if(parametriCorretti){
            datiAudio.setCampioni(ModificaAudio.foldback(datiAudio,valore));
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_menuFoldbackActionPerformed

    private void generaSinusoide(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaSinusoide
        double[] valori=go.richiediValori("Onda Sinusoidale");
        boolean parametriCorretti=true;
        for(int i=0;i<valori.length;i++){
            if(valori[i]==-1) parametriCorretti=false;
        }
        if(parametriCorretti){
            datiAudio.parametri.copiaParametri(go.parametri);
            datiAudio.setCampioni(go.generaOndaSinusoidale((int)valori[0],valori[1],valori[2]),"OndaSinusoidale");
            resetZoom();
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_generaSinusoide

    private void generaOndaQuadra(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaOndaQuadra
        double[] valori=go.richiediValori("Onda Quadra");
        boolean parametriCorretti=true;
        for(int i=0;i<valori.length;i++){
            if(valori[i]==-1) parametriCorretti=false;
        }
        if(parametriCorretti){
            datiAudio.parametri.copiaParametri(go.parametri);
            datiAudio.setCampioni(go.generaOndaQuadra((int)valori[0],valori[1],valori[2]),"OndaQuadra");
            resetZoom();
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_generaOndaQuadra

    private void generaOndaTriangolare(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaOndaTriangolare
        double[] valori=go.richiediValori("Onda Triangolare");
        boolean parametriCorretti=true;
        for(int i=0;i<valori.length;i++){
            if(valori[i]==-1) parametriCorretti=false;
        }
        if(parametriCorretti){
            datiAudio.parametri.copiaParametri(go.parametri);
            System.out.println(datiAudio.parametri.toString());
            datiAudio.setCampioni(go.generaOndaTriangolare((int)valori[0],valori[1],valori[2]),"OndaTriangolare");
            resetZoom();
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_generaOndaTriangolare

    private void generaDenteDiSega(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaDenteDiSega
        double[] valori=go.richiediValori("Dente Di Sega");
        boolean parametriCorretti=true;
        for(int i=0;i<valori.length;i++){
            if(valori[i]==-1) parametriCorretti=false;
        }
        if(parametriCorretti){
            datiAudio.parametri.copiaParametri(go.parametri);
            datiAudio.setCampioni(go.generaDenteDiSega((int)valori[0],valori[1],valori[2]),"DenteDiSega");
            resetZoom();
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_generaDenteDiSega

    private void menuNormalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNormalizeActionPerformed
        double valore=ModificaAudio.richiediValore();
        boolean parametriCorretti=true;
        if(valore==-1) parametriCorretti=false;
        if(parametriCorretti){
            datiAudio.setCampioni(ModificaAudio.normalize(datiAudio,valore));
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_menuNormalizeActionPerformed

    private void menuLivelliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLivelliActionPerformed
        double[] valori=ModificaAudio.richiediValori(1);
        boolean parametriCorretti=true;
        for(int i=0;i<valori.length;i++){
            if(valori[i]==-1) parametriCorretti=false;
        }
        if(parametriCorretti){
            datiAudio.setCampioni(ModificaAudio.setLevels(datiAudio,valori[0],valori[1]));
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_menuLivelliActionPerformed

    private void menuSommaFrequenzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSommaFrequenzaActionPerformed
        double[] valori=ModificaAudio.richiediValori(2);
        boolean parametriCorretti=true;
        for(int i=0;i<valori.length;i++){
            if(valori[i]==-1) parametriCorretti=false;
        }
        if(parametriCorretti){
            datiAudio.setCampioni(ModificaAudio.sommaFrequenza(datiAudio,valori[0],valori[1]));
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_menuSommaFrequenzaActionPerformed

    private void caricaWavetable(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caricaWavetable
        JFileChooser chooser=new JFileChooser(HOME_PATH);
        chooser.setFileFilter(new FileNameExtensionFilter("File Wavetable","txt"));
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            go.caricaWavetable(chooser.getSelectedFile().getPath());
            setStatus();
        }      
    }//GEN-LAST:event_caricaWavetable

    private void visualizzaWavetable(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizzaWavetable
        if(!go.wavetable.isEmpty())
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FinestraWavetable(menuWavetable,go.wavetable).setVisible(true);
            }
        });         
    }//GEN-LAST:event_visualizzaWavetable

    private void generaDaWaveTable(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaDaWaveTable
        double[] valori=go.richiediValori("Sintesi Wavetable");
        boolean parametriCorretti=true;
        for(int i=0;i<valori.length;i++){
            if(valori[i]==-1) parametriCorretti=false;
        }
        if(parametriCorretti){
            datiAudio.parametri.copiaParametri(go.parametri);
            datiAudio.setCampioni(go.tableLookupInterpolation((int)valori[0],valori[1],valori[2]),"SintesiWavetable");
            resetZoom();
            grafico.repaint();
            setStatus();
        }        
    }//GEN-LAST:event_generaDaWaveTable

    private void buttonRiproduciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRiproduciActionPerformed
       if(!datiAudio.campioni.isEmpty()){
            if (buttonRiproduci.getText().equals("Riproduci")) {   
                playerThread=new PlayerThread(datiAudio.estraiByteSample());
                
                playerThread.addPropertyChangeListener(new PropertyChangeListener() {
                     @Override
                     public void propertyChange(final PropertyChangeEvent event) {
                         switch (event.getPropertyName()) {
                             case "progress":
                                 System.out.println((Integer)event.getNewValue());
                                 break;
                             case "state":
                                 switch ((SwingWorker.StateValue) event.getNewValue()) {
                                     case PENDING:  // Stato iniziale
                                         break;
                                     case DONE: // Task concluso 
                                          buttonRiproduci.setText("Riproduci");
                                         break;
                                     case STARTED: // Prima dell’esecuzione di doInBackground
                                         break;
                                 }
                                 break;
                         }
                     }
                });

                playerThread.execute(); 
                buttonRiproduci.setText("Stop"); 
             } 
            else{
                playerThread.cancel(true);
                buttonRiproduci.setText("Riproduci");
            }
       }
    }//GEN-LAST:event_buttonRiproduciActionPerformed

    private void menuParametriAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuParametriAudioActionPerformed
        if(!datiAudio.campioni.isEmpty())
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FinestraParametri(menuParametriAudio,datiAudio.parametri).setVisible(true);
            }
        }); 
    }//GEN-LAST:event_menuParametriAudioActionPerformed

    private void graficoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graficoMouseClicked
        if(!datiAudio.campioni.isEmpty()){
            ((Grafico) grafico).getXZoom();
            if(SwingUtilities.isRightMouseButton(evt)){
                datiAudio.selezione.annullaSelezione();
            }
            else{
                if(!datiAudio.selezione.isSelected()){
                    if(datiAudio.selezione.getLimiteInf()==-1) datiAudio.selezione.setLimiteInf(((Grafico) grafico).mouseXToIndice(evt.getX()));
                    if(datiAudio.selezione.getLimiteSup()==-1) datiAudio.selezione.setLimiteSup(((Grafico) grafico).mouseXToIndice(evt.getX()));
                }
                else{
                    datiAudio.selezione.annullaSelezione();
                }
            }
            grafico.repaint();
        }
        if(datiAudio.selezione.isSelected()) menuTaglia.setEnabled(true);
        else menuTaglia.setEnabled(false);
        //System.out.println(datiAudio.selezione);
    }//GEN-LAST:event_graficoMouseClicked

    private void scrollbarGraficoAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollbarGraficoAdjustmentValueChanged
        ((Grafico) grafico).setPosizioneBarra(evt.getValue());
        grafico.repaint();
    }//GEN-LAST:event_scrollbarGraficoAdjustmentValueChanged

    private void menuTagliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTagliaActionPerformed
        if(datiAudio.selezione.isSelected()){
            datiAudio.setCampioni(ModificaAudio.tagliaCampioni(datiAudio));
            datiAudio.selezione.annullaSelezione();
            grafico.repaint();
            setStatus();
        }
    }//GEN-LAST:event_menuTagliaActionPerformed

    private void menuMicrofonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMicrofonoActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinestraMicrofono(menuMicrofono).setVisible(true);
            }
        });       
    }//GEN-LAST:event_menuMicrofonoActionPerformed

    private void graficoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graficoMouseMoved
        if(!datiAudio.campioni.isEmpty()){
            int indice=((Grafico)grafico).mouseXToIndice(evt.getX());
            if(indice>=0&&datiAudio.getIstanteTemporale(indice)<=datiAudio.getDurata()){
                labelIstanteTemporale.setText("Posizione: "+datiAudio.durataToString(datiAudio.getIstanteTemporale(indice))+"s");
            }
        }
        else labelIstanteTemporale.setText("");
    }//GEN-LAST:event_graficoMouseMoved

    private void graficoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graficoMouseExited
        labelIstanteTemporale.setText("");
    }//GEN-LAST:event_graficoMouseExited

    private void menuFrequenzeMusicaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFrequenzeMusicaliActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinestraFrequenzeMusicali(menuFrequenzeMusicali).setVisible(true);
            }
        });
    }//GEN-LAST:event_menuFrequenzeMusicaliActionPerformed

    private void menuFrequenzaVariabileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFrequenzaVariabileActionPerformed
            datiAudio.parametri.copiaParametri(go.parametri);
            datiAudio.setCampioni(go.generaFrequenzaVariabile(),"FrequenzaVariabile");
            resetZoom();
            grafico.repaint();
            setStatus();
    }//GEN-LAST:event_menuFrequenzaVariabileActionPerformed

    private void menuR2D2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuR2D2ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinestraR2D2(menuR2D2).setVisible(true);
            }
        });
    }//GEN-LAST:event_menuR2D2ActionPerformed

    private void menuSalvaWavetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalvaWavetableActionPerformed
        if(go.wavetable.size()>0){
            String nomeFile="wavetable.txt";
            JFileChooser chooser = new JFileChooser(HOME_PATH);
            chooser.setFileFilter(new FileNameExtensionFilter("File TXT","txt"));
            chooser.setSelectedFile(new File(nomeFile));
            if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
                if(chooser.getSelectedFile()!=null){               
                    nomeFile=chooser.getSelectedFile().getName();             
                }
                go.salvaWavetable(nomeFile);
            }
        }
    }//GEN-LAST:event_menuSalvaWavetableActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(Finestra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Finestra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Finestra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Finestra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Finestra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonRiproduci;
    private javax.swing.JPanel grafico;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel labelDX;
    private javax.swing.JLabel labelIstanteTemporale;
    private javax.swing.JLabel labelSX;
    private javax.swing.JLabel labelZoom;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuCaricaFile;
    private javax.swing.JMenuItem menuCaricaWavetable;
    private javax.swing.JMenuItem menuClip;
    private javax.swing.JMenuItem menuDaWavetable;
    private javax.swing.JMenuItem menuDenteDiSega;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFoldback;
    private javax.swing.JMenuItem menuFrequenzaVariabile;
    private javax.swing.JMenuItem menuFrequenzeMusicali;
    private javax.swing.JMenu menuGenera;
    private javax.swing.JMenuItem menuHeader;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuLivelli;
    private javax.swing.JMenuItem menuMicrofono;
    private javax.swing.JMenu menuModifica;
    private javax.swing.JMenuItem menuNormalize;
    private javax.swing.JMenuItem menuOndaQuadra;
    private javax.swing.JMenuItem menuOndaTriangolare;
    private javax.swing.JMenuItem menuParametri;
    private javax.swing.JMenuItem menuParametriAudio;
    private javax.swing.JMenuItem menuR2D2;
    private javax.swing.JMenuItem menuRumoreBianco;
    private javax.swing.JMenu menuSalvaFile;
    private javax.swing.JMenuItem menuSalvaRaw;
    private javax.swing.JMenuItem menuSalvaWav;
    private javax.swing.JMenuItem menuSalvaWavetable;
    private javax.swing.JMenuItem menuSinusoide;
    private javax.swing.JMenuItem menuSommaFrequenza;
    private javax.swing.JMenuItem menuTaglia;
    private javax.swing.JMenu menuVisualizza;
    private javax.swing.JMenuItem menuWavetable;
    private javax.swing.JScrollBar scrollbarGrafico;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton zoomXMeno;
    private javax.swing.JButton zoomXPiu;
    // End of variables declaration//GEN-END:variables
}
