
public abstract class AbstractBuilder {
    private static AbstractBuilder ab;
    
    public static AbstractBuilder getInstance(){
        if(ab==null) ab = new ConcreteBuilder();
        return ab;
    }
    
    public abstract void addAdresaDestinatar(String d);
    public abstract void addSubiect(String s);
    public abstract void addContinut(String c);
    public abstract void addUsernamePwd(String u, String p);

    public abstract MesajEmail getMesaj();

}
