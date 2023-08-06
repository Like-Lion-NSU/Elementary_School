package thisisus.school.security.common.converters;

public interface ProviderUserConverter<T,R> {
    R convert(T t);
}
