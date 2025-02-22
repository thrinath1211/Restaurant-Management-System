import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuItem {
    protected String name;
    protected double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String displayInfo() {
        return name + " - $" + price;
    }
}

class Dish extends MenuItem {
    private String cuisine;

    public Dish(String name, double price, String cuisine) {
        super(name, price);
        this.cuisine = cuisine;
    }

    @Override
    public String displayInfo() {
        return "Dish: " + super.displayInfo() + " (Cuisine: " + cuisine + ")";
    }
}

class Drink extends MenuItem {
    private boolean isAlcoholic;

    public Drink(String name, double price, boolean isAlcoholic) {
        super(name, price);
        this.isAlcoholic = isAlcoholic;
    }

    @Override
    public String displayInfo() {
        return "Drink: " + super.displayInfo() + (isAlcoholic ? " (Alcoholic)" : " (Non-Alcoholic)");
    }
}

class Order {
    private List<MenuItem> items;
    private double totalCost;

    public Order() {
        this.items = new ArrayList<>();
        this.totalCost = 0;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalCost += item.getPrice();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void displayOrder() {
        System.out.println("Order Details:");
        for (MenuItem item : items) {
            System.out.println(item.displayInfo());
        }
        System.out.println("Total Cost: $" + totalCost);
    }
}

public class RestaurantManagementSystem {
    private static List<MenuItem> menu = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu.add(new Dish("Pasta", 12.99, "Italian"));
        menu.add(new Dish("Sushi", 15.99, "Japanese"));
        menu.add(new Drink("Coke", 2.50, false));
        menu.add(new Drink("Wine", 8.99, true));

        Order order = new Order();

        while (true) {
            System.out.println("\nRestaurant Management System");
            System.out.println("1. View Menu");
            System.out.println("2. Add Item to Order");
            System.out.println("3. View Order");
            System.out.println("4. Checkout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    addItemToOrder(order);
                    break;
                case 3:
                    order.displayOrder();
                    break;
                case 4:
                    System.out.println("Final Total: $" + order.getTotalCost());
                    System.out.println("Checkout successful. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewMenu() {
        for (MenuItem item : menu) {
            System.out.println(item.displayInfo());
        }
    }

    private static void addItemToOrder(Order order) {
        System.out.print("Enter item name: ");
        scanner.nextLine(); 
        String itemName = scanner.nextLine();

        for (MenuItem item : menu) {
            if (item.name.equalsIgnoreCase(itemName)) {
                order.addItem(item);
                System.out.println(itemName + " added to order.");
                return;
            }
        }
        System.out.println("Item not found.");
    }
}
