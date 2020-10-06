package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.vo.CurrencyBean;

@Service
public class CurrencyService {
	public Map<String, Integer> getCurrencyAmount(List<CurrencyBean> list) {
		System.out.println();
		
		Map<String, Integer> sum = list.stream().collect(Collectors.groupingBy(CurrencyBean::getCurrency, Collectors.summingInt(CurrencyBean::getAmount)));
	    sum.entrySet().forEach(x -> list.add(new CurrencyBean(x.getKey(),x.getValue())));
		
	    return sum;
	}
}

