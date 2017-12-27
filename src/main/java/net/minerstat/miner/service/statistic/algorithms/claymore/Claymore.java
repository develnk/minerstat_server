package net.minerstat.miner.service.statistic.algorithms.claymore;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.entity.WorkerStat;
import net.minerstat.miner.entity.WorkerStatDetail;
import net.minerstat.miner.entity.WorkerStatDetailGPU;
import net.minerstat.miner.service.statistic.Algorithm;
import net.minerstat.miner.service.statistic.MinerCommon;
import org.bouncycastle.util.io.Streams;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class Claymore extends MinerCommon implements Algorithm {

    public Claymore() {}

    @Override
    public Worker parseAlgorithm(Worker worker, Object data) {
        LinkedHashMap data_convert = (LinkedHashMap) data;
        HashMap tcp = (HashMap) data_convert.get("tcp");
        if (checkMinimumParse(tcp)) {
            ArrayList all_gpu = (ArrayList) tcp.get("all_gpu");
            LinkedHashMap eth_total = (LinkedHashMap) tcp.get("eth_total");
            LinkedHashMap dec_total = (LinkedHashMap) tcp.get("dec_total");
            int workTime = Integer.parseInt(tcp.get("work_time").toString());
            worker.setWorkerStat(calcWorkerStat(all_gpu, eth_total, dec_total, workTime, worker.getWorkerStat()));
            worker.setWorkerStatDetail(calcWorkerStatDetails(eth_total, dec_total, worker.getWorkerStatDetail()));
            worker.setWorkerStatDetailGPU(calcWorkerStatDetailGPU(all_gpu, worker));
        }
        return worker;
    }

    private boolean checkMinimumParse(HashMap data) {
        return (data != null && data.get("all_gpu") != null && data.get("eth_total") != null
                && data.get("dec_total") != null
                && data.get("work_time") != null);
    }

    /**
     *
     * @param allGpu
     * @param eth
     * @param dec
     * @param workTime
     * @param workerStat
     * @return
     */
    private WorkerStat calcWorkerStat(ArrayList allGpu, LinkedHashMap eth, LinkedHashMap dec, int workTime, WorkerStat workerStat) {
        if (workerStat == null) {
            workerStat = new WorkerStat();
        }
        workerStat.setUpTime(workTime);
        workerStat.setDevAmount(allGpu.size());

        workerStat.setOneHash(getNumber(eth, "total_hash"));
        workerStat.setTwoHash(getNumber(dec, "total_hash"));
        workerStat.setAverageRate(0);
        return workerStat;
    }

    /**
     *
     * @param eth
     * @param dec
     * @param workerStatDetail
     * @return
     */
    private WorkerStatDetail calcWorkerStatDetails(LinkedHashMap eth, LinkedHashMap dec, WorkerStatDetail workerStatDetail) {
        if (workerStatDetail == null) {
            workerStatDetail = new WorkerStatDetail();
        }

        // One coin.
        workerStatDetail.setOneHash(getNumber(eth, "total_hash"));
        workerStatDetail.setOneNumber(getNumber(eth, "number"));
        workerStatDetail.setOneRejected(getNumber(eth, "rejected"));
        workerStatDetail.setOneInvalid(getNumber(eth, "invalid"));

        // Two coin.
        workerStatDetail.setTwoHash(getNumber(dec, "total_hash"));
        workerStatDetail.setTwoNumber(getNumber(dec, "number"));
        workerStatDetail.setTwoRejected(getNumber(dec, "rejected"));
        workerStatDetail.setTwoInvalid(getNumber(dec, "invalid"));

        return workerStatDetail;
    }

    private List<WorkerStatDetailGPU> calcWorkerStatDetailGPU(ArrayList<HashMap> allGpu, Worker worker) {
        List<WorkerStatDetailGPU> workerStatDetailGPU = worker.getWorkerStatDetailGPU();
        if (workerStatDetailGPU == null) {
            workerStatDetailGPU = new ArrayList<>();
        }

        for (int i = 0; i < allGpu.size(); i++) {
            final int ii = i;
            Map<Integer, WorkerStatDetailGPU> listDetailGPU = workerStatDetailGPU.stream().filter(p -> p.getGpuIndex() == ii).collect(Collectors.toMap(workerStatDetailGPU::indexOf, c -> c));
            WorkerStatDetailGPU detailGPU = (listDetailGPU.size() != 0) ? listDetailGPU.get(listDetailGPU.keySet().toArray()[0]) : new WorkerStatDetailGPU();
            detailGPU.setGpuIndex(i);
            detailGPU.setOneHash(getNumber(allGpu.get(i), "eth_hash"));
            detailGPU.setTwoHash(getNumber(allGpu.get(i), "dec_hash"));
            detailGPU.setTemp(getNumber(allGpu.get(i), "temp"));
            detailGPU.setFanSpeed(getNumber(allGpu.get(i), "fan_speed"));

            if (detailGPU.getId() == null) {
                detailGPU.setWorkerId(worker.getId());
                workerStatDetailGPU.add(detailGPU);
            }
            else {
                int key = listDetailGPU.keySet().stream().findFirst().get();
                workerStatDetailGPU.set(key, detailGPU);
            }
        }

        return workerStatDetailGPU;
    }

    private Integer getNumber(HashMap data, String field_name) {
        return (data.get(field_name) != null &&  !data.get(field_name).toString().equals("")) ? Integer.parseInt(data.get(field_name).toString()) : 0;
    }
}
