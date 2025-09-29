package service;

import static spark.Spark.*;
import dao.LivroDAO;
import model.Livro;
import java.util.List;
import java.util.ArrayList;

public class LivroService {

    private LivroDAO dao = new LivroDAO();

    public LivroService() {
        port(4567);
        staticFiles.location("/public");

        // Página inicial: listar livros + pesquisa + adicionar
        get("/", (req, res) -> {
            // Parâmetros de pesquisa
            String pid = req.queryParams("id");
            String ptitulo = req.queryParams("titulo");
            String pautor = req.queryParams("autor");
            String pstatus = req.queryParams("status");

            List<Livro> livros = dao.getAll();
            List<Livro> filtrados = new ArrayList<>();

            for (Livro l : livros) {
                boolean match = true;

                if (pid != null && !pid.isEmpty()) {
                    try {
                        if (l.getId() != Integer.parseInt(pid)) match = false;
                    } catch (NumberFormatException e) {
                        match = false;
                    }
                }

                if (ptitulo != null && !ptitulo.isEmpty() && !l.getTitulo().toLowerCase().contains(ptitulo.toLowerCase())) match = false;
                if (pautor != null && !pautor.isEmpty() && !l.getAutor().toLowerCase().contains(pautor.toLowerCase())) match = false;
                if (pstatus != null && !pstatus.isEmpty() && l.getStatus() != pstatus.charAt(0)) match = false;

                if (match) filtrados.add(l);
            }

            // Monta HTML
            StringBuilder sb = new StringBuilder("<h1>Biblioteca</h1>");

            // Formulário de pesquisa
            sb.append("<h2>Pesquisar Livros</h2>");
            sb.append("<form method='get' action='/'>");
            sb.append("ID: <input name='id' value='" + (pid != null ? pid : "") + "'> ");
            sb.append("Título: <input name='titulo' value='" + (ptitulo != null ? ptitulo : "") + "'> ");
            sb.append("Autor: <input name='autor' value='" + (pautor != null ? pautor : "") + "'> ");
            sb.append("Status: <input name='status' maxlength='1' value='" + (pstatus != null ? pstatus : "") + "'> ");
            sb.append("<button type='submit'>Pesquisar</button></form><br>");

            // Formulário para adicionar novo livro
            sb.append("<h2>Adicionar Novo Livro</h2>");
            sb.append("<form action='/insert' method='post'>");
            sb.append("ID: <input name='id'><br>");
            sb.append("Título: <input name='titulo'><br>");
            sb.append("Autor: <input name='autor'><br>");
            sb.append("Status: <input name='status' maxlength='1'><br>");
            sb.append("<button type='submit'>Adicionar</button></form><br>");

            // Tabela de livros
            sb.append("<h2>Lista de Livros</h2>");
            sb.append("<table border='1' cellpadding='5'><tr><th>ID</th><th>Título</th><th>Autor</th><th>Status</th><th>Ações</th></tr>");
            for (Livro l : filtrados) {
                sb.append("<tr>");
                sb.append("<td>").append(l.getId()).append("</td>");
                sb.append("<td>").append(l.getTitulo()).append("</td>");
                sb.append("<td>").append(l.getAutor()).append("</td>");
                sb.append("<td>").append(l.getStatus()).append("</td>");
                sb.append("<td>")
                  .append("<a href='/delete/").append(l.getId()).append("'>Remover</a>")
                  .append("</td>");
                sb.append("</tr>");
            }
            sb.append("</table>");
            return sb.toString();
        });

        // Inserir livro
        post("/insert", (req, res) -> {
            try {
                int id = Integer.parseInt(req.queryParams("id"));
                String titulo = req.queryParams("titulo");
                String autor = req.queryParams("autor");
                char status = req.queryParams("status").charAt(0);
                dao.insert(new Livro(id, titulo, autor, status));
            } catch (Exception e) {
                return "Erro ao adicionar livro: " + e.getMessage();
            }
            res.redirect("/");
            return null;
        });

        // Remover livro
        get("/delete/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params("id"));
                dao.delete(id);
            } catch (Exception e) {
                return "Erro ao remover livro: " + e.getMessage();
            }
            res.redirect("/");
            return null;
        });
    }

    public static void main(String[] args) {
        new LivroService();
    }
}
