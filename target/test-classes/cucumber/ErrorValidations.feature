@tag
Feature: Error Validations in the E commerce site
		I want to use this template as feature file
		
	
@ErrorValidation
Scenario Outline: Negative cases with wrong credentials to show Error Validation message
Given I have landed on Ecommerce page
When Logged in with <username> and <password> 
Then Error message with "Incorrect" word should pop out

Examples:
|username                |password       |
|contact@arijitsinha.com |Amkkkksdf@1234 |
