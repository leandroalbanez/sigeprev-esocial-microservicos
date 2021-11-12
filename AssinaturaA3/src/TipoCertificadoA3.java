

public enum TipoCertificadoA3 {

    Smartcard("Smartcard"),
    Safenet("Safenet"),
    Aladdin("Aladdin"),
    pronova("pronova");


    private final String codigo;

    private TipoCertificadoA3(final String codigo) {
        this.codigo = codigo;
    }


    public String getCodigo() {
        return this.codigo;
    }

    public static TipoCertificadoA3 valueOfCodigo(final String codigo) {
        for (TipoCertificadoA3 tipoCertificadoA3 : TipoCertificadoA3.values()) {
            if (tipoCertificadoA3.getCodigo().equalsIgnoreCase(codigo)) {
                return tipoCertificadoA3;
            }
        }
        return null;
    }


}