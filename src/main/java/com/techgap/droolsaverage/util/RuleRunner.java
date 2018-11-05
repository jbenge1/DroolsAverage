package com.techgap.droolsaverage.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleRunner {

	private static final String PATH = System.getProperty("java.io.tmpdir")+File.separator;
	
	@Autowired
	private AssetDAO assetDao;
	
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
            	if(st.startsWith("#"))
            		continue;
            	else {
	                String[] temp = st.split(",");

	                ArrayList<java.math.BigDecimal> temp_arr = new ArrayList<>();
	                temp_arr.add(new java.math.BigDecimal(temp[1]));
	                temp_arr.add(new java.math.BigDecimal(temp[2]));
	                temp_arr.add(new java.math.BigDecimal(temp[3]));
	                temp_arr.add(new java.math.BigDecimal(temp[4]));

	                org.kie.api.runtime.rule.FactHandle handle = session.insert(temp_arr);

	                org.kie.api.runtime.rule.FactHandle handle1 = session.insert(temp_arr.get(0));
	                org.kie.api.runtime.rule.FactHandle handle2 = session.insert(temp_arr.get(1));
	                org.kie.api.runtime.rule.FactHandle handle3 = session.insert(temp_arr.get(2));
	                org.kie.api.runtime.rule.FactHandle handle4 = session.insert(temp_arr.get(3));
	                
	                session.fireAllRules(1);	              
	                
	                session.delete(handle);
	                session.delete(handle1);
	                session.delete(handle2);
	                session.delete(handle3);
	                session.delete(handle4);
	                
	                assetDao.addEmployee(temp_arr, Integer.parseInt(month), Integer.parseInt(year), temp[0]);
	                
            	}
            }   
            reader.close();
		} catch (Exception e) {System.err.println(e.getMessage());}
	}
	
	public void fireRulesHashMap2(String fileName1, String fileName2, String month, String year) {
		try {
            if(!fileName2.contains(".drl"))
            	fileName2 += ".drl";
            KieSession session = getKieSession(PATH + fileName2);
			
			if(!fileName1.contains(".csv"))
				fileName1 += ".csv";
            File file = new File(PATH + fileName1);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st;
            
            //Pretty strait forward
            java.math.BigDecimal totHWorkedInSidip = new java.math.BigDecimal(0);
            //The list representing the list of employees
            ArrayList<ArrayList<java.math.BigDecimal>> employee_list = new ArrayList<>();
            //This loop calculates totHWorkedInSidip
            while((st = reader.readLine()) != null){
            	if(st.startsWith("#"))
            		continue;
            	else {
	                String[] temp = st.split(",");
	                //This list represents a single employee
	                ArrayList<java.math.BigDecimal> temp_arr = new ArrayList<>();
	                temp_arr.add(new java.math.BigDecimal(temp[1]));
	                temp_arr.add(new java.math.BigDecimal(temp[2]));

	                org.kie.api.runtime.rule.FactHandle handle = session.insert(temp_arr);
	                org.kie.api.runtime.rule.FactHandle handle1 = session.insert(totHWorkedInSidip);
	                
	                session.fireAllRules(1);	              
	                
	                session.delete(handle);
	                session.delete(handle1);
	                
	                employee_list.add(temp_arr);
//	                assetDao.addEmployee(temp_arr, Integer.parseInt(month), Integer.parseInt(year), temp[0]);
	                
            	}
            }   
            reader.close();
            
            //Now lets calculate all other values for employees
            for(ArrayList<java.math.BigDecimal> employee: employee_list) {
            	org.kie.api.runtime.rule.FactHandle handle = session.insert(employee);
            	org.kie.api.runtime.rule.FactHandle handle1 = session.insert(totHWorkedInSidip.doubleValue());
                
                session.fireAllRules(1);
                session.delete(handle);
                session.delete(handle1);
            }
            //Now calculate max(totEffect)
            java.math.BigDecimal totEffect = new java.math.BigDecimal(0);
            for(ArrayList<java.math.BigDecimal> employee: employee_list) {
            	org.kie.api.runtime.rule.FactHandle handle = session.insert(totEffect);
            	org.kie.api.runtime.rule.FactHandle handle1 = session.insert(employee);
            	
            	session.fireAllRules(1);
            	
            	session.delete(handle);
            	session.delete(handle1);
            }
            
            //Finally calculate the final KPI Value
            for(ArrayList<java.math.BigDecimal> employee: employee_list) {
            	org.kie.api.runtime.rule.FactHandle handle = session.insert(employee);
            	org.kie.api.runtime.rule.FactHandle handle1 = session.insert(totEffect.doubleValue());
            	org.kie.api.runtime.rule.FactHandle handle2 = session.insert(true);
            	
            	session.fireAllRules();
            	
            	session.delete(handle);
            	session.delete(handle1);
            	session.delete(handle2);
            	assetDao.addEmployee(employee, Integer.parseInt(month), Integer.parseInt(year));
            }
		} catch (Exception e) {System.err.println(e.getMessage());}
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
		return kieContainer.newKieSession();
	}
}