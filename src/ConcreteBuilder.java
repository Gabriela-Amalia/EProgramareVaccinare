
public class ConcreteBuilder extends AbstractBuilder {
    private MesajEmail mesaj;
    
    public ConcreteBuilder(){
        mesaj=new MesajEmail();
    }
     public void addAdresaDestinatar(String d){
         mesaj.setEmailAddress(d);
     }
    public void addSubiect(String s){
        mesaj.setSubject(s);
    }
    public void addContinut(String c){
        mesaj.setMessageEmail(c);
    }
public  void addUsernamePwd(String u, String p){
    mesaj.setUsername(u);
    mesaj.setPassword(p);
}
    public MesajEmail getMesaj(){
        return mesaj;
    }
}
