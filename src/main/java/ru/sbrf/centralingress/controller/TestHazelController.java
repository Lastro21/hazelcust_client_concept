package ru.sbrf.centralingress.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestHazelController {

    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public TestHazelController(@Qualifier("customHazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @RequestMapping(value = "/hello2")
    public int rrt() {

        IMap<Integer, String> map = hazelcastInstance.getMap("myMap");

        map.put(1, "Vasya");
        map.put(2, "Petya");
        map.put(3, "Kolya");
        map.put(4, "Kolya");
        map.put(5, "Kolya");
        map.put(6, "Kolya");
        map.put(7, "Kolya");

        System.out.println("Map size: " + map.size());
        String name = map.remove(6);
        System.out.println("Map size: " + map.size());

        return map.size();
    }

}
