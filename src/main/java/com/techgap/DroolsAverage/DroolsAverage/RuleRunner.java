package com.techgap.DroolsAverage.DroolsAverage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.List;
import java.util.HashMap;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
//import org.drools.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.drools.KnowledgeBase;
//import org.drools.KnowledgeBaseFactory;
//import org.drools.builder.KnowledgeBuilder;
//import org.drools.builder.KnowledgeBuilderError;
//import org.drools.builder.KnowledgeBuilderErrors;
//import org.drools.builder.KnowledgeBuilderFactory;
//import org.drools.builder.ResourceType;
//import org.drools.io.ResourceFactory;
//import org.drools.logger.KnowledgeRuntimeLogger;
//import org.drools.logger.KnowledgeRuntimeLoggerFactory;
//import org.drools.runtime.StatefulKnowledgeSession;
//
//import com.sample.KieBuilder;
//import com.sample.KieContainer;
//import com.sample.KieFileSystem;
//import com.sample.KieServices;
//import com.sample.KieSession;
import com.techgap.DroolsAverage.DroolsAverage.EmployeeClass.Employee;

@Service
public class RuleRunner {

//	private final String PATH = "/home/justin/Documents/Misc/";
	private final String PATH = System.getProperty("java.io.tmpdir")+"/";
	
	@Autowired
	private AssetDAO assetDao;
	
	/**
	 * 
	 * @param fileName1
	 * @param fileName2
	 * @param month
	 * @param year
	 */
	public void fireRules(String fileName1, String fileName2, String month, String year) {
		try {
            if(!fileName2.contains(".drl"))
            	fileName2 += ".drl";
            KieSession session = getKieSession(PATH + fileName2);
			
			if(!fileName1.contains(".csv"))
				fileName1 += ".csv";
            File file = new File(PATH + fileName1);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st;
            
            while((st = reader.readLine()) != null){
            	if(st.startsWith("#"))
            		continue;
            	else {
	                String[] temp = st.split(",");
	                Employee emp = new Employee(temp[0],  new java.math.BigDecimal(temp[1]),new java.math.BigDecimal(temp[2]), new java.math.BigDecimal(temp[3]), new java.math.BigDecimal(temp[4]));
	                org.kie.api.runtime.rule.FactHandle handle = session.insert(emp);
	                session.fireAllRules();
	                session.delete(handle);
	                assetDao.addEmployee(emp, Integer.parseInt(month), Integer.parseInt(year));
            	}
            }   
            reader.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	/**
	 * 
	 * @param fileName1
	 * @param fileName2
	 * @param month
	 * @param year
	 */
	public void fireRulesHashMap(String fileName1, String fileName2, String month, String year) {
		try {
            if(!fileName2.contains(".drl"))
            	fileName2 += ".drl";
            KieSession session = getKieSession(PATH + fileName2);
			
			if(!fileName1.contains(".csv"))
				fileName1 += ".csv";
            File file = new File(PATH + fileName1);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st;
            
            while((st = reader.readLine()) != null){
            	System.out.println(st);
            	if(st.startsWith("#"))
            		continue;
            	else {
	                String[] temp = st.split(",");
//	                Employee emp = new Employee(temp[0],  new java.math.BigDecimal(temp[1]),new java.math.BigDecimal(temp[2]), new java.math.BigDecimal(temp[3]), new java.math.BigDecimal(temp[4]));

	                ArrayList<java.math.BigDecimal> temp_arr = new ArrayList<java.math.BigDecimal>();
	                temp_arr.add(new java.math.BigDecimal(temp[1]));
	                temp_arr.add(new java.math.BigDecimal(temp[2]));
	                temp_arr.add(new java.math.BigDecimal(temp[3]));
	                temp_arr.add(new java.math.BigDecimal(temp[4]));

	                org.kie.api.runtime.rule.FactHandle handle = session.insert(temp_arr);

	                org.kie.api.runtime.rule.FactHandle handle1 = session.insert(temp_arr.get(0));
	                org.kie.api.runtime.rule.FactHandle handle2 = session.insert(temp_arr.get(1));
	                org.kie.api.runtime.rule.FactHandle handle3 = session.insert(temp_arr.get(2));
	                org.kie.api.runtime.rule.FactHandle handle4 = session.insert(temp_arr.get(3));
	                System.out.println(temp_arr);
	                
	                session.fireAllRules(1);	              
	                
	                session.delete(handle);
	                session.delete(handle1);
	                session.delete(handle2);
	                session.delete(handle3);
	                session.delete(handle4);
	                
	                assetDao.addEmployee(temp_arr, Integer.parseInt(month), Integer.parseInt(year));
	                temp_arr = null;
            	}
            }   
            reader.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	/**
	 * 
	 * @param ruleset
	 * @return
	 */
	private static KieSession getKieSession(String ruleset) {
		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kfs = kieServices.newKieFileSystem();
		File file = new File(ruleset);
		org.kie.api.io.Resource resource = kieServices.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
		kfs.write(resource);
	
		KieBuilder Kiebuilder = kieServices.newKieBuilder(kfs);
		Kiebuilder.buildAll();
		KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		KieSession ksession = kieContainer.newKieSession();
		return ksession;
	}
}
