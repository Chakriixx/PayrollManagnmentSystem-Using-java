import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayrollManagementSystemGUI extends JFrame {
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Database connection parameters
    private final String URL = "jdbc:mysql://localhost:3306/payrollmanagement";
    private final String USER = "root";
    private final String PASSWORD = "chakri";

    public PayrollManagementSystemGUI() {
        setTitle("Payroll Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Initialize components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Create a GridBagConstraints instance
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = GridBagConstraints.CENTER;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);

        // Add components to the frame
        add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1; // Update the gridx value for nameField
        add(nameField, gridBagConstraints);

        gridBagConstraints.gridx = 0; // Reset gridx value for passwordLabel
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        add(passwordLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1; // Update the gridx value for passwordField
        add(passwordField, gridBagConstraints);

        gridBagConstraints.gridx = 0; // Reset gridx value for loginButton
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.gridwidth = 2; // Span across two columns
        add(loginButton, gridBagConstraints);

        // Add action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        pack();
        setVisible(true);
    }

    // Handle the login functionality
    private void login() {
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate the login credentials
        if (name.equals("name") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login successful!");

            // Open the main dashboard frame
            openMainDashboard();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    // Open the main dashboard frame after successful login
    private void openMainDashboard() {
        JFrame mainDashboardFrame = new JFrame("Payroll Management System - Main Dashboard");
        mainDashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainDashboardFrame.setLayout(new GridBagLayout());

        // Create a label for Main Dashboard
        JLabel mainDashboardLabel = new JLabel("Main Dashboard");
        mainDashboardLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create a panel to hold the components
        JPanel mainDashboardPanel = new JPanel();
        mainDashboardPanel.setLayout(new GridBagLayout());

        // Add the mainDashboardLabel to the panel
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.anchor = GridBagConstraints.CENTER;
        labelConstraints.insets = new Insets(10, 0, 20, 0); // Adjust top padding

        mainDashboardPanel.add(mainDashboardLabel, labelConstraints);

        // Initialize components for main dashboard
        JButton addEmployeeButton = new JButton("Add New Employee");
        JButton modifyEmployeeButton = new JButton("Modify Employee Record");
        JButton deleteEmployeeButton = new JButton("Delete Employee Record");
        JButton displayEmployeeButton = new JButton("Display Employee Record");
        JButton displayEmployeesListButton = new JButton("Display List of Employees");

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        // Add buttons to the button panel
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = GridBagConstraints.RELATIVE;
        buttonConstraints.anchor = GridBagConstraints.CENTER;
        buttonConstraints.insets = new Insets(10, 0, 10, 0);

        buttonPanel.add(addEmployeeButton, buttonConstraints);
        buttonPanel.add(modifyEmployeeButton, buttonConstraints);
        buttonPanel.add(deleteEmployeeButton, buttonConstraints);
        //buttonPanel.add(printSalarySlipButton, buttonConstraints);
        buttonPanel.add(displayEmployeeButton, buttonConstraints);
        buttonPanel.add(displayEmployeesListButton, buttonConstraints);

        // Add the buttonPanel to the mainDashboardPanel
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 1; // Position the buttons below the label
        panelConstraints.anchor = GridBagConstraints.CENTER;
        panelConstraints.insets = new Insets(0, 0, 10, 0); // Adjust spacing

        mainDashboardPanel.add(buttonPanel, panelConstraints);

        // Add the mainDashboardPanel to the main dashboard frame
        GridBagConstraints frameConstraints = new GridBagConstraints();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = GridBagConstraints.CENTER;
        frameConstraints.anchor = GridBagConstraints.CENTER;
        frameConstraints.insets = new Insets(10, 0, 10, 0);

        mainDashboardFrame.add(mainDashboardPanel, frameConstraints);
        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform "Add New Employee" functionality
                openAddEmployeeFrame();
                // You can implement this functionality as per your requirements
            }
        });

        modifyEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform "Modify Employee Record" functionality
                // You can implement this functionality as per your requirements
                modifyEmployeeDetails();
            }
        });

        deleteEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform "Delete Employee Record" functionality
                // You can implement this functionality as per your requirements
                deleteEmployee();
            }
        });

        displayEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform "Display Employee Record" functionality
                // You can implement this functionality as per your requirements
                displayEmployeeDetails();
            }
        });

        displayEmployeesListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform "Display List of Employees" functionality
                // You can implement this functionality as per your requirements
                displayEmployeesList();
            }
        });

        mainDashboardFrame.pack();
        mainDashboardFrame.setVisible(true);
    }
    
    private void openAddEmployeeFrame() {
        JFrame addEmployeeFrame = new JFrame("Add Employee");
        addEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEmployeeFrame.setLayout(new GridBagLayout());

        // Create a label for "Add New Employee Details"
        JLabel titleLabel = new JLabel("Add New Employee Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Create a panel to hold the components
        JPanel addEmployeePanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Create components for the add employee frame
        JLabel idLabel = new JLabel("Employee ID:");
        JTextField idField = new JTextField(20);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel designationLabel = new JLabel("Designation:");
        JTextField designationField = new JTextField(20);
        JLabel salaryLabel = new JLabel("Salary:");
        JTextField salaryField = new JTextField(20);

        // Add components to the add employee panel
        addEmployeePanel.add(idLabel);
        addEmployeePanel.add(idField);
        addEmployeePanel.add(nameLabel);
        addEmployeePanel.add(nameField);
        addEmployeePanel.add(designationLabel);
        addEmployeePanel.add(designationField);
        addEmployeePanel.add(salaryLabel);
        addEmployeePanel.add(salaryField);

        // Add the main label and panel to the add employee frame
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 0, 20, 0);

        addEmployeeFrame.add(titleLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        addEmployeeFrame.add(addEmployeePanel, gridBagConstraints);

        // Add the save button at the bottom
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveEmployeeDetails(idField.getText(), nameField.getText(), designationField.getText(), salaryField.getText());
            }
        });

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        addEmployeeFrame.add(saveButton, gridBagConstraints);

        addEmployeeFrame.pack();
        addEmployeeFrame.setVisible(true);
        addEmployeeFrame.setLocationRelativeTo(null); // Center the frame on the screen
    }
      private void saveEmployeeDetails(String id, String name, String designation, String salary) {
       try {
        // Establish the database connection
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        // Check if the employee details already exist in the database
        String checkSql = "SELECT COUNT(*) FROM employeedetails WHERE id = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkSql);
        checkStatement.setString(1, id);
        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        if (count > 0) {
            JOptionPane.showMessageDialog(this, "The Employee Record Already Exists.");
            return;
        }

        // Create a prepared statement to insert the employee details
        String insertSql = "INSERT INTO employeedetails (id, name, designation, salary) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertSql);
        insertStatement.setString(1, id);
        insertStatement.setString(2, name);
        insertStatement.setString(3, designation);
        insertStatement.setString(4, salary);

        // Execute the query
        int rowsAffected = insertStatement.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Employee details saved successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save employee details.");
        }

        // Close the result set, statements, and connection
        resultSet.close();
        insertStatement.close();
        checkStatement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Handle the delete employee functionality
    public void deleteEmployee() {
    String employeeID = JOptionPane.showInputDialog(this, "Enter the Employee ID to delete:");

    try {
        // Establish the database connection
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        // Create a prepared statement to delete the employee details
        String sql = "DELETE FROM employeedetails WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employeeID);

        // Execute the query
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Employee details deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete employee details. Employee ID not found.");
        }

        // Close the statement and connection
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Handle the display employee record functionality
public void displayEmployeeDetails() {
    String employeeID = JOptionPane.showInputDialog(this, "Enter the Employee ID:");

    try {
        // Establish the database connection
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        // Create a prepared statement to select the employee details
        String sql = "SELECT * FROM employeedetails WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employeeID);

        // Execute the query
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Employee details found
            String name = resultSet.getString("name");
            String designation = resultSet.getString("designation");
            String salary = resultSet.getString("salary");

            String employeeDetails = "Employee ID: " + employeeID + "\n"
                    + "Name: " + name + "\n"
                    + "Designation: " + designation + "\n"
                    + "Salary: " + salary;

            JOptionPane.showMessageDialog(this, employeeDetails);
        } else {
            // Employee not found
            JOptionPane.showMessageDialog(this, "Employee details not found. Employee ID: " + employeeID);
        }

        // Close the result set, statement, and connection
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



public void displayEmployeesList() {
    // Establish the database connection
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
        // Create a statement to execute the SQL query
        String sql = "SELECT * FROM employeedetails";
        Statement statement = connection.createStatement();

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery(sql);

        // Create a string builder to store the employee records
        StringBuilder employeeList = new StringBuilder();

        // Append the employee records to the string builder
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String designation = resultSet.getString("designation");
            double salary = resultSet.getDouble("salary");

            // Format the employee record
            String employeeRecord = "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: $" + salary;

            // Append the employee record to the string builder
            employeeList.append(employeeRecord).append("\n");
        }

        // Close the result set and statement
        resultSet.close();
        statement.close();

        // Show the employee list in a dialog
        JTextArea textArea = new JTextArea(employeeList.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Employee List", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void modifyEmployeeDetails() {
    // Create a dialog to get the employee ID to modify
    String employeeId = JOptionPane.showInputDialog(null, "Enter the Employee ID to modify:", "Modify Employee", JOptionPane.QUESTION_MESSAGE);

    if (employeeId != null && !employeeId.isEmpty()) {
        // Establish the database connection
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Check if the employee exists
            String checkSql = "SELECT * FROM employeedetails WHERE id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setString(1, employeeId);

            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // Employee exists, retrieve current details
                String currentName = resultSet.getString("name");
                String currentDesignation = resultSet.getString("designation");
                double currentSalary = resultSet.getDouble("salary");

                // Create a panel to hold the input fields
                JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
                panel.add(new JLabel("Employee ID:"));
                panel.add(new JLabel(employeeId));
                panel.add(new JLabel("Name:"));
                JTextField nameField = new JTextField(currentName);
                panel.add(nameField);
                panel.add(new JLabel("Designation:"));
                JTextField designationField = new JTextField(currentDesignation);
                panel.add(designationField);
                panel.add(new JLabel("Salary:"));
                JTextField salaryField = new JTextField(String.valueOf(currentSalary));
                panel.add(salaryField);

                // Show the input fields in a dialog
                int result = JOptionPane.showConfirmDialog(null, panel, "Modify Employee Details",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    // Retrieve the modified details from the input fields
                    String newName = nameField.getText();
                    String newDesignation = designationField.getText();
                    double newSalary = Double.parseDouble(salaryField.getText());

                    // Update the employee details in the database
                    String updateSql = "UPDATE employeedetails SET name = ?, designation = ?, salary = ? WHERE id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newName);
                    updateStatement.setString(2, newDesignation);
                    updateStatement.setDouble(3, newSalary);
                    updateStatement.setString(4, employeeId);

                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Employee details updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update employee details.");
                    }

                    updateStatement.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Employee with ID " + employeeId + " does not exist.");
            }

            resultSet.close();
            checkStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PayrollManagementSystemGUI();
            }
            
        });
        
    }
}