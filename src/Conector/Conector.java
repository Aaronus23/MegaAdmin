package Conector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author aaronus23
 */
public final class Conector {
     public java.sql.Connection conexion;
     public java.sql.Statement sentencia;
     public java.sql.ResultSet cdr;
     public String DbDateFormat(String sentence) {
        StringBuilder sb = new StringBuilder(sentence.length() + 1);
        String[] words = sentence.split("/");
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append('-');
        }
        sb.setLength(sb.length() - 1);  // Strip trailing space
        return sb.toString();
    }
    public Conector() throws ClassNotFoundException,java.sql.SQLException,InstantiationException,IllegalAccessException {
        String controlador="com.mysql.jdbc.Driver";
        Class.forName(controlador).newInstance();
        conectar();
    }
    public void conectar() throws java.sql.SQLException {
        String URL_bd = "jdbc:mysql://localhost:3306/megagraphicsDB";
        String usuario="root";
        String password="";
        //Conectar con la BD
        conexion=java.sql.DriverManager.getConnection(URL_bd,usuario,password);
    }
    public void cerrarConexion() throws java.sql.SQLException {
        if(cdr!=null) cdr.close();
        if(sentencia!=null) sentencia.close();
        if(conexion!=null) conexion.close();
    }
    public DefaultTableModel buildTableModel(String consulta, Vector<String> colNames) throws SQLException {
    // names of columns
        Vector<String> columnNames = new Vector<>();
        cdr=sentencia.executeQuery(consulta);
        for (int column = 0; column < colNames.size(); column++) {
            columnNames.add(colNames.get(column));
        }
       Vector<Vector<Object>> data = new Vector<>();
       while (cdr.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <=colNames.size(); columnIndex++) 
                vector.add(cdr.getObject(columnIndex));
        data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
    public void Buscar(String query) throws SQLException{
        sentencia=conexion.createStatement();
        cdr=sentencia.executeQuery(query);
    }
    public void Insertar(String query) throws SQLException {
            sentencia=conexion.createStatement();
            sentencia.executeUpdate(query);
    }
    
}
