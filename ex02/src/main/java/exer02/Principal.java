package exer02;

import java.util.Scanner;

public class Principal {
	
	private static void pausar(Scanner sc, String mensagem) {
	    System.out.println("\nPressione ENTER para " + mensagem + "...");
	    sc.nextLine();
	}

    public static void main(String[] args) {
        DAO dao = new DAO();
        Scanner sc = new Scanner(System.in);
        dao.conectar();

        int opcao = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Listar livros");
            System.out.println("2 - Inserir livro");
            System.out.println("3 - Atualizar livro");
            System.out.println("4 - Excluir livro");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.println("=== LISTA DE LIVROS ===");
                    Livro[] livros = dao.getLivros();
                    if (livros != null && livros.length > 0) {
                        for (Livro livro : livros) {
                            System.out.println(livro.toString());
                        }
                    } else {
                        System.out.println("Nenhum livro encontrado.");
                    }
                    
                    pausar(sc, "voltar ao menu"); 
                    
                    break;

                case 2:
                    System.out.print("Digite o ID do livro: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o título do livro: ");
                    String titulo = sc.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = sc.nextLine();
                    System.out.print("Digite o status (D = disponível, E = emprestado): ");
                    char status = sc.nextLine().charAt(0);

                    Livro novoLivro = new Livro(id, titulo, autor, status);
                    if (dao.inserirLivro(novoLivro)) {
                        System.out.println("Livro inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir livro.");
                    }
                    
                    pausar(sc, "voltar ao menu"); 
                    
                    break;

                case 3:
                    System.out.print("Digite o ID do livro que deseja atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o novo título: ");
                    String novoTitulo = sc.nextLine();
                    System.out.print("Digite o novo autor: ");
                    String novoAutor = sc.nextLine();
                    System.out.print("Digite o novo status (D/E): ");
                    char novoStatus = sc.nextLine().charAt(0);

                    Livro livroAtualizado = new Livro(idAtualizar, novoTitulo, novoAutor, novoStatus);
                    if (dao.atualizarLivro(livroAtualizado)) {
                        System.out.println("Livro atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar livro.");
                    }
                    
                    pausar(sc, "voltar ao menu"); 
                    
                    break;

                case 4:
                    System.out.print("Digite o ID do livro que deseja excluir: ");
                    int idExcluir = sc.nextInt();
                    sc.nextLine();

                    if (dao.excluirLivro(idExcluir)) {
                        System.out.println("Livro excluído com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir livro.");
                    }
                    
                    pausar(sc, "voltar ao menu"); 
                    
                    break;

                case 5:
                	pausar(sc, "confirmar encerramento"); 
                	
                    System.out.println("Encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);

        dao.close();
        sc.close();
    }
}
