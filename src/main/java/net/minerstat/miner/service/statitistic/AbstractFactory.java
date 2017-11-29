package net.minerstat.miner.service.statitistic;

import net.minerstat.miner.enums.MinerTypes;

public interface AbstractFactory {
    Algorithm getMiner(MinerTypes type);

}
