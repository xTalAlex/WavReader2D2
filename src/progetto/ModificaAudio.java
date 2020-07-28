package progetto;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class ModificaAudio {
    
    public static ArrayList<Campioni> clip(DatiAudio dati, double normalizedAmplitude){
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        
        short clipAmplitude=(short)(dati.parametri.getBitZero()*normalizedAmplitude);
        
        if(!dati.selezione.isSelected())
            for(int i=0;i<dati.campioni.size();i++){
                if(dati.campioni.get(i).left>clipAmplitude) dati.campioni.get(i).left=clipAmplitude;
                if(dati.campioni.get(i).left<-clipAmplitude) dati.campioni.get(i).left=(short)-clipAmplitude;
                if(dati.campioni.get(i).right>clipAmplitude) dati.campioni.get(i).right=clipAmplitude;
                if(dati.campioni.get(i).right<-clipAmplitude) dati.campioni.get(i).right=(short)-clipAmplitude;
            }
        else
            for(int i=dati.selezione.getLimiteInf();i<dati.selezione.getLimiteSup();i++){
                if(dati.campioni.get(i).left>clipAmplitude) dati.campioni.get(i).left=clipAmplitude;
                if(dati.campioni.get(i).left<-clipAmplitude) dati.campioni.get(i).left=(short)-clipAmplitude;
                if(dati.campioni.get(i).right>clipAmplitude) dati.campioni.get(i).right=clipAmplitude;
                if(dati.campioni.get(i).right<-clipAmplitude) dati.campioni.get(i).right=(short)-clipAmplitude;
            }
        
        System.out.println("Clipping Effettuato");
        return dati.campioni;
    }
    
    public static ArrayList<Campioni> normalize(DatiAudio dati, double normalizedAmplitude){
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        
        short maxAmplitude=0;
        
        if(!dati.selezione.isSelected()){
            for(int i=0;i<dati.campioni.size();i++){
                if(Math.abs(dati.campioni.get(i).left)>maxAmplitude) maxAmplitude=(short)Math.abs(dati.campioni.get(i).left);
                if(Math.abs(dati.campioni.get(i).right)>maxAmplitude) maxAmplitude=(short)Math.abs(dati.campioni.get(i).right);
            }

            double rescale=normalizedAmplitude*dati.parametri.getBitZero()/maxAmplitude;
            for(int i=0;i<dati.campioni.size();i++){
                dati.campioni.get(i).left=(short)(dati.campioni.get(i).left*rescale);
                dati.campioni.get(i).right=(short)(dati.campioni.get(i).right*rescale);
            }
        }
        else{
            for(int i=dati.selezione.getLimiteInf();i<dati.selezione.getLimiteSup();i++){
                if(Math.abs(dati.campioni.get(i).left)>maxAmplitude) maxAmplitude=(short)Math.abs(dati.campioni.get(i).left);
                if(Math.abs(dati.campioni.get(i).right)>maxAmplitude) maxAmplitude=(short)Math.abs(dati.campioni.get(i).right);
            }

            double rescale=normalizedAmplitude*dati.parametri.getBitZero()/maxAmplitude;
            for(int i=dati.selezione.getLimiteInf();i<dati.selezione.getLimiteSup();i++){
                dati.campioni.get(i).left=(short)(dati.campioni.get(i).left*rescale);
                dati.campioni.get(i).right=(short)(dati.campioni.get(i).right*rescale);
            }
        }
        System.out.println("Campioni normalizzati a: "+normalizedAmplitude); 
        return dati.campioni;
    }
    
    public static ArrayList<Campioni> normalize(DatiAudio dati){ 
        return normalize(dati,1);
    }
    
    public static ArrayList<Campioni> setLevels(DatiAudio dati, double normalizedLeftLevel, double normalizedRightLevel){
        if(Math.abs(normalizedLeftLevel)>1) normalizedLeftLevel = 1;
        if(Math.abs(normalizedRightLevel)>1) normalizedRightLevel = 1;
        normalizedLeftLevel=Math.abs(normalizedLeftLevel);
        normalizedRightLevel=Math.abs(normalizedRightLevel);
        
        if(!dati.selezione.isSelected())
            for(int i=0;i<dati.campioni.size();i++){
                dati.campioni.get(i).left=(short)(dati.campioni.get(i).left*normalizedLeftLevel);
                dati.campioni.get(i).right=(short)(dati.campioni.get(i).right*normalizedRightLevel);
            }
        else
            for(int i=dati.selezione.getLimiteInf();i<dati.selezione.getLimiteSup();i++){
                dati.campioni.get(i).left=(short)(dati.campioni.get(i).left*normalizedLeftLevel);
                dati.campioni.get(i).right=(short)(dati.campioni.get(i).right*normalizedRightLevel);
            }
            
        System.out.println("Livelli impostati a: "+normalizedLeftLevel+"L/"+normalizedRightLevel+"R");
        return dati.campioni;
    }
    
    public static ArrayList<Campioni> foldback(DatiAudio dati, double normalizedAmplitude){
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        
        short foldbackAmplitude=(short)(dati.parametri.getBitZero()*normalizedAmplitude);
        
        if(!dati.selezione.isSelected())
            for(int i=0;i<dati.campioni.size();i++){
                if(dati.campioni.get(i).left>foldbackAmplitude) dati.campioni.get(i).left=(short)(foldbackAmplitude-(dati.campioni.get(i).left-foldbackAmplitude));
                if(dati.campioni.get(i).right>foldbackAmplitude) dati.campioni.get(i).right=(short)(foldbackAmplitude-(dati.campioni.get(i).right-foldbackAmplitude));
                if(dati.campioni.get(i).left<-foldbackAmplitude) dati.campioni.get(i).left=(short)(-foldbackAmplitude-(dati.campioni.get(i).left+foldbackAmplitude));
                if(dati.campioni.get(i).right<-foldbackAmplitude) dati.campioni.get(i).right=(short)(-foldbackAmplitude-(dati.campioni.get(i).right+foldbackAmplitude));
            }
        else
            for(int i=dati.selezione.getLimiteInf();i<dati.selezione.getLimiteSup();i++){
                if(dati.campioni.get(i).left>foldbackAmplitude) dati.campioni.get(i).left=(short)(foldbackAmplitude-(dati.campioni.get(i).left-foldbackAmplitude));
                if(dati.campioni.get(i).right>foldbackAmplitude) dati.campioni.get(i).right=(short)(foldbackAmplitude-(dati.campioni.get(i).right-foldbackAmplitude));
                if(dati.campioni.get(i).left<-foldbackAmplitude) dati.campioni.get(i).left=(short)(-foldbackAmplitude-(dati.campioni.get(i).left+foldbackAmplitude));
                if(dati.campioni.get(i).right<-foldbackAmplitude) dati.campioni.get(i).right=(short)(-foldbackAmplitude-(dati.campioni.get(i).right+foldbackAmplitude));
            }
        System.out.println("Foldbacking Effettuato");
        return dati.campioni;
    }
    
    public static ArrayList<Campioni> sommaFrequenza(DatiAudio dati,double normalizedAmplitude,double frequenza){
        if(Math.abs(normalizedAmplitude)>1) normalizedAmplitude=1;
        normalizedAmplitude=Math.abs(normalizedAmplitude);
        
        double samplesPerCycle=dati.parametri.getSampleRate()/frequenza;
        if(!dati.selezione.isSelected())
            for(int i=0;i<dati.campioni.size();i++){          
                dati.campioni.get(i).left=(short)(dati.campioni.get(i).left/2+dati.parametri.getBitZero()*normalizedAmplitude/2*Math.sin(2*Math.PI*(i/samplesPerCycle)));
                dati.campioni.get(i).right=(short)(dati.campioni.get(i).right/2+dati.parametri.getBitZero()*normalizedAmplitude/2*Math.sin(2*Math.PI*(i/samplesPerCycle)));
            }
        else
            for(int i=dati.selezione.getLimiteInf();i<dati.selezione.getLimiteSup();i++){          
                dati.campioni.get(i).left=(short)(dati.campioni.get(i).left/2+dati.parametri.getBitZero()*normalizedAmplitude/2*Math.sin(2*Math.PI*(i/samplesPerCycle)));
                dati.campioni.get(i).right=(short)(dati.campioni.get(i).right/2+dati.parametri.getBitZero()*normalizedAmplitude/2*Math.sin(2*Math.PI*(i/samplesPerCycle)));
            }
        System.out.println("Sintesi additiva con "+frequenza+"Hz");
        return dati.campioni;
    }
    
    public static ArrayList<Campioni> tagliaCampioni(DatiAudio dati){
        if(dati.selezione.isSelected()){          
            for(int i=dati.selezione.getLimiteSup();i>=dati.selezione.getLimiteInf();i--){          
                dati.campioni.remove(i);
            }
            System.out.println("Tagliati campioni da "+dati.selezione.getLimiteInf()+" a "+dati.selezione.getLimiteSup());
        }
        return dati.campioni;
    }
    
    //per clip, foldback, noramlized
    public static double richiediValore(){
        double val=-1;
        
        JSlider inputAmplitude = new JSlider(0,100,100);
        JLabel valoreAmplitude=new JLabel("1.0");
        inputAmplitude.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                double value=inputAmplitude.getValue()/(double)100;
                valoreAmplitude.setText(""+value);
            }
        });       
        JComponent[] inputs= new JComponent[]{new JLabel("Normalized Amplitude"),inputAmplitude,valoreAmplitude};   
        int result=JOptionPane.showConfirmDialog(null, inputs, "Parametri di modifica", JOptionPane.PLAIN_MESSAGE);
        if(result==JOptionPane.OK_OPTION){
            try{
                val=inputAmplitude.getValue()/(double)100;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Parametri non validi","Errore",JOptionPane.ERROR_MESSAGE);
            }
        }        
        return val;
    }
    
    //op -> 1 per livelli, 2 per somma frequenza
    public static double[] richiediValori(int op){      
        int n=2;
        double[] val=new double[n];
        for(int i=0;i<n;i++){
            val[i]=-1;
        }
        JSlider inputAmplitude1 = new JSlider(0,100,100);
        JLabel valoreAmplitude1=new JLabel("1.0");
        inputAmplitude1.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                double value=inputAmplitude1.getValue()/(double)100;
                valoreAmplitude1.setText(""+value);
            }
        });
        if(op==1){
            JSlider inputAmplitude2 = new JSlider(0,100,100);
            JLabel valoreAmplitude2=new JLabel("1.0");
            inputAmplitude2.addChangeListener(new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e){
                    double value=inputAmplitude2.getValue()/(double)100;
                    valoreAmplitude2.setText(""+value);
                }
            }); 
            JComponent[] inputs= new JComponent[]{new JLabel("Normalized Amplitude SX"),inputAmplitude1,valoreAmplitude1,new JLabel("Normalized Amplitude DX"),inputAmplitude2,valoreAmplitude2}; 
            int result=JOptionPane.showConfirmDialog(null, inputs, "Parametri di modifica", JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                try{
                    val[0]=inputAmplitude1.getValue()/(double)100;
                    val[1]=inputAmplitude2.getValue()/(double)100;
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Parametri non validi","Errore",JOptionPane.ERROR_MESSAGE);
                }
            }  
        }
        if(op==2){
            JTextField inputFrequenza = new JTextField("440");
            JComponent[] inputs= new JComponent[]{new JLabel("Frequenza"),inputFrequenza,new JLabel("Ampiezza"),inputAmplitude1,valoreAmplitude1}; 
            int result=JOptionPane.showConfirmDialog(null, inputs, "Parametri di modifica", JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                try{
                    val[0]=inputAmplitude1.getValue()/(double)100;
                    val[1]=Double.parseDouble(inputFrequenza.getText());
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Parametri non validi","Errore",JOptionPane.ERROR_MESSAGE);
                }
            }  
        }
        return val;
    }
    
}
