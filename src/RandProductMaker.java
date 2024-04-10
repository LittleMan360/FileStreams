import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RandProductMaker extends JFrame {

    public static Product[] products;
    //Variables

    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField costField;
    private JTextField idField;
    private JTextField recordCountField;
    private JButton addButton;
    private JButton quitButton;
    private int recordCount=0;

    //Constructor
    public RandProductMaker()
    {
        //Set up frame
        super("Add Product");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        //Initialize variables
        nameField = new JTextField();
        descriptionField = new JTextField();
        costField = new JTextField();
        idField = new JTextField();
        recordCountField = new JTextField();
        addButton = new JButton("Submit");
        quitButton = new JButton("Cancel");

        //Add components to frame
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Description:"));
        add(descriptionField);
        add(new JLabel("Cost:"));
        add(costField);
        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Record Count:"));
        add(recordCountField);
        add(addButton);
        add(quitButton);

        //Add action listeners
        addButton.addActionListener(this::addproduct);
        quitButton.addActionListener(e -> System.exit(0));

        //Set frame to be visible
        setVisible(true);
    }

    private void addproduct(ActionEvent event)
    {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String description = descriptionField.getText();
            double cost = Double.parseDouble(costField.getText());

            // Validation could be more thorough in a real application
            if (name.isEmpty() || description.isEmpty() || id.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product product = new Product(id, name, description, cost);

            try (RandomAccessFile file = new RandomAccessFile("Product.dat", "rw"))
            {
                file.seek(file.length());
                file.write(product.toByteArray());
                recordCount++;
                recordCountField.setText(String.valueOf(recordCount));
                clearFields();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to write to file", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cost must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields()
    {
        idField.setText("");
        nameField.setText("");
        descriptionField.setText("");
        costField.setText("");
    }

    public static void main(String[] args)
    {
        new RandProductMaker();
    }


}