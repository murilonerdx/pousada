import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Principal {

	public static Pousada pousada = new Pousada();

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("#########################################################");
		System.out.println(
				"1 - Realizar uma reserva\n2 - Consultar reservas\n3 - Consultar Quartos\n4 - Valor final da hospedagem\n5 - Sair");
		System.out.println("#########################################################");
		int menu = scan.nextInt();
		ArrayList<Reserva> minhaReservas = new ArrayList<Reserva>();
		while (menu != 5) {
			switch (menu) {
				case 1:
					System.out.println("Preencha os dados da reserva: ");
					System.out.println("Numero do quarto: ");
					int numQuarto = scan.nextInt();
					int numPessoas;
					String dataEntrada;
					String dataSaida;
					Quarto quarto = pousada.obterQuartoPorNumero(numQuarto);
					if (quarto == null) {
						System.out.println("Numero do quarto invalido");
					} else {
						System.out.println("Digite a quantidade de pessoas: ");
						numPessoas = scan.nextInt();
						if (numPessoas > quarto.getMaxPessoas()) {
							System.out.println("A quantidade de pessoas é maior que a capacidade do quarto");
						} else {
							System.out.println("Digite a data de entrada: ");
							dataEntrada = scan.next();
							System.out.println("Digite a data de saida: ");
							dataSaida = scan.next();
							Boolean quartoDisponivelNoPeriodo = pousada.quartoDisponivel(numQuarto, dataEntrada,
									dataSaida);
							if (quartoDisponivelNoPeriodo == false) {
								System.out.println("Já existe uma reserva para este quarto neste periodo");
							} else {
								LocalDate dataEntradaDate = LocalDate.parse(dataEntrada,
										DateTimeFormatter.ofPattern("dd/MM/yyyy"));
								LocalDate dataSaidaDate = LocalDate.parse(dataSaida,
										DateTimeFormatter.ofPattern("dd/MM/yyyy"));
								Reserva reserva = new Reserva(numQuarto, numPessoas, dataEntradaDate, dataSaidaDate);
								String validacao = pousada.adicionar(reserva);
								if (validacao == "") {
									System.out.println("Reserva realizada com sucesso");
									minhaReservas.add(reserva);
								} else
									System.out.println(validacao);
							}
						}
					}
					break;
				case 2:
					System.out.println("###############################");
					System.out.println("RESERVAS CADASTRADAS NO SISTEMA");
					ArrayList<Reserva> reservasBD = pousada.consulta();
					int count = 0;
					while (count < reservasBD.size()) {
						Reserva r = reservasBD.get(count);
						System.out.println("N. Quarto: " + r.getNumeroQuarto() + ", Qtde. de Pessoas: "
								+ r.getQtdePessoas() + ", Data Entrada: "
								+ r.getDataEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
								+ ", Data Saida: "
								+ r.getDataSaida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

						count++;
					}
					break;
				case 3:
					System.out.println("#########################################################");
					System.out.println("QUARTOS");
					ArrayList<Quarto> quartosBD = pousada.consultaQuartos();
					int countQuartos = 0;
					while (countQuartos < quartosBD.size()) {
						Quarto q = quartosBD.get(countQuartos);

						//1º - Forma de obter a categoria pegando direto da tabela onde adicionamos a coluna Categoria e obtendo a descrição do enum
						//2º - Processando o numero de identificação do quarto para obter a categoria de acordo com a regra de negocio 
						
						//1º
						//String categoria = q.getCategoria();

						String categoria = "";

						//2º
						if(q.getNumero() > 0 && q.getNumero() <= 20)
							categoria = CategoriaEnum.APARTAMENTO.name();
						else if(q.getNumero() > 20)
							categoria = CategoriaEnum.VIP.name();
						
						System.out.println("Numero: " + q.getNumero() + ", Categoria: " + categoria
								+ ", Capacidade de Pessoas: " + q.getMaxPessoas() + ", Valor da Diária: "
								+ String.valueOf(q.getValorDiaria()));
						countQuartos++;
					}
					break;
				case 4:
					double total = 0;
					for (int i = 0; i < minhaReservas.size(); i++) {
						Reserva re = minhaReservas.get(i);
						Quarto q = pousada.obterQuartoPorNumero(re.getNumeroQuarto());
						Period period = Period.between(re.getDataEntrada(), re.getDataSaida());
						int totalDias = period.getDays() + (period.getMonths() * 30) + (period.getYears() * 365);
						double totalPorDias = totalDias * q.getValorDiaria();
						total += totalPorDias;
						System.out.println("N. Quarto: " + re.getNumeroQuarto() + ", Qtde. Dias: " + totalDias
								+ ", Valor Diária: " + q.getValorDiaria() + ", Valor Total: " + totalPorDias);
					}
					
					System.out.println("Valor Total das Reservas: " + total);
					break;
				case 5:
					return;
				default:
					System.out.println("Opção inválida");
			}
			System.out.println("#########################################################");
			System.out.println(
					"1 - Realizar uma reserva\n2 - Consultar reservas\n3 - Consultar Quartos\n4 - Valor final da hospedagem\n5 - Sair");
			System.out.println("#########################################################");
			menu = scan.nextInt();
		}
	}

}