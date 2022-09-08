import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StringSorter implements Sorter<String>{
    ParametersParser parser;

    public StringSorter(ParametersParser parser){
        this.parser=parser;
    }
    @Override
    public void readElement(FileData<String> data) throws ReadElementException{
        try{
            String temp = readValue(data);
            if(temp == null){
                throw new ReadElementException("file " + data.getFileName() + " is ended");
            }else{
                data.setData(temp);
            }
        } catch (IOException e) {
            throw new ReadElementException("Read data ERROR");
        } catch (IncorrectDataException e) {
            throw new ReadElementException(e.getMassage());
        }
        if((parser.isReverse() && data.compareData()>0) || (!parser.isReverse() && data.compareData()<0)){
            throw  new ReadElementException("The sorting order in the file is broken");
        }
    }

    @Override
    public void start() {
        List<FileData<String>> streams = new ArrayList<>();
        for (String inputFile : parser.getInputFilesNames()) {
            FileData<String> data = new FileData<>(inputFile);
            try {
                readElement(data);
                streams.add(data);
            } catch (ReadElementException e) {
                System.out.println(e.getMassage());
            }
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(parser.getOutputFileName()))){
            while (streams.size()>0) {
                sort(streams, parser);
                out.write(streams.get(0).getData() + "\n");
                try {
                    readElement(streams.get(0));
                } catch (ReadElementException e) {
                    System.out.println(e.getMassage());
                    streams.remove(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
