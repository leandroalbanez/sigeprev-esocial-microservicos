package br.com.atlantic.comum.dao.daogenerico;

public interface CodigoTransacionalComRetorno<T> {
    public T execute(DataBaseTXInterface conn) throws Exception;
}
