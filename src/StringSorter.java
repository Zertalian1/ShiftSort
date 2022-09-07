import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StringSorter implements Sorter<String>{
    ParametersParser parser;

    public StringSorter(ParametersParser parser){
        this.parser=parser;
    }
    @Override
    public boolean readElement(FileData<String> data) {
        try{
            String temp = readValue(data);
            if(temp == null){
                System.out.println("file " + data.getFileName() + " is ended");
                return true;
            }else{
                data.setData(temp);
            }
        } catch (IOException e) {
            System.err.println("Read data ERROR");
            return true;
        } catch (IncorrectDataException e) {
            System.err.println(e.getMassage());
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        List<FileData<String>> streams = new ArrayList<>();
        for (String inputFile : parser.getInputFilesNames()) {
            FileData<String> data = new FileData<>(inputFile);
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
