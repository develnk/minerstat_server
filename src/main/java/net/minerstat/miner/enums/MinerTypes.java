package net.minerstat.miner.enums;

public enum MinerTypes {

    CLAYMORE_DUAL_ETH(0),
    BFGMINER(1),
    SGMINER(2),
    XMR_STACK_CPU(3);

    public final int value;

    MinerTypes(int value) {
        this.value = value;
    }

    public static MinerTypes setValue( int value){
        switch(value){
            case 0: return MinerTypes.CLAYMORE_DUAL_ETH;
            case 1: return MinerTypes.BFGMINER;
            case 2: return MinerTypes.SGMINER;
            case 3: return MinerTypes.XMR_STACK_CPU;
            default:
                return MinerTypes.CLAYMORE_DUAL_ETH;
        }
    }
}