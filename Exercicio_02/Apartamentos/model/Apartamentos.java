package model;

public class Apartamentos {
    private String tipo;
    private int numeroQuartos;
    private double area;
    private boolean garagem;

    public Apartamentos() {
        this.tipo = "";
        this.numeroQuartos = 0;
        this.area = 0.0;
        this.garagem = false;
    }

    public Apartamentos(String tipo, int numeroQuartos, double area, boolean garagem) {
        this.tipo = tipo;
        this.numeroQuartos = numeroQuartos;
        this.area = area;
        this.garagem = garagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isGaragem() {
        return garagem;
    }

    public void setGaragem(boolean garagem) {
        this.garagem = garagem;
    }

    @Override
    public String toString() {
        return "Apartamentos [tipo=" + tipo + ", numeroQuartos=" + numeroQuartos 
                + ", area=" + area + ", garagem=" + garagem + "]";
    }
}
