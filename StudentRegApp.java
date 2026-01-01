import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentRegApp {
    private JFrame frame;
    private JTextField nameField;
    private JTextField idField;
    private DefaultTableModel tableModel;
    private JTable studentTable;

    public StudentRegApp() {
        // 1. Create the Frame
        frame = new JFrame("Student Registration");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen

        // 2. Main Layout
        frame.setLayout(new BorderLayout(10, 10));

        // --- TOP PANEL: INPUTS ---
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        inputPanel.add(new JLabel("")); // Spacer
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // --- CENTER PANEL: TABLE ---
        String[] columns = { "ID", "Name" };
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);

        // Wrap table in ScrollPane
        JScrollPane scrollPane = new JScrollPane(studentTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- BOTTOM PANEL: STATUS ---
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(new JLabel("Ready"));
        frame.add(statusPanel, BorderLayout.SOUTH);

        // 3. Show Frame
        frame.setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();

        // Validation
        if (name.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add to model
        tableModel.addRow(new Object[] { id, name });

        // Clear inputs
        nameField.setText("");
        idField.setText("");
        nameField.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            new StudentRegApp();
        });
    }
}