public class Main {
    public static void main(String[] args) {
        ParametersParser parser = new ParametersParser(args);
        if(parser.isInteger()) {
            IntegerSorter sorter = new IntegerSorter(parser);
            sorter.start();
        }else{
            StringSorter sorter = new StringSorter(parser);
            sorter.start();
        }
    }

}
