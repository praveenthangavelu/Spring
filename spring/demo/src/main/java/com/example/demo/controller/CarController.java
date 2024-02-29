package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/car")
    public ResponseEntity<Car> postMethodName(@RequestBody Car product) {
        if(carService.postall(product) == true)
        {
            return new ResponseEntity<>(product,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(product,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping("/car")
    public ResponseEntity<List<Car>> getMethodName() {
        List<Car> k = carService.getall();
        if(k.size()==0)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(k,HttpStatus.CREATED);
    }
    @GetMapping("/car/{product_id}")
    public ResponseEntity<Car> getMethodNameId(@PathVariable int product_id) {
        Car k = carService.getallId(product_id);
        if(k==null)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(k,HttpStatus.CREATED);
    }

    @GetMapping("/sortBy/{field}")
    public List<Car> sortByRate(@PathVariable("field") String field) {
        return carService.getSortedList(field);
    }

    @GetMapping("/pagination/{offset}/{pagesize}")
    public List<Car> getMethodName1(@PathVariable("offset") int offset,@PathVariable("pagesize") int pagesize) {
        return carService.getPageList(offset,pagesize);
    }
    
}


