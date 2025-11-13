package assignments;

public class CustomerMain
{
    static void main(String[] args)
    {
        Customer c1 = new Customer("Bob");
        Customer c2 = new Customer("Joe",34);

        c1.addProduct();
        System.out.println(c1.getProducts());
        c2.addProduct(3);
        System.out.println(c2.getProducts());

        c1.removeProduct(2);
        System.out.println(c1.getProducts());
        c2.removeProduct();
        System.out.println(c2.getProducts());




    }
}
