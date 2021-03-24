import java.util.*;

class Link<T> {

    T val;
    Link<T> next;

    Link(T val, Link<T> next){
        this.val=val;
        this.next=next;
    }

}

public class SList<T> {

    static Link head;

    private static SListIterator iterator(Link head){

        SListIterator iter = new SListIterator(head);
        return iter;

    }

    public static void main(String args[]){

        SList sl = new SList();
        head = new Link("Head Link",null);
        SListIterator iter = iterator(head);

        while(true){
            System.out.println("Press 1 to add a new link");
            System.out.println("Press 2 to remove the last link");
            System.out.println("Press 3 to print the list");
            System.out.println("Press -1 to exit");

            Scanner scan = new Scanner(System.in);
            int response = scan.nextInt();


            if(response == 1){
                System.out.println("give data for new link");
                iter.insert(scan.next());
            }
            else if(response == 2){
                iter.remove();
            }
            else if(response == 3){
                iter.iterate();
            }
            else if(response == -1){
                break;
            }
            else{
                System.out.println("Invalid respone!");
            }
        }

    }

}

class SListIterator<T>{

    Link head;
    Link current;
    Link previous;
    Link prePrevious;

    public SListIterator(Link head) {
        this.head=head;
        current=head;
    }

    void insert(T val){
        Link node = new Link(val,null);
        current.next=node;
        previous=current;
        current=node;
    }

    void remove(){
        Link curr = head;
        int count = 0;
        while(curr.next!=null){
            prePrevious = previous;
            previous = curr;
            curr=curr.next;
            count++;
        }

        current = previous;
        current.next = null;
        previous = prePrevious;

        if(count>0){
            System.out.println("last link removed");
        }
        else{
            System.out.println("No links to remove");
        }

    }

    void iterate(){
        Link cur=head;
        while(cur!=null){
            if(cur!=head){
                System.out.print(" -> ");
            }
            System.out.print(cur.val);
            cur=cur.next;
        }
        System.out.println("");
    }

}
