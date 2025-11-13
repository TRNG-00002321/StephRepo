package assignments;

public class Customer
{
    final String name;
    int age;
    static int products;
    public Customer(String name)
    {
        this.name = name;
        this.age = 20;
        this.products = 0;
    }
    public Customer(String name, int age)
    {
        this.name = name;
        this.age = age;
        this.products = 0;
    }
    public int getProducts()
    {
        return products;
    }

    public void addProduct()
    {
        products++;
    }
    public void addProduct(int num)
    {
        products+=num;
    }
    public void removeProduct()
    {
        products--;
    }
    public void removeProduct(int num)
    {
        products-=num;
    }
}
