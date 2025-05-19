# 🚀 MotorPH Employee App – GUI Version

This project is the Graphical User Interface (GUI) implementation of the **MotorPH Employee App**. It is developed based on the company’s class diagram and integrates the approved change request **MPHCR-01** to transition from a console-based to a GUI-based application.

---

## 📌 Project Objectives

- Convert the original **console-based MotorPH Employee App** into a **GUI-based version** using Java Swing.
- Implement **exception handling** for user input, particularly:
  - **Employee ID** (must be a valid integer)
  - **Pay Coverage** (must be a positive number)

---

## 🛠 Features

- Interactive Swing-based GUI form
- Basic salary computation
- Tax calculation with a 12% tax rate
- Payslip generation with net salary
- Validation of input fields using `try-catch` blocks and error messages

---

## 📂 Project Structure

src/
├── MotorPHGUI.java # GUI form with all Swing components
├── Employee.java # Employee data model class
├── Tax.java # Tax calculation logic
├── Payroll.java # Payroll computation logic

---

## ✅ Sample Usage

**Input:**
- Employee ID: `101`
- Pay Coverage (days): `20`

**Output:**

Employee ID: 101
Position: Developer
Pay Days: 20
Gross Salary: ₱10000.0
Tax: ₱1200.0
Net Salary: ₱10800.0


---

## 📋 Technologies Used

- Java SE
- Java Swing (GUI)
- NetBeans IDE 12/13/18+

---

## 📎 How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/ayoumax/GROUP5
   cd motorph-employee-gui

   Open the project in NetBeans IDE.

Ensure MotorPHGUI.java is set as the main class:

Right-click the project → Properties → Run → Main Class → MotorPHGUI

Click Run Project (F6) to launch the GUI.

🔄 Change Request Reference
This project is based on the approved change MPHCR-01 from the Change Request Compilation:

DESCRIPTION OF CHANGE:
Provision of a more intuitive and interactive interface in the MotorPH Employee App by implementing a version of the application that has a Graphical User Interface.

COMMENTS:

Convert the console-based application to a working GUI-based application.

Provide exception handling for the input of Employee Number and Pay Coverage.
