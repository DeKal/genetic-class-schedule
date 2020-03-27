package com.dekal.scheduling.algorithm;

import com.dekal.scheduling.config.Config;
import com.dekal.scheduling.entity.Schedule;

import java.util.List;

public class SelectAlgorithm {

    private boolean isCrossOver() {
        return Math.random() > 0.5;
    }

    Schedule pickCrossOverSchedule(Schedule schedule1, Schedule schedule2) {
        if (isCrossOver()) {
            return schedule1;
        }
        return schedule2;
    }

    private boolean isMutate() {
        return Config.MUTATION_RATE > Math.random();
    }

    Schedule pickScheduleIfMutated(Schedule schedule) {
        return isMutate() ? schedule : null;
    }

    public <T> T pickRandom(List<T> list) {
        int randomPos = (int) (list.size() * Math.random());
        return list.get(randomPos);
    }

}
