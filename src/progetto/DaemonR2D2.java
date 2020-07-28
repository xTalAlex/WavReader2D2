package progetto;

import com.jsyn.*;
import com.jsyn.data.FloatSample;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.VariableRateDataReader;
import com.jsyn.unitgen.VariableRateStereoReader;
import com.jsyn.util.SampleLoader;
import com.softsynth.shared.time.TimeStamp;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.swing.*;

public class DaemonR2D2 extends SwingWorker<Void,String> {
    
    Synthesizer synth;
    DatiAudio datiAudio;
    GeneratoreOnde go;
    
    public DaemonR2D2(){
        datiAudio=new DatiAudio();
        datiAudio.setNomeAudio("parolaDiR2D2");
        go=new GeneratoreOnde();
    }

    @Override
    protected Void doInBackground() throws Exception {
        synth=JSyn.createSynthesizer();
        
        LineOut lineOut=new LineOut();
        synth.add(lineOut);
                   
        VariableRateDataReader samplePlayer=new VariableRateStereoReader();
        samplePlayer.output.connect(0,lineOut.input,0);
        samplePlayer.output.connect(1,lineOut.input,1); 
        
        boolean continua=true;  
        Random rand=new Random();      
        while(continua){
            datiAudio.campioni=go.generaFrequenzaVariabile();
            synth.start(); 
            TimeStamp ts=new TimeStamp(synth.getCurrentTime());

            InputStream stream=new ByteArrayInputStream(datiAudio.estraiByteSample());           
            FloatSample sample;     
            try {           
                sample = SampleLoader.loadFloatSample(stream);
                synth.add(samplePlayer);     

                samplePlayer.rate.set(sample.getFrameRate());
                lineOut.start(ts=ts.makeRelative(0.5));
                samplePlayer.dataQueue.queue(sample);
                do{
                    try {
                        synth.sleepFor(1.0);
                    } catch (InterruptedException ex) {
                        System.out.println("R2D2 si é inceppato: "+ex);
                    }
                }while(samplePlayer.dataQueue.hasMore());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"R2D2 non riesce a comunicare","Errore",JOptionPane.ERROR_MESSAGE);
                synth.stop();
            }
            Thread.sleep((rand.nextInt(60)+2)*1000);
        }
        
        return null;
    }
    
    @Override
    protected void process(List<String> chunks){
    
    }
    
    @Override
    protected void done(){
        synth.stop();
    }
}
