import java.util.LinkedList;
import java.util.List;

public class ListOfConatants {
    private List<PhoneNumberIL> list;
    public ListOfConatants(){
        list=new LinkedList<PhoneNumberIL>();
    }
    public void addPhoneNumberIL(PhoneNumberIL phoneNumberIL){
        list.add(phoneNumberIL);
    }

    @Override
    public String toString() {
        String temp = "";
        for (PhoneNumberIL contact: this.list) {
            temp=temp+"Name is :" + contact.getName()+" is Number Is: "+contact.getPhoneNumber() + " is sent : " +contact.isSent() +".\n";

        }
        return temp;
    }

    public int size(){
        return this.list.size();
    }
    public PhoneNumberIL getConants(int index){
        return this.list.get(index);
    }
    public void remove(PhoneNumberIL item){
        this.list.remove(item);
    }
}
