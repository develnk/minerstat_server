package net.minerstat.miner.service.statistic;

import net.minerstat.miner.enums.MinerTypes;

public interface Factory {
    Algorithm getMiner(MinerTypes type);

}
