package br.com.atlantic.comum.dao.daogenerico;

public interface CodigoTransacional {
    public void execute(DataBaseTXInterface conn) throws Exception;
}
