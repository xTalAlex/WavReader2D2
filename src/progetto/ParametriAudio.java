
package progetto;

import javax.swing.JOptionPane;

public class ParametriAudio {
    
    private int sampleRate;
    private int nCanali;
    private int bitDepth;
    private int bitZero;
    
    public ParametriAudio(){
        sampleRate=44100;
        nCanali=2;
        bitDepth=16;
        bitZero=(int)(Math.pow(2,bitDepth)/2-1);
    }
    
    public ParametriAudio(int sampleRate,int nCanali, int bitDepth){
        this.sampleRate=sampleRate;
        this.nCanali=nCanali;
        this.bitDepth=bitDepth;
        this.nCanali=nCanali;
        bitZero=(int)(Math.pow(2,bitDepth)/2-1);
    }
    
    public void setSampleRate(int sampleRate){ this.sampleRate=sampleRate; }
    
    public void setNCanali(int nCanali){ 
        if(nCanali==1||nCanali==2)
            this.nCanali=nCanali;
        else
            JOptionPane.showMessageDialog(null,"Canali supportati: mono o stereo. Non sono supportati "+nCanali+" canali","Parametri Non Validi",JOptionPane.ERROR_MESSAGE);
    }
    
    public void setBitDepth(int bitDepth){
        if(bitDepth==8||bitDepth==16){
            this.bitDepth=bitDepth;
            bitZero=(int)(Math.pow(2,bitDepth)/2-1);
        }
        else
            JOptionPane.showMessageDialog(null,"BithDepth supportate: 8 e 16. Non sono supportate BitDepth di "+bitDepth+" bit","Parametri Non Validi",JOptionPane.ERROR_MESSAGE);

    }
    
    public int getSampleRate() { return sampleRate; }
    
    public int getNCanali() { return nCanali; }
    
    public int getBitDepth() { return bitDepth; }
    
    public int getBitZero() { return bitZero; }
    
    public void copiaParametri(ParametriAudio parametri){
        setSampleRate(parametri.getSampleRate());
        setBitDepth(parametri.getBitDepth());
        setNCanali(parametri.getNCanali());
    }

    @Override
    public String toString(){
        return "Sample Rate: "+sampleRate+", Canali: "+nCanali+", Bit Depth: "+bitDepth;
    }
}
