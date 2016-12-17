package factories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionFactory {

    private static ConnectionFactory instance;
    private Connection connection;
    private Properties properties;

    private ConnectionFactory() {
        this.properties=new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Sodium\\Downloads\\DataBaseSemestr\\src\\main\\resources\\connection.properties"));
            String url = properties.getProperty("jdbc.url");
            String user=properties.getProperty("jdbc.name");
            String ps = properties.getProperty("jdbc.password");
            String driverName= properties.getProperty("jdbc.driver");
            Class.forName(driverName);
            connection= DriverManager.getConnection(url,user,ps);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class not found");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connection");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static  {
        instance = new ConnectionFactory();
    }
    public static ConnectionFactory getInstance() {
        return instance;
    }
    public Connection getConnection() {
//        System.out.println("Returned connection");
        return this.connection;
    }
}
