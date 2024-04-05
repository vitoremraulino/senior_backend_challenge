package vitor.projeto.Model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "hospedes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hospede {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    private String nome;
    private int documento;
    private String telefone;

    @Override
    public String toString() {
        return "Hospede{" +
                "uuid=" + uuid +
                ", nome='" + nome + '\'' +
                ", documento=" + documento +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
