package net.minerstat.miner.service.statistic;

import net.minerstat.miner.entity.Worker;
import org.json.simple.JSONObject;

public interface Algorithm {

    /**
     * Start point to execute algorithm.
     */
    Worker parseAlgorithm(Worker worker, JSONObject data);

}
