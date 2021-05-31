import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerProgramari {
    private static Conectare c = Conectare.getInstanta();

    public static void memoreazaProgramare(int idCentru, String data, int idcont){
        Statement st = c.getStatement();
        try {
            String comandaSQL = "insert into programari (centru, data, idcont) "+
                    "values ("+idCentru+",#"+data+"#,"+idcont+");";
            System.out.println(comandaSQL);
            st.executeUpdate(comandaSQL);

        }catch (SQLException e){e.printStackTrace();}
    }

    public static String existaProgramare(int idCont){
        Statement st = c.getStatement();
        try {
            String comandaSQL = "Select * from Programari where idcont=" + idCont +";";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()) return rs.getString(2)+"@"+rs.getString(3);
        }catch (SQLException e){e.printStackTrace(); }
        return "";
    }

    public static boolean stergeProgramare(int idCont){
        Statement st = c.getStatement();
        try {
            String comandaSQL = "Select * from Programari where idcont=" + idCont +";";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()) rs.deleteRow();
            System.out.println("Programarea a fost stearsa");
            return true;
        }catch (SQLException e){e.printStackTrace(); }
        System.out.println("Eroare");
        return false;
    }

    public static boolean stergeDataVaccin(int idCentru, String data){
        Statement st = c.getStatement();
        try {
            String comandaSQL = "Select * from DateVaccinare where centru=" + idCentru +" and data like \""+data+"\";";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()) rs.deleteRow();
            System.out.println("Programarea a fost stearsa");
            return true;
        }catch (SQLException e){e.printStackTrace(); }
        System.out.println("Eroare");
        return false;
    }

    public static void memoreazaDateVaccin(int idCentru, String data){
        Statement st = c.getStatement();
        try {
            String comandaSQL = "insert into DateVaccinare (centru, data) "+
                    "values ("+idCentru+",\""+data+"\");";
            System.out.println(comandaSQL);
            st.executeUpdate(comandaSQL);

        }catch (SQLException e){e.printStackTrace();}
    }


}
