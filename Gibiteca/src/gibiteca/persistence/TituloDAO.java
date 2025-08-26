package gibiteca.persistence;

import gibiteca.model.Titulo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO baseado em JSON salvo em ./data/titulos.json
 */
public class TituloDAO {

    private final Path path;

    public TituloDAO() {
        String projectDir = System.getProperty("user.dir");
        Path dataDir = Paths.get(projectDir, "data");
        this.path = dataDir.resolve("titulos.json");
        try {
            if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
            if (!Files.exists(path)) Files.writeString(path, "[]", StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Não foi possível preparar o arquivo de dados: " + e.getMessage());
        }
    }

    public List<Titulo> carregar() {
        List<Titulo> titulos = new ArrayList<>();
        try {
            String json = Files.readString(path, StandardCharsets.UTF_8).trim();
            if (json.isEmpty()) json = "[]";
            if (json.startsWith("[")) {
                int i = 0, n = json.length();
                while (i < n) {
                    int objStart = json.indexOf('{', i);
                    if (objStart < 0) break;
                    int brace = 1;
                    int j = objStart + 1;
                    while (j < n && brace > 0) {
                        char c = json.charAt(j);
                        if (c == '{') brace++;
                        else if (c == '}') brace--;
                        j++;
                    }
                    if (brace == 0) {
                        String obj = json.substring(objStart, j);
                        Titulo t = fromJson(obj);
                        if (t != null) titulos.add(t);
                        i = j;
                    } else break;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar títulos: " + e.getMessage());
        }
        return titulos;
    }

    public void salvar(List<Titulo> titulos) {
        long max = 0;
        for (Titulo t : titulos) if (t.getId() > max) max = t.getId();
        for (Titulo t : titulos) {
            if (t.getId() == 0) {
                max += 1;
                t.setId(max);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < titulos.size(); i++) {
            if (i > 0) sb.append(',');
            sb.append(toJson(titulos.get(i)));
        }
        sb.append(']');
        try {
            Files.writeString(path, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Erro ao salvar títulos: " + e.getMessage());
        }
    }

    // ---------- Helpers JSON ----------

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    private static String toJson(Titulo t) {
        return "{"
                + "\"id\":" + t.getId()
                + ",\"nome\":\"" + esc(t.getNome()) + "\""
                + ",\"editora\":\"" + esc(t.getEditora()) + "\""
                + ",\"autor\":\"" + esc(t.getAutor()) + "\""
                + "}";
    }

    private static String unesc(String s) {
        StringBuilder out = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < s.length()) {
                char n = s.charAt(i + 1);
                switch (n) {
                    case 'n': out.append('\n'); break;
                    case 'r': out.append('\r'); break;
                    case 't': out.append('\t'); break;
                    case '\\': out.append('\\'); break;
                    case '"': out.append('\"'); break;
                    default: out.append(n); break;
                }
                i++;
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    private static String getJsonString(String obj, String key) {
        String pat = "\"" + key + "\":";
        int idx = obj.indexOf(pat);
        if (idx < 0) return null;
        idx += pat.length();
        while (idx < obj.length() && Character.isWhitespace(obj.charAt(idx))) idx++;
        if (idx >= obj.length()) return null;

        if (obj.charAt(idx) == '\"') {
            // valor string
            int j = idx + 1;
            StringBuilder val = new StringBuilder();
            while (j < obj.length()) {
                char c = obj.charAt(j);
                if (c == '\\') {
                    if (j + 1 < obj.length()) {
                        val.append(c);
                        j++;
                        val.append(obj.charAt(j));
                        j++;
                        continue;
                    }
                } else if (c == '\"') {
                    break;
                }
                val.append(c);
                j++;
            }
            return unesc(val.toString());
        } else {
            // valor numérico simples
            int j = idx;
            while (j < obj.length() && Character.isDigit(obj.charAt(j))) j++;
            return obj.substring(idx, j);
        }
    }

    private static Titulo fromJson(String obj) {
        try {
            String sid = getJsonString(obj, "id");
            String nome = getJsonString(obj, "nome");
            String editora = getJsonString(obj, "editora");
            String autor = getJsonString(obj, "autor");
            long id = 0;
            if (sid != null && !sid.isBlank()) id = Long.parseLong(sid);
            return new Titulo(id, nome, editora, autor);
        } catch (Exception e) {
            return null;
        }
    }
}

