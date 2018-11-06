package com.techgap.droolsaverage.model;

import org.apache.commons.csv.CSVRecord;
import org.kie.api.definition.type.Modifies;

import java.util.HashMap;
import java.util.Map;

public class EmployeeForDrools {
    private CSVRecord record;
    private Map<String, Double> kpiStore;

    public EmployeeForDrools(CSVRecord record) {
        this.record = record;
        this.kpiStore = new HashMap<>();
    }

    public CSVRecord getRecord() {
        return record;
    }

    public void setRecord(CSVRecord record) {
        this.record = record;
    }

    public Map<String, Double> getKpiStore() {
        return kpiStore;
    }

    public void setKpiStore(Map<String, Double> kpiStore) {
        this.kpiStore = kpiStore;
    }

    @Modifies("kpiStore")
    public void setKpi(String kpi, Double value) {
        this.kpiStore.put(kpi, value);
    }

    @Override
    public String toString() {
        return "EmployeeForDrools{" +
                "record=" + record.getRecordNumber() +
                ", kpiStore=" + kpiStore +
                '}';
    }
}
