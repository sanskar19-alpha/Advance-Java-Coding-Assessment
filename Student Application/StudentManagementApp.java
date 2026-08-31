import javax.swing.SwingUtilities;

public class StudentManagementApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentView view = new StudentView();
            new StudentController(view);
            view.setVisible(true);
        });
    }
}
