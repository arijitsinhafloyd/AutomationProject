@tag
Feature: Purchase the order from ECommerce
	I want to use this template as feature file
	
	Background:
	Given I have landed on Ecommerce page
	
 @Regression
 Scenario Outline: Positive test of Purchasing the order
 	Given Logged in with <username> and <password>
 	When I have added any <productName> from Ecommerce site
 	And checkout the <productName> from Cart
 	Then my order should be placed and message would come as "Thankyou for the order."
 	
 	Examples:
 		|username               |password   |productName   |
 		|contact@arijitsinha.com|Asdf@1234  |IPHONE 13 PRO |
