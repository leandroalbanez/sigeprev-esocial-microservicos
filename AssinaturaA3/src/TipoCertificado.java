

/**
 * Created by Waldney on 26/03/2015.
 */
public enum TipoCertificado {

    A1("A1"),
    A3("A3");

    private final String codigo;

    private TipoCertificado(final String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public static TipoCertificado valueOfCodigo(final String codigo) {
        for (TipoCertificado tipoCertificado : TipoCertificado.values()) {
            if (tipoCertificado.getCodigo().equalsIgnoreCase(codigo)) {
                return tipoCertificado;
            }
        }
        return null;
    }


}