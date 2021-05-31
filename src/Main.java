import javax.swing.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    private static Conectare c = Conectare.getInstanta();
    public static void main(String[] args) {
        Statement st = c.getStatement();
        try {
            String comandaSQL = "insert into programari (centru, data, idcont) "+
                    "values ("+2+","+"#2021-05-07#"+","+26+");";
            System.out.println(comandaSQL);
            st.executeUpdate(comandaSQL);

        }catch (SQLException e){e.printStackTrace();}

    }

}
