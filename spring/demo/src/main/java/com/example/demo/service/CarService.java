package com.example.demo.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepo;

@Service
public class CarService {
    private CarRepo carRepo;

    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }
    
    public boolean postall(Car product)
    {
        try{
            carRepo.save(product);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public List<Car> getall()
    {
        return carRepo.findAll();
    }

    public Car getallId(int product_id)
    {
        return carRepo.findById(product_id).orElse(null);
    }

    public List<Car> getSortedList(String field) {
        return carRepo.findAll(Sort.by(Sort.Direction.DESC, field));
    }

     public List<Car> getPageList(int offset, int pagesize) {
        Page<Car> k = carRepo.findAll(PageRequest.of(offset, pagesize));
        return k.getContent();
    }
}

