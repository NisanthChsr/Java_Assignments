class DivideByZeroException extends Exception
{
    DivideByZeroException(String s)
    {
        super(s);
    }
}
class InvalidName extends Exception
{
    InvalidName(String s)
    {
        super(s);
    }
}
class InvalidAge extends Exception
{
    InvalidAge(String s)
    {
        super(s);
    }
}
public class Assignment8 {
    public static void method()
    {
        String name=null;
        int age=15;
        int a=10,b=0;
        try {
            if (name == null)
                throw new InvalidName("Not a valid name");
            if(age<=18)
                throw new InvalidAge("Invalid age");
            if(b==0)
                throw new DivideByZeroException("Divide by zero");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        try {
            method();
            throw new NullPointerException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("It is Finally block");
        }
    }
}
