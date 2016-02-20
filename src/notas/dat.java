
package notas;

import java.util.Date;


public class dat{
    public Date fecha;
    public String concepto;
    public String monto;
     public dat(){}
    public dat(Date fecha, String concepto, String monto) {
        this.fecha=fecha;
        this.concepto=concepto;
        this.monto=monto;
    }
}
