package com.techgap.droolsaverage.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

public class EmployeeClass {

	private EmployeeClass() {}
	
	@Service
	@Scope("request")
	public static class Employee {
        private java.math.BigDecimal performance1, performance2, performance3,performance4,performanceTotal;
		private int calculate = 0;
        private String name;
        private int code;
        private int month, year;

        public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public Employee(java.math.BigDecimal perf1, java.math.BigDecimal perf2, java.math.BigDecimal perf3, java.math.BigDecimal perf4) {
        	this.performance1 = perf1;
        	this.performance2 = perf2;
        	this.performance3 = perf3;
        	this.performance4 = perf4;
        	
        }

        public Employee(){}

        public String getName(){
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCalculate(){
            return this.calculate;
        }

        public void setCalculate(int calculate) {
            this.calculate = calculate;
        }
        
        public void setPerformance3(java.math.BigDecimal perf) {
        	this.performance3 = perf;
        }
        
        public void setPerformance4(java.math.BigDecimal perf) {
        	this.performance4 = perf;
        }
        
        public void setPerformanceTotal(java.math.BigDecimal perf) {
        	this.performanceTotal = perf;
        }
        
        public java.math.BigDecimal getPerformance3() {
        	return this.performance3;
        }
        
        public java.math.BigDecimal getPerformance4() {
        	return this.performance4;
        }
        
        public java.math.BigDecimal getPerformanceTotal() {
        	return this.performanceTotal;
        }
        
        public void setCode(int code) {
        	this.code = code;
        }
        
        public int getCode() {
        	return this.code;
        }
        @Override
        public String toString() {
        	return "Performance1=" + this.performance1+ " Performance2="+this.performance2+ " Performance3="+this.performance3+ " Performance4="+this.performance4 + " month="+this.month + " year="+this.year;
        }

	}
}
