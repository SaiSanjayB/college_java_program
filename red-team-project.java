import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Red-Team Prompt Injection & Attack Payload Vault
 * Main Application Engine
 */
public class PayloadVault extends JFrame {

    // Database configurations
    private static final String DB_URL = "jdbc:sqlite:vault.db";
    private Connection connection;

    // UI Components
    private JTable payloadTable;
    private DefaultTableModel tableModel;
    private JTextArea responseArea;
    private JTextField targetUrlField;
    private JComboBox<String> severityCombo;

    public PayloadVault() {
        setTitle("Imperial Red-Team Payload Vault");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initDatabase();
        buildUI();
        loadData();
    }

    /**
     * Initializes the SQLite Database and creates tables if they do not exist.
     */
    private void initDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement stmt = connection.createStatement();
            
            // Create Payloads Table
            String createPayloads = "CREATE TABLE IF NOT EXISTS PAYLOADS (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "category TEXT NOT NULL, " +
                "severity TEXT, " +
                "payload_body TEXT NOT NULL)";
            stmt.execute(createPayloads);
            
            // Create Logs Table
            String createLogs = "CREATE TABLE IF NOT EXISTS LOGS (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "payload_id INTEGER, " +
                "response TEXT, " +
                "status_code INTEGER, " +
                "FOREIGN KEY(payload_id) REFERENCES PAYLOADS(id))";
            stmt.execute(createLogs);
            
            System.out.println("Database Initialization Complete.");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }
    }

    /**
     * Constructs the Java Swing GUI Layout
     */
    private void buildUI() {
        setLayout(new BorderLayout(10, 10));
        
        // --- TOP PANEL: Controls ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdd = new JButton("Add Payload");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnExecute = new JButton("EXECUTE ATTACK");
        btnExecute.setBackground(new Color(139, 0, 0));
        btnExecute.setForeground(Color.WHITE);
        
        targetUrlField = new JTextField("http://localhost:11434/api/generate", 25);
        
        topPanel.add(new JLabel("Target API:"));
        topPanel.add(targetUrlField);
        topPanel.add(btnAdd);
        topPanel.add(btnDelete);
        topPanel.add(btnExecute);
        
        add(topPanel, BorderLayout.NORTH);
        
        // --- CENTER PANEL: Data Table ---
        String[] columns = {"ID", "Title", "Category", "Severity", "Payload Data"};
        tableModel = new DefaultTableModel(columns, 0);
        payloadTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(payloadTable);
        
        add(tableScroll, BorderLayout.CENTER);
        
        // --- BOTTOM PANEL: Response Log ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("HTTP Response"));
        
        responseArea = new JTextArea(10, 50);
        responseArea.setEditable(false);
        responseArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        responseArea.setBackground(new Color(40, 44, 52));
        responseArea.setForeground(new Color(152, 195, 121));
        JScrollPane logScroll = new JScrollPane(responseArea);
        
        bottomPanel.add(logScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // --- EVENT LISTENERS ---
        btnAdd.addActionListener(e -> addNewPayload());
        btnDelete.addActionListener(e -> deletePayload());
        btnExecute.addActionListener(e -> executeSelectedPayload());
    }

    /**
     * Loads payload data from SQLite into the JTable
     */
    private void loadData() {
        tableModel.setRowCount(0); // Clear existing data
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PAYLOADS");
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("category"),
                    rs.getString("severity"),
                    rs.getString("payload_body")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows a dialog to add a new payload vector
     */
    private void addNewPayload() {
        JTextField titleField = new JTextField();
        JTextField catField = new JTextField("OWASP_LLM_01");
        String[] severities = {"LOW", "MEDIUM", "HIGH", "CRITICAL"};
        severityCombo = new JComboBox<>(severities);
        JTextArea bodyArea = new JTextArea(5, 20);
        
        Object[] message = {
            "Title:", titleField,
            "Category:", catField,
            "Severity:", severityCombo,
            "Payload Body:", new JScrollPane(bodyArea)
        };
        
        int option = JOptionPane.showConfirmDialog(
            this, message, "Add Payload", JOptionPane.OK_CANCEL_OPTION
        );
        
        if (option == JOptionPane.OK_OPTION) {
            String sql = "INSERT INTO PAYLOADS " +
                         "(title, category, severity, payload_body) " +
                         "VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, titleField.getText());
                pstmt.setString(2, catField.getText());
                pstmt.setString(3, severityCombo.getSelectedItem().toString());
                pstmt.setString(4, bodyArea.getText());
                pstmt.executeUpdate();
                loadData();
                responseArea.append("[SYSTEM] Payload Added Successfully.\n");
            } catch (SQLException e) {
                responseArea.append("[ERROR] Insert failed: " + e.getMessage() + "\n");
            }
        }
    }

    /**
     * Deletes the selected payload from the database
     */
    private void deletePayload() {
        int selectedRow = payloadTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a payload to delete.");
            return;
        }
        
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        try {
            String sql = "DELETE FROM PAYLOADS WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            loadData();
            responseArea.append("[SYSTEM] Payload ID " + id + " Deleted.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the payload against the Target HTTP API
     */
    private void executeSelectedPayload() {
        int selectedRow = payloadTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a payload.");
            return;
        }
        
        int payloadId = (int) tableModel.getValueAt(selectedRow, 0);
        String payloadBody = (String) tableModel.getValueAt(selectedRow, 4);
        String targetUrl = targetUrlField.getText();
        
        responseArea.append("\n[ACTION] Dispatching to " + targetUrl + "...\n");
        
        // Escape quotes for basic JSON construction
        String safePayload = payloadBody.replace("\"", "\\\"");
        String jsonBody = "{ \"model\": \"llama3\", " +
                          "\"prompt\": \"" + safePayload + "\", " +
                          "\"stream\": false }";
        
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
                
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
                
        // Execute asynchronously
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
              .thenApply(response -> {
                  int status = response.statusCode();
                  String body = response.body();
                  logExecutionToDB(payloadId, status, body);
                  return "[HTTP " + status + "] Response:\n" + body + "\n";
              })
              .thenAccept(result -> {
                  SwingUtilities.invokeLater(() -> responseArea.append(result));
              })
              .exceptionally(e -> {
                  SwingUtilities.invokeLater(() -> 
                      responseArea.append("[ERROR] " + e.getMessage() + "\n")
                  );
                  return null;
              });
    }

    /**
     * Saves the execution result back into the SQLite Logs table
     */
    private void logExecutionToDB(int payloadId, int status, String response) {
        String sql = "INSERT INTO LOGS (payload_id, response, status_code) " +
                     "VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, payloadId);
            pstmt.setString(2, response);
            pstmt.setInt(3, status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Set Look and Feel to System Default for cleaner Swing UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            PayloadVault vault = new PayloadVault();
            vault.setVisible(true);
        });
    }
}
