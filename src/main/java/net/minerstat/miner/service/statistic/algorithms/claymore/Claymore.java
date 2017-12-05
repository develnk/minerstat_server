package net.minerstat.miner.service.statistic.algorithms.claymore;

import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.entity.WorkerStat;
import net.minerstat.miner.entity.WorkerStatDetail;
import net.minerstat.miner.entity.WorkerStatDetailGPU;
import net.minerstat.miner.service.statistic.Algorithm;
import net.minerstat.miner.service.statistic.MinerCommon;
import org.bouncycastle.util.io.Streams;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class Claymore extends MinerCommon implements Algorithm {

    public Claymore() {}

    @Override
    public Worker parseAlgorithm(Worker worker, JSONObject data) {
        ArrayList all_gpu = (ArrayList) data.get("all_gpu");
        LinkedHashMap eth_total = (LinkedHashMap) data.get("eth_total");
        LinkedHashMap dec_total = (LinkedHashMap) data.get("dec_total");
        int workTime = Integer.parseInt(data.get("work_time").toString());
        worker.setWorkerStat(calcWorkerStat(all_gpu, eth_total, dec_total, workTime, worker.getWorkerStat()));
        worker.setWorkerStatDetail(calcWorkerStatDetails(eth_total, dec_total, worker.getWorkerStatDetail()));
        worker.setWorkerStatDetailGPU(calcWorkerStatDetailGPU(all_gpu, worker));
        return worker;
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
        workerStat.setOneHash(Integer.parseInt(eth.get("total_hash").toString()));
        workerStat.setTwoHash(Integer.parseInt(dec.get("total_hash").toString()));
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
        workerStatDetail.setOneHash(Integer.parseInt(eth.get("total_hash").toString()));
        workerStatDetail.setOneNumber(Integer.parseInt(eth.get("number").toString()));
        workerStatDetail.setOneRejected(Integer.parseInt(eth.get("rejected").toString()));
        workerStatDetail.setOneInvalid(Integer.parseInt(eth.get("invalid").toString()));

        // Two coin.
        workerStatDetail.setTwoHash(Integer.parseInt(dec.get("total_hash").toString()));
        workerStatDetail.setTwoNumber(Integer.parseInt(dec.get("number").toString()));
        workerStatDetail.setTwoRejected(Integer.parseInt(dec.get("rejected").toString()));
        workerStatDetail.setTwoInvalid(Integer.parseInt(dec.get("invalid").toString()));

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
            detailGPU.setOneHash(Integer.parseInt(allGpu.get(i).get("eth_hash").toString()));
            detailGPU.setTwoHash(Integer.parseInt(allGpu.get(i).get("dec_hash").toString()));
            detailGPU.setTemp(Integer.parseInt(allGpu.get(i).get("temp").toString()));
            detailGPU.setFanSpeed(Integer.parseInt(allGpu.get(i).get("fan_speed").toString()));

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
}
