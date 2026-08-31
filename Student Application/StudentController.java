import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentController implements ActionListener {
    private final StudentView view;
    private final List<Student> students = new ArrayList<>();

    public StudentController(StudentView view) {
        this.view = view;
        this.view.setController(this);

        students.add(new Student("S101", "Alice", 20, "Computer Science", "Female"));
        students.add(new Student("S102", "John", 21, "Mechanical", "Male"));
        students.add(new Student("S103", "Maya", 19, "Electronics", "Female"));

        refreshTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "ADD":
                addStudent();
                break;
            case "UPDATE":
                updateStudent();
                break;
            case "DELETE":
                deleteStudent();
                break;
            case "CLEAR":
                view.clearForm();
                break;
            default:
                break;
        }
    }

    public void loadSelectedStudent(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < students.size()) {
            view.setFormData(students.get(selectedRow));
        }
    }

    private void addStudent() {
        Student student = view.getStudentFromForm();
        if (student == null) {
            return;
        }

        for (Student existingStudent : students) {
            if (existingStudent.getId().equalsIgnoreCase(student.getId())) {
                view.showMessage("Student ID already exists. Please use a unique ID.");
                return;
            }
        }

        students.add(student);
        refreshTable();
        view.clearForm();
        view.showMessage("Student added successfully.");
    }

    private void updateStudent() {
        Student student = view.getStudentFromForm();
        if (student == null) {
            return;
        }

        int index = findStudentIndex(student.getId());
        if (index == -1) {
            view.showMessage("Student not found. Please select an existing record.");
            return;
        }

        students.set(index, student);
        refreshTable();
        view.clearForm();
        view.showMessage("Student updated successfully.");
    }

    private void deleteStudent() {
        String id = view.getStudentId();
        if (id.isEmpty()) {
            view.showMessage("Please select a student row or enter a student ID to delete.");
            return;
        }

        int index = findStudentIndex(id);
        if (index == -1) {
            view.showMessage("Student not found.");
            return;
        }

        students.remove(index);
        refreshTable();
        view.clearForm();
        view.showMessage("Student deleted successfully.");
    }

    private int findStudentIndex(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(studentId)) {
                return i;
            }
        }
        return -1;
    }

    private void refreshTable() {
        view.populateTable(students);
    }
}
