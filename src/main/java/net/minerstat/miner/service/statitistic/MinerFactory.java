package net.minerstat.miner.service.statitistic;

import net.minerstat.miner.enums.MinerTypes;
import net.minerstat.miner.service.statitistic.claymore.Claymore;

public class MinerFactory implements AbstractFactory {

    @Override
    public Algorithm getMiner(MinerTypes type) {
        Algorithm algorithm;
        switch (type) {
            case CLAYMORE_DUAL_ETH:
            default:
                algorithm = new Claymore();
                break;

//            case 1:
//                algorithm = new Sgminer();
//                break;
//
//            case 2:
//                algorithm = new Cgminer();
//                break;
//
//            case 3:
//                algorithm = new Bfgminer();
//                break;
        }

        return algorithm;
    }
}
