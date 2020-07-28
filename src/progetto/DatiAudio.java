package progetto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DatiAudio {
    
    ArrayList<Campioni> campioni;
    ParametriAudio parametri;
    private String filePath;
    private String nomeAudio;
    private boolean wav;
    Selezione selezione;
    
    public DatiAudio(){
        campioni=new ArrayList<>();
        parametri=new ParametriAudio();
        nomeAudio="";
        filePath="";
        wav=false;
        selezione=new Selezione();
    }
    
    public DatiAudio(ParametriAudio parametri){
        campioni=new ArrayList<>();
        nomeAudio="";
        filePath="";
        wav=false;
        this.parametri.copiaParametri(parametri);
        selezione=new Selezione();
    }
    
    public void setPath(String path){
        filePath=path;
        nomeAudio=(new File(path).getName());
        String parts[]=path.split("\\.");
        if(parts.length>1){
            if(parts[1].toLowerCase().equals("wav")) wav=true;
            else wav=false;
        }
        else
            wav=false;
    }
    
    public void setNomeAudio(String nome){
        nomeAudio=nome;
        filePath="";
        wav=false;
    }
    
    public String getNomeAudio(){ return nomeAudio; }
    
    public String getFilePath() { return filePath; } 
    
    public boolean isWav() {return wav; }

    public void setWav(boolean wav){
        this.wav=wav;
    }
    
    public double getDurata(){
        double durata=0;
        if(!campioni.isEmpty())
            durata=campioni.size()/(double) parametri.getSampleRate();
        return durata;
    }
    
    public String durataToString(double durata){
        return String.format("%.3f",durata);
    }
    
    public String durataToString(){
        return String.format("%.3f",getDurata());
    }
    
    public double getIstanteTemporale(int indice){
        double istante=0;
        if(!campioni.isEmpty()){
            istante=indice/(double) parametri.getSampleRate();
        } 
        return istante;
    }
    
    public byte[] buildHeader(){
        byte[] header=new byte[44];   
        int nCampioni=campioni.size();
        int nCanali=parametri.getNCanali();
        int sampleRate=parametri.getSampleRate();
        int bitDepth=parametri.getBitDepth();
        
        int audioSamplesByteSize=nCampioni*nCanali*bitDepth/8;
        
        header[0]='R';
        header[1]='I';
        header[2]='F';
        header[3]='F';
        
        int chunkSize=(int) (audioSamplesByteSize+36); //44byte meno i 2 fino a chunkSize
        
        header[4]=(byte)(chunkSize&0xff);
        header[5]=(byte)(chunkSize>>8&0xff);
        header[6]=(byte)(chunkSize>>16&0xff);
        header[7]=(byte)(chunkSize>>24&0xff);
        
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        
        header[12] = 'f';
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        
        header[16] = 16;
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        
        header[20] = 1; 
        header[21] = 0;
        
        header[22] = (byte)(nCanali&0xff); 
        header[23] = (byte)(nCanali>>8&0xff);

        header[24] = (byte) (sampleRate & 0xff);
        header[25] = (byte) ((sampleRate >> 8) & 0xff);
        header[26] = (byte) ((sampleRate >> 16) & 0xff);
        header[27] = (byte) ((sampleRate >> 24) & 0xff);
        
        int byteRate=sampleRate*nCanali*bitDepth/8;
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        
        int blockAlign=nCanali*bitDepth/8;
        header[32] = (byte) (blockAlign & 0xff);
        header[33] = (byte) ((blockAlign >> 8) & 0xff);
        
        header[34] = (byte) (bitDepth&0xff);
        header[35] = (byte) (bitDepth>>8&0xff);
        
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        
        header[40] = (byte) (audioSamplesByteSize & 0xff);
        header[41] = (byte) ((audioSamplesByteSize >> 8) & 0xff);
        header[42] = (byte) ((audioSamplesByteSize >> 16) & 0xff);
        header[43] = (byte) ((audioSamplesByteSize >> 24) & 0xff);
        
        return header;
    }
 
    public boolean parseAudioBytes(String filePath){
        int nCanali=-1;
        int sampleRate=-1;
        int bitDepth=-1;
        int bitZero=-1;
  
        boolean success=true;
        File file=new File(filePath);
        if(file.exists()){
            try {
                setPath(filePath);
                InputStream input=new FileInputStream(file);
                    int dataChunks;
                    if(wav)
                        dataChunks=(int)file.length()-44;
                    else
                        dataChunks=(int)file.length();                  
                    if(dataChunks>=0){
                        byte[] bytes=new byte[dataChunks];                        
                        try {
                            if(wav){
                                input.skip(22);
                                nCanali=((byte)input.read()&0xff|(((byte)input.read()&0xff)<<8));
                                sampleRate=(((byte)input.read()&0xff)|((byte)input.read()&0xff)<<8|((byte)input.read()&0xff)<<16|((byte)input.read()&0xff)<<24);
                                input.skip(6);
                                bitDepth=((byte)input.read()&0xff|(((byte)input.read()&0xff)<<8));
                                bitZero=(int)(Math.pow(2,bitDepth)/2-1);
                                input.skip(8);
                            }
                            else{
                                //form inserimento parametri raw
                                JTextField inputSampleRate = new JTextField();
                                inputSampleRate.setText(""+parametri.getSampleRate());
                                JComboBox  inputNCanali = new JComboBox(new String[]{"1","2"});
                                if(parametri.getNCanali()==2) inputNCanali.setSelectedIndex(1);
                                else inputNCanali.setSelectedIndex(0);
                                JComboBox  inputBitDepth = new JComboBox(new String[]{"16","8"});
                                if(parametri.getBitDepth()==8) inputBitDepth.setSelectedIndex(1);
                                else inputBitDepth.setSelectedIndex(0);
                                JComponent[] inputs= new JComponent[]{new JLabel("Sample Rate"),inputSampleRate,new JLabel("Numero Canali"),inputNCanali,new JLabel("Bit Depth"),inputBitDepth};   
                                int result= JOptionPane.showConfirmDialog(null, inputs, "Parametri file RAW", JOptionPane.PLAIN_MESSAGE);
                                if(result==JOptionPane.OK_OPTION){
                                    try{
                                        sampleRate=Integer.parseInt(inputSampleRate.getText());
                                        nCanali=Integer.parseInt(inputNCanali.getSelectedItem().toString());
                                        bitDepth=Integer.parseInt(inputBitDepth.getSelectedItem().toString());
                                    }catch(Exception e){
                                        JOptionPane.showMessageDialog(null,"Parametri non validi","Errore",JOptionPane.ERROR_MESSAGE);
                                        success=false;
                                    }
                                }
                                else success=false;
                            }
                            for(int i=0;i<dataChunks;i++)
                                bytes[i]=(byte) (input.read()&0xff);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null,"Errore nella lettura del file!!","Errore",JOptionPane.ERROR_MESSAGE);
                            success=false;
                        }
                        if(success){
                            selezione.annullaSelezione();
                            campioni.clear();
                            short[] ordinati=new short[(int) dataChunks/(bitDepth/8)];
                            if(bitDepth==16)
                                ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(ordinati);
                            else
                                for(int i=0;i<dataChunks;i++){
                                    ordinati[i]=(short)((bytes[i]&0xff)-bitZero);
                                }
                            if(nCanali==1){
                                for(int i=0;i<ordinati.length;i++){
                                    Campioni c=new Campioni(ordinati[i]);
                                    campioni.add(c);
                                }
                            }
                            if(nCanali==2){
                                for(int i=0;i<ordinati.length;i+=2){
                                    if(i+1<ordinati.length){
                                        Campioni c=new Campioni(ordinati[i],ordinati[i+1]);
                                        campioni.add(c);
                                    }
                                }
                            }
                            parametri=new ParametriAudio(sampleRate,nCanali,bitDepth);                      
                            System.out.println("Byte Audio Caricati: "+dataChunks);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Il file selezionato non é valido!!","Errore",JOptionPane.ERROR_MESSAGE);
                        success=false;
                    }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Errore durante l'apertura del file!!!","Errore",JOptionPane.ERROR_MESSAGE);
                success=false;
            }
        }
        else    success=false;
        
        return success;
    }   
    
    public boolean salvaCampioni(File file){
        
        boolean success=true;
        int nCanali=parametri.getNCanali();
        int bitDepth=parametri.getBitDepth();
        
        byte bytes[]=new byte[campioni.size()*nCanali*bitDepth/8];
        if(nCanali==1&&bitDepth==8)
            for(int i=0;i<campioni.size();i++){
                bytes[i]=(byte) ((int)campioni.get(i).left+128);
            }
        if(nCanali==1&&bitDepth==16)
            for(int i=0;i<campioni.size();i++){
                bytes[2*i]=(byte) (campioni.get(i).left&0xff);
                bytes[2*i+1]=(byte) (campioni.get(i).left>>8&0xff);
            }
        if(nCanali==2&&bitDepth==8)
           for(int i=0;i<campioni.size();i++){
                bytes[2*i]=(byte) ((int)campioni.get(i).left+128);
                bytes[2*i+1]=(byte) ((int)campioni.get(i).right+128);
            } 
        if(nCanali==2&&bitDepth==16)
            for(int i=0;i<campioni.size();i++){
                bytes[4*i]=(byte) (campioni.get(i).left&0xff);
                bytes[4*i+1]=(byte) (campioni.get(i).left>>8&0xff);
                bytes[4*i+2]=(byte) (campioni.get(i).right&0xff);
                bytes[4*i+3]=(byte) (campioni.get(i).right>>8&0xff);
            }
              
        try {
            FileOutputStream fos = new FileOutputStream(file);
            String parts[]=file.getName().split("\\.");
            if(parts[1].toLowerCase().equals("wav")){
                byte[] header=buildHeader();
                fos.write(header);
            }
            fos.write(bytes);
            fos.close();
            System.out.println("File salvato come "+file.getName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Errore durante il salvataggio del file!!!","Errore",JOptionPane.ERROR_MESSAGE);
            success=false;
        }
        return  success;
    }

    public byte[] estraiByteSample(){
        
        int nCanali=parametri.getNCanali();
        int bitDepth=parametri.getBitDepth();
             
        byte[] dati=new byte[44+campioni.size()*nCanali*16/8];
        
        if(bitDepth==16){
            if(nCanali==1)
                for(int i=0;i<campioni.size();i++){
                    dati[44+2*i]=(byte) ((campioni.get(i).left)&0xff);
                    dati[44+2*i+1]=(byte) ((campioni.get(i).left>>8)&0xff);
                }
            if(nCanali==2)
                for(int i=0;i<campioni.size();i++){
                    dati[44+4*i]=(byte) ((campioni.get(i).left)&0xff);
                    dati[44+4*i+1]=(byte) ((campioni.get(i).left>>8)&0xff);
                    dati[44+4*i+2]=(byte) ((campioni.get(i).right)&0xff);
                    dati[44+4*i+3]=(byte) ((campioni.get(i).right>>8)&0xff);
                }
            }
        if(bitDepth==8){
            if(nCanali==1)
                for(int i=0;i<campioni.size();i++){                    
                    dati[44+2*i]=(byte) (0&0xff);
                    dati[44+2*i+1]=(byte) ((campioni.get(i).left)&0xff);
                }
            if(nCanali==2)
                for(int i=0;i<campioni.size();i++){
                    dati[44+4*i]=(byte) (0&0xff);
                    dati[44+4*i+1]=(byte) ((campioni.get(i).left)&0xff);
                    dati[44+4*i+2]=(byte) (0&0xff);
                    dati[44+4*i+3]=(byte) ((campioni.get(i).right)&0xff);
                    
                }
            }
        
        boolean temp=false;
        if(bitDepth==8){
            parametri.setBitDepth(16);
            temp=true;
        }        
        byte[] header=buildHeader();
        if(temp) parametri.setBitDepth(8);
        for(int i=0;i<header.length;i++){
            dati[i]=(header[i]);
        } 
        System.out.println("Byte dei campioni estratti: "+dati.length+"B");
            
        return  dati;
        
    }
    
    public byte[] caricaHeader(){
        File file=new File(filePath);
        int dataChunks=44;
        byte[] bytes=new byte[dataChunks];
            try {
                InputStream input=new FileInputStream(file);                   
                    for(int i=0;i<dataChunks;i++)
                        bytes[i]=(byte) (input.read()&0xff);
             } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Errore nella lettura del file!!","Errore",JOptionPane.ERROR_MESSAGE);
            }
        return bytes;
    }
    
    public void setCampioni(ArrayList<Campioni> campioni,String nomeAudio){
        this.campioni=campioni;
        setNomeAudio(nomeAudio);
        selezione.annullaSelezione();
        
    }
    
    public void setCampioni(ArrayList<Campioni> campioni){
        this.campioni=campioni;
    }
}
