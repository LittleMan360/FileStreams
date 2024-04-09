import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RandProductMaker extends JFrame {

    private JPanel counterPnl;
    private JPanel addItemPnl;
    private JPanel cmdPnl;

    private final JTextField idTxt = new JTextField(10);
    private final JTextField nameTxt = new JTextField(10);
    private final JTextField descTxt = new JTextField(10);
    private final JTextField costTxt = new JTextField(10);

    private final JButton addBtn = new JButton("Add");
    private final JButton saveBtn = new JButton("Save");

    private final JButton quitBtn = new JButton("Quit");



    private ArrayList<Product> productList = new ArrayList<>();
    private int count = 0;

    private final Path path = Paths.get("products.dat");

    {
        addBtn.addActionListener(this::addBtnActionPerformed);

        saveBtn.addActionListener(this::saveBtnActionPerformed);

        quitBtn.addActionListener(e -> System.exit(0));
    }


    public RandProductMaker(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Creator");


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile())))
        {
            productList = (ArrayList<Product>) in.readObject();
            count = productList.size();
        }
        catch (ClassNotFoundException | IOException p)
        {
            p.printStackTrace();
        }

        JPanel mainPnl = new JPanel();
        add(mainPnl);

        generateCounterPnl();
        mainPnl.add(counterPnl, BorderLayout.NORTH);

        generateAddItemPnl();
        mainPnl.add(addItemPnl, BorderLayout.CENTER);

        generateCmdPnl();
        mainPnl.add(cmdPnl, BorderLayout.SOUTH);

        pack();
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateCounterPnl(){
        counterPnl = new JPanel();
        counterPnl.setLayout(new FlowLayout());
        counterPnl.add(new JLabel("Product Count: " + count));
    }

    private void generateAddItemPnl(){
        addItemPnl = new JPanel();
        addItemPnl.setLayout(new GridLayout(4,2));
        addItemPnl.add(new JLabel("ID:"));
        addItemPnl.add(idTxt);
        addItemPnl.add(new JLabel("Name:"));
        addItemPnl.add(nameTxt);
        addItemPnl.add(new JLabel("Description:"));
        addItemPnl.add(descTxt);
        addItemPnl.add(new JLabel("Cost:"));
        addItemPnl.add(costTxt);
    }

    private void generateCmdPnl(){
        cmdPnl = new JPanel();
        cmdPnl.setLayout(new FlowLayout());
        cmdPnl.add(addBtn);
        cmdPnl.add(saveBtn);
        cmdPnl.add(quitBtn);
    }

    private void addBtnActionPerformed(ActionEvent e){
        addRecord();
    }


    private void saveBtnActionPerformed(ActionEvent e){
        saveRecords();
    }

    private void addRecord(){
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String desc = descTxt.getText();
        double cost = Double.parseDouble(costTxt.getText());

        Product product = new Product(id, name, desc, cost);
        productList.add(product);
        count++;
        clearFields();
        updateCounter();
    }

    private void saveRecords(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toFile())))
        {
            out.writeObject(productList);
        }
        catch (IOException p)
        {
            p.printStackTrace();
        }
    }

    private void updateCounter(){
        counterPnl.removeAll();
        counterPnl.add(new JLabel("Product Count: " + count));
        counterPnl.revalidate();
        counterPnl.repaint();
    }

    private void clearFields() {
        idTxt.setText("");
        nameTxt.setText("");
        descTxt.setText("");
        costTxt.setText("");
    }

    public static void main(String[] args){
        new RandProductMaker();
    }


}