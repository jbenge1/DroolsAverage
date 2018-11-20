package ruleEngineTest;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techgap.droolsaverage.util.AssetDAO;
import com.techgap.droolsaverage.util.RuleRunner;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class TestRule {

	private RuleRunner ruleRunner = new RuleRunner();
	private AssetDAO assetDao; 

	@Before
	public void init() {
		SingleConnectionDataSource ds = new SingleConnectionDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost/droolsTestDB");
//		ds.setUrl("jdbc:postgresql://db/droolsTestDB");
		ds.setUsername("postgres");
		ds.setPassword("postgres");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		
		assetDao = new AssetDAO(jdbcTemplate);
	}
	
	
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
