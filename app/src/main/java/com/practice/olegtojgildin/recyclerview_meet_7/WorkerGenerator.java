package com.practice.olegtojgildin.recyclerview_meet_7;

/**
 * Created by olegtojgildin on 07/01/2019.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorkerGenerator {
    private static List<String> femaleNames = new ArrayList<>(Arrays.asList("Anna", "Emma", "Sophie", "Jessica", "Scarlett", "Molly", "Lucy", "Megan"));
    private static List<String> surnames = new ArrayList<>(Arrays.asList("Green", "Smith", "Taylor", "Brown", "Wilson", "Walker", "White", "Jackson", "Wood"));
    private static List<String> positions = new ArrayList<>(Arrays.asList("Android programmer", "iOs programmer", "Web programmer", "Designer"));

    public static Worker generateWorker() {
        Worker worker = new Worker();
        Random randomGenerator = new Random();
        int index;

        index = randomGenerator.nextInt(femaleNames.size());
        String randomName = femaleNames.get(index);
        index = randomGenerator.nextInt(surnames.size());
        String randomSurname = surnames.get(index);
        worker.setName(randomName + " " + randomSurname);
        worker.setPhoto(R.drawable.ic_male_black);
        worker.setType(randomGenerator.nextInt(3));
        worker.setAge(Integer.toString(20 + randomGenerator.nextInt(10)));
        index = randomGenerator.nextInt(positions.size());
        worker.setPosition(positions.get(index));
        return worker;
    }

    public static List<Worker> generateWorkers(int workersCount) {
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < workersCount; i++) {
            workers.add(generateWorker());
        }
        return workers;
    }
}
