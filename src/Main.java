public class Main {
    public static void main(String[] args) {
        ParametersParser parser = new ParametersParser(args);
        if(parser.getDataType().equals("Integer")) {
            IntegerSorter sorter = new IntegerSorter(parser);
            sorter.start();
        }
        if(parser.getDataType().equals("String")){
            StringSorter sorter = new StringSorter(parser);
            sorter.start();
        }
    }

}
