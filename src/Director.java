
public class Director {
    
    public MesajEmail build(AbstractBuilder ab, String ad, String s, String c){
        ab.addAdresaDestinatar(ad);
        ab.addSubiect(s);
        ab.addContinut(c);
        return ab.getMesaj();
    }
}
