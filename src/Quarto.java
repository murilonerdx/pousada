class Quarto{
    private int numero;
    private CategoriaEnum categoria;
    private int maxPessoas;
    private double valorDiaria;

    // Reserva reserva = new Reserva();
    public Quarto(){

    }

    public Quarto(int numero, CategoriaEnum categoria, int maxPessoas, double valorDiaria){
       this.categoria = categoria;
       this.maxPessoas = maxPessoas;
       this.numero = numero;
       this.valorDiaria = valorDiaria;
    }

    public String getCategoria() {
        return categoria.getDescricao();
    }
    
    public int getMaxPessoas() {
        return maxPessoas;
    }

    public int getNumero() {
        return numero;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public void setMaxPessoas(int maxPessoas) {
        this.maxPessoas = maxPessoas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}

