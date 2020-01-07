package com.example.demo.filtering;

import com.example.demo.pojos.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean(){
      SomeBean someBean = new SomeBean("value1","value2","value3");
       // FilterProvider filters = new simpleFilterProvider;
       // MappingJacksonValue mapping = new MappingJacksonValue(someBean);
       // mapping.setFilters(filters);
        return someBean;
    }
}
