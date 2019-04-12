package com.logic;

public class Cartella {

    private Integer[] numeri;

    public Cartella(Integer[] numeri) {
        this.numeri = numeri;
    }

    /**
     * Getter dei numeri della cartella
     * @return ritorna l'array contenente i numeri della cartella
     */
    public Integer[] getNumeri() {
        return numeri;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numeri.length; i++) {
            sb.append(numeri[i]+",");
        }

        sb.deleteCharAt(sb.toString().length()-1);

        return sb.toString();
    }
}
