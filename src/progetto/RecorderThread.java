package progetto;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.unitgen.LineIn;
import com.jsyn.unitgen.VariableRateDataReader;
import com.jsyn.util.WaveRecorder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

public class RecorderThread extends SwingWorker<Void,Long>{
    
    Synthesizer synth;
    VariableRateDataReader samplePlayer;
    WaveRecorder recorder;
    String fileName;
    
    public RecorderThread(String fileName){
        synth=JSyn.createSynthesizer();   
        this.fileName=fileName;
    }  

    @Override
    protected Void doInBackground() throws Exception {
        File waveFile=new File(Finestra.HOME_PATH+fileName);
        try {
            recorder= new WaveRecorder(synth, waveFile );                
            int numInputChannels = 2;
            int numOutputChannels = 2; 
            synth.start( 44100, AudioDeviceManager.USE_DEFAULT_DEVICE, numInputChannels, AudioDeviceManager.USE_DEFAULT_DEVICE,numOutputChannels );
            LineIn lineIn= new LineIn();
            synth.add(lineIn);
            lineIn.output.connect( 0, recorder.getInput(), 0 ); 
            lineIn.output.connect( 0, recorder.getInput(), 1 );
            long startTime=System.currentTimeMillis();
            recorder.start();
            while(true){
                //
            }
         } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Override
    protected void process(List<Long> chunks){

    }
    
    @Override
    public void done(){
        recorder.stop();
        try {
            recorder.close();
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }       
        synth.stop();
    }
}
