import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RandProductSearch {

    public static void main(String[] args) {
        ProductSearchSection section = new ProductSearchSection();
        section.setVisible(true);
    }

    public static class ProductSearchSection extends JFrame {
        JPanel mainPanel, titlePanel, inputPanel, displayPanel, buttonPanel;
        JLabel titleLabel, inputLabel;
        JTextField inputTextField;
        JTextArea displayTextArea;
        JScrollPane displayScroll;
        JButton searchButton, quitButton;
        ActionListener quit = new Quit();
        ActionListener search = new SearchListener();
        String userInput, displayOutput;

        ProductSearchSection() {
            setTitle("Product Search");
            setSize(800, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            mainPanel = new JPanel();
            titlePanel = new JPanel();
            inputPanel = new JPanel();
            displayPanel = new JPanel();
            buttonPanel = new JPanel();
            titleLabel = new JLabel("Random Product Search");
            inputLabel = new JLabel("Product Search :");
            inputTextField = new JTextField();
            displayTextArea = new JTextArea(6, 75);
            displayScroll = new JScrollPane(displayTextArea);

            searchButton = new JButton("Search");
            searchButton.addActionListener(search);
            quitButton = new JButton("Quit");
            quitButton.addActionListener(quit);

            add(mainPanel);
            mainPanel.setLayout(new GridLayout(4, 1, 50 , 50));

            mainPanel.add(titlePanel);
            titlePanel.add(titleLabel);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);

            mainPanel.add(inputPanel);
            inputPanel.setLayout(new GridLayout(2, 1, 100, 0));
            inputPanel.add(inputLabel);
            inputPanel.add(inputTextField);


            mainPanel.add(displayPanel);
            displayPanel.add(displayScroll);


            mainPanel.add(buttonPanel);
            buttonPanel.add(searchButton);
            buttonPanel.add(quitButton);
        }

        private class SearchListener implements ActionListener {
            public void actionPerformed(ActionEvent AE) {
                displayTextArea.setText("");
                userInput = inputTextField.getText();
                File workingDirectory = new File(System.getProperty("user.dir"));
                Path file = Paths.get(workingDirectory.getPath() + "\\Product.dat");
                try {
                    RandomAccessFile inFile = new RandomAccessFile(file.toString(), "r");
                    while ((displayOutput = inFile.readLine()) != null) {
                        if (displayOutput.contains(userInput)) {
                            displayTextArea.append(displayOutput + "\n");
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not Found!");
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private class Quit implements ActionListener {
            public void actionPerformed(ActionEvent AE) {
                System.exit(0);
            }
        }
    }
}