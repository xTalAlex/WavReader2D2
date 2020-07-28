package progetto;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.data.FloatSample;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.VariableRateDataReader;
import com.jsyn.unitgen.VariableRateMonoReader;
import com.jsyn.unitgen.VariableRateStereoReader;
import com.jsyn.util.SampleLoader;
import com.softsynth.shared.time.TimeStamp;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class PlayerThread extends SwingWorker<Void,String>{
    
    Synthesizer synth;
    byte[] samples;

    public PlayerThread(byte[] samples){
        synth=JSyn.createSynthesizer(); 
        this.samples=samples;
    } 
    
    @Override
    protected Void doInBackground() throws Exception {     
        LineOut lineOut=new LineOut();
        synth.add(lineOut);          
        synth.start();        
        TimeStamp ts=new TimeStamp(synth.getCurrentTime());       
        VariableRateDataReader samplePlayer=new VariableRateStereoReader();
        InputStream stream=new ByteArrayInputStream(samples);           
        FloatSample sample;
        
        boolean success=true;
        try {           
            sample = SampleLoader.loadFloatSample(stream);
            if(sample.getChannelsPerFrame()==1){
                synth.add(samplePlayer=new VariableRateMonoReader());
                samplePlayer.output.connect(0,lineOut.input,0);
                samplePlayer.output.connect(0,lineOut.input,1);
            }
            else if(sample.getChannelsPerFrame()==2){
                synth.add(samplePlayer=new VariableRateStereoReader());
                samplePlayer.output.connect(0,lineOut.input,0);
                samplePlayer.output.connect(1,lineOut.input,1);
            }           
            else{
              JOptionPane.showMessageDialog(null,"Formato non supportato!!","Errore",JOptionPane.ERROR_MESSAGE);
              synth.stop();
              success=false;
            }
            if(success){
                samplePlayer.rate.set(sample.getFrameRate());
                lineOut.start(ts=ts.makeRelative(0.5));
                samplePlayer.dataQueue.queue(sample);
                do{
                    try {
                        synth.sleepFor(1.0);
                    } catch (InterruptedException ex) {
                        System.out.println("Errore durante l'esecuzione dell'onda: "+ex);
                    }
                }while(samplePlayer.dataQueue.hasMore());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Errore nella lettura del file!!","Errore",JOptionPane.ERROR_MESSAGE);
            synth.stop();
        }
     
        return null;
    }
    
    @Override
    protected void done(){
        synth.stop();
    }
}
