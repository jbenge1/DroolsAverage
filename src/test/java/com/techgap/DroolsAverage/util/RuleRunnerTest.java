package com.techgap.DroolsAverage.util;

import com.techgap.droolsaverage.util.RuleRunner;
import org.junit.Test;

import java.nio.file.Paths;

public class RuleRunnerTest {
    RuleRunner ruleRunner = new RuleRunner();

    @Test
    public void testStuff() throws Exception {
        ruleRunner.fireRulesHashMap2(
                Paths.get("meritogame-input.csv"),
                Paths.get("src/main/rules/Meritome_Game_Rules_GCO.drl"), "1", "2018");
    }

}
