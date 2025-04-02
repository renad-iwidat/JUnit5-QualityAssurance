package main.najah.code;

public class Recipe {
    private String name;
    private int price;
    private int amtCoffee;
    private int amtMilk;
    private int amtSugar;
    private int amtChocolate;

    // Default constructor
    public Recipe() {
        this.name = "";
        this.price = 0;
        this.amtCoffee = 0;
        this.amtMilk = 0;
        this.amtSugar = 0;
        this.amtChocolate = 0;
    }

    // Getter and setter methods
    public int getAmtChocolate() {
        return amtChocolate;
    }

    public void setAmtChocolate(String chocolate) throws RecipeException {
        try {
            amtChocolate = Integer.parseInt(chocolate);
            if (amtChocolate < 0) throw new RecipeException("Units of chocolate must be a positive integer");
        } catch (NumberFormatException e) {
            throw new RecipeException("Units of chocolate must be a positive integer");
        }
    }

    public int getAmtCoffee() {
        return amtCoffee;
    }

    public void setAmtCoffee(String coffee) throws RecipeException {
        try {
            amtCoffee = Integer.parseInt(coffee);
            if (amtCoffee < 0) throw new RecipeException("Units of coffee must be a positive integer");
        } catch (NumberFormatException e) {
            throw new RecipeException("Units of coffee must be a positive integer");
        }
    }

    public int getAmtMilk() {
        return amtMilk;
    }

    public void setAmtMilk(String milk) throws RecipeException {
        try {
            amtMilk = Integer.parseInt(milk);
            if (amtMilk < 0) throw new RecipeException("Units of milk must be a positive integer");
        } catch (NumberFormatException e) {
            throw new RecipeException("Units of milk must be a positive integer");
        }
    }

    public int getAmtSugar() {
        return amtSugar;
    }

    public void setAmtSugar(String sugar) throws RecipeException {
        try {
            amtSugar = Integer.parseInt(sugar);
            if (amtSugar < 0) throw new RecipeException("Units of sugar must be a positive integer");
        } catch (NumberFormatException e) {
            throw new RecipeException("Units of sugar must be a positive integer");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(String price) throws RecipeException {
        try {
            this.price = Integer.parseInt(price);
            if (this.price < 0) throw new RecipeException("Price must be a positive integer");
        } catch (NumberFormatException e) {
            throw new RecipeException("Price must be a positive integer");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Recipe other = (Recipe) obj;
        return name != null && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }
}
