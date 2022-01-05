package org.chelck.dp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table {
    private final int numberOfPhilosophers;
    private final Set<Integer> usedForks;
    private final Map<Integer, Set<Integer>> philosophersWithForks;

    public Table(int numberOfPhilosophers) {
        this.numberOfPhilosophers = Objects.requireNonNull(numberOfPhilosophers);
        this.usedForks = new HashSet<>();
        this.philosophersWithForks = new HashMap<>();

        IntStream.range(0, numberOfPhilosophers).forEach(p -> this.philosophersWithForks.put(p, new HashSet<>()));
    }

    public Optional<Integer> getLeftFork(int philosopher) {
        assert philosopher >= 0 && philosopher < numberOfPhilosophers : "philosopher is " + philosopher;
        int leftFork = philosopher;

        if (usedForks.contains(leftFork) || isLastPhilosopher("Use left fork", philosopher)) {
            return Optional.empty();
        }
        else {
            usedForks.add(leftFork);
            philosophersWithForks.get(philosopher).add(leftFork);
            isLastPhilosopher("Use left fork", philosopher);
            return Optional.of(leftFork);
        }
    }

    public Optional<Integer> getRightFork(int philosopher) {
        assert philosopher >= 0 && philosopher < numberOfPhilosophers : "philosopher is " + philosopher;
        int rightFork = (philosopher + 1) % numberOfPhilosophers;

        if (usedForks.contains(rightFork) || isLastPhilosopher("Use right fork", philosopher)) {
            return Optional.empty();
        }
        else {
            usedForks.add(rightFork);
            philosophersWithForks.get(philosopher).add(rightFork);
            isLastPhilosopher("Use right fork", philosopher);
            return Optional.of(rightFork);
        }
    }

    public void returnLeftFork(int philosopher) {
        assert philosopher >= 0 && philosopher < numberOfPhilosophers : "philosopher is " + philosopher;

        int leftFork = philosopher;
        usedForks.remove(leftFork);
        philosophersWithForks.get(philosopher).remove(leftFork);
    }

    public void returnRightFork(int philosopher) {
        assert philosopher >= 0 && philosopher < numberOfPhilosophers : "philosopher is " + philosopher;
        int rightFork = (philosopher + 1) % numberOfPhilosophers;
        usedForks.remove(rightFork);
        philosophersWithForks.get(philosopher).remove(rightFork);
    }

    private boolean isLastPhilosopher(String comment, int philosopher) {
        List<Integer> philosophersWithNoForks = this.philosophersWithForks.entrySet().stream()
                .filter(e -> e.getValue().isEmpty())
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        //System.out.println("CJH no forks: " + philosophersWithNoForks);

        return philosophersWithNoForks.size() == 1 && philosophersWithNoForks.contains(philosopher);    }
}
