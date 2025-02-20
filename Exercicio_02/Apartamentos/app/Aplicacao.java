package app;

import java.util.List;
import java.util.Scanner;

import dao.ApartamentosDAO;
import model.Apartamento;

public class Aplicacao {
    
    public static void main(String[] args) throws Exception {
        ApartamentosDAO apartamentosDAO = new ApartamentosDAO();
        apartamentosDAO.conectar();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        
        while (opcao != 5) {
            System.out.println("\n==== MENU DE OPÇÕES ====");
            System.out.println("1- Inserir Apartamento");
            System.out.println("2- Listar Apartamentos");
            System.out.println("3- Atualizar Apartamento");
            System.out.println("4- Excluir Apartamento");
            System.out.println("5- Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 1) { 
                System.out.println("\n==== Inserir apartamento ===");
                System.out.print("Digite a localização: ");
                String localizacao = scanner.nextLine();
                System.out.print("Digite o número de quartos: ");
                int quartos = scanner.nextInt();
                System.out.print("Digite o valor do aluguel: ");
                double aluguel = scanner.nextDouble();
                
                Apartamento novoApartamento = new Apartamento(localizacao, quartos, aluguel);
                if (apartamentosDAO.insert(novoApartamento)) {
                    System.out.println("Apartamento inserido com sucesso: " + novoApartamento.toString());
                } else {
                    System.out.println("Erro ao inserir apartamento.");
                }
            } 
            else if (opcao == 2) { 
                System.out.println("\n==== Listar apartamentos ===");
                List<Apartamento> apartamentos = apartamentosDAO.getAll();
                for (Apartamento a : apartamentos) {
                    System.out.println(a.toString());
                }
            } 
            else if (opcao == 3) { 
                System.out.println("\n==== Atualizar apartamento ===");
                System.out.print("Digite a localização do apartamento a ser atualizado: ");
                String localizacaoAtualizar = scanner.nextLine();
                Apartamento apartamento = apartamentosDAO.getByLocalizacao(localizacaoAtualizar);

                if (apartamento != null) {
                    System.out.print("Nova localização: ");
                    apartamento.setLocalizacao(scanner.nextLine());
                    System.out.print("Novo número de quartos: ");
                    apartamento.setQuartos(scanner.nextInt());
                    System.out.print("Novo valor do aluguel: ");
                    apartamento.setAluguel(scanner.nextDouble());

                    if (apartamentosDAO.update(apartamento)) {
                        System.out.println("Apartamento atualizado com sucesso: " + apartamento.toString());
                    } else {
                        System.out.println("Erro ao atualizar o apartamento.");
                    }
                } else {
                    System.out.println("Apartamento não encontrado.");
                }
            } 
            else if (opcao == 4) { 
                System.out.println("\n==== Excluir apartamento ===");
                System.out.print("Digite a localização do apartamento a ser excluído: ");
                String localizacaoExcluir = scanner.nextLine();

                if (apartamentosDAO.delete(localizacaoExcluir)) {
                    System.out.println("Apartamento excluído com sucesso.");
                } else {
                    System.out.println("Erro ao excluir o apartamento.");
                }
            }
            else if (opcao == 5) { 
                System.out.println("Saindo...");
            } 
            else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close(); 
    }
}
