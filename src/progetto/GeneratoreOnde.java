package progetto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class GeneratoreOnde {
    
    ArrayList<Double> wavetable;
    ParametriAudio parametri;
    
    public GeneratoreOnde(){
        wavetable=new ArrayList<>();
        parametri=new ParametriAudio();
    }
    
    public ArrayList<Campioni> generaRumoreBianco(int nCampioni,double normalizedAmplitude){
        ArrayList<Campioni> campioni=new ArrayList<>();        
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        Random rand=new Random();
        
        for(int i=0;i<nCampioni;i++){
            Campioni c=new Campioni((short)(normalizedAmplitude*(rand.nextInt(parametri.getBitZero()*2)-parametri.getBitZero())));
            if(parametri.getNCanali()==2) c.right=((short)(normalizedAmplitude*(rand.nextInt(parametri.getBitZero()*2)-parametri.getBitZero())));
            campioni.add(c);
        }
        System.out.println("Generato Rumore Bianco");
        return campioni;
    }
    
    public ArrayList<Campioni> generaOndaQuadra(int nCampioni,double normalizedAmplitude, double frequenza){
        ArrayList<Campioni> campioni=new ArrayList<>();
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        short max=(short)(parametri.getBitZero()*normalizedAmplitude);
        
        double samplesPerCycle=(double)(parametri.getSampleRate()/frequenza);
        for(int i=0;i<nCampioni;i++){
            int val;
            if(i%samplesPerCycle<samplesPerCycle/2) val=max;
            else val=(-1)*max;
            if(frequenza==0) val=0;
            Campioni c=new Campioni((short)(val));
            if(parametri.getNCanali()==2) c.right=(short)(val);
            campioni.add(c);
        }
        System.out.println("Generata Onda Quadra");
        return campioni;
    }
    
    public ArrayList<Campioni> generaDenteDiSega(int nCampioni,double normalizedAmplitude, double frequenza){
        ArrayList<Campioni> campioni=new ArrayList<>();
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        short max=(short)(parametri.getBitZero()*normalizedAmplitude);
        
        double samplesPerCycle=(double)(parametri.getSampleRate()/frequenza);
        short val=0;
        for(int i=0;i<nCampioni;i++){
            val=(short)((int)(((i+samplesPerCycle/2)%samplesPerCycle)*(max*2)/samplesPerCycle)-max);
            if(frequenza==0) val=0;
            Campioni c=new Campioni(val);
            if(parametri.getNCanali()==2) c.right=(short)(val);
            campioni.add(c);

        }
        System.out.println("Generata Onda A Dente Di Sega");
        return campioni;
    }
    
    public ArrayList<Campioni> generaOndaTriangolare(int nCampioni,double normalizedAmplitude, double frequenza){
        ArrayList<Campioni> campioni=new ArrayList<>();
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        short max=(short)(parametri.getBitZero()*normalizedAmplitude);
        
        double samplesPerCycle=(double)(parametri.getSampleRate()/frequenza);
        short val=0;
        for(int i=0;i<nCampioni;i++){
            //int currentSample = (int) ((i + samplesPerCycle / 4) % samplesPerCycle);
            val = (short) (2 * Math.abs((((int) ((i + samplesPerCycle / 4) % samplesPerCycle) * (max * 2) / samplesPerCycle) - max)) - max);
            /*if((i+samplesPerCycle*(double)3/4)%samplesPerCycle<samplesPerCycle/2)
                val=(short)(2*(max*2)*(samplesPerCycle-((int)(i+samplesPerCycle*3/(double)4))%samplesPerCycle)/samplesPerCycle-max);
            else
                val=(short)(2*(max*2)*(((int)(i+samplesPerCycle*3/(double)4))%samplesPerCycle)/samplesPerCycle-max);*/
            if(frequenza==0) val=0;
            Campioni c=new Campioni(val);
            if(parametri.getNCanali()==2) c.right=(short)(val);
            campioni.add(c);
        }
        System.out.println("Generata Ondra Triangolare");
        return campioni;
    }
    
    public ArrayList<Campioni> generaOndaSinusoidale(int nCampioni,double normalizedAmplitude, double frequenza){
        ArrayList<Campioni> campioni=new ArrayList<>();
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        short max=(short)(parametri.getBitZero()*normalizedAmplitude);
                
        double samplesPerCycle=(double)(parametri.getSampleRate()/frequenza);
        short val=0;
        for(int i=0;i<nCampioni;i++){
            val=(short)(max*Math.sin(2*Math.PI*(i/samplesPerCycle)));
            Campioni c=new Campioni(val);
            if(parametri.getNCanali()==2) c.right=(short)(val);
            campioni.add(c);
        }
        System.out.println("Generata Ondra Sinusoidale");
        return campioni;
    }   
      
    public boolean caricaWavetable(String wavetableFilePath){
        wavetable.clear();
        boolean success=true;
        File file=new File(wavetableFilePath);
        if(file.exists()){
            try {
                BufferedReader input=new BufferedReader(new FileReader(file));             
                try {
                    String line;
                    while((line=input.readLine())!=null){
                        wavetable.add(Double.parseDouble(line));
                    }
                    input.close();
                    System.out.println("Wavetable Caricata: "+wavetable.size()+" campioni");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Errore nella lettura della wavetable!!","Errore",JOptionPane.ERROR_MESSAGE);
                    success=false;
                }                   
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Errore durante l'apertura del file wavetable!!!","Errore",JOptionPane.ERROR_MESSAGE);
                success=false;
            }
        }
        else    success=false;
        
        return success;
    } 
    
    public void salvaWavetable(String nomeFile){
        File file=new File(Finestra.HOME_PATH+nomeFile);  
        if(!file.exists()){
            try {
                file.createNewFile();                   
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Impossibile creare il file!!!","Errore",JOptionPane.ERROR_MESSAGE);
            }
        } 
        try(BufferedWriter bfw=new BufferedWriter(new FileWriter(file));) {        
            for(double d:wavetable){
                bfw.write(Double.toString(d)+"\n");
            }
            System.out.println("Wavetable salvata in: "+nomeFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Errore di scrittura del file wavetable","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Campioni> tableLookupNoInterpolation(int nCampioni,double normalizedAmplitude, double frequenza){
        ArrayList<Campioni> campioni=new ArrayList<>();
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        short max=(short)(parametri.getBitZero()*normalizedAmplitude);
        
        int nSamplesPerCycle=(int) (parametri.getSampleRate()/frequenza);  
        if(nSamplesPerCycle<1) nSamplesPerCycle=1;
        
        double ratio=(double)wavetable.size()/nSamplesPerCycle;
        short val;
        for(int i=0;i<nCampioni;i++){
            int indice=i%nSamplesPerCycle;
            int indiceInterpolato=(int)((double)indice*ratio);
            val=(short)(-wavetable.get(indiceInterpolato)*max);
            Campioni c=new Campioni(val);
            if(parametri.getNCanali()==2) c.right=(short)(val);
            campioni.add(c);
        }
        System.out.println("Generata Ondra Da Wavetable Senza Interpolazione");
        return campioni;
    }
    
    public ArrayList<Campioni> tableLookupInterpolation(int nCampioni,double normalizedAmplitude, double frequenza){
        ArrayList<Campioni> campioni=new ArrayList<>();
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        short max=(short)((parametri.getBitZero())*normalizedAmplitude);
 
        int nSamplesPerCycle=(int) (parametri.getSampleRate()/frequenza); 
        if(nSamplesPerCycle<1) nSamplesPerCycle=1;
        
        double ratio=(double)wavetable.size()/nSamplesPerCycle;
        short val;
        for(int i=0;i<nCampioni;i++){
            int indice=i%nSamplesPerCycle;
            double indiceInterpolato=((double)indice*ratio);
            int floor=(int)Math.floor(indiceInterpolato);
            if (floor+1<wavetable.size())
                val=(short)(-((indiceInterpolato-floor)*(wavetable.get(floor+1)-wavetable.get(floor))+wavetable.get(floor))*max);
            else
                val=(short)(-max*wavetable.get(floor)*max);
            Campioni c=new Campioni(val);
            if(parametri.getNCanali()==2) c.right=(short)(val);
            campioni.add(c);
        }
        System.out.println("Generata Ondra Da Wavetable Con Interpolazione");
        return campioni;
    }
    
    public void impostaParametri(){  
        JTextField inputSampleRate = new JTextField();
        inputSampleRate.setText(""+parametri.getSampleRate());
        JComboBox  inputNCanali = new JComboBox(new String[]{"1","2"});
        if(parametri.getNCanali()==2) inputNCanali.setSelectedIndex(1);
        else inputNCanali.setSelectedIndex(0);
        JComboBox  inputBitDepth = new JComboBox(new String[]{"16","8"});
        if(parametri.getBitDepth()==8) inputBitDepth.setSelectedIndex(1);
        else inputBitDepth.setSelectedIndex(0);
        JComponent[] inputs= new JComponent[]{new JLabel("Sample Rate"),inputSampleRate,new JLabel("Numero Canali"),inputNCanali,new JLabel("Bit Depth"),inputBitDepth};   
        int result= JOptionPane.showConfirmDialog(null, inputs, "Parametri generatore", JOptionPane.PLAIN_MESSAGE);
        if(result==JOptionPane.OK_OPTION){
            try{
                int sampleRate=Integer.parseInt(inputSampleRate.getText());
                int nCanali=Integer.parseInt(inputNCanali.getSelectedItem().toString());
                int bitDepth=Integer.parseInt(inputBitDepth.getSelectedItem().toString());
                parametri.setSampleRate(sampleRate);
                parametri.setNCanali(nCanali);
                parametri.setBitDepth(bitDepth);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Modifiche non applicate","Parametri Non Validi",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public double[] richiediValori(String tipoOnda){
        int n=3;
        double[] val=new double[n];
        for(int i=0;i<n;i++) val[i]=-1;
        
        JTextField inputNCampioni = new JTextField();
        inputNCampioni.setText(""+parametri.getSampleRate());
        JSlider inputAmplitude = new JSlider(0,100,100);
        JLabel valoreAmplitude=new JLabel("1.0");
        inputAmplitude.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                double value=inputAmplitude.getValue()/(double)100;
                valoreAmplitude.setText(""+value);
            }
        });
        JTextField inputFrequenza = new JTextField();
        inputFrequenza.setText("440");
        if(tipoOnda.toLowerCase().contains("rumore")) inputFrequenza.setEnabled(false);
        JComponent[] inputs= new JComponent[]{new JLabel("Numero Campioni"),inputNCampioni,new JLabel("Normalized Amplitude"),inputAmplitude,valoreAmplitude,new JLabel("Frequenza"),inputFrequenza};   
        int result=JOptionPane.showConfirmDialog(null, inputs, "Parametri "+tipoOnda, JOptionPane.PLAIN_MESSAGE);
        if(result==JOptionPane.OK_OPTION){
            try{
                val[0]=Integer.parseInt(inputNCampioni.getText());
                val[1]=Double.parseDouble(Double.toString(inputAmplitude.getValue()/(double)100));
                val[2]=Double.parseDouble(inputFrequenza.getText());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Parametri non validi","Errore",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return val;
    } 
    
    public ArrayList<Campioni> generaFrequenzaVariabile(){
            ArrayList<Campioni> campioni=new ArrayList<>();
            Random rand=new Random();
            
            double normalizedAmplitude=0.5;
            short amplitude = (short) (parametri.getBitZero()*Math.abs(normalizedAmplitude));
            
            int nCampioni=parametri.getSampleRate()*(rand.nextInt(2)+1);           
            double fBase=1200;            
            int nSalti=rand.nextInt(4)+4;
            
            int intervalloToken=nCampioni/nSalti;
            int intervalli[]=new int[nSalti];
            int sommaIntervalli=0;
            for(int i=0;i<intervalli.length-1;i++){              
                intervalli[i]=rand.nextInt(intervalloToken);
                sommaIntervalli+=intervalli[i];
            }
            int campioniRimasti=nCampioni-sommaIntervalli;
            intervalli[intervalli.length-1]=campioniRimasti;
            
            int categorieSalti[]=new int[]{5000,50,4000,3000,2000,1000,500,200,100};
            double salti[]=new double[nSalti]; //max
                       
            String sindici="\nIndici: ";
            for(int i=0;i<salti.length;i++){
                int indice=rand.nextInt(categorieSalti.length);
                sindici+=""+indice+",";
                salti[i]=rand.nextDouble()*categorieSalti[indice];
                if(rand.nextBoolean()==false) salti[i]*=-1; 
            }
            
            double f1=fBase+salti[0];
            
            short val=0;
            for(int i=1;i<nSalti;i++){
                double frequencyToken=(salti[i])/nCampioni;
                for (int j=0;j<intervalli[i];j++) {
                    double nSamplesPerCycle1=parametri.getSampleRate()/f1;
                    val=(short)(amplitude*Math.sin(2*Math.PI*(j/nSamplesPerCycle1)));
                    Campioni c= new Campioni(val);
                    if(parametri.getNCanali()==2) c.right=(short)(val);
                    campioni.add(c);
                        f1+=frequencyToken;
                }           
            }
            
         String output="Generata Frequenza Variabile: "+nCampioni+"campioni, "+nSalti+"salti, "+"\n"+"Intervalli:";
         for(int i=0;i<intervalli.length;i++){              
                output+=""+intervalli[i]+",";
            }
         output+="\n+ Salti:";
         for(int i=0;i<salti.length;i++){              
                output+=""+salti[i]+",";
            }
         output+=sindici;
         System.out.println(output);
         return campioni;          
    }      
}
