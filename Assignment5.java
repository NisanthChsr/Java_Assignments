package nisanth.assignment.data;
class FirstClass
{
    int number;
    char character;
    void printVariables()
    {
        System.out.println(number+"  "+character);
    }
    void printLocalVaribles()
    {
        int num;
        char ch;
        System.out.println(num+" "+ch);
    }
}

package nisanth.assignment.singleton
class SecondClass
{
    String member;
    static SecondClass method(String s)
    {
        SecondClass obj=new SecondClass();
        obj.member=s;
        return obj;
    }
    void printMember()
    {
        System.out.println(member);
    }
}

package nisanth.assignment.main
public class Assignment5
{
    FirstClass obj1=new FirstClass();
    obj1.printVariables();//Not possible
    obj1.printLocalVariables();//Not possible
    SecondClass obj2=SecondClass.method("Nisanth");
    obj2.printMember();//possible

}
