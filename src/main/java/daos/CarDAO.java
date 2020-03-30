package daos;

import models.Car;

import java.sql.*;
import java.util.ArrayList;
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
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from Car;");
            List<Car> cars = new ArrayList<Car>();
            while (resultSet.next()) {
                Car car = extractCarFromResultSet(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;

    }

    public Car update(Car car) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("update Car set make = ?, model = ?, year = ?, color = ?, price =? where id = ?");
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setDouble(5, car.getPrice());
            preparedStatement.setInt(6, car.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return car;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Car create(Car car) {
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into Car VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1, car.getId());
            preparedStatement.setString(2, car.getMake());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4,car.getYear());
            preparedStatement.setString(5,car.getColor());
            preparedStatement.setDouble(6, car.getPrice());
            int i = preparedStatement.executeUpdate();
            if(i == 1){
                return car;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {
        try
        {
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate("delete from Car where id =" + id);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

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
