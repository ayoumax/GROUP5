MotorPH Employee App – GUI-Based Version

📋 Project Description
This is the Graphical User Interface (GUI) implementation of the MotorPH Employee App based on the class diagram designed in Lab Work #1 and enhanced through the change request MPHCR-01.

This version provides:

A user-friendly interface for employees and payroll processing.

Input validation and exception handling for Employee ID and Pay Coverage.

Simple salary computation including tax deduction and net pay display.

🛠 Features Implemented
GUI built using Java Swing.

Input validation for:

Employee Number (must be a valid integer)

Pay Coverage (must be a positive number)

Salary computation including:

Gross Salary

Tax Calculation (12% by default)

Net Salary

Error messages displayed using try-catch blocks.

📦 Technologies Used
Java SE

Java Swing (for GUI)

NetBeans IDE

git clone https://github.com/ayoumax/GROUP5/
cd motorph-gui-app
Open in NetBeans IDE or any Java IDE.

Set MotorPHGUI as the main class in project properties.

Run the project (F6 or right-click → Run).

🧪 Sample Test Inputs
Input Field	Sample Value
Employee ID	101
Pay Coverage (days)	22

✅ Sample Output
yaml
Copy
Edit
Employee ID: 101
Position: Developer
Pay Days: 22
Gross Salary: ₱11000.0
Tax: ₱1320.0
Net Salary: ₱11780.0
🧾 Change Request Reference
This update implements MPHCR-01:

Converted console-based MotorPH Employee App to a GUI application.

Added exception handling for Employee ID and Pay Coverage inputs.
