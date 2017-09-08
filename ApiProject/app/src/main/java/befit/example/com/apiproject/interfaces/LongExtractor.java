package befit.example.com.apiproject.interfaces;

public interface LongExtractor<T> {

    public long getLongValue(T item, int position);
}