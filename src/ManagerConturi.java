import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerConturi {
    private static Conectare c = Conectare.getInstanta();

    public static int existaCont(String email, String parola){
        int cont = 0;
        Statement st = c.getStatement();
        try {
            String comandaSQL = "Select * from Cont where email like \""+email+"\" and parola like \""+parola+"\"";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()){
                cont = Integer.parseInt(rs.getString(1));
            }
        }catch (SQLException e){e.printStackTrace();}
        return cont;
    }

    public static void memoreazaCont(String email, String parola){
        Statement st = c.getStatement();

        try {
            String comandaSQL = "insert into cont (Email, Parola) values(\""+email+"\",\""+parola+"\");";
            st.executeUpdate(comandaSQL);

        }catch (SQLException e){e.printStackTrace();}
    }

    public static void memoreazaProfil(String nume, String prenume, String cnp, String serie, String numar, String judet, String localitate, int cont){
        Statement st = c.getStatement();
        try {
            String comandaSQL = "insert into profil (nume, prenume, cnp, serie, numar, judet, localitate, contid) "+
                    "values (\""+nume+"\",\""+prenume+"\",\""+cnp+"\",\""+serie+"\",\""+numar+"\" ,"+ManagerZone.getIdJudet(judet)+", "+ManagerZone.getIdLocalitate(localitate)+", "+ cont+");";
            System.out.println(comandaSQL);
            st.executeUpdate(comandaSQL);

        }catch (SQLException e){e.printStackTrace();}
    }

    public static int getIdCont(String email, String parola){
        Statement st = c.getStatement();
        try {
            String comandaSQL = "Select id from cont where email like \""+email+"\" and parola like \""+parola+"\";";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()) return Integer.parseInt(rs.getString(1));

        }catch (SQLException e){e.printStackTrace();}
        return 0;
    }

    public static String getProfilFromCont(int contId){
        Statement st = c.getStatement();
        try {
            String comandaSQL = "Select * from profil where contid = " + contId + ";";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()) return "Nume: "+rs.getString(2)+"\n"+"Prenume: "+rs.getString(3)+"\n"+"CNP: "+rs.getString(4)+"\n"+"Serie buletin: "+rs.getString(5)+"\n"+"Numar buletin: "+rs.getString(6)+"\n";//sau de 1
        }catch (Exception e){e.printStackTrace();}
        return "";
    }
}
