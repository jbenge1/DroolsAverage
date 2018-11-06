package com.techgap.droolsaverage.util;

import com.techgap.droolsaverage.model.EmployeeForDrools;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RuleRunner {

    @Autowired
    private AssetDAO assetDao;

    public List<CSVRecord> parseCsv(Path csvFileName) throws IOException {
        CSVParser parser = CSVParser.parse(csvFileName, StandardCharsets.UTF_8, CSVFormat.EXCEL.withFirstRecordAsHeader());
        return parser.getRecords();
    }

    public void fireRulesHashMap2(Path inputFileName, Path ruleFileName, String month, String year) {
        try {
            KieSession session = getKieSession(ruleFileName);
            List<CSVRecord> records = parseCsv(inputFileName);
            List<EmployeeForDrools> employeeForDrools = records.stream().map(EmployeeForDrools::new).collect(Collectors.toList());
            for (EmployeeForDrools employeeForDrool : employeeForDrools) {
                session.insert(employeeForDrool);
            }
            session.fireAllRules();
            for (EmployeeForDrools employeeForDrool : employeeForDrools) {
                List<Double> kpis = new ArrayList<>(4);
                final Map<String, Double> computedKpis = employeeForDrool.getKpiStore();
                kpis.add(computedKpis.getOrDefault("kpi1", 0.0));
                kpis.add(computedKpis.getOrDefault("kpi2", 0.0));
                kpis.add(computedKpis.getOrDefault("kpi3", 0.0));
                kpis.add(computedKpis.getOrDefault("kpi4", 0.0));
                kpis.add(computedKpis.getOrDefault("kpiTot", 0.0));
                assetDao.addEmployee(kpis, Integer.parseInt(month), Integer.parseInt(year), employeeForDrool.getCsv().get("EmployeeID"));
            }
            session.destroy();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @param ruleset
     * @return
     */
    private static KieSession getKieSession(Path ruleset) {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        org.kie.api.io.Resource resource = kieServices.getResources().newFileSystemResource(ruleset.toFile()).setResourceType(ResourceType.DRL);
        kfs.write(resource);

        KieBuilder Kiebuilder = kieServices.newKieBuilder(kfs);
        Kiebuilder.buildAll();
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        return kieContainer.newKieSession();
    }
}
