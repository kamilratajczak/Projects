package befit.example.com.favouriteplaces.interfaces;

public interface LongExtractor<T> {

    public long getLongValue(T item, int position);
}