public class PhoneNumberIL {
    private String phoneNumber;
    private String urlToSend;
    private String name;
    private String message;
    private boolean sent;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private boolean canToSend;
    public final String DEFAULT_URL_TO_SEND = "https://web.whatsapp.com/send?phone=972";
    public final int LENGTH_OF_CELLULAR_NUMBER_WITH_PREFIX = 12;

    public PhoneNumberIL(String phoneNumber,String name) {
        if (legallNumber(phoneNumber)){
            this.phoneNumber=phoneNumber;
            this.urlToSend=DEFAULT_URL_TO_SEND+this.phoneNumber.substring(this.phoneNumber.indexOf('5'));
            this.name=name;
            this.sent=false;




        }else {
            throw new RuntimeException("The number is incorrect. Please try again");
        }

    }

    private boolean legallNumber(String phoneNumber) {
        phoneNumber = "972" + phoneNumber.substring(phoneNumber.indexOf('5'));
        if (phoneNumber.length() < LENGTH_OF_CELLULAR_NUMBER_WITH_PREFIX || phoneNumber.length() > LENGTH_OF_CELLULAR_NUMBER_WITH_PREFIX) {
            return false;

        } else {


            return true;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getUrlToSend() {
        return urlToSend;
    }

    public boolean isCanToSend() {
        return canToSend;
    }

    public void setCanToSend(boolean canToSend) {
        this.canToSend = canToSend;
    }
}
