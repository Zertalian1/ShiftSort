import java.io.*;
import java.util.ArrayList;;
import java.util.List;

public class IntegerSorter implements Sorter<Integer>{
    ParametersParser parser;

    public IntegerSorter(ParametersParser parser){
        this.parser=parser;
    }
    @Override
    public boolean readElement(FileData<Integer> data) {
        try {
            String temp = readValue(data);
            if(temp == null){
                System.out.println("file " + data.getFileName() + " is ended");
                return true;
            }else{
                data.setData(Integer.valueOf(temp));
            }
        } catch (IOException e) {
            System.err.println("Read data ERROR");
            return true;
        } catch (IncorrectDataException e) {
            System.err.println(e.getMassage());
            return true;
        } catch (NumberFormatException e){
            System.out.println("incorrect data format in "+ data.getFileName());
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        List<FileData<Integer>> streams = new ArrayList<>();
        for (String inputFile : parser.getInputFilesNames()) {
            FileData<Integer> data = new FileData<>(inputFile);
            if(!readElement(data)) {
                streams.add(data);
            }
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(parser.getOutputFileName()))){
            while (streams.size()>0) {
                sort(streams, parser);
                out.write(streams.get(0).getData() + "\n");
                if(readElement(streams.get(0))){
                    streams.remove(0);
                }
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
