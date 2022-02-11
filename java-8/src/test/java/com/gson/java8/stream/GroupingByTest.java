package com.gson.java8.stream;

import com.gson.java8.model.City;
import com.gson.java8.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupingByTest {
    @Test
    public void testGroup(){
        List<Person> personList = new ArrayList<>();

        City shangHai = new City(1, "上海");
        City hangzhou = new City(2, "杭州");

        Person person1 = new Person();
        person1.setLastName("chen");
        person1.setCity(shangHai);

        Person person2 = new Person();
        person2.setLastName("feng");
        person2.setCity(hangzhou);

        Person person3 = new Person();
        person3.setLastName("jie");
        person3.setCity(hangzhou);

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        Map<City, List<Person>> cityPerson = personList.stream().collect(Collectors.groupingBy(Person::getCity));
        System.out.println(cityPerson);

        // 如果需要 City-->List<PersonLastName>呢
        Map<City, Set<String>> city2PersonName = personList.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.mapping(Person::getLastName, Collectors.toSet())));
        System.out.println(city2PersonName);

        // 如果需要 CityName-->List<PersonLastName>呢
        Map<String, Set<String>> cityName2PersonName = personList.stream().collect(Collectors.groupingBy(person->person.getCity().getCityName(), Collectors.mapping(Person::getLastName, Collectors.toSet())));
        System.out.println(cityName2PersonName);
    }

}
