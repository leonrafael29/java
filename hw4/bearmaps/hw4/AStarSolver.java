package bearmaps.hw4;
import java.util.List;
import java.util.HashMap;
import java.util.*;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private HashMap<Vertex, Double> disTo;
    private HashMap<Vertex, List<Vertex>> edgeTo;
    private HashSet<Vertex> vertexVisited;


    public SolverOutcome outcome;
    public List<Vertex> solution;
    public double solutionWeight;
    public int statesExplored;
    public double timeSpent;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        DoubleMapPQ<Vertex> pq = new DoubleMapPQ<>();
        disTo = new HashMap<>();
        solution = new ArrayList<>();


        pq.add(start, input.estimatedDistanceToGoal(start, end));

        disTo.put(start, 0.0);
        statesExplored = 0;

        do {
            Vertex p = pq.getSmallest();
            solution.add(p);
            statesExplored++;

            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(p);

            //loop over the neighbors of p
            for (WeightedEdge<Vertex> e : neighborEdges) {
                Vertex t = e.from();
                Vertex q = e.to();
                double w = e.weight();
                double distanceTo = disTo.get(t) + w;
                double priority = distanceTo + input.estimatedDistanceToGoal(q, end);

                if (!disTo.containsKey(q)) {
                    disTo.put(q, distanceTo);
                    pq.add(q, priority);

                }

                if (distanceTo < disTo.get(q)) {
                    disTo.replace(q, distanceTo);
                    pq.changePriority(q, priority);
                }
            }

            if (p.equals(end) & sw.elapsedTime() < timeout) {
                this.outcome = SolverOutcome.SOLVED;
                break;
            }

            if (pq.size() == 0) {
                this.outcome = SolverOutcome.UNSOLVABLE;
                break;
            }
            if (sw.elapsedTime() >= timeout) {
                this.outcome = SolverOutcome.TIMEOUT;
                break;
            }


        } while (pq.size() > 0);
        this.timeSpent = sw.elapsedTime();



    }
    @Override
    public SolverOutcome outcome() {
        return this.outcome;

    }
    @Override
    public List<Vertex> solution() {
        return this.solution;

    }
    @Override
    public double solutionWeight() {
        return this.solutionWeight;

    }
    @Override
    public int numStatesExplored() {
        return this.statesExplored;

    }

    @Override
    public double explorationTime() {
        return this.timeSpent;

    }
}
