package com.techgap.DroolsAverage.DroolsAverage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;

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
	
	
//	public void runRules(Employee employee) {
//		try {
//
//			KnowledgeBase base = readKnowledgeBase("Ruleset3.drl");
//			StatefulKnowledgeSession session = base.newStatefulKnowledgeSession();
//
//			@SuppressWarnings("unused")
//			String cwd = System.getProperty("user.dir");
//			
//			FactHandle handle = session.insert(employee);
//			session.fireAllRules();
//			session.retract(handle);
//			assetDao.addEmployee(employee);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void fireRules(String fileName1, String fileName2, String month, String year) {
		try {
            if(!fileName2.contains(".drl"))
            	fileName2 += ".drl";
            KieSession session = getKieSession(PATH + fileName2);
//			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(session, "test");
			
//			@SuppressWarnings("unused")
//			String cwd = System.getProperty("user.dir");
			
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
//          
//            empList.sort(Comparator.comparing(Employee::getPerformance1));
//            System.out.println("Performance 1 metrics");
//            System.out.println("____________________________");
//            printEmpList(empList,true);
//            
//            System.out.println();
//            
//            empList.sort(Comparator.comparing(Employee::getPerformance2));
//            System.out.println("Performance 2 metrics");
//            System.out.println("____________________________");
//            printEmpList(empList,false);
//			logger.close();
			
		} catch (Exception e) {e.printStackTrace();}
	}

	
	
	/**
	 * 
	 * @param empList
	 * @param flag
	 */
//	private static void printEmpList(List<Employee> empList, boolean flag) {
//		int j=1;
//        int tempI = empList.size()-1;
//        int tempJ = tempI - 11;
//        for(int i=tempI;i>tempJ;i--) {
//        	System.out.print((j++)+".) " + empList.get(i));
//        	if(flag)
//        		System.out.println(empList.get(i).getPerformance1());
//        	else
//        		System.out.println(empList.get(i).getPerformance2());
//        	
//        }
//	}
	
	/**
	 * 
	 * @param ruleSet
	 * @return
	 * @throws Exception
	 */
//	private static KnowledgeBase readKnowledgeBase(String ruleSet) throws Exception {
//		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//		builder.add(ResourceFactory.newClassPathResource(ruleSet), ResourceType.DRL);
//		KnowledgeBuilderErrors errors = builder.getErrors();
//		if (errors.size() > 0) {
//			for (KnowledgeBuilderError error : errors)
//				System.err.println(error);
//			throw new IllegalArgumentException("Could not parse knowledge :(");
//		}
//		KnowledgeBase base = KnowledgeBaseFactory.newKnowledgeBase();
//		base.addKnowledgePackages(builder.getKnowledgePackages());
//		return base;
//	}
	
	
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
