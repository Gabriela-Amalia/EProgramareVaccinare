import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerZone {

    private static Conectare c = Conectare.getInstanta();

    public static String[] getJudete(){
        String[] judete = new String[42];
        Statement st = c.getStatement();
        int i = 0;
        try {
            String comandaSQL = "Select * from judete order by denjudet";
            ResultSet rs = st.executeQuery(comandaSQL);
            while (rs.next()){
                judete[i] = rs.getString(2);
                i++;
            }
        }catch (SQLException e){e.printStackTrace();}
        return judete;
    }

    public static String getLocalitati(int id){
        Statement st = c.getStatement();
        StringBuffer sb = new StringBuffer();
        try {
            String comandaSQL = "Select nume from localitati where idjudet="+id+" order by nume";
            ResultSet rs = st.executeQuery(comandaSQL);
            while (rs.next()){
                sb.append(rs.getString(1)+"@");
            }

        }catch (SQLException e){e.printStackTrace();}
        return sb.toString();
    }

    public static String getDate(int id){
        Statement st = c.getStatement();
        StringBuffer sb = new StringBuffer();
        try {
            String comandaSQL = "Select data\n" +
                    "from datevaccinare inner join centrevaccinare on datevaccinare.centru=centrevaccinare.id where centrevaccinare.id ="+id+";";
            ResultSet rs = st.executeQuery(comandaSQL);
            while (rs.next()){
                sb.append(rs.getString(1).substring(0,10)+"@");
            }

        }catch (SQLException e){e.printStackTrace();}
        return sb.toString();
    }

    public static String getCentre(int id){
        Statement st = c.getStatement();
        StringBuffer sb = new StringBuffer();
        try {
            String comandaSQL = "Select dencentru from centrevaccinare where judet="+id+" order by dencentru";
            ResultSet rs = st.executeQuery(comandaSQL);
            while (rs.next()){
                sb.append(rs.getString(1)+"@");
            }

        }catch (SQLException e){e.printStackTrace();}
        return sb.toString();
    }

    public static int getIdJudet(String denumire){
        Statement st = c.getStatement();
        StringBuffer sb = new StringBuffer();
        try {
            String comandaSQL = "Select id from judete where denjudet like \""+denumire+"\"";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()) return Integer.parseInt(rs.getString(1));

        }catch (SQLException e){e.printStackTrace();}
        return 0;
    }

    public static int getIdCentru(String denumire){
        Statement st = c.getStatement();
        StringBuffer sb = new StringBuffer();
        try {
            String comandaSQL = "Select id from centrevaccinare where dencentru like \""+denumire+"\"";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()) return Integer.parseInt(rs.getString(1));

        }catch (SQLException e){e.printStackTrace();}
        return 0;
    }

    public static int getIdLocalitate(String denumire){
        Statement st = c.getStatement();
        StringBuffer sb = new StringBuffer();
        try {
            String comandaSQL = "Select id from localitati where nume like \""+denumire+"\";";
            ResultSet rs = st.executeQuery(comandaSQL);
            if(rs.next()) return Integer.parseInt(rs.getString(1));

        }catch (SQLException e){e.printStackTrace();}
        return 0;
    }




}
