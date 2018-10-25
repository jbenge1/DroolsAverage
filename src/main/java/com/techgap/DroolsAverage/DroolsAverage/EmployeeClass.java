package com.techgap.DroolsAverage.DroolsAverage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

public class EmployeeClass {

	@Service
	@Scope("request")
	public static class Employee {
        private java.math.BigDecimal performance1, performance2, performance3,performance4,performanceTotal;
//		private int weeklyHours, numDaysWorked;
//		private double average, rate, totalPay;
		private int calculate = 0;
//		private int totalHours;
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

		public Employee(String name,java.math.BigDecimal perf1, java.math.BigDecimal perf2, java.math.BigDecimal perf3, java.math.BigDecimal perf4) {
        	this.code = code;
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


//        public int getTotalHours(){
//            return this.totalHours;
//        }
//
//        public void setTotalHours(int totalHours){
//           this.totalHours = totalHours;
//        } 

        public int getCalculate(){
            return this.calculate;
        }

        public void setCalculate(int calculate) {
            this.calculate = calculate;
        }

//		public int getWeeklyHours() {
//			return this.weeklyHours;
//		}
//		
//		public void setWeeklyHours(int weeklyHours) {
//			this.weeklyHours = weeklyHours;
//		}
//		
//		public int getNumDaysWorked() {
//			return this.numDaysWorked;
//		}
//		
//		public void setNumDaysWorked(int numDaysWorked) {
//			this.numDaysWorked = numDaysWorked;
//		}
//		
//		public void setAverage(double average) {
//			this.average = average;
//		}
//		
//		public double getAverage() {
//			return this.average;
//		}
//
        public java.math.BigDecimal getPerformance1(){
            return this.performance1;
        }

        public void setPerformance1(java.math.BigDecimal performance){
            this.performance1 = performance;
        }

        public java.math.BigDecimal getPerformance2(){
            return this.performance2;
        }

        public void setPerformance2(java.math.BigDecimal performance){
            this.performance2 = performance;
        }
//
//        public void setRate(double rate) {
//        	this.rate = rate;
//        }
//        
//        public double getRate() {
//        	return this.rate;
//        }
//        
//        public double getTotalPay() {
//        	return this.totalPay;
//        }
//        
//        public void setTotalPay(double totalPay) {
//        	this.totalPay = totalPay;
//        }
        
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
