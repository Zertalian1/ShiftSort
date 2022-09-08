public class IncorrectDataException extends Exception{

        public String getMassage() {
            return super.getMessage();
        }

        public IncorrectDataException(String message){
            super(message);
        }
}
