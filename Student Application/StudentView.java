import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentView extends JFrame {
    private final JTextField idField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JTextField ageField = new JTextField();
    private final JTextField branchField = new JTextField();
    private final JRadioButton maleRadio = new JRadioButton("Male");
    private final JRadioButton femaleRadio = new JRadioButton("Female");
    private final ButtonGroup genderGroup = new ButtonGroup();

    private final JButton addButton = new JButton("Add Student");
    private final JButton updateButton = new JButton("Update Student");
    private final JButton deleteButton = new JButton("Delete Student");
    private final JButton clearButton = new JButton("Clear");

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"ID", "Name", "Age", "Branch", "Gender"}, 0
    );
    private final JTable studentTable = new JTable(tableModel);

    private StudentController controller;

    public StudentView() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);

        formPanel.add(new JLabel("Branch:"));
        formPanel.add(branchField);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 8, 0));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);

        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        add(tableScrollPane, BorderLayout.CENTER);

        addButton.setActionCommand("ADD");
        updateButton.setActionCommand("UPDATE");
        deleteButton.setActionCommand("DELETE");
        clearButton.setActionCommand("CLEAR");
    }

    public void setController(StudentController controller) {
        this.controller = controller;

        addButton.addActionListener(controller);
        updateButton.addActionListener(controller);
        deleteButton.addActionListener(controller);
        clearButton.addActionListener(controller);

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && controller != null) {
                int row = studentTable.getSelectedRow();
                if (row >= 0) {
                    controller.loadSelectedStudent(row);
                }
            }
        });
    }

    public Student getStudentFromForm() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String branch = branchField.getText().trim();
        String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");

        if (id.isEmpty() || name.isEmpty() || ageText.isEmpty() || branch.isEmpty() || gender.isEmpty()) {
            showMessage("Please fill in all student fields.");
            return null;
        }

        try {
            int age = Integer.parseInt(ageText);
            if (age < 0 || age > 120) {
                showMessage("Age must be between 0 and 120.");
                return null;
            }

            return new Student(id, name, age, branch, gender);
        } catch (NumberFormatException ex) {
            showMessage("Age must be a valid number.");
            return null;
        }
    }

    public void setFormData(Student student) {
        if (student == null) {
            clearForm();
            return;
        }

        idField.setText(student.getId());
        nameField.setText(student.getName());
        ageField.setText(String.valueOf(student.getAge()));
        branchField.setText(student.getBranch());

        if ("Male".equalsIgnoreCase(student.getGender())) {
            maleRadio.setSelected(true);
        } else if ("Female".equalsIgnoreCase(student.getGender())) {
            femaleRadio.setSelected(true);
        }
    }

    public void clearForm() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        branchField.setText("");
        genderGroup.clearSelection();
        studentTable.clearSelection();
    }

    public void populateTable(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getBranch(),
                    student.getGender()
            });
        }
    }

    public String getStudentId() {
        return idField.getText().trim();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
