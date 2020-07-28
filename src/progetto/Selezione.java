package progetto;

public class Selezione {
    private int limite_inf;
    private int limite_sup;
    
    public Selezione(){
        limite_inf=-1;
        limite_sup=-1;
    }
    
    public Selezione(int limite_inf,int limite_sup){
        if(limite_inf<limite_sup){
            this.limite_inf=limite_inf;
            this.limite_sup=limite_sup;
        }
        else{
            this.limite_inf=limite_sup;
            this.limite_sup=limite_inf;
        }
    }
    
    public void annullaSelezione(){
        limite_inf=-1;
        limite_sup=-1;
    }
    
    public void setLimiteInf(int limite){
        if(limite<0) limite=0;
        limite_inf=limite;
        if(limite_inf>limite_sup){
            int temp=limite_sup;
            limite_sup=limite_inf;
            limite_inf=temp;
        }
        if(limite_inf==limite_sup) annullaSelezione();
    }
    
    public void setLimiteSup(int limite){
        if(limite<0) limite=0;
        limite_sup=limite;
        if(limite_inf>limite_sup){
            int temp=limite_sup;
            limite_sup=limite_inf;
            limite_inf=temp;
        }
        if(limite_inf==limite_sup) annullaSelezione();
    }
    
    public int getLimiteInf(){
        return limite_inf;
    }
    
    public int getLimiteSup(){
        return limite_sup;
    }
    
    public boolean isSelected(){
        boolean selezione;
        if(limite_sup==limite_inf||limite_inf==-1) selezione=false;
        else selezione=true;      
        return selezione;
    }
    
    public boolean notEmpty(){
        boolean empty;
        if(limite_sup!=-1||limite_inf!=-1) empty=true;
        else empty=false;
        return empty;
    }
    
    @Override
    public String toString(){
        String stringa;
        if(limite_sup==limite_inf||limite_inf==-1) stringa="Nessuna selezione";
        else stringa="Selezione da "+limite_inf+" a "+limite_sup;
        return stringa;
    }
}
