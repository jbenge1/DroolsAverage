package ruleEngineTest;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.techgap.droolsaverage.util.AssetDAO;
import com.techgap.droolsaverage.util.RuleRunner;

public class TestRule {

	RuleRunner ruleRunner = new RuleRunner();
	
	@Autowired
    private AssetDAO assetDao;
	
	@Test
    public void testSimpleRule() throws Exception {
        ruleRunner.fireRulesHashMap2(
                Paths.get("meritogame-input-easy.csv"),
                Paths.get("src/main/rules/Meritome_Game_Rules_Easy.drl"), "1", "2018");
        
        List<ArrayList<Object>> employees;
   	 	employees = assetDao.getEmployeesListAdmin(1,2018);
   	 	
   	 	for(ArrayList<Object> employee: employees) {
   	 		assertEquals((int)employee.get(0), 10);
   	 		assertEquals((int)employee.get(0), 15);
   	 	}
	}
	

}
