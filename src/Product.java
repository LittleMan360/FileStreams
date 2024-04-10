
//Imports
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
public class Product implements Serializable
{
    //Variables
    private String ID;
    private String name;
    private String description;
    private double cost;

    private static final long serialVersionUID = 1L;
    private static final int NAME_LENGTH = 35;
    private static final int DESCRIPTION_LENGTH = 75;
    private static final int ID_LENGTH = 6;

    //Constructor
    public Product(String ID, String name, String description, double cost)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    //Getters and Setters and ToString Methods

    //lets user get name
    public String getPaddedID()
    {
        return padString(ID, 6);
    }
    public String getID() {
        return ID;
    }

    //lets user set name
    public String getPaddedName()
    {
        return padString(name, 35);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //lets user get description
    public String getPaddedDescription()
    {
        return padString(description, 75);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //lets user set description
    private String padString(String input, int length)
    {
        if (input.length() > length) {
            return input.substring(0, length);
        }
        return String.format("%-" + length + "s", input);
    }

    //lets user get id
    public String trimString(String input)
    {
        return input.trim();
    }
    //lets user get cost
    public double getCost()
    {
        return cost;
    }


    //lets user set cost
    public void setCost(double cost)
    {
        this.cost = cost;
    }


    public String toCSVDataRecord() {
        return ID + "," + name + "," + description + "," + cost;
    }

    public byte[] toByteArray()
    {
        String record = getPaddedID() + getPaddedName() + getPaddedDescription() + String.format("%.2f", cost);
        return record.getBytes(StandardCharsets.UTF_8);
    }
}