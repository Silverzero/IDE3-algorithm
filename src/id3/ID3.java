package id3;

import java.util.ArrayList;

import GUI.GUI;
import Parser.Parser;

import java.util.Iterator;

/**
 *
 * @author
 */
public class ID3 {

    private ArrayList<Atributo> atributos;
    private ArrayList<String[]> juego;
    private Atributo solucion;

    private Parser parser;

    public ID3() {
        parser = new Parser();
        initAtributos();
        initJuego();
        ejecutaID3();
        System.out.println(this.solucion.getNombre());

    }

    public ArrayList<Atributo> getAtributos() {
		return atributos;
	}

    /**
     * Introduce en el ArrayList atributos los atributos del fichero. (Tiempo
     * Exterior, Humedad,...)
     */
    public void initAtributos() {
        String[] aux = parser.lecturaAtributos();
        atributos = new ArrayList<>();
        for (int i = 0; i < aux.length; i++) {
            this.atributos.add(new Atributo(aux[i]));
        }
    }

    public void initJuego() {
        String[] aux;
        this.juego = new ArrayList<>();
        while (parser.hayValores()) {
            aux = parser.lecturaValores();
            for (int i = 0; i < aux.length - 1; i++) {
                if (!this.atributos.get(i).contieneNombre(aux[i])) {
                    this.atributos.get(i).anyadeNombre(aux[i]);
                }
            } // Fin for
            this.juego.add(aux);
        } // Fin while
    }

    public void ejecutaID3() {
    	 ArrayList<Atributo> a = (ArrayList<Atributo>) this.atributos.clone();
         ArrayList<String[]> j = (ArrayList<String[]>) this.juego.clone();
        this.solucion = ejecutaID3(a, j);
    }

    /**
     * Funcion recursiva que devuelve el atributo con mejor merito.
     *
     * @param atributosAux
     * @param juegoAux
     * @return
     */
    private Atributo ejecutaID3(ArrayList<Atributo> a, ArrayList<String[]> j) {
        ArrayList<Atributo> auxA = a;
        ArrayList<String[]> auxJ = j;

        int auxIdx = a.indexOf(calculaMejorMerito(a, j));     
        
        
        Atributo auxSol = a.get(auxIdx);
        Valor auxVal;
        String[] auxS = new String[j.size()];
        
        auxA.remove(auxSol);
           
        Iterator iter = auxSol.getValores().iterator();
        Iterator iter2 = j.iterator();
        
        while (iter.hasNext()) {
            auxVal = (Valor) iter.next();
            auxJ = new ArrayList<>();
                        
            while (iter2.hasNext()) {
                auxS = (String[])iter2.next();
                                
                if (auxS[auxIdx].equals(auxVal.getNombre())) {
                    auxJ.add(anyadeLineaDeJuego(auxS, auxIdx));                        
                }
                
            }

            if (auxS.length > 0){
            	auxVal.setAt(ejecutaID3(auxA, auxJ));
            }
            
        }
        return auxSol;
    }

    private Atributo calculaMejorMerito(ArrayList<Atributo> a, ArrayList<String[]> j) {
        String[] auxJ;
        Atributo aux, auxSol = null;
        double auxMerito = Double.MAX_VALUE;
        Iterator it = j.iterator();

        while (it.hasNext()) {
            auxJ = (String[]) it.next();

            for (int i = 0; i < auxJ.length - 1; i++) {
                if (a.get(i).contieneNombre(auxJ[i])) {
                    if (auxJ[auxJ.length - 1].equalsIgnoreCase("si")) { // positivo

                        int posit = a.get(i).getValor(auxJ[i]).getPositivos();
                        posit++;
                        a.get(i).getValor(auxJ[i]).setPositivos(posit);

                    } else { // negativo
                        int negat = a.get(i).getValor(auxJ[i]).getNegativos();
                        negat++;
                        a.get(i).getValor(auxJ[i]).setNegativos(negat);
                    }
                    // contador++ a
                    a.get(i).getValor(auxJ[i]).A_plus();
                }
            }
        }

        Iterator ita = a.iterator();
        boolean isNan = false;
        
        while (ita.hasNext() && !isNan) {
            aux = (Atributo) ita.next();

            if(Double.isNaN(aux.merito())){                
                auxSol = aux;
                isNan = true;
            }                
                    
            else if (aux.merito() < auxMerito) {   
                auxSol = aux; 
                auxMerito = aux.merito();                        
            }            
        }        
        return auxSol;
    }

    private String[] anyadeLineaDeJuego(String[] s, int idx) {
        String[] aux;
        int j = 0;
        int size = s.length;
        String local;
        aux = new String[size-1];
        
        
        for (int i = 0; i < s.length; i++) {
            if (i != idx) {
                local = s[i];
                aux[j] = local;
                j++;
            }
        }        
        return aux;
    }

    /**
     * se recorre el arrayList y se decide si es positivo o no (que a�n no se
     * puede pero se podr�) accedemos a un atributo, escogemos el valor, si no
     * es final, seguimos con el arbol, hasta que es final, y se devuelve
     * positivo o negativo.
     *
     * @param a - Un ArrayList de atributos de los nuevos valores introducidos a
     * trav�s de la interfaz.
     * @return
     */
    public boolean esPosible(String[] s) {
    	Atributo at = this.solucion;
    	Valor v = at.getValor(s[this.atributos.indexOf(at)]);
    	
    	while(!v.esFinal()){
            try{
    		at = at.getValor(s[this.atributos.indexOf(at)]).getAt();
                if(at.getNombre().equalsIgnoreCase("jugar")){
                    return true;
                }
    		v = at.getValor(s[this.atributos.indexOf(at)]);
            }catch(Exception e){
                return false;
            }
    	}
    	
    	if(v.esPositivo()){
    		return true;
    	}else{
    		return false;
    	}
    }

}
