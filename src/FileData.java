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

    /**
     * сравнивает старое значение data с новым
     * меньше 0, если новое меньше старого
     * больше 0, если новое больше старого
     * 0, иначе
     * @param parser
     * @return
     */
    public int compareData(ParametersParser parser){
        return data.compareTo(prevData);
    }
    public String getFileName() {
        return fileName;
    }
    public void setData(T data) {
        this.data = data;
    }
    public void setPrevData(T prevData) {
        this.prevData = prevData;
    }
    public T getPrevData() {
        return prevData;
    }
    public void addOffset(long of){
        offset+=of;
    }
    public T getData() {
        return data;
    }
    public long getOffset() {
        return offset;
    }
}
