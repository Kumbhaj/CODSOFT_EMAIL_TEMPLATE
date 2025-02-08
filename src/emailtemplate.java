import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EmailTemplateEditor extends JFrame {
    private JTextField subjectField;
    private JTextArea bodyTextArea;
    private JButton previewButton;
    private JLabel messageLabel;

    public EmailTemplateEditor() {
        setTitle("Email Template Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        subjectField = new JTextField();
        bodyTextArea = new JTextArea(10, 50);

        topPanel.add(new JLabel("Subject:"));
        topPanel.add(subjectField);

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(new JLabel("Body:"), BorderLayout.NORTH);
        middlePanel.add(new JScrollPane(bodyTextArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        previewButton = new JButton("Preview Email");
        messageLabel = new JLabel("Fill in the subject and body, then click Preview.");

        previewButton.addActionListener(new PreviewButtonListener());

        bottomPanel.add(previewButton);
        bottomPanel.add(messageLabel);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class PreviewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String subject = subjectField.getText().trim();
            String body = bodyTextArea.getText().trim();

            if (subject.isEmpty() || body.isEmpty()) {
                messageLabel.setText("Please fill in both the subject and body.");
            } else {
                messageLabel.setText("Preview generated. Check new window.");
                showEmailPreview(subject, body);
            }
        }
    }

    private void showEmailPreview(String subject, String body) {
        JFrame previewFrame = new JFrame("Email Preview");
        previewFrame.setSize(500, 300);
        previewFrame.setLayout(new BorderLayout());

        JLabel previewSubject = new JLabel("Subject: " + subject);
        JTextArea previewBody = new JTextArea(body);
        previewBody.setLineWrap(true);
        previewBody.setWrapStyleWord(true);
        previewBody.setEditable(false);

        previewFrame.add(previewSubject, BorderLayout.NORTH);
        previewFrame.add(new JScrollPane(previewBody), BorderLayout.CENTER);

        previewFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new EmailTemplateEditor();
    }
}
