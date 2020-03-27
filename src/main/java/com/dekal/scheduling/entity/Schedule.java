package com.dekal.scheduling.entity;

import java.util.List;

public class Schedule {
    private List<Class> classes;
    private int conflictNum = -1;
    private double fitness = -1;
    public Schedule(List<Class> classes) {
        this.classes = classes;
    }

    public List<Class> getClasses() {
        return classes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int classIndex = 0; classIndex < classes.size() - 1; ++classIndex) {
            stringBuilder.append(classes.get(classIndex));
            stringBuilder.append(',');
        }
        stringBuilder.append(classes.get(classes.size() - 1));
        return stringBuilder.toString();
    }

    public int getConflictNum() {
        return conflictNum;
    }

    public void setConflictNum(int conflictNum) {
        this.conflictNum = conflictNum;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
