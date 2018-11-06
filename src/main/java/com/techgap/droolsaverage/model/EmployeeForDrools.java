package com.techgap.droolsaverage.model;

import org.apache.commons.csv.CSVRecord;
import org.kie.api.definition.type.Modifies;

import java.util.HashMap;
import java.util.Map;

public class EmployeeForDrools {
    private CSVRecord csv;
    private Map<String, Double> kpiStore;

    public EmployeeForDrools(CSVRecord csv) {
        this.csv = csv;
        this.kpiStore = new HashMap<>();
    }

    public CSVRecord getCsv() {
        return csv;
    }

    public void setCsv(CSVRecord csv) {
        this.csv = csv;
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
                "csv=" + csv.getRecordNumber() +
                ", kpiStore=" + kpiStore +
                '}';
    }
}
