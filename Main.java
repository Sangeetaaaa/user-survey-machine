import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.io.BufferedWriter;
import java.awt.event.*;
import java.nio.file.*;

// 1. Create a form (GUI)
// 2. Let user fill in data there and once they click on submit let the data be saved in a file 

class Main implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JTextField name;
    private JTextArea feedback;
    private String content = "";

    public static void main(String[] args) {
        Main main = new Main();
        main.go();
    }

    void go() {
        frame = new JFrame();
        // BorderLayout layout = new BorderLayout();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("User's Survey System");
        heading.setFont(new Font("Calibri", Font.BOLD, 20));

        JLabel labelname = new JLabel("Enter name");
        name = new JTextField(20);
        JLabel labelfeed = new JLabel("Enter Your Feedback");
        feedback = new JTextArea(20, 30);

        JScrollPane scroller = new JScrollPane(feedback);
        feedback.setLineWrap(true);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        

        JButton submit = new JButton("Submit");
        submit.addActionListener(this);


        panel.add(heading);
        panel.add(labelname);
        panel.add(name);
        panel.add(labelfeed);
        panel.add(scroller);
        panel.add(submit);

        frame.getContentPane().add(panel);
        frame.setSize(300, 350);
        frame.setVisible(true);


        try {
            File myFile = new File("content.txt");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                content = content + "\n" + line;
            }
            reader.close();
            System.out.println("USERS FEEDBACK LIST");
            System.out.println(content);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ev) {
            String getDetails = "Name : " + name.getText() + "\n" + "FeedBack : " + feedback.getText();
            content = content + "\n\n" + getDetails;
            name.setText("");
            feedback.setText("");

            try {
                FileWriter file = new FileWriter("content.txt");
                BufferedWriter buffer = new BufferedWriter(file);
                PrintWriter print = new PrintWriter(buffer);
                print.println(content);
                print.flush();
            } catch(Exception ex) {
                System.out.println("couldn’t write the cardList out");
                ex.printStackTrace();
            }
        
    }
}



