
package progetto;

import java.util.ArrayList;

public class GeneratoreModulazioni {

    int sampleRate;
    int bitZero;
    ArrayList<Campioni> campioni;
    
    public GeneratoreModulazioni(DatiAudio dati){
        sampleRate=dati.parametri.getSampleRate();
        bitZero=dati.parametri.getBitZero();
        campioni=dati.campioni;
    }
    
    public void getRM(int nCampioni, double fc, double ac, double fm, double am){
        campioni.clear();
        double samplesPerCycleC=sampleRate/fc;
        double samplesPerCycleM=sampleRate/fm;
        
        short val;
        for(int i=0;i<nCampioni;i++){
            //VAM = [Ap + Am· sin(2πfm· t)] · sin(2πfp· t)           
            double modulator = am * Math.sin(2 * Math.PI * (i / samplesPerCycleM));
            val=(short)( ((modulator)*(ac*Math.sin(2*Math.PI*(i/samplesPerCycleC))))*bitZero );
            Campioni c=new Campioni((short)(val));
            campioni.add(c);
        }
        System.out.println("Modulazione RM generata");
    }
        
    public void getAM(int nCampioni, double fc, double ac, double fm, double am){
        campioni.clear();
        double samplesPerCycleC=sampleRate/fc;
        double samplesPerCycleM=sampleRate/fm;
        
        short val;
        for(int i=0;i<nCampioni;i++){
            //VAM = [Ac + Am· cos(2πfm· t)] · cos(2πfc· t)          
            double modulator = am * Math.sin(2 * Math.PI * (i / samplesPerCycleM))+am;
            double carriage = ac* Math.sin(2 * Math.PI * (i/ samplesPerCycleC));
            val=(short)(modulator*carriage*bitZero);
            Campioni c=new Campioni((short)(val));
            campioni.add(c);
        }
        System.out.println("Modulazione AM generata");
    }
               
    public void getFM1(int nCampioni, double fc, double ac, double fm, double am){
        campioni.clear();
        double samplesPerCycleC=sampleRate/fc;
        double samplesPerCycleM=sampleRate/fm;
        
        short val;
        for(int i=0;i<nCampioni;i++){
            //VFM = Ap· cos[2πfp· t + Am cos(2πfm· t)]         
            double modulator = am * Math.sin(2 * Math.PI * (i / samplesPerCycleM));
            double carriage = ac* Math.sin(2 * Math.PI * (i/ samplesPerCycleC) + modulator);
            val=(short)(carriage*bitZero);
            Campioni c=new Campioni((short)(val));
            campioni.add(c);
        }
        System.out.println("Modulazione FM1 generata");    
    }
                
    public void getFM2(int nCampioni, double fc, double ac, double fm1, double fm2, double am){
        campioni.clear();
        double samplesPerCycleC=sampleRate/fc;
        double nFreqs=(fm2-fm1)/nCampioni;
        
        for (int i = 0; i < nCampioni; i++) {
            double samplesPerCycleM = sampleRate/fm1;
            double modulator = am * Math.sin(2 * Math.PI * (i / samplesPerCycleM));
            double tempResult = (ac * Math.sin(2 * Math.PI * (i / samplesPerCycleC) + modulator));
            short val=(short) (tempResult *bitZero);
            fm1+= nFreqs;
            Campioni c=new Campioni((short)(val));
            campioni.add(c);
        }
        System.out.println("Modulazione FM2 generata: da frequenza "+fm1+" a "+fm2);
    }
}
