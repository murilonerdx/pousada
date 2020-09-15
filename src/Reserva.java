import java.time.LocalDate;

class Reserva {
    private int numero;
    private int numeroQuarto;
    private Quarto quarto;
    private int qtdePessoas;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public Reserva(int numeroQuarto, int qtdePessoas, LocalDate dataEntrada, LocalDate dataSaida) {
        this.qtdePessoas = qtdePessoas;
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }
    public Reserva(int numero, int numeroQuarto, int qtdePessoas, LocalDate dataEntrada, LocalDate dataSaida) {
        this.numero = numero;
        this.qtdePessoas = qtdePessoas;
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataEntrada() {
        return this.dataEntrada;
    }

    public LocalDate getDataSaida() {
        return this.dataSaida;
    }

    public int getQtdePessoas() {
        return this.qtdePessoas;
    }

    public Quarto getQuarto() {
        return this.quarto;
    }

    public int getNumeroQuarto() {
        return this.numeroQuarto;
    }

    public int getNumero() {
        return numero;
    }

}
