package daos;

import models.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CarDAO implements Dao {
    Connection conn;


    public Car findById(int id) {
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Car WHERE Id=" + id);
            if(resultSet.next())
                return  extractCarFromResultSet(resultSet);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List findAll() {
        return null;
    }

    public Car update(Car car) {
        return null;
    }

    public Car create(Car car) {
        return null;
    }

    public void delete(int id) {

    }
    private Car extractCarFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId( resultSet.getInt("id") );
        car.setMake( resultSet.getString("make") );
        car.setModel( resultSet.getString("model") );
        car.setYear( resultSet.getInt("year") );
        car.setColor(resultSet.getString("color"));
        car.setPrice(resultSet.getDouble("price"));
        return car;
    }
}
