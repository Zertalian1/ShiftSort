public class FileData<T extends Comparable<T>> implements Comparable<FileData<T>>{
    private final String fileName;
    private T data;
    private T prevData;
    private long offset;

    public FileData(String  name){
        fileName = name;
    }
    @Override
    public int compareTo(FileData<T> input) {
        return data.compareTo(input.getData());
    }

    public int compareData(){
        if(prevData == null)
            return 0;
        return data.compareTo(prevData);
    }   // добавить зависимость от parser

    public String getFileName() {
        return fileName;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void addOffset(long of){
        offset+=of;
    }

    public T getData() {
        return data;
    }

    public void setPrevData(T prevData) {
        this.prevData = prevData;
    }

    public T getPrevData() {
        return prevData;
    }

    public long getOffset() {
        return offset;
    }
}
