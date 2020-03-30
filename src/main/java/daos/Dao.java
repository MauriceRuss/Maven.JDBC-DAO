package daos;

import models.Car;

import java.util.List;

public interface Dao {
    public Car findById(int id);
    public List findAll();
    public Car update(Car car);
    public Car create(Car car);
    public void delete(int id);
}
