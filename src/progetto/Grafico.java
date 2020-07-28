package progetto;

import java.awt.*;
import javax.swing.*;

    public class Grafico extends JPanel{
        
        private int xZoom;
        DatiAudio datiAudio;
        private int posizioneBarra;
        
        public Grafico(DatiAudio datiAudio){
            xZoom=1;
            posizioneBarra=0;
            this.datiAudio=datiAudio;
        }
        
        public int getXZoom(){ return xZoom; }
        
        public void setXZoom(int xZoom) { 
            if(xZoom<1) xZoom=1;
            this.xZoom=xZoom; 
        }
        
        public int getScrollBarPointerSize(){
            double temp=(double)datiAudio.campioni.size()/(double)xZoom;
            double rapporto=temp/(double)datiAudio.campioni.size();
            return  (int)(rapporto*100);           
        }
        
        public void setPosizioneBarra(int posizioneBarra){
            this.posizioneBarra=posizioneBarra;
        }
        
        public int mouseXToIndice(int mouseX){
            int indice=mouseX-10;
            int larghezza=getWidth();
            int porzione=(datiAudio.campioni.size()-1)/xZoom;
            double xRatio=(double)(larghezza-20)/(porzione);
            indice=(int)Math.floor(indice/xRatio);     
            
            int divisore=100-getScrollBarPointerSize();
            int offsetCampioni=((datiAudio.campioni.size()-porzione)/(divisore==0?1:divisore))*posizioneBarra; 
            indice+=offsetCampioni;
            
            if(indice>=datiAudio.campioni.size()) indice=datiAudio.campioni.size()-1;

            return indice;
        }
        
        public int indiceToMouseX(int indice){
            int mouseX=indice;
            int larghezza=getWidth();
            int porzione=(datiAudio.campioni.size()-1)/xZoom;
            double xRatio=(double)(larghezza-20)/(porzione);               
            
            int divisore=100-getScrollBarPointerSize();
            int offsetCampioni=((datiAudio.campioni.size()-porzione)/(divisore==0?1:divisore))*posizioneBarra; 
            mouseX-=offsetCampioni;
            mouseX=(int)(10+mouseX*xRatio); 

            return mouseX;
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            setBackground(Color.WHITE);
            
            int altezza=getHeight();
            int larghezza=getWidth();        
            g.setColor(Color.BLACK);
            g.drawLine(10,altezza/2,larghezza-10,altezza/2);
            
            if(datiAudio.campioni.size()>0){
                int porzione=(datiAudio.campioni.size()-1)/xZoom;
                int divisore=100-getScrollBarPointerSize();
                int offsetCampioni=((datiAudio.campioni.size()-porzione)/(divisore==0?1:divisore))*posizioneBarra;
                double xRatio=(double)(larghezza-20)/(porzione);
                double yRatio=(double)(altezza-20)/(2*(datiAudio.parametri.getBitZero()+1));
                //int offset=(int)((so.getBitZero())*yRatio);
                //if(so.getBitDepth()==16)

                g.setColor(Color.BLUE);
                for(int i=0;i<porzione;i++){
                    if(i+offsetCampioni+1<datiAudio.campioni.size()){
                            g.drawLine((int) (10+i*xRatio),(int)((altezza/2)+(int)(yRatio*datiAudio.campioni.get(i+offsetCampioni).left)),
                                    (int) (10+(i+1)*xRatio),(int)((altezza/2)+(int)(yRatio*datiAudio.campioni.get(i+1+offsetCampioni).left)));
                    }
                }
                g.setColor(Color.RED);
                for(int i=0;i<porzione;i++){
                    if(i+offsetCampioni+1<datiAudio.campioni.size()){
                            g.drawLine((int) (10+i*xRatio),(int)((altezza/2)+(int)(yRatio*datiAudio.campioni.get(i+offsetCampioni).right)),
                                    (int) (10+(i+1)*xRatio),(int)((altezza/2)+(int)(yRatio*datiAudio.campioni.get(i+1+offsetCampioni).right)));
                    }
                } 
                if(datiAudio.selezione.isSelected()){
                    g.setColor(Color.BLACK);
                    g.drawLine(indiceToMouseX(datiAudio.selezione.getLimiteInf()),0,indiceToMouseX(datiAudio.selezione.getLimiteInf()),altezza);
                    g.drawLine(indiceToMouseX(datiAudio.selezione.getLimiteSup()),0,indiceToMouseX(datiAudio.selezione.getLimiteSup()),altezza);
                    g.setColor(Color.YELLOW);
                    g.fillRect(indiceToMouseX(datiAudio.selezione.getLimiteInf())+1,0,(int)((datiAudio.selezione.getLimiteSup()-datiAudio.selezione.getLimiteInf())*xRatio-1),10);
                }
                else{
                     g.setColor(Color.BLACK);
                     if(datiAudio.selezione.notEmpty()){
                        g.drawLine(indiceToMouseX(datiAudio.selezione.getLimiteSup()),0,indiceToMouseX(datiAudio.selezione.getLimiteSup()),altezza);
                     }
                }
            }
        }
    }
