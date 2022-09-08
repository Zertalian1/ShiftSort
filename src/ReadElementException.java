public class ReadElementException extends Exception{

    public ReadElementException(String msg){
        super(msg);

    }

    public String getMassage() {
        return super.getMessage();
    }
}
