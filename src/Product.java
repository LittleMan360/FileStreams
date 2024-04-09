
//Imports
import java.io.Serializable;
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
    public String getName()
    {
        return name;
    }

    //lets user set name
    public void setName(String name)
    {
        this.name = name;
    }

    //lets user get description
    public String getDescription()
    {
        return description;
    }

    //lets user set description
    public void setDescription(String description)
    {
        this.description = description;
    }

    //lets user get id
    public String getID()
    {
        return ID;
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

    @Override
    public String toString()
    {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", Name='" + name + '\'' +
                ", Description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    //Method toCSVRecordMethod
    public String toCSVRecordMethod()
    {
        return this.ID + ", " + this.name + ", " + this.description + ", " + cost;
    }

    public String toRandomRecord() {
        return String.format("%-" + NAME_LENGTH + "s%-"
                + DESCRIPTION_LENGTH + "s%-"
                + ID_LENGTH + "s%-8.2f", name, description, ID, cost);
    }
}