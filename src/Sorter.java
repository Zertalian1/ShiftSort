import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public interface Sorter <T extends Comparable<T>>{
    boolean readElement(FileData<T> data);
    void start();

    default void sort(List<FileData<T>> streams, ParametersParser parser) throws IOException {
        Collections.sort(streams);
        if (parser.isReverse()) {
            Collections.reverse(streams);
        }
    }

    default String readValue(FileData<T> data) throws IOException, IncorrectDataException {
        BufferedReader in = new BufferedReader(new FileReader(data.getFileName()));
        skip(in, data.getOffset());
        data.setPrevData(data.getData());
        String input = in.readLine();
        in.close();
        if(input == null)
            return null;
        if(input.contains(" ")){
            throw  new IncorrectDataException("File " + data.getFileName() + " contains space");
        }
        data.addOffset(input.length());
        return input;
    }
    default void skip(BufferedReader in, long offset) throws IOException {
        for(int i=0;i<offset;i++){
            in.readLine();
        }
    }
}
