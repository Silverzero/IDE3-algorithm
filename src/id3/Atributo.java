package id3;

import java.util.ArrayList;

public class Atributo {

    private String nombre;
    private ArrayList<Valor> valores;

    public Atributo(String nombre) {
        this.nombre = nombre;
        this.valores = new ArrayList<>();

    }

    public double merito() {        
        double merito = 0;        
        double b = 0;
        for(int i = 0; i < valores.size(); i++){
            merito = merito + this.valores.get(i).getA()*this.valores.get(i).infor();
            b = this.valores.get(i).infor();
        }
        
        /*
         *intentamos convertir el valor NaN a algo numerico para poder procesarlo
         * la funcion isNaN deber�a estar auto-implementada, mirar si vosotros la teneis
        if(isNaN(merito)){
        	merito = Double.MIN_VALUE;
        }
        */
        
        return merito;
    }

    public boolean contieneNombre(String nombre) {
        for (int i = 0; i < valores.size(); i++) {
            if (valores.get(i).getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void anyadeNombre(String nombre) {
        this.valores.add(new Valor(nombre));
    }

    /**
     * @return the valores
     */
    public ArrayList<Valor> getValores() {
        return valores;
    }

    /**
     * @param valores the valores to set
     */
    public void setValores(ArrayList<Valor> valores) {
        this.valores = valores;
    }

    /**
     * Damos por hecho que el valor a buscar está en la lista. Simplemente lo
     * devolvemos.
     *
     * @param nombre
     * @return el objeto valor con el nombre dado.
     */
    public Valor getValor(String nombre) {
        Valor valor;
        boolean encontrado = false;
        int i = 0;

        while (!encontrado) {
            if (valores.get(i).getNombre().equals(nombre)) {
                encontrado = true;
            } else {
                i++;
            }
        }
        return valores.get(i);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    public ArrayList<String> getNombres(){
        ArrayList<String> nombres = new ArrayList<>();
        for(int i = 0; i < valores.size(); i++){
            nombres.add(valores.get(i).getNombre());
        }
        
        return nombres;
    }

}
