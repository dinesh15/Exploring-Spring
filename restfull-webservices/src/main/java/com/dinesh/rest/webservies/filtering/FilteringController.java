package com.dinesh.rest.webservies.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filterbeans")
    public MappingJacksonValue filteredBeans(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        String[] strings = {"field1", "field2"};
        String someBeanFilter = "SomeBeanFilter";
        return applyFilter(SimpleBeanPropertyFilter.filterOutAllExcept(strings), someBeanFilter, mappingJacksonValue);
//        return mappingJacksonValue;
    }

    private MappingJacksonValue applyFilter(SimpleBeanPropertyFilter strings, String someBeanFilter, MappingJacksonValue mappingJacksonValue) {
        SimpleBeanPropertyFilter filter = strings;
        FilterProvider filters = new SimpleFilterProvider().addFilter(someBeanFilter, filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filterbeansArray")
    public MappingJacksonValue filteredBeansArray(){
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value1", "value2", "value3"), new SomeBean("value1", "value2", "value3"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        return applyFilter(SimpleBeanPropertyFilter.filterOutAllExcept("field2"), "SomeBeanFilter", mappingJacksonValue);
//        return mappingJacksonValue;
    }

}
