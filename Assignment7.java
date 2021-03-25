
abstract class Rodent
{
    abstract void eat();
}
class Mouse extends Rodent
{
    public Mouse()
    {
        System.out.println("This is Mouse");
    }
    void eat()
    {
        System.out.println("Mouse eat grains, fruits etc");
    }
}
class Gerbil extends Rodent
{
    public Gerbil()
    {
        System.out.println("This is Gerbil");
    }
    void eat()
    {
        System.out.println("Gerbils eat plants, meat");
    }
}
class Hamster extends Rodent
{
    public Hamster()
    {
        System.out.println("This is Hamster");
    }
    void eat()
    {
        System.out.println("Hamsters eat small amounts of fresh fruit, vegetables or herbs.");
    }
}



class Cycle
{
    void balance()
    {
        System.out.println("Balance method in Cycle class");
    }
}
class Unicycle extends Cycle
{
    void balance()
    {
        System.out.println("This Unicycle needs balance");
    }
}
class Bicycle extends Cycle
{
    void balance()
    {
        System.out.println("This Unicycle needs balance");
    }
}
class Tricycle extends Cycle
{

}
class FactoryCycle
{
    public Cycle getType(String type)
    {
        if(type==null) return null;
        if(type.equalsIgnoreCase("Unicycle"))
            return new Unicycle();
        if(type.equalsIgnoreCase("Bicycle"))
            return new Bicycle();
        if(type.equalsIgnoreCase("Tricycle"))
            return new Tricycle();
        return null;
    }
}



interface Interface1
{
    void method1();
}
interface Interface2
{
    void method2();
}
interface Interface3
{
    void method3();
}
interface Interface4 extends Interface1,Interface2,Interface3
{
    void method4();
}
class ConcreteClass
{
    void method5()
    {
        System.out.println("Method5 in concrete class");
    }
}
class Implementor extends ConcreteClass implements Interface4
{
    public void method1()
    {
        System.out.println("Method1");
    }
    public void method2()
    {
        System.out.println("Method2");
    }
    public void method3()
    {
        System.out.println("Method3");
    }
    public void method4()
    {
        System.out.println("Method4");
    }
    public void method6(Interface1 I)
    {
        I.method1();
    }
    public void method7(Interface2 I)
    {
        I.method2();
    }
    public void method8(Interface3 I)
    {
        I.method3();
    }
    public void method9(Interface4 I)
    {
        I.method1();
        I.method2();
        I.method3();
        I.method4();
    }
}



class Outer1
{
    static public class Inner1
    {
        public Inner1(String name)
        {
            System.out.println(name);
        }
    }
    public class Inner2
    {
        public Inner2(String name)
        {
            System.out.println(name);
        }
    }
}
class Outer2
{
    class Inner3 extends Outer1.Inner1
    {
        public Inner3()
        {
            super("Nisanth");
        }
    }
    class Inner4 extends Outer1.Inner2
    {
        public Inner4()
        {
            new Outer1().super("Nisanth");
        }
    }
}


public class Assignment7 {
    public static void main(String[] args) {
        Rodent rodents[]=new Rodent[3];
        rodents[0]=new Mouse(); rodents[0].eat();
        rodents[1]=new Gerbil(); rodents[1].eat();
        rodents[2]=new Hamster(); rodents[2].eat();

        Cycle cycles[]=new Cycle[3];
        cycles[0]=new Unicycle();
        cycles[0].balance();
        cycles[0]=new Cycle();
        cycles[0].balance();
        cycles[1]=new Bicycle();
        cycles[1].balance();
        cycles[1]=new Cycle();
        cycles[1].balance();
        cycles[2]=new Tricycle();
        cycles[2].balance();
        cycles[2]=new Cycle();
        cycles[2].balance();

        Implementor obj=new Implementor();
        obj.method1();
        obj.method2();
        obj.method3();
        obj.method4();
        obj.method5();
        obj.method6(obj);
        obj.method7(obj);
        obj.method8(obj);
        obj.method9(obj);

        FactoryCycle cycle1=new FactoryCycle();
        Cycle uni=cycle1.getType("Unicycle");
        Cycle bi=cycle1.getType("Bicycle");
        Cycle Tri=cycle1.getType("Tricycle");


    }
}
