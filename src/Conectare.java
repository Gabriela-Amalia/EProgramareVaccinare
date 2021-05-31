import java.sql.*;

public class Conectare {//aplica modelul de proiectare singleton
    private static Conectare instanta;//=null;
    private Connection c;
    private Statement s;

    private Conectare(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            c = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/amali/Desktop/OOP2-Facultate/BazaDeDateProgramareVaccin.accdb");
            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("A fost facut conexiunea la bd.");
        }catch (SQLException e){
            System.out.println("Eroare din conectare bd: "+e.getMessage());
        }
        catch (ClassNotFoundException e){
            System.out.println("Eroare bd: "+e.getMessage());
        }
    }

    public static Conectare getInstanta(){
        if(instanta == null) instanta = new Conectare();
        return instanta;
    }

    public Statement getStatement(){
        return s;
    }

    public Connection getConexiune(){
        return c;
    }

    public void inchide(){
        try{
            s.close();
            c.close();
        }catch (SQLException e){
            System.out.println("Eroare la inchidere bd: "+e.getMessage());
        }
    }

    public DatabaseMetaData getMetaData() throws SQLException
    {
        return c.getMetaData();
    }
}
