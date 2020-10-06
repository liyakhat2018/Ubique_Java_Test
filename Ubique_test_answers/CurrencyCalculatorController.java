package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CurrencyService;
import com.example.demo.vo.CurrencyBean;

@RestController
@RequestMapping(path="/api")
public class CurrencyCalculatorController {
	
    @Autowired
    CurrencyService currencyService;

    
    @PostMapping("/currcalc")
    public ResponseEntity<Object> distinctCurrencyAmount(@RequestBody List<CurrencyBean> input) {
    	
    	Map<String, Integer> map = currencyService.getCurrencyAmount(input);
        
        if (null != map) {
        	return ResponseEntity.status(HttpStatus.OK).body(map);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update as it contains the same body");
        }
    }

}
