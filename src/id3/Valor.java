package id3;

/**
 *
 * @author 
 */
class Valor {

    private String nombre;
    private int positivos;
    private int negativos;
    private int cant;
    private Atributo at;

    public Valor() {
        this.positivos = 0;
        this.negativos = 0;
        this.cant = 0;
        this.nombre = "";
        this.at = null;
    }

	public Valor(String nombre) {
        this.positivos = 0;
        this.negativos = 0;
        this.cant = 0;
        this.nombre = nombre;
        this.at = null;
    }

    public double infor() { 
        double var1 = calcularP();
        double var2 = calcularN();
        double resultado = -var1 * log2(var1) - var2 * log2(var2);
        return resultado;
    }

    private double log2(double d) {
        return Math.log(d) / Math.log(2);
    }

    /**
     * 
     * @param N Cantidad de valores.
     * @return 
     */
    private double calcularP() {        
        double pos = this.positivos;
        double as = this.cant;        
        return pos/as;
    }
    
    /**
     * 
     * @param N Cantidad de valores.
     * @return 
     */
    private double calcularN() {
        double neg = this.negativos;
        double as = this.cant;
        return neg/as;
    }

    public double calcularR(int N) {        
        return this.cant/N;
    }
    
    public boolean esPositivo(){
    	return this.cant==this.positivos;
    }
    
    public boolean esNegativo(){
    	return this.cant==this.negativos;
    }
    
    public boolean esFinal(){
    	return esPositivo() || esNegativo();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the positivos
     */
    public int getPositivos() {
        return positivos;
    }

    /**
     * @param positivos the positivos to set
     */
    public void setPositivos(int positivos) {
        this.positivos = positivos;
    }

    /**
     * @return the negativos
     */
    public int getNegativos() {
        return negativos;
    }

    /**
     * @param negativos the negativos to set
     */
    public void setNegativos(int negativos) {
        this.negativos = negativos;
    }

    /**
     * @return the a
     */
    public int getA() {
        return this.cant;
    }

    /**
     * @param a the a to set
     */
    public void setA(int a) {
        this.cant = a;
    }
    
    public void A_plus(){
        this.cant++;
    }
    

    public Atributo getAt() {
		return at;
	}

	public void setAt(Atributo at) {
		this.at = at;
	}
}
