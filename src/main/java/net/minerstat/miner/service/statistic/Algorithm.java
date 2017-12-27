package net.minerstat.miner.service.statistic;

import net.minerstat.miner.entity.Worker;

public interface Algorithm {

    /**
     * Start point to execute algorithm.
     */
    Worker parseAlgorithm(Worker worker, Object data);

}
