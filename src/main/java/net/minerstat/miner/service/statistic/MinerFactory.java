package net.minerstat.miner.service.statistic;

import net.minerstat.miner.enums.MinerTypes;
import net.minerstat.miner.service.statistic.algorithms.claymore.Claymore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinerFactory implements Factory {

    @Autowired(required=false)
    private Claymore claymore;

    @Override
    public Algorithm getMiner(MinerTypes type) {
        Algorithm algorithm;
        switch (type) {
            case CLAYMORE_DUAL_ETH:
            default:
                algorithm = claymore;
                break;

//            case SGMINER:
//                algorithm = new Sgminer();
//                break;

//            case XMR_STACK_CPU:
//                algorithm = new XMRminer();
//                break;

//            case BFGMINER:
//                algorithm = new Bfgminer();
//                break;
        }

        return algorithm;
    }
}
