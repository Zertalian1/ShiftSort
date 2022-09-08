public class Main {
    public static void main(String[] args) {
        ParametersParser parser = new ParametersParser(args);
        if(parser.getDataType() == ValuesDataType.INTEGER) {
            IntegerSorter sorter = new IntegerSorter(parser);
            sorter.start();
        }
        if(parser.getDataType() == ValuesDataType.STRING){
            StringSorter sorter = new StringSorter(parser);
            sorter.start();
        }
    }

}
